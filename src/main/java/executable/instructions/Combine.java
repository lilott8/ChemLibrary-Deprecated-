package executable.instructions;


import substance.Substance;

/**
 * Created by jason on 2016/08/15.
 */
public class Combine extends Instruction {

	public Combine(long id, String name) {
		super(id, name, Combine.class);
	}

	public Combine(long id) {
		super(id, Combine.class);
	}

	public Combine(String name) {
		super(name, Combine.class);
	}

	public void execute() {

	}

	public void execute(Substance... args) {

	}

	public String getName() {
		return super.name;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		return sb.toString();
	}
}
