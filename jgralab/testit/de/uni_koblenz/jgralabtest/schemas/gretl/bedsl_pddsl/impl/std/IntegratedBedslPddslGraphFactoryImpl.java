/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std;

import de.uni_koblenz.jgralab.impl.GraphFactoryImpl;

import de.uni_koblenz.jgralab.ImplementationType;

import de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.IntegratedBedslPddslGraphFactory;

public class IntegratedBedslPddslGraphFactoryImpl extends GraphFactoryImpl implements IntegratedBedslPddslGraphFactory {

	public IntegratedBedslPddslGraphFactoryImpl() {
		super(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.IntegratedBedslPddslSchema.instance(), ImplementationType.STANDARD);
		createMaps();
		setGraphImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.IntegratedBedslPddslGraph.GC, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.IntegratedBedslPddslGraphImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity.VC, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.EntityImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.ReferenceAttribute.VC, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.ReferenceAttributeImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.SimpleAttribute.VC, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.SimpleAttributeImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Slot.VC, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.SlotImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Chassis.VC, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.ChassisImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Card.VC, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.CardImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Configuration.VC, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.ConfigurationImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.References.EC, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.ReferencesImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasCard.EC, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.HasCardImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute.EC, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.HasAttributeImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSlot.EC, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.HasSlotImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype.EC, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.HasSupertypeImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasConfig.EC, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.HasConfigImpl.class);
	}
}
