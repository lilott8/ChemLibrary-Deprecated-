package executable;


import substance.Substance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/08/25.
 */
public class Experiment implements Executable {

	private String name = "";
	private int id = -1;
	private Map<String, Substance> inputs = new HashMap<String, Substance>();
	private List<Executable> instructions = new ArrayList<Executable>();
	private Map<String, Substance> outputs = new HashMap<String, Substance>();

	public Experiment(int id) {
		this.id = id;
	}

	public Experiment(String name) {
		this.name = name;
		this.id = -1;
	}

	public Experiment(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public void addInputs(Map<String, Substance> inputs) {
		this.inputs.putAll(inputs);
	}

	public void addInput(Substance v) {
		this.inputs.put(v.getName(), v);
	}

	public void addOutputs(Map<String, Substance> v) {
		this.outputs.putAll(v);
	}

	public void addOutput(Substance v) {
		this.outputs.put(v.getName(), v);
	}

	public void addInstructions(List<Executable> instructions) {
		for(Executable i : instructions) {
			this.addInstruction(i);
		}
	}

	public List<Executable> getInstructions() {
		return instructions;
	}

	public void addInstruction(Executable e) {
		this.instructions.add(e);
	}

	public Map<String, Substance> getInputs() {
		return this.inputs;
	}

	public Map<String, Substance> getOutputs() {
		return this.outputs;
	}

	public void execute(Substance... variables) {
		this.execute();
	}

	public void execute() {
		for(Executable e : this.instructions) {
			e.execute();
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Experiments: ");

		sb.append("Name: ").append(this.name).append(System.lineSeparator());
		sb.append("Id: ").append(this.id).append(System.lineSeparator());
		sb.append("Inputs: ").append(this.inputs.toString()).append(System.lineSeparator());
		sb.append("Instructions: ").append(this.instructions.toString()).append(System.lineSeparator());
		sb.append("Outputs: ").append(this.outputs.toString()).append(System.lineSeparator());

		return sb.toString();
	}
}
