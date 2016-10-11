package executable.instructions;

import substance.Substance;

/**
 * Created by jason on 2016/09/22.
 */
public class Dispense extends Instruction {

	public Dispense(long id, String name) {
		super(id, name, Dispense.class);
	}

	public Dispense(long id) {
		super(id, Dispense.class);
	}

	public Dispense(String name) {
		super(name, Dispense.class);
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
