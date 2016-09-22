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

	private String name;
	private int id = -1;
	private Map<Integer, Variable> inputs = new HashMap<Integer, Variable>();
	private List<Executable> instructions = new ArrayList<Executable>();
	private Map<Integer, Variable> outputs = new HashMap<Integer, Variable>();

	public Subroutine(String name) {
		this.name = name;
	}

	public Subroutine(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public void addInstruction(Executable instruction) {
		this.instructions.add(instruction);
	}

	public void addInstructions(List<Executable> instructions) {
		for(Executable i : instructions) {
			this.addInstruction(i);
		}
	}

	public void addInputs(List<Variable> inputs) {
		for(Variable v : inputs) {
			this.addInput(v);
		}
	}

	public void addInput(Variable input) {
		this.inputs.put(input.getId(), input);
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

	public Map<Integer, Variable> getOutputs() {
		return this.outputs;
	}

	public Map<Integer, Variable> getInputs() {
		return null;
	}
}
