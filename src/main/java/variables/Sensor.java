package variables;

import common.Property;

/**
 * Created by jason on 2016/08/25.
 */
public class Sensor implements Variable {

	private String name;
	private int id = -1;

	public Sensor(String name) {
		this.name = name;
	}

	public Sensor(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public Property getProperty() {
		return null;
	}

	@Override
	public String toString() {
		return String.format("Sensor Name: %s", this.name);
	}

	public int getId() {
		return id;
	}
}
