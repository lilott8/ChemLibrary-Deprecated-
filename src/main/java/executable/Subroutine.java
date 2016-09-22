package executable;

import variables.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/08/25.
 */
public class Subroutine implements Executable {

	private String name = "";
	private int id = -1;
	private Map<String, Variable> inputs = new HashMap<String, Variable>();
	private List<Executable> instructions = new ArrayList<Executable>();
	private Map<String, Variable> outputs = new HashMap<String, Variable>();

	public Subroutine(String name) {
		this.name = name;
	}

	public Subroutine(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public Subroutine(int id) {
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

	public void addInput(Variable input) {
		this.inputs.put(input.getName(), input);
	}

	public void addOutputs(Map<String, Variable> output) {
		this.outputs.putAll(output);
	}

	public void addInstruction(Executable instruction) {
		this.instructions.add(instruction);
	}

	public void addInstructions(List<Executable> instructions) {
		for(Executable i : instructions) {
			this.addInstruction(i);
		}
	}

	public Map<String, Variable> getOutputs() {
		return this.outputs;
	}

	public Map<String, Variable> getInputs() {
		return this.inputs;
	}

	public void execute() {
		for(Executable e : this.instructions) {
			e.execute();
		}
	}

	public void execute(Variable... variables) {
		this.execute();
	}
}
