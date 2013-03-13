package visualisation.view.extract;

import com.jidesoft.swing.Selectable;

import de.uni_koblenz.jgralab.Vertex;

/**
 * This class wraps a {@link Vertex} which must contain the attribute
 * <code>title</code> of type {@link String}. Therefore it is not very type safe.
 * 
 * @author dmosen
 * 
 */
class CheckBoxListItem implements Selectable {

	private Vertex vertex;
	private boolean selected;
	private boolean enabled;

	protected CheckBoxListItem(Vertex vertex) {
		this.vertex = vertex;
		selected = false;
		enabled = true;
	}

	@Override
	public void invertSelected() {
		selected = !selected;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return vertex.getAttribute("title");
	}

	public Vertex getVertex() {
		return vertex;
	}
}
