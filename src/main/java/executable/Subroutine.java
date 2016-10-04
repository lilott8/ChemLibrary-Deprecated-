package executable;

import substance.Substance;

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
	private Map<String, Substance> inputs = new HashMap<String, Substance>();
	private List<Executable> instructions = new ArrayList<Executable>();
	private Map<String, Substance> outputs = new HashMap<String, Substance>();

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

	public void addInputs(Map<String, Substance> inputs) {
		this.inputs.putAll(inputs);
	}

	public void addInput(Substance input) {
		this.inputs.put(input.getName(), input);
	}

	public void addOutput(Substance output) {
		this.outputs.put(output.getName(), output);
	}

	public void addOutputs(Map<String, Substance> output) {
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
	public List<Executable> getInstructions() {
		return  instructions;
	}

	public Map<String, Substance> getOutputs() {
		return this.outputs;
	}

	public Map<String, Substance> getInputs() {
		return this.inputs;
	}

	public void execute() {
		for(Executable e : this.instructions) {
			e.execute();
		}
	}

	public void execute(Substance... variables) {
		this.execute();
	}
}
