package variable;

import substance.Substance;

import java.util.Map;

/**
 * Created by jason on 2016/09/29.
 */
public class Reference implements Variable {

	private String id;
	private String name;
	private Variable reference;

	public Reference(String id, String name, Variable ref) {
		this.id = id;
		this.name = name;
		this.reference = ref;
	}

	public String getName() {
		return this.name;
	}

	public String getId() {
		return this.id;
	}

	public Map<String, Substance> getSubstance() {
		return this.reference.getSubstance();
	}

	public Variable getReference() {
		return this.reference;
	}

	public boolean setReference(Variable v) {
		this.reference = v;
		return true;
	}
}
