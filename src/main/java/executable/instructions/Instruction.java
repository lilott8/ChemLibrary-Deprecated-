package executable.instructions;

import executable.Executable;
import variables.Chemical;
import variables.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/08/15.
 */
public abstract class Instruction implements Executable {

	protected String name = "";
	protected Class classification = this.getClass();
	protected int id = -1;
	protected Map<String, Variable> inputs = new HashMap<String, Variable>();
	private Map<String, Variable> outputs = new HashMap<String, Variable>();

	protected Instruction(int id, Class c) {
		this.id = id;
		this.classification = c;
	}

	protected Instruction(String name, Class c) {
		this.name = name;
		this.classification = c;
	}

	protected Instruction(int id, String name) {
		this.id = id;
		this.name = name;
	}

	protected Instruction(int id, String name, Class classification) {
		this.name = name;
		this.classification = classification;
		this.id = id;
	}

	public void addInstruction(Executable instruction) {}

	public void addInstructions(List<Executable> instructions) {}

	public void addInput(Variable input) {
		this.inputs.put(input.getName(), input);
	}

	public void addInputs(List<Variable> inputs) {
		for(Variable v : inputs) {
			this.addInput(v);
		}
	}

	public void addInputs(Map<String, Variable> inputs) {
		this.inputs.putAll(inputs);
	}

	public void addOutputs(List<Variable> outputs) {
		for(Variable v : outputs) {
			this.addOutput(v);
		}
	}

	public void addOutput(Variable variable) {
		this.outputs.put(variable.getName(), variable);
	}


	public String getName() {
		return this.name;
	}

	public int getId() {
		return this.id;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Instruction: ").append(this.classification.getName()).append("\n");
		sb.append("Name: ").append(this.name).append("\n");
		sb.append("Id: ").append(this.id).append("\n");
		sb.append("Inputs: ").append("\n");
		for(Map.Entry<String, Variable> entry : this.inputs.entrySet()) {
			sb.append(entry.getValue().toString()).append("\n");
		}
		sb.append("Outputs: ").append("\n");
		for(Map.Entry<String, Variable> entry : this.outputs.entrySet()) {
			sb.append(entry.getValue().toString()).append("\n");
		}
		return sb.toString();
	}

	public Map<String, Variable> getOutputs() {
		return this.outputs;
	}

	public Map<String, Variable> getInputs() {
		return this.inputs;
	}
}
