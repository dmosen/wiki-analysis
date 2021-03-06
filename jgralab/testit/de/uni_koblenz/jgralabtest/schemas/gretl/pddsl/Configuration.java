/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.pddsl;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface Configuration extends de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Element {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.PddslSchema.instance().vc_Configuration;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Configuration vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Configuration getNextConfiguration();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Element vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Element getNextElement();

	/**
	 * @return the first edge of class HasSlot at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot getFirstHasSlotIncidence();

	/**
	 * @return the first edge of class HasSlot at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot getFirstHasSlotIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class HasConfig at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig getFirstHasConfigIncidence();

	/**
	 * @return the first edge of class HasConfig at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig getFirstHasConfigIncidence(EdgeDirection orientation);
		/**
		 * adds the given vertex as <code>slot</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot add_slot(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Slot vertex);
		/**
		 * removes all slot adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Slot> remove_slot();
		/**
		 * removes the given vertex as <code>slot</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_slot(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Slot vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'slot'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Slot> get_slot();

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasSlot or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot> getHasSlotIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasSlot.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot> getHasSlotIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasConfig or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig> getHasConfigIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasConfig.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig> getHasConfigIncidences(EdgeDirection direction);
}
