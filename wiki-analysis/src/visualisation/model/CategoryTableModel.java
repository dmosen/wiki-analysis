package visualisation.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import schemas.categoryschema.CategoryGraph;
import schemas.categoryschema.Subcategory;
import utils.WikipediaAnalysis;
import de.uni_koblenz.jgralab.algolib.algorithms.AlgorithmTerminatedException;

@SuppressWarnings("serial")
public class CategoryTableModel extends AbstractTableModel {

	private static String[] headings = new String[] { "Path", "Title",
			"Blacklisting", "Comment" };
	private ArrayList<CategoryTreeNode> nodes = new ArrayList<CategoryTreeNode>();
	private boolean blacklistingChanged = false;

	public CategoryTableModel(CategoryGraph graph) {
		try {
			List<Subcategory> edges = WikipediaAnalysis
					.getBlacklistedOrCommentedEdges(graph);
			for (Subcategory e : edges) {
				nodes.add(CategoryTreeModel.edgeToNodeMap.get(e));
			}
		} catch (AlgorithmTerminatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getRowCount() {
		return nodes.size();
	}

	@Override
	public int getColumnCount() {
		return headings.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return nodes.get(rowIndex).getPath();

		case 1:
			return nodes.get(rowIndex).getTitle();

		case 2:
			return nodes.get(rowIndex).isBlacklisted();

		case 3:
			return nodes.get(rowIndex).getComment();
		}
		return "";
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
		if (columnIndex >= 2 && !(Boolean) nodes.get(rowIndex).isBackwardArc()) {
			return true;
		}
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 2:
			blacklistingChanged = true;
			nodes.get(rowIndex).setBlacklisting((Boolean) aValue);
			fireTableCellUpdated(rowIndex, columnIndex);
			break;

		case 3:
			nodes.get(rowIndex).setComment((String) aValue);
			fireTableCellUpdated(rowIndex, columnIndex);
			break;
		}
	}

	public boolean isBlacklistingChanged() {
		return blacklistingChanged;
	}
}
