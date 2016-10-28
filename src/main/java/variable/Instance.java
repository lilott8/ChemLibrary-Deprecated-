package variable;

import substance.Substance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/09/29.
 */
public class Instance implements Variable {

	private String id;
	private String name;
	private Map<String, Substance> substances = new HashMap<String, Substance>();

	public Instance(String id, String name, List<Substance> substances) {
		this.id = id;
		this. name = name;
		for(Substance s : substances) {
			this.substances.put(s.getName(), s);
		}
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) { this.name = name; }

	public String getID() {
		return this.id;
	}
	public void setID(String ID) { this.id = ID; }

	public Map<String, Substance> getSubstance() {
		return this.substances;
	}

	public Variable getReference() {
		return this;
	}

	public boolean setReference(Map<String, Substance> v) {
		return false;
	}

	public String toString(){
		return this.toString("");
	}

	public String toString(String indentBuffer){
		String ret=indentBuffer +"(Instance)"+ name + '\n' ;
		for (Substance s: substances.values())
			ret += s.toString(indentBuffer + '\t') +'\n';

		return ret;
	}
}
