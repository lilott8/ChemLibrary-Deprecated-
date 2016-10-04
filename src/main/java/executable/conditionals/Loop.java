package executable.conditionals;

import executable.instructions.Instruction;

import java.util.List;

/**
 * Created by jason on 2016/10/03.
 */
public class Loop extends Conditional {

	public Loop(String expression, List<Instruction> tBranch, List<Instruction> eIBranch, List<Instruction> eBranch) {
		super(expression, tBranch, eIBranch, eBranch);
	}

	public Loop(int id, String expression, List<Instruction> tBranch, List<Instruction> eIBranch, List<Instruction> eBranch) {
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
	public void addTrueBranch(Instruction i) {
		this.trueBranch.add(i);
	}

	@Override
	public void addElseIfBranch(Instruction i) {
		this.elseIfBranch.add(i);
	}

	@Override
	public void addElseBranch(Instruction i) {
		this.elseBranch.add(i);
	}
}
