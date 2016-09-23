package variables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/08/25.
 */
public class Compound implements Variable {

	private String name = "";
	private int id = -1;
	private Property volume = null;
	private Map<String, Variable> chemicals = new HashMap<String, Variable>();

	public Compound(String name, Property property) {
		this.name = name;
		this.volume = property;
	}

	public Compound(int id, String name, Property property) {
		this.id = id;
		this.name = name;
		this.volume = property;
	}

	public String getName() {
		return this.name;
	}

	public Property getProperty() {
		return this.volume;
	}

	public void addChemical(Variable v) {
		this.chemicals.put(v.getName(), v);
	}

	public void addChemicals(Map<String, Variable> chems) {
		this.chemicals.putAll(chems);
	}

	public Map<String, Variable> getVariables() {
		return this.chemicals;
	}

	public Property getVariableProperty() {
		return this.volume;
	}

	public List<Property> getVariablesProperties() {
		return null;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Compound: ").append("\n");

		sb.append("Name: ").append(this.name).append("\n");
		sb.append("Properties: ").append(this.volume.toString()).append("\n");
		for(Map.Entry<String, Variable> entry : this.chemicals.entrySet()) {
			sb.append(entry.getValue().toString());
		}
		return sb.toString();
	}

	public int getId() {
		return id;
	}
}
