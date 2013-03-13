package utils;

/**
 * 
 * @author dmosen@uni-koblenz.de
 *
 */
public enum Cmtype {
	DEFAULT(""), SUBCAT("subcat"), PAGE("page"), FILE("file");

	public final String id;

	Cmtype(String id) {
		this.id = id;
	}
}
