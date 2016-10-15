package executable.conditionals;

import executable.instructions.Instruction;
import substance.Property;
import substance.Substance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jason on 2016/10/03.
 */
public class Loop extends Instruction {
	protected int id = -1;
	protected String evaluation;
	protected List<Instruction> trueBranch = new ArrayList<Instruction>();

	protected List<Property> properties = new ArrayList<Property>();

	public Loop(long id, String name, String expression) {
		super(id, name, Loop.class);

		evaluation = expression;
	}

	public Loop(String expression, List<Instruction> tBranch) {
		super(-1, "Branch", Loop.class);

		evaluation = expression;
		trueBranch = tBranch;

	}

	public Loop(int id, String expression, List<Instruction> tBranch) {
		super(id, "Branch", Loop.class);

		evaluation = expression;
		trueBranch = tBranch;
	}

	public List<Instruction> getTrueBranch() {
		return this.trueBranch;
	}

	public void addTrueBranch(Instruction i) {
		this.trueBranch.add(i);
	}

	public void addProperties(Property... p) {
		this.properties.addAll(Arrays.asList(p));
	}

	public void execute() {

	}

	public void execute(Substance... variables) {

	}
}
