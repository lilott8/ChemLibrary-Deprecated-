package executable.conditionals;

import executable.instructions.Instruction;
import substance.Property;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jason on 2016/10/03.
 */
public class Branch extends Conditional {

	public Branch(String expression, List<Instruction> tBranch, List<Instruction> eIBranch, List<Instruction> eBranch) {
		super(expression, tBranch, eIBranch, eBranch);
	}

	public Branch(int id, String expression, List<Instruction> tBranch, List<Instruction> eIBranch, List<Instruction> eBranch) {
		super(id, expression, tBranch, eIBranch, eBranch);
	}

	@Override
	public List<Instruction> getTrueBranch() {
		return this.trueBranch;
	}

	@Override
	public List<Instruction> getElseIfBranch() {
		return this.elseIfBranch;
	}

	@Override
	public List<Instruction> getElseBranch() {
		return this.elseBranch;
	}

	@Override
	public Conditional addTrueBranch(Instruction i) {
		this.trueBranch.add(i);
		return this;
	}

	@Override
	public Conditional addElseIfBranch(Instruction i) {
		this.elseIfBranch.add(i);
		return this;
	}

	@Override
	public Conditional addElseBranch(Instruction i) {
		this.elseBranch.add(i);
		return this;
	}

	@Override
	public Conditional addProperties(Property... p) {
		this.properties.addAll(Arrays.asList(p));
		return this;
	}
}
