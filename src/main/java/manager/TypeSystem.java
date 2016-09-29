package manager;

import executable.instructions.Instruction;
import variable.Variable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jason on 2016/09/28.
 */
public enum TypeSystem {
	INSTANCE;

	private Map<String, Variable> variableTable = new HashMap<String, Variable>();
	private Map<Integer, Instruction> operationTable = new HashMap<Integer, Instruction>();

	public void addVariable(Variable v) {

	}

	public void addOperation(Instruction i) {

	}
}
