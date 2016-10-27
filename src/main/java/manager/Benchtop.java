package manager;

import executable.Executable;
import executable.Experiment;
import substance.Substance;
import variable.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/09/21.
 */
public enum Benchtop {
	INSTANCE;

	// all the experiments on the manager
	private Map<String, ArrayList<Experiment>> experiments = new HashMap<String, ArrayList<Experiment>>();
	// inputs for the all experiments
	private Map<String, Variable> inputs = new HashMap<String, Variable>();
	private Map<String, Variable> outputs = new HashMap<String, Variable>();
	private List<Executable> executables = new ArrayList<Executable>();

	private String name = "";
	private int id = -1;


	public void setName(String n) {
		if(this.name.isEmpty()) {
			this.name = n;
		}
	}

	public void setId(int i) {
		if(this.id == -1) {
			this.id = i;
		}
	}

	public void addInstruction(Executable instruction) {
		this.executables.add(instruction);
	}

	public void addInstructions(List<Executable> instructions) {
		for(Executable i : instructions) {
			this.addInstruction(i);
		}
	}

	public void addInputs(Map<String, Variable> inputs) {
		this.inputs.putAll(inputs);
	}

	public void addInput(Variable input) {
		this.inputs.put(input.getName(), input);
	}

	public void addOutputs(List<Variable> outputs) {
		if(outputs != null) {
			for (Variable v : outputs) {
				this.addOutput(v);
			}
		}
	}

	public void addOutput(Variable variable) {
		this.outputs.put(variable.getName(), variable);
	}

	public void addExperiment(Experiment experiment) {
		if(this.experiments.get(experiment.getName()) == null) {
			this.experiments.put(experiment.getName(), new ArrayList<Experiment>());
		}
		this.experiments.get(experiment.getName()).add(experiment);
	}

	public void addExperiments(List<Experiment> experiments) {
		for(Experiment e : experiments) {
			this.addExperiment(e);
		}
	}

	public Map<String, ArrayList<Experiment>> getExperiments() {return experiments; }
	public Map<String, Variable> getInputs() { return inputs; }
	public Map<String, Variable> getOutputs() { return outputs; }

	public void runAllExperiments() {
		for(Map.Entry<String, ArrayList<Experiment>> entry : this.experiments.entrySet()) {
			this.runExperiment(entry.getKey());
		}
	}

	public void runExperiment(String name) {
		if (this.experiments.get(name) != null) {
			for (Experiment e : this.experiments.get(name)) {
				e.execute();
			}
		}
	}

	public String toString() {
		return this.toString("");
	}
	public String toString(String indentBuffer){
		String ret=indentBuffer+ "Benchtop : \n";
		for(Variable v : inputs.values()) {
			ret+= v.toString(indentBuffer+'\t') + '\n';
		}
		if(experiments != null) {
			for (ArrayList<Experiment> experiment : experiments.values())
				for (Experiment experimen : experiment)
					ret += experimen.toString(indentBuffer + '\t') + '\n';

		}
		/*StringBuilder sb = new StringBuilder("Benchtop").append(System.lineSeparator());
		sb.append("Inputs").append(inputs.toString()).append(System.lineSeparator());
		sb.append("Outputs:").append(outputs.toString()).append(System.lineSeparator());
		sb.append("Experiments:").append(experiments.toString()).append(System.lineSeparator());
		sb.append("Executables:").append(executables.toString()).append(System.lineSeparator());*/
		return ret;
	}

}
