package executable.instructions;

import substance.Substance;

/**
 * Created by jason on 2016/09/22.
 */
public class Heat extends Instruction {

	public Heat(long id, String name) {
		super(id, name, Heat.class);
	}

	public Heat(long id) {
		super(id, Heat.class);
	}

	public Heat(String name) {
		super(name, Heat.class);
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
