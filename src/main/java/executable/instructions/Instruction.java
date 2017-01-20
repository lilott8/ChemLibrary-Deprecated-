package executable.instructions;

import executable.Executable;
import substance.Property;
import substance.Substance;
import variable.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/08/15.
 */
public abstract class Instruction implements Executable {

	protected String name = "";
	protected Class classification = this.getClass();
	protected long id = -1;
	protected Map<String, Variable> inputs = new HashMap<String, Variable>();
	protected ArrayList<Property> properties = new ArrayList<Property>();
	private Map<String, Variable> outputs = new HashMap<String, Variable>();

	protected Instruction(long id, Class c) {
		this.id = id;
		this.classification = c;
	}

	protected Instruction(String name, Class c) {
		this.name = name;
		this.classification = c;
	}

	protected Instruction(long id, String name) {
		this.id = id;
		this.name = name;
	}

	protected Instruction(long id, String name, Class classification) {
		this.name = name;
		this.classification = classification;
		this.id = id;
	}

	public void addProperty(Property p){ this.properties.add(p); }

	public void addInstruction(Executable instruction) {}

	public void addInstructions(List<Executable> instructions) {}

	public void addInput(Variable input) {
		this.inputs.put(input.getName(), input);
	}

	public void addInputs(List<Variable> inputs) {
		for(Variable v : inputs) {
			this.addInput(v);
		}
	}

	public void addInputs(Map<String, Variable> inputs) {
		this.inputs.putAll(inputs);
	}

	public void addOutputs(List<Variable> outputs) {
		for(Variable v : outputs) {
			this.addOutput(v);
		}
	}

	public void addOutput(Variable variable) {
		this.outputs.put(variable.getName(), variable);
	}

	public void removeOutput(Variable variable) {
		if (this.outputs.containsKey(variable.getName())){
			this.outputs.remove(variable.getName());
		}
	}


	public String getName() {
		return this.name;
	}

	public long getId() {
		return this.id;
	}

	public String toString(){
		return this.toString("");
	}

	public String toString(String indentBuffer) {
		String ret = indentBuffer + this.name + "(" + this.id + ")" + '\n';

		if(this.inputs!=null &&this.inputs.size() > 0){
			ret+= indentBuffer +'\t' + "Inputs: "+ '\n';
			for(Variable v : this.inputs.values()){
				ret+= indentBuffer + "\t\t" + v.getName() +'\n';
			}
		}

		if(this.properties != null && this.properties.size() > 0) {
            ret += indentBuffer + '\t' + "Properties: " + '\n';
            for(Property p : this.properties)
                ret += indentBuffer + "\t\t" + p.toString() + '\n';
        }

		if(this.outputs!=null &&this.outputs.size() > 0){
			ret+= indentBuffer +'\t' + "Outputs: "+ '\n';
			for(Variable v : this.outputs.values()){
				ret+= v.toString(indentBuffer+"\t\t");
			}
		}

		return ret;
	}


	public Map<String, Variable> getOutputs() {
		return this.outputs;
	}

	public Map<String, Variable> getInputs() {
		return this.inputs;
	}

	public ArrayList<Property> getProperties() { return this.properties; }
}
