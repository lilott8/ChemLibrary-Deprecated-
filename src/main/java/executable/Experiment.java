package executable;

import variables.Variable;

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
	private Map<String, Variable> inputs = new HashMap<String, Variable>();
	private List<Executable> instructions = new ArrayList<Executable>();
	private Map<String, Variable> outputs = new HashMap<String, Variable>();

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

	public void addInputs(Map<String, Variable> inputs) {
		this.inputs.putAll(inputs);
	}

	public void addInput(Variable v) {
		this.inputs.put(v.getName(), v);
	}

	public void addOutputs(Map<String, Variable> v) {
		this.outputs.putAll(v);
	}

	public void addInstructions(List<Executable> instructions) {
		for(Executable i : instructions) {
			this.addInstruction(i);
		}
	}

	public void addInstruction(Executable e) {
		this.instructions.add(e);
	}

	public Map<String, Variable> getInputs() {
		return null;
	}

	public Map<String, Variable> getOutputs() {
		return this.outputs;
	}

	public void execute(Variable... variables) {
		this.execute();
	}

	public void execute() {
		for(Executable e : this.instructions) {
			e.execute();
		}
	}
}
