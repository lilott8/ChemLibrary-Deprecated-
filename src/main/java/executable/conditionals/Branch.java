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
public class Branch extends Instruction {
	protected int id = -1;
	protected String evaluation;
	protected List<Instruction> trueBranch = new ArrayList<Instruction>();
	protected List<Instruction> elseIfBranch = new ArrayList<Instruction>();
	protected List<Instruction> elseBranch = new ArrayList<Instruction>();
	protected List<Property> properties = new ArrayList<Property>();


	public Branch(long id, String name, String expression) {
		super(id, name, Branch.class);

		evaluation = expression;
	}

	public Branch(String expression, List<Instruction> tBranch, List<Instruction> eIBranch, List<Instruction> eBranch) {
		super(-1, "Branch", Branch.class);

		evaluation = expression;
		trueBranch = tBranch;
		elseIfBranch = eIBranch;
		elseBranch = eBranch;

	}

	public Branch(int id, String expression, List<Instruction> tBranch, List<Instruction> eIBranch, List<Instruction> eBranch) {
		super(id, "Branch", Branch.class);

		evaluation = expression;
		trueBranch = tBranch;
		elseIfBranch = eIBranch;
		elseBranch = eBranch;
	}


	public List<Instruction> getTrueBranch() {
		return this.trueBranch;
	}


	public List<Instruction> getElseIfBranch() {
		return this.elseIfBranch;
	}


	public List<Instruction> getElseBranch() {
		return this.elseBranch;
	}

	public Branch addTrueBranch(Instruction i) {
		this.trueBranch.add(i);
		return this;
	}

	public Branch addElseIfBranch(Instruction i) throws IllegalArgumentException {

		if (i instanceof Branch)
			this.elseIfBranch.add(i);

		else
			throw new IllegalArgumentException("You must add a Branch to the Else if list");
		return this;
	}

	public Branch addElseBranch(Instruction i) {
		this.elseBranch.add(i);
		return this;
	}

	public Branch addProperties(Property... p) {
		this.properties.addAll(Arrays.asList(p));
		return this;
	}

	public void execute() {

	}

	public void execute(Substance... variables) {

	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("Evaluation: " + evaluation);
		sb.append("TrueBranch: " + trueBranch);
		sb.append("ElseBranch: " + elseBranch);
		sb.append("ElseIf: " + elseIfBranch);
		sb.append("Properties: " + properties);
		return sb.toString();
	}

}
