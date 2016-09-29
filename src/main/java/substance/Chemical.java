package substance;

/**
 * Created by jason on 2016/09/29.
 */
public class Chemical {

	private int groupId;
	private String name = "";
	private String smiles = "";
	private String raw = "";
	private Property volume = null;
	private int id = -1;

	public Chemical(int id, String name, Property property) {
		this.id = id;
		this.name = name;
		this.volume = property;
	}

	public Chemical(String name, Property property) {
		this.name = name;
		this.volume = property;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return String.format("%s has properties: %s", this.name, this.volume.toString());
	}

}
