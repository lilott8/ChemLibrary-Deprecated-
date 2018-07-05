package executable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import substance.Substance;
import variable.Variable;

/**
 * Created by jason on 2016/08/25.
 */
public class Subroutine implements Executable {

	private String name = "";
	private long id = -1;
	private Map<String, Variable> inputs = new HashMap<String, Variable>();
    private List<Variable> inputsAsList = new ArrayList<>();
	private List<Executable> instructions = new ArrayList<Executable>();
	private Map<String, Variable> outputs = new HashMap<String, Variable>();

	public Subroutine(String name) {
		this.name = name;
	}

	public Subroutine(long id, String name) {
		this.name = name;
		this.id = id;
	}

	public Subroutine(long id) {
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
        for (Map.Entry<String, Variable> entry : inputs.entrySet()) {
            this.inputsAsList.add(entry.getValue());
        }
	}

	public void addInput(Variable input) {
		this.inputs.put(input.getName(), input);
        this.inputsAsList.add(input);
	}

	public void addOutput(Variable output) {
		this.outputs.put(output.getName(), output);
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
	public List<Executable> getInstructions() {
		return  instructions;
	}

	public Map<String, Variable> getOutputs() {
		return this.outputs;
	}

	public Map<String, Variable> getInputs() {
		return this.inputs;
	}

    @Override
    public List<Variable> getInputsAsList() {
        return this.inputsAsList;
    }

	public void execute() {
		for(Executable e : this.instructions) {
			e.execute();
		}
	}

    @Override
    public Variable getVariableByName(String input) {
        Variable result = null;
        for (Variable v : this.inputsAsList) {
            if (input.equalsIgnoreCase(v.getName())) {
                result = v;
            }
        }
        return result;
    }

	public void execute(Substance... variables) {
		this.execute();
	}

	public String toString(){
		return this.toString("");
	}
	public String toString(String indentbuffer) {
		return indentbuffer + "Subroutine Print Not implemented";
	}
}
