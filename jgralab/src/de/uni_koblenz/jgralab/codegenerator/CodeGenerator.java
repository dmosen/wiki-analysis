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

package de.uni_koblenz.jgralab.codegenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.regex.Matcher;

import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.schema.impl.compilation.InMemoryJavaSourceFile;

/**
 * TODO add comment
 * 
 * @author ist@uni-koblenz.de
 * 
 */
public abstract class CodeGenerator {

	/**
	 * 
	 * @author ist@uni-koblenz.de
	 * 
	 */
	protected enum GenerationCycle {
		// FIXME The order here matters! CLASSONLY must be last!
		ABSTRACT, STDIMPL, DBIMPL, TRANSIMPL, CLASSONLY;

		protected static List<GenerationCycle> filter(
				CodeGeneratorConfiguration config) {
			List<GenerationCycle> out = new ArrayList<GenerationCycle>();
			out.add(ABSTRACT);
			if (config.hasStandardSupport()) {
				out.add(STDIMPL);
			}
			if (config.hasTransactionSupport()) {
				out.add(TRANSIMPL);
			}
			if (config.hasDatabaseSupport()) {
				out.add(DBIMPL);
			}
			out.add(CLASSONLY);
			return out;
		}

		/**
		 * 
		 * @return
		 */
		protected boolean isStdImpl() {
			return this == STDIMPL;
		}


		/**
		 * 
		 * @return Returns true if support for database impl classes is enabled,
		 *         otherwise false.
		 */
		protected boolean isDbImpl() {
			return this == DBIMPL;
		}

		/**
		 * 
		 * @return
		 */
		protected boolean isTransImpl() {
			return this == TRANSIMPL;
		}

		/**
		 * 
		 * @return
		 */
		protected boolean isAbstract() {
			return this == ABSTRACT;
		}

		/**
		 * 
		 * @return
		 */
		protected boolean isClassOnly() {
			return this == CLASSONLY;
		}

		/**
		 * 
		 * @return
		 */
		protected boolean isStdOrDbImplOrTransImpl() {
			return (this == STDIMPL) || (this == TRANSIMPL) || (this == DBIMPL);
		}
	}

	private final List<GenerationCycle> cycles;

	private int cycleCount = 0;

	private static Logger logger = Logger.getLogger(CodeGenerator.class
			.getName());

	protected CodeList rootBlock;

	private final ImportCodeSnippet imports;

	protected String schemaRootPackageName;

	protected CodeGeneratorConfiguration config;

	protected GenerationCycle currentCycle;

	/**
	 * Creates a {@link CodeGenerator} for a single class.
	 * 
	 * @param schemaRootPackageName
	 *            the name of the root package of the schema, for instance
	 *            de.uni_koblenz.jgralab.greql2
	 * @param packageName
	 *            the name of the package the class is located in, for instance
	 *            comprehensions Out of the three parameters, the CodeGenerator
	 *            calculates the name
	 *            schemaRootPackageName.packageName.implementationName, in the
	 *            example
	 *            "de.uni_koblenz.jgralab.greql2.comprehension.Listcomprehension"
	 *            for the interface and possibly
	 *            schemaRootPackageName.impl.packageName.implementationName, in
	 *            the example
	 *            "de.uni_koblenz.jgralab.greql2.impl.comprehension.Listcomprehension"
	 *            for the default implementation class
	 * @param config
	 *            The {@link CodeGeneratorConfiguration} to be used when
	 *            generating code.
	 */
	public CodeGenerator(String schemaRootPackageName, String packageName,
			CodeGeneratorConfiguration config) {
		this.schemaRootPackageName = schemaRootPackageName;
		this.config = config;

		rootBlock = new CodeList(null);
		rootBlock.setVariable("jgPackage", "de.uni_koblenz.jgralab");
		rootBlock.setVariable("jgTransPackage", "de.uni_koblenz.jgralab.trans");
		rootBlock.setVariable("jgImplPackage", "de.uni_koblenz.jgralab.impl");
		rootBlock.setVariable("jgImplStdPackage",
				"de.uni_koblenz.jgralab.impl.std");
		rootBlock.setVariable("jgImplTransPackage",
				"de.uni_koblenz.jgralab.impl.trans");
		rootBlock.setVariable("jgImplDbPackage",
				"de.uni_koblenz.jgralab.impl.db");
		rootBlock.setVariable("jgSchemaPackage",
				"de.uni_koblenz.jgralab.schema");
		rootBlock.setVariable("jgSchemaImplPackage",
				"de.uni_koblenz.jgralab.schema.impl");

		if ((packageName != null) && !packageName.equals("")) {
			rootBlock.setVariable("schemaPackage", schemaRootPackageName + "."
					+ packageName);
			// schema implementation packages (standard, db and for
			// transaction)
			rootBlock.setVariable("schemaImplStdPackage", schemaRootPackageName
					+ ".impl.std." + packageName);
			rootBlock.setVariable("schemaImplTransPackage",
					schemaRootPackageName + ".impl.trans." + packageName);
			rootBlock.setVariable("schemaImplDbPackage", schemaRootPackageName
					+ ".impl.db." + packageName);
		} else {
			rootBlock.setVariable("schemaPackage", schemaRootPackageName);
			rootBlock.setVariable("schemaImplStdPackage", schemaRootPackageName
					+ ".impl.std");
			rootBlock.setVariable("schemaImplTransPackage",
					schemaRootPackageName + ".impl.trans");
			rootBlock.setVariable("schemaImplDbPackage", schemaRootPackageName
					+ ".impl.db");
		}
		rootBlock.setVariable("isClassOnly", "false");
		rootBlock.setVariable("isImplementationClassOnly", "false");
		rootBlock.setVariable("isAbstractClass", "false");

		imports = new ImportCodeSnippet();
		cycles = GenerationCycle.filter(config);
	}

