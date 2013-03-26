package visualisation.model;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import schemas.categoryschema.Category;
import schemas.categoryschema.CategoryGraph;
import schemas.categoryschema.Subcategory;
import utils.WikipediaAnalysis;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.algolib.algorithms.AlgorithmTerminatedException;

@SuppressWarnings("serial")
public class CategoryTableModel extends AbstractTableModel {

	private static String[] headings = new String[] { "Path", "Title",
			"Blacklisting", "Comment" };
	private ArrayList<Subcategory> edges = new ArrayList<Subcategory>();

	public CategoryTableModel(CategoryGraph graph) {
		try {
			edges = WikipediaAnalysis.getBlacklistedOrCommentedEdges(graph);
		} catch (AlgorithmTerminatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getRowCount() {
		return edges.size();
	}

	@Override
	public int getColumnCount() {
		return headings.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return getPath(edges.get(rowIndex));

		case 1:
			return edges.get(rowIndex).getOmega().get_title();

		case 2:
			return edges.get(rowIndex).is_blacklisted();

		case 3:
			return edges.get(rowIndex).get_comment();
		}
		return "";
	}

	private Object getPath(Subcategory edge) {
		LinkedList<String> path = new LinkedList<String>();
		path.addFirst((String) edge.getAlpha().get_title());

		Subcategory current = edge;

		do {
			for (Subcategory e : current.getAlpha().getSubcategoryIncidences(
					EdgeDirection.IN)) {
				if (!e.is_backwardArc()) {
					current = e;
					path.addFirst((String) current.getAlpha().get_title());
					break;
				}
			}
		} while (calculateIndegree(current.getAlpha()) > 0);

		return path.toString();
	}

	private int calculateIndegree(Category v) {
		int indegree = 0;

		for (Subcategory e : v.getSubcategoryIncidences(EdgeDirection.IN)) {
			if (!e.is_backwardArc()) {
				indegree++;
			}
		}
		return indegree;
	}

	@Override
	public String getColumnName(int column) {
		return headings[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;

		case 1:
			return String.class;

		case 2:
			return Boolean.class;

		case 3:
			return String.class;
		}
		return super.getColumnClass(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex >= 2
				&& !(Boolean) edges.get(rowIndex).getAttribute("backwardArc")) {
			return true;
		}
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 2:
			edges.get(rowIndex).set_blacklisted((Boolean) aValue);
			fireTableCellUpdated(rowIndex, columnIndex);
			break;

		case 3:
			edges.get(rowIndex).set_comment((String) aValue);
			fireTableCellUpdated(rowIndex, columnIndex);
			break;
		}
	}
}
