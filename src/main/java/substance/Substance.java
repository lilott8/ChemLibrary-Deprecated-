package substance;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jason on 2016/09/28.
 */
public class Substance {

	public enum Synthetic {
		CHEMICAL, COMPOUND
	}

	private String name;
	private int id;
	private Synthetic mixtureType;
	private Property property;
	private Map<String, Chemical> chemicals = new HashMap<String, Chemical>();

	public Substance(int id, String name, Property p, String type) {
		this.id = id;
		this.name = name;
		this.mixtureType = Synthetic.valueOf(type);
		this.property = p;
		if(this.mixtureType == Synthetic.CHEMICAL) {
			this.chemicals.put(name, new Chemical(this.id, this.name, this.property));
		}
	}

	public Substance(int id, String name, Property p, Synthetic s) {
		this.id = id;
		this.name = name;
		this.mixtureType = s;
		this.property = p;
		if(this.mixtureType == Synthetic.CHEMICAL) {
			this.chemicals.put(name, new Chemical(this.id, this.name, this.property));
		}
	}

	public Synthetic getMixtureType() {
		return this.mixtureType;
	}

	public boolean addChemical(Chemical c) {
		if(this.mixtureType != Synthetic.COMPOUND) {
			this.mixtureType = Synthetic.COMPOUND;
		}

		this.chemicals.put(c.getName(), c);
		return true;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public Property getProperty() {
		return property;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Substance:").append("\n");
		sb.append("Name: ").append(this.name).append("\n");
		sb.append("Volume: ").append(this.property.toString());

		return sb.toString();
	}
}
