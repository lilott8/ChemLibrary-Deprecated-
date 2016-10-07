package executable.instructions;


import substance.Substance;

/**
 * Created by jason on 2016/08/15.
 */
public class Split extends Instruction {

	public Split(long id, String name) {
		super(id, name, Split.class);
	}

	public Split(long id) {
		super(id, Split.class);
	}

	public Split(String name) {
		super(name, Split.class);
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
