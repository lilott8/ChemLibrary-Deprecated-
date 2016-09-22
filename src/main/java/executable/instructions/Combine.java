package executable.instructions;

import errors.CompatabilityException;
import variables.Chemical;
import variables.Variable;

import java.util.Map;

/**
 * Created by jason on 2016/08/15.
 */
public class Combine extends Instruction {

	public Combine(String name, Class classification, int id) {
		super(name, classification, id);
	}

	public void execute() {

	}

	public void execute(Variable... args) {

	}

	public boolean checkCompatibility(Chemical one, Chemical two) throws CompatabilityException {
		return false;
	}

	public boolean checkCompatibility(Chemical... args) throws CompatabilityException {
		return false;
	}

	public String getName() {
		return super.name;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		return sb.toString();
	}

	public Map<Integer, Variable> getInputs() {
		return null;
	}


}
