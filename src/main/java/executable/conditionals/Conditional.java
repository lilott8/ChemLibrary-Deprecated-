package executable.conditionals;

import executable.instructions.Instruction;
import substance.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/10/03.
 */
public abstract class Conditional {

	protected int id = -1;
	protected String evaluation;
	protected List<Instruction> trueBranch = new ArrayList<Instruction>();
	protected List<Instruction> elseIfBranch = new ArrayList<Instruction>();
	protected List<Instruction> elseBranch = new ArrayList<Instruction>();
	protected List<Property> properties = new ArrayList<Property>();


	protected Conditional(String expression, List<Instruction> tBranch, List<Instruction> eIBranch, List<Instruction> eBranch) {
		this.evaluation = expression;
		this.trueBranch = tBranch;
		this.elseIfBranch = eIBranch;
		this.elseBranch = eBranch;
	}

	protected Conditional(int id, String expression, List<Instruction> tBranch, List<Instruction> eIBranch, List<Instruction> eBranch) {
		this.evaluation = expression;
		this.trueBranch = tBranch;
		this.elseIfBranch = eIBranch;
		this.elseBranch = eBranch;
		this.id = id;
	}

	public String getEvaluationString() {
		return this.evaluation;
	}

	public abstract List<Instruction> getTrueBranch();
	public abstract List<Instruction> getElseIfBranch();
	public abstract List<Instruction> getElseBranch();
	public abstract Conditional addTrueBranch(Instruction i);
	public abstract Conditional addElseIfBranch(Instruction i);
	public abstract Conditional addElseBranch(Instruction i);
	public abstract Conditional addProperties(Property... p);

}
