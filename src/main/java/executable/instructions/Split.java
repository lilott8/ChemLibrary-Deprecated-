package executable.instructions;

import variables.Variable;

import java.util.Map;

/**
 * Created by jason on 2016/08/15.
 */
public class Split extends Instruction {

	public Split(String name, Class classification, int id) {
		super(name, classification, id);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		return sb.toString();
	}

	public Map<Integer, Variable> getInputs() {
		return null;
	}

	public void execute() {
	}

	public void execute(Variable... variables) {

	}
}
