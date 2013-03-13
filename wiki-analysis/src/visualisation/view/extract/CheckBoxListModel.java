package visualisation.view.extract;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;

import de.uni_koblenz.jgralab.Vertex;

/**
 * 
 * @author dmosen@uni-koblenz.de
 *
 */
@SuppressWarnings("serial")
public class CheckBoxListModel extends DefaultListModel<CheckBoxListItem> {

	private CheckBoxListItem[] listItems;

	public CheckBoxListModel(LinkedList<Vertex> list) {
		listItems = new CheckBoxListItem[list.size()];

		int i = 0;
		for (Vertex v : list) {
			listItems[i] = new CheckBoxListItem(v);
			i++;
		}
	}

	@Override
	public CheckBoxListItem get(int index) {
		return getElementAt(index);
	}
	
	@Override
	public CheckBoxListItem getElementAt(int index) {
		return listItems[index];
	}

	@Override
	public int getSize() {
		return listItems.length;
	}
	
	public void setSelected(CheckBoxListItem item, int index, boolean value) {
		item.setSelected(value);
		fireContentsChanged(this, index, index);
	}

	public List<Vertex> getSelectedItems() {
		ArrayList<Vertex> result = new ArrayList<Vertex>();
		for (int i = 0; i < getSize(); i++) {
			CheckBoxListItem item = getElementAt(i);
			if (!item.isSelected()) {
				result.add(item.getVertex());
			}
		}
		return result;
	}
	
	@Override
	public void clear() {
		int lastIndex = listItems.length;
		listItems = new CheckBoxListItem[0];
		fireIntervalRemoved(this, 0, lastIndex);
	}
}

@SuppressWarnings("serial")
class CheckBoxListItem extends JCheckBox {
	
	private Vertex vertex;

	public CheckBoxListItem(Vertex v) {
		super(v.getAttribute("title").toString(), true);
		vertex = v;
	}

	public Vertex getVertex() {
		return vertex;
	}

	public String getName() {
		return vertex.getAttribute("title");
	}

}