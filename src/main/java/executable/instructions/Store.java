package executable.instructions;

import variables.Variable;

import java.util.Map;

/**
 * Created by jason on 2016/09/22.
 */
public class Store extends Instruction {

	public Store(String name, Class classification, int id) {
		super(name, classification, id);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		return sb.toString();
	}

	public void execute() {

	}

	public void execute(Variable... variables) {

	}

	public Map<Integer, Variable> getInputs() {
		return null;
	}
}
