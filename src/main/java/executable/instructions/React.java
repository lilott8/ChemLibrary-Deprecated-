package executable.instructions;

import substance.Substance;

/**
 * Created by jason on 2016/11/07.
 */
public class React extends Instruction {

	public React(long id, String name) {
		super(id, name, Output.class);
	}

	public React(long id) {
		super(id, Output.class);
	}

	public React(String name) {
		super(name, Output.class);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		return sb.toString();
	}

	public void execute(Substance... variables) {

	}

	public void execute() {

	}
}
