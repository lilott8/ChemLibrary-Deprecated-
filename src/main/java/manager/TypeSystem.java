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
	private Map<Long, Instruction> operationTable = new HashMap<Long, Instruction>();

	public void addVariable(Variable v) {
		this.variableTable.put(v.getName(), v);
	}

	public void addOperation(Instruction i) {
		this.operationTable.put(i.getId(), i);
	}

	public Variable getVariable(Variable v) {
		return this.variableTable.get(v.getName());
	}

	public Variable getVariable(String key) {
		return this.variableTable.get(key);
	}
}
