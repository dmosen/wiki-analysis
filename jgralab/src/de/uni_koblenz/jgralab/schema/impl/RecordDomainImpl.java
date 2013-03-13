/*
 * JGraLab - The Java Graph Laboratory
 *
 * Copyright (C) 2006-2011 Institute for Software Technology
 *                         University of Koblenz-Landau, Germany
 *                         ist@uni-koblenz.de
 *
 * For bug reports, documentation and further information, visit
 *
 *                         http://jgralab.uni-koblenz.de
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see <http://www.gnu.org/licenses>.
 *
 * Additional permission under GNU GPL version 3 section 7
 *
 * If you modify this Program, or any covered work, by linking or combining
 * it with Eclipse (or a modified version of that program or an Eclipse
 * plugin), containing parts covered by the terms of the Eclipse Public
 * License (EPL), the licensors of this Program grant you additional
 * permission to convey the resulting work.  Corresponding Source for a
 * non-source form of such a combination shall include the source code for
 * the parts of JGraLab used as well as that of the covered work.
 */

package de.uni_koblenz.jgralab.schema.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.Record;
import de.uni_koblenz.jgralab.codegenerator.CodeBlock;
import de.uni_koblenz.jgralab.codegenerator.CodeGenerator;
import de.uni_koblenz.jgralab.codegenerator.CodeSnippet;
import de.uni_koblenz.jgralab.schema.CompositeDomain;
import de.uni_koblenz.jgralab.schema.Domain;
import de.uni_koblenz.jgralab.schema.Package;
import de.uni_koblenz.jgralab.schema.RecordDomain;
import de.uni_koblenz.jgralab.schema.exception.DuplicateRecordComponentException;
import de.uni_koblenz.jgralab.schema.exception.InvalidNameException;
import de.uni_koblenz.jgralab.schema.exception.NoSuchRecordComponentException;
import de.uni_koblenz.jgralab.schema.exception.RecordCycleException;
import de.uni_koblenz.jgralab.schema.exception.SchemaClassAccessException;
import de.uni_koblenz.jgralab.schema.exception.SchemaException;
import de.uni_koblenz.jgralab.schema.exception.WrongSchemaException;
import de.uni_koblenz.jgralab.schema.impl.compilation.SchemaClassManager;

