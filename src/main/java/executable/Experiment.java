package executable;


import executable.instructions.Instruction;
import substance.Substance;
import variable.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/08/25.
 */
public class Experiment implements Executable {

	private String name = "";
	private long id = -1;
	private Map<String, Variable> inputs = new HashMap<String, Variable>();
	private List<Executable> instructions = new ArrayList<Executable>();
	private Map<String, Variable> outputs = new HashMap<String, Variable>();

	public Experiment(long id) {
		this.id = id;
	}

	public Experiment(String name) {
		this.name = name;
		this.id = -1;
	}

	public Experiment(long id, String name) {
		this.name = name;
		this.id = id;
	}

	public long getId() {
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

	public void addOutput(Variable v) {
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

	public Map<String, Variable> getInputs() {
		return this.inputs;
	}

	public Map<String, Variable> getOutputs() {
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
		return this.toString("");

	}
	public String toString(String indentbuffer){
		String ret= indentbuffer + "Experiement : " + this.name + '\n';
		if(inputs!= null && inputs.size() > 0) {
			ret += indentbuffer + '\t' + "Inputs:" + '\n';
			for (Variable v : inputs.values()) {
				ret += v.toString(indentbuffer + "\t\t");
			}
		}

		if(this.instructions != null && this.instructions.size() >0) {
			ret+= indentbuffer+'\t' + "Instructions: " + '\n';
			for (Executable e : this.instructions) {
				ret+= e.toString(indentbuffer + "\t\t");
			}
		}

		return ret;
	}
}
