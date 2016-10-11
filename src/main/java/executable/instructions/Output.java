package executable.instructions;

import substance.Substance;

/**
 * Created by jason on 2016/09/22.
 */
public class Output extends Instruction {

	public Output(long id, String name) {
		super(id, name, Output.class);
	}

	public Output(long id) {
		super(id, Output.class);
	}

	public Output(String name) {
		super(name, Output.class);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		return sb.toString();
	}

	public void execute() {

	}

	public void execute(Substance... variables) {

	}
}