	protected abstract CodeBlock createHeader();

	protected abstract CodeBlock createBody();

	protected CodeBlock createFooter() {
		return new CodeSnippet("}");
	}

	public static CodeBlock createDisclaimer() {
		return new CodeSnippet("/*",
				" * This code was generated automatically.",
				" * Do NOT edit this file, changes will be lost.",
				" * Instead, change and commit the underlying schema.", " */");
	}

	/**
	 * writes the source code to location path+fileName,
	 * 
	 * @param pathPrefix
	 *            the path where the java source code is to be
	 * @param fileName
	 *            the filename of the java source code including .java
	 * @param aPackage
	 * @throws GraphIOException
	 */
	public void writeCodeToFile(String pathPrefix, String fileName,
			String aPackage) throws GraphIOException {
		aPackage = aPackage.replace(".", File.separator);

		File dir = new File(pathPrefix + aPackage);

		if (dir.exists()) {
			if (!dir.isDirectory()) {
				throw new GraphIOException("'" + dir.getAbsolutePath()
						+ "' exists but is not a directory");
			}
		} else {
			if (!dir.mkdirs()) {
				throw new GraphIOException(
						"Couldn't create directory hierachy for '" + dir + "'.");
			}
		}

		File outputFile = null;
		try {
			outputFile = new File(dir.getAbsolutePath() + File.separator
					+ fileName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
			bw.write(rootBlock.getCode());
			bw.close();
		} catch (IOException e) {
			throw new GraphIOException("Unable to create file "
					+ outputFile.getAbsolutePath(), e);
		}
	}

	public void createFiles(String pathPrefix) throws GraphIOException {
		// String className = rootBlock.getVariable("className");
		String simpleClassName = rootBlock.getVariable("simpleClassName");
		String schemaPackage = rootBlock.getVariable("schemaPackage");
		String simpleImplClassName = rootBlock
				.getVariable("simpleImplClassName");
		String schemaImplPackage = "";

		logger.finer("createFiles(\"" + pathPrefix + "\")");
		logger.finer(" - simpleClassName=" + simpleClassName);
		logger.finer(" - schemaPackage=" + schemaPackage);
		logger.finer(" - simpleImplClassName=" + simpleImplClassName);

		currentCycle = getNextCycle();
		while (currentCycle != null) {
			createCode();
			if (currentCycle.isAbstract()) {
				logger
						.finer("Creating interface for class: "
								+ simpleClassName);
				logger.finer("Writing file to: " + pathPrefix + "/"
						+ schemaPackage);
			}
			if (currentCycle.isStdOrDbImplOrTransImpl()) {
				if (currentCycle.isStdImpl()) {
					schemaImplPackage = rootBlock
							.getVariable("schemaImplStdPackage");
					logger
							.finer(" - schemaImplStdPackage="
									+ schemaImplPackage);
				}
				if (currentCycle.isTransImpl()) {
					schemaImplPackage = rootBlock
							.getVariable("schemaImplTransPackage");
					logger.finer(" - schemaImplTransPackage="
							+ schemaImplPackage);
				}
				if (currentCycle.isDbImpl()) {
					schemaImplPackage = rootBlock
							.getVariable("schemaImplDbPackage");
					logger.finer(" - schemaImplDbPackage=" + schemaImplPackage);
				}
				writeCodeToFile(pathPrefix, simpleImplClassName + ".java",
						schemaImplPackage);
			} else {
				writeCodeToFile(pathPrefix, simpleClassName + ".java",
						schemaPackage);
			}
			currentCycle = getNextCycle();
		}
	}

	/**
	 * creates the generated code string for a class
	 */
	public void createCode() {
		imports.clear();
		rootBlock.clear();
		rootBlock.addNoIndent(createDisclaimer());
		rootBlock.addNoIndent(createPackageDeclaration());
		CodeBlock header = createHeader();
		CodeBlock body = createBody();
		CodeBlock footer = createFooter();
		rootBlock.addNoIndent(imports);
		rootBlock.addNoIndent(header);
		rootBlock.addNoIndent(body);
		rootBlock.addNoIndent(footer);
	}

	protected CodeBlock createPackageDeclaration() {
		CodeSnippet code = new CodeSnippet(true);

		if (rootBlock.getVariable("isClassOnly").equals("true")) {
			code.add("package #schemaPackage#;");
		} else {
			switch (currentCycle) {
			case ABSTRACT:
				code.add("package #schemaPackage#;");
				break;
			case STDIMPL:
				code.add("package #schemaImplStdPackage#;");
				break;
			case TRANSIMPL:
				code.add("package #schemaImplTransPackage#;");
				break;
			case DBIMPL:
				code.add("package #schemaImplDbPackage#;");
				break;
			case CLASSONLY:
				code.add("package #schemaPackage#;");
				break;
			}
		}
		return code;
	}

	protected void addImports(String... importPackages) {
		imports.add(importPackages);
	}

	/**
	 * Transforms the given String into a CamelCase String
	 */
	public static String camelCase(String aString) {
		if (aString.length() < 1) {
			return aString;
		}
		if (aString.length() < 2) {
			return aString.toUpperCase();
		}
		return aString.substring(0, 1).toUpperCase() + aString.substring(1);
	}

	/**
	 * @param aString
	 *            some String
	 * @return the string with " quoted as \"
	 */
	public static String stringQuote(String aString) {
		return aString.replaceAll("\"", Matcher.quoteReplacement("\\\""));
	}

	/**
	 * Returns {@code JavaSourceFromString}s from the generated code.
	 * 
	 * @return a Vector of {@code JavaSourceFromString}s from the generated code
	 */
	public Vector<InMemoryJavaSourceFile> createJavaSources() {
		String className = rootBlock.getVariable("simpleClassName");
		String implClassName = rootBlock.getVariable("simpleImplClassName");
		Vector<InMemoryJavaSourceFile> javaSources = new Vector<InMemoryJavaSourceFile>(
				2);

		currentCycle = getNextCycle();
		while (currentCycle != null) {
			createCode();
			if (currentCycle.isStdOrDbImplOrTransImpl()) {
				javaSources.add(new InMemoryJavaSourceFile(implClassName,
						rootBlock.getCode()));
			} else {
				javaSources.add(new InMemoryJavaSourceFile(className, rootBlock
						.getCode()));
			}
			currentCycle = getNextCycle();
		}
		return javaSources;
	}

	/**
	 * FIXME loop over cycles instead of counter variable and direct access.
	 * This takes a certain order of ENUM values into account! See other fixme!
	 * 
	 * @return The next matching {@link GenerationCycle}.
	 */
	private GenerationCycle getNextCycle() {
		// end of generation cycle
		if (cycleCount >= cycles.size()) {
			// cycleCount = 0;
			return null;
		}

		GenerationCycle currentCycle = cycles.get(cycleCount);

		// currentCycle = cycles[cycleCount];

		// abstract classes should only have generation cycle ABSTRACT
		if (rootBlock.getVariable("isAbstractClass").equals("true")
				&& !currentCycle.isAbstract()) {
			cycleCount = 0;
			return null;
		}

		// if it is an implementation class only no abstract classes and
		// interfaces
		// should be generated
		if (rootBlock.getVariable("isImplementationClassOnly").equals("true")
				&& currentCycle.isAbstract()) {
			cycleCount++;
			return getNextCycle();
		}

		// if class only (schema, factory, enum), then only generation cycle
		// CLASSONLY is valid
		if (rootBlock.getVariable("isClassOnly").equals("true")) {
			cycleCount = GenerationCycle.CLASSONLY.ordinal() + 1;
			return GenerationCycle.CLASSONLY;
		}

		// if not class only, then skip generation cycle CLASSONLY
		if (rootBlock.getVariable("isClassOnly").equals("false")
				&& currentCycle.isClassOnly()) {
			cycleCount = 0;
			return null;
		}

		cycleCount++;
		return currentCycle;
	}
}
