package graph;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.schema.EdgeClass;
import de.uni_koblenz.jgralab.schema.Schema;
import de.uni_koblenz.jgralab.schema.VertexClass;

/**
 * 
 * @author dmosen@uni-koblenz.de
 *
 */
public class GraphProperties {

	private static final String SCHEMA_FILE = "category-graph-schema.tg";
	public final Schema schema;

	public final VertexClass categoryNodeVC;
	public final VertexClass pageNodeVC;
	public final EdgeClass subcategoryLinkEC;
	public final EdgeClass containsPageLinkEC;

	private static GraphProperties instance = null;

	public static GraphProperties getInstance() {
		if (instance == null) {
			instance = new GraphProperties();
		}
		return instance;
	}

	public GraphProperties() {
		Schema schema = null;

		try {
			schema = GraphIO.loadSchemaFromFile(SCHEMA_FILE);
		} catch (GraphIOException e) {
			e.printStackTrace();
			System.err.println("Error on processing schema file " + SCHEMA_FILE
					+ ".");
			System.exit(1);
		}

		this.schema = schema;

		categoryNodeVC = schema.getGraphClass().getVertexClass("Category");
		pageNodeVC = schema.getGraphClass().getVertexClass("Page");
		subcategoryLinkEC = schema.getGraphClass().getEdgeClass("Subcategory");
		containsPageLinkEC = schema.getGraphClass().getEdgeClass("ContainsPage");
	}

}
