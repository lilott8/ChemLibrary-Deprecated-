package variable;

import substance.Substance;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jason on 2016/09/29.
 */
public class Instance implements Variable {

	private String id;
	private String name;
	private Map<String, Substance> substances = new HashMap<String, Substance>();

	public Instance(String id, String name, Substance... substances) {
		this.id = id;
		this. name = name;
		for(Substance s : substances) {
			this.substances.put(s.getName(), s);
		}
	}

	public String getName() {
		return this.name;
	}

	public String getId() {
		return this.id;
	}

	public Map<String, Substance> getSubstance() {
		return this.substances;
	}

	public Variable getReference() {
		return this;
	}

	public boolean setReference(Variable v) {
		return false;
	}
}
