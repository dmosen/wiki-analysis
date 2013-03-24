package visualisation.model;

import graph.GraphProperties;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import utils.WikipediaAnalysis;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.algolib.algorithms.AlgorithmTerminatedException;

@SuppressWarnings("serial")
public class CategoryTableModel extends AbstractTableModel {

	private GraphProperties gp;

	private static String[] headings = new String[] { "Path", "Title",
			"Blacklisting", "Comment" };
	private ArrayList<Edge> edges = new ArrayList<Edge>();

	public CategoryTableModel(Graph graph) {
		gp = GraphProperties.getInstance();
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
			return getTitle(edges.get(rowIndex));

		case 2:
			return getBlacklisting(edges.get(rowIndex));

		case 3:
			return getComment(edges.get(rowIndex));
		}
		return "";
	}

	private Object getPath(Edge edge) {
		LinkedList<String> path = new LinkedList<String>();
		path.addFirst((String) edge.getAlpha().getAttribute("title"));

		Edge current = edge;

		do {
			for (Edge e : current.getAlpha().incidences(gp.subcategoryLinkEC,
					EdgeDirection.IN)) {
				if (!(Boolean) e.getAttribute("backwardArc")) {
					current = e;
					path.addFirst((String) current.getAlpha().getAttribute(
							"title"));
					break;
				}
			}
		} while (calculateIndegree(current.getAlpha()) > 0);

		return path.toString();
	}

	private int calculateIndegree(Vertex v) {
		int indegree = 0;

		for (Edge e : v.incidences(gp.subcategoryLinkEC, EdgeDirection.IN)) {
			if (!(Boolean) e.getAttribute("backwardArc")) {
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
			edges.get(rowIndex).setAttribute("blacklisted", aValue);
			fireTableCellUpdated(rowIndex, columnIndex);
			break;

		case 3:
			edges.get(rowIndex).setAttribute("comment", aValue);
			fireTableCellUpdated(rowIndex, columnIndex);
			break;
		}
	}

	private Object getTitle(Edge edge) {
		return edge.getOmega().getAttribute("title");
	}

	private Object getComment(Edge edge) {
		return edge.getAttribute("comment");
	}

	private Object getBlacklisting(Edge edge) {
		return edge.getAttribute("blacklisted");
	}

}
