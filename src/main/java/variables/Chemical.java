package variables;


import java.util.HashMap;
import java.util.Map;

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

	public Chemical(int id, String name, Property property) {
		this.id = id;
		this.name = name;
		this.volume = property;
	}

	public Chemical(String name, Property property) {
		this.name = name;
		this.volume = property;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public Property getProperty() {
		return this.volume;
	}

	public Map<String, Variable> getVariables() {
		Map<String, Variable> vars = new HashMap<String, Variable>();
		vars.put(this.name, this);
		return vars;
	}

	public String getSmiles() {
		return this.smiles;
	}

	public Property getVariableProperty() {
		return this.volume;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Chemical:").append("\n");
		sb.append("Name: ").append(this.name).append("\n");
		sb.append("Volume: ").append(this.volume.toString());

		return sb.toString();
	}
}
