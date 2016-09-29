package executable.instructions;

import substance.Substance;

/**
 * Created by jason on 2016/09/22.
 */
public class Detect extends Instruction {

	public Detect(int id, String name) {
		super(id, name, Detect.class);
	}

	public Detect(int id) {
		super(id, Detect.class);
	}

	public Detect(String name) {
		super(name, Detect.class);
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
