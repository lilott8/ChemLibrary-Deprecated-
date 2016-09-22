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

	private String name;
	private int id;
	private Map<Integer, Variable> inputs = new HashMap<Integer, Variable>();
	private List<Executable> instructions = new ArrayList<Executable>();
	private Map<Integer, Variable> outputs = new HashMap<Integer, Variable>();


	public Experiment(String name) {
		this.name = name;
		this.id = -1;
	}

	public Experiment(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public void addInputs(List<Variable> inputs) {
		for(Variable v : inputs) {
			this.addInput(v);
		}
	}

	public void addInput(Variable v) {
		this.inputs.put(v.getId(), v);
	}

	public void addInstructions(List<Executable> instructions) {
		for(Executable i : instructions) {
			this.addInstruction(i);
		}
	}

	public void addInstruction(Executable e) {
		this.instructions.add(e);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public void execute() {
		for(Executable e : this.instructions) {
			e.execute();
		}
	}

	public void execute(Variable... variables) {
		this.execute();
	}

	public Map<Integer, Variable> getInputs() {
		return null;
	}

	public Map<Integer, Variable> getOutputs() {
		return this.outputs;
	}
}
