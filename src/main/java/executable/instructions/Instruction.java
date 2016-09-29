package executable.instructions;

import executable.Executable;
import substance.Substance;

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
	protected Map<String, Substance> inputs = new HashMap<String, Substance>();
	private Map<String, Substance> outputs = new HashMap<String, Substance>();

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

	public void addInput(Substance input) {
		this.inputs.put(input.getName(), input);
	}

	public void addInputs(List<Substance> inputs) {
		for(Substance v : inputs) {
			this.addInput(v);
		}
	}

	public void addInputs(Map<String, Substance> inputs) {
		this.inputs.putAll(inputs);
	}

	public void addOutputs(List<Substance> outputs) {
		for(Substance v : outputs) {
			this.addOutput(v);
		}
	}

	public void addOutput(Substance variable) {
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
		for(Map.Entry<String, Substance> entry : this.inputs.entrySet()) {
			sb.append(entry.getValue().toString()).append("\n");
		}
		sb.append("Outputs: ").append("\n");
		for(Map.Entry<String, Substance> entry : this.outputs.entrySet()) {
			sb.append(entry.getValue().toString()).append("\n");
		}
		return sb.toString();
	}

	public Map<String, Substance> getOutputs() {
		return this.outputs;
	}

	public Map<String, Substance> getInputs() {
		return this.inputs;
	}
}
