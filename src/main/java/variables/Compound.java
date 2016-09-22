package variables;

import common.Property;
import common.Units;

import java.util.ArrayList;
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

	// todo: compounds need a property!
	public Compound(String name, Property property) {
		this.name = name;
		this.volume = property;
		this.generateProperty();
	}

	public Compound(String name) {
		this.name = name;
		this.generateProperty();
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

	/**
	 * Compounds don't have explicit properties, so we must generate it
	 * after the chemicals have been added
	 * todo: add unit testing for this method
	 */
	public void generateProperty() {
		if(this.chemicals.size() > 0) {
			/*common.Units.Volume unit = this.chemicals.get(0).getProperty().getUnit();
			float amount = 0;
			for(Variable c : this.chemicals) {
				c = (Chemical) c;
				if(c.getProperty().getUnit() == unit) {
					amount += c.getProperty().getQuantity();
				} else {
					// normalize the units to the base unit

				}
			}*/
			this.volume = new Property<Units.Volume>(100, Units.Volume.L);
		}
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
