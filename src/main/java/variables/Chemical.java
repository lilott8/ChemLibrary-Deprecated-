package variables;


import common.Property;

/**
 * Created by jason on 2016/08/15.
 */
public class Chemical implements Variable {

	private int groupId;
	private String name = "";
	private String smiles = "";
	private String raw = "";
	private Property volume = null;
	private int id = -1;

	public Chemical(String name, Property property) {
		this.name = name;
		this.volume = property;
	}

	public String getName() {
		return this.name;
	}

	public Property getProperty() {
		return this.volume;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Chemical:").append("\n");
		sb.append("Name: ").append(this.name).append("\n");
		sb.append("Volume: ").append(this.volume.toString());

		return sb.toString();
	}

	public int getId() {
		return id;
	}
}