public final class RecordDomainImpl extends CompositeDomainImpl implements
		RecordDomain {

	/**
	 * The class object representing the generated interface for this
	 * RecordDomain
	 */
	private Class<? extends Object> schemaClass;

	/**
	 * holds a list of the components of the record
	 */
	private final Map<String, RecordComponent> components = new TreeMap<String, RecordComponent>();

	/**
	 * @param qn
	 *            the unique name of the record in the schema
	 * @param components
	 *            a list of the components of the record
	 */
	RecordDomainImpl(String sn, Package pkg,
			Collection<RecordComponent> components) {
		super(sn, pkg);
		if (components != null) {
			for (RecordComponent c : components) {
				addComponent(c.getName(), c.getDomain());
			}
		}
	}

	@Override
	public void addComponent(String name, Domain domain) {
		if(((SchemaImpl)getSchema()).isFinished()){
			throw new SchemaException("No changes to finished schema!");
		}
		
		if (name.isEmpty()) {
			throw new InvalidNameException(
					"Cannot create a record component with an empty name.");
		}
		if (components.containsKey(name)) {
			throw new DuplicateRecordComponentException(name,
					getQualifiedName());
		}
		if (parentPackage.getSchema().getDomain(domain.getQualifiedName()) != domain) {
			throw new WrongSchemaException(domain.getQualifiedName()
					+ " must be a domain of the schema "
					+ parentPackage.getSchema().getQualifiedName());
		}
		if (!staysAcyclicAfterAdding(domain)) {
			throw new RecordCycleException(
					"The creation of a component, which has the type " + domain
							+ ", would create a cycle of RecordDomains.");
		}
		RecordComponent c = new RecordComponent(name, domain);
		components.put(name, c);
		((SchemaImpl)parentPackage.getSchema()).getDomainsDag().createEdge(domain,this);
	}

	@Override
	public Set<Domain> getAllComponentDomains() {
		Set<Domain> domains = new HashSet<Domain>();
		for (RecordComponent c : components.values()) {
			domains.add(c.getDomain());
		}
		return domains;
	}

	@Override
	public Collection<RecordComponent> getComponents() {
		return components.values();
	}

	@Override
	public Domain getDomainOfComponent(String name) {
		if (!components.containsKey(name)) {
			throw new NoSuchRecordComponentException(getQualifiedName(), name);
		}
		return components.get(name).getDomain();
	}

	@Override
	public String getJavaAttributeImplementationTypeName(
			String schemaRootPackagePrefix) {
		return schemaRootPackagePrefix + "." + getQualifiedName();
	}

	@Override
	public String getJavaClassName(String schemaRootPackagePrefix) {
		return getJavaAttributeImplementationTypeName(schemaRootPackagePrefix);
		// return getJavaAttributeTypeName(schemaRootPackagePrefix);
	}

	@Override
	public Class<? extends Object> getSchemaClass() {
		if (schemaClass == null) {
			String schemaClassName = getSchema().getPackagePrefix() + "."
					+ getQualifiedName();
			try {
				schemaClass = Class.forName(schemaClassName, true,
						SchemaClassManager.instance(getSchema()
								.getQualifiedName()));
			} catch (ClassNotFoundException e) {
				throw new SchemaClassAccessException(
						"Can't load (generated) schema class for RecordDomain '"
								+ getQualifiedName() + "'", e);
			}
		}
		return schemaClass;
	}

	@Override
	public CodeBlock getReadMethod(String schemaPrefix, String variableName,
			String graphIoVariableName) {
		CodeSnippet code = new CodeSnippet();
		code.setVariable("name", variableName);
		code.setVariable("init", "");
		internalGetReadMethod(code, schemaPrefix, variableName,
				graphIoVariableName);

		return code;
	}

	@Override
	public String getTGTypeName(Package pkg) {
		return getQualifiedName(pkg);
	}

	@Override
	public CodeBlock getWriteMethod(String schemaRootPackagePrefix,
			String variableName, String graphIoVariableName) {
		CodeSnippet code = new CodeSnippet();
		code.setVariable("name", variableName);
		internalGetWriteMethod(code, schemaRootPackagePrefix, variableName,
				graphIoVariableName);

		return code;
	}

	/**
	 * @param d
	 *            the component domain which should be checked
	 * @return <code>true</code> if the addition of <code>d</code> wouldn't
	 *         create an inclusion cycle, <code>false</code> otherwise
	 */
	private boolean staysAcyclicAfterAdding(Domain d) {
		if (d == this) {
			return false;
		}
		if (!(d instanceof CompositeDomain)) {
			return true;
		}
		CompositeDomain c = (CompositeDomain) d;
		for (CompositeDomain comp : c.getAllComponentCompositeDomains()) {
			if (!staysAcyclicAfterAdding(comp)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder("Record " + getQualifiedName());
		String delim = " (";
		for (RecordComponent component : components.values()) {
			output.append(delim);
			output.append(component.getName());
			output.append('=');
			output.append(component.getDomain());
			delim = ", ";
		}
		output.append(")");
		return output.toString();
	}

	private void internalGetReadMethod(CodeSnippet code, String schemaPrefix,
			String variableName, String graphIoVariableName) {
		code.add("#init#");
		code.add("if (" + graphIoVariableName + ".isNextToken(\"(\")) {");
		code.add("\t" + "#name# = new " + getSchema().getPackagePrefix() + "."
				+ getQualifiedName() + "(io);");
		code.add("} else if (" + graphIoVariableName
				+ ".isNextToken(GraphIO.NULL_LITERAL)) {");
		code.add("\t" + graphIoVariableName + ".match();");
		code.add("\t" + variableName + " = null;");
		code.add("} else {");
		code.add("\tthrow new GraphIOException(\"This is no record!\");");
		code.add("}");
	}

	private void internalGetWriteMethod(CodeSnippet code,
			String schemaRootPackagePrefix, String variableName,
			String graphIoVariableName) {
		code.add("if (#name# != null) {");
		code.add("\t" + "#name#.writeComponentValues(" + graphIoVariableName
				+ ");");
		code.add("} else {");
		code.add("\t" + graphIoVariableName
				+ ".writeIdentifier(GraphIO.NULL_LITERAL);");
		code.add("}");
	}

	@Override
	public CodeBlock getTransactionReadMethod(String schemaPrefix,
			String variableName, String graphIoVariableName) {
		CodeSnippet code = new CodeSnippet();
		code.setVariable("name", variableName);
		code.setVariable("init",
				getJavaAttributeImplementationTypeName(schemaPrefix)
						+ " #name# = null;");
		internalGetReadMethod(code, schemaPrefix, variableName,
				graphIoVariableName);
		return code;
	}

	@Override
	public CodeBlock getTransactionWriteMethod(String schemaRootPackagePrefix,
			String variableName, String graphIoVariableName) {
		CodeSnippet code = new CodeSnippet();
		code.setVariable("name", "get" + CodeGenerator.camelCase(variableName)
				+ "()");
		internalGetWriteMethod(code, schemaRootPackagePrefix, variableName,
				graphIoVariableName);
		return code;
	}

	@Override
	public String getTransactionJavaAttributeImplementationTypeName(
			String schemaRootPackagePrefix) {
		return getJavaAttributeImplementationTypeName(schemaRootPackagePrefix);
	}

	@Override
	public String getTransactionJavaClassName(String schemaRootPackagePrefix) {
		return getJavaAttributeImplementationTypeName(schemaRootPackagePrefix);
	}

	@Override
	public String getVersionedClass(String schemaRootPackagePrefix) {
		return "de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<"
				+ getTransactionJavaAttributeImplementationTypeName(schemaRootPackagePrefix)
				+ ">";
	}

	@Override
	public String getInitialValue() {
		return "null";
	}

	@Override
	public Boolean hasComponent(String name) {
		return components.containsKey(name);
	}

	@Override
	public Object parseGenericAttribute(GraphIO io) throws GraphIOException {
		if (io.isNextToken("(")) {
			de.uni_koblenz.jgralab.impl.RecordImpl result = de.uni_koblenz.jgralab.impl.RecordImpl
					.empty();
			io.match("(");

			// Component values are expected in lexicographic order ->
			// RecordDomainImpl uses a TreeMap for Components and provides
			// the collection provided by getComponents() is backed by it.
			// Iteration will be done in the order of Map's keys
			// (Component-names)
			Iterator<RecordDomain.RecordComponent> componentIterator = getComponents()
					.iterator();
			RecordComponent component = componentIterator.next();
			while (!io.isNextToken(")")) {
				Object componentValue = null;
				componentValue = component.getDomain()
						.parseGenericAttribute(io);
				result = result.plus(component.getName(), componentValue);
				component = componentIterator.hasNext() ? componentIterator
						.next() : null;
			}
			assert (!componentIterator.hasNext());
			io.match(")");
			return result;
		} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
			io.match();
			return null;
		} else {
			throw new GraphIOException("This is no record!");
		}
	}

	@Override
	public void serializeGenericAttribute(GraphIO io, Object data)
			throws IOException {
		if (data != null) {
			io.writeSpace();
			io.write("(");
			io.noSpace();

			// RecordDomainImpl uses a TreeMap to store its components =>
			// Collection of components is backed by the TreeMap and
			// components are
			// iterated in the order of their keys
			for (RecordComponent rc : getComponents()) {
				rc.getDomain().serializeGenericAttribute(io,
						((Record) data).getComponent(rc.getName()));
			}
			io.write(")");
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
	}

	@Override
	public boolean isConformGenericValue(Object value) {
		boolean result = true;
		if (value == null) {
			return result;
		}
		result &= value instanceof Record;
		if (!result) {
			return false;
		}
		// RecordDomainImpl uses a TreeMap for storing the components.
		// The iterator is backed by the TreeMap and iterates over its
		// elements in the order of the TreeMap's keys.
		Iterator<RecordComponent> iterator = getComponents().iterator();
		while (iterator.hasNext() && result) {
			RecordComponent component = iterator.next();
			result &= component.getDomain().isConformGenericValue(
					((Record) value).getComponent(component.getName()));
		}
		assert (!iterator.hasNext());
		return result;
	}
}
