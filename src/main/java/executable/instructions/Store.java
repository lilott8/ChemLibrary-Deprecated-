package executable.instructions;

import variables.Variable;

import java.util.Map;

/**
 * Created by jason on 2016/09/22.
 */
public class Store extends Instruction {

	public Store(int id, String name) {
		super(id, name, Store.class);
	}

	public Store(int id) {
		super(id, Store.class);
	}

	public Store(String name) {
		super(name, Store.class);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		return sb.toString();
	}

	public void execute() {

	}

	public void execute(Variable... variables) {

	}
}
