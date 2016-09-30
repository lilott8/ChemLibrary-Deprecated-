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
}
