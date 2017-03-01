package variable;

import com.sun.org.apache.xpath.internal.operations.Bool;
import substance.Chemical;
import substance.Substance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/09/29.
 */
public class Instance implements Variable {

	private String id;
	private String name;
	private Boolean isStationary;
	private Chemical chemical;
//	private ArrayList<Chemical> chemicalMakeup = new ArrayList<Chemical>();

	//private Map<String, Substance> substances = new HashMap<String, Substance>();

	public Instance(String id, String name) {
		this.id = id;
		this. name = name;
		this.isStationary = false;
		chemical = null;
	}
	public Instance(String id, String name, Chemical chemical) {
		this.id = id;
		this. name = name;
		this.isStationary = false;

		this.chemical = chemical;
		//for(Chemical chemical : chemicals){
		//	chemicalMakeup.add(chemical);
		//}
	}


	public String getName() {
		return this.name;
	}
	public void setName(String name) { this.name = name; }

	public String getID() {
		return this.id;
	}
	public void setID(String ID) { this.id = ID; }

	public Boolean getIsStationary() { return isStationary; }
	public void setIsStationary(Boolean isStationary) { this.isStationary = isStationary; }

	public Chemical getSubstance() {
		return this.chemical;
//		return this.chemicalMakeup;
	}

	//public Variable getReference() {
	//	return this;
	//}

	//public boolean setReference(Map<String, Substance> v) {
//		return false;
//	}

	public String toString(){
		return this.toString("");
	}

	public String toString(String indentBuffer){

        String ret;
        if (this.chemical != null)
            ret = indentBuffer + this.chemical.toString();
        else
    		ret = indentBuffer + name + '\n' ;
		//for (Substance s: substances.values())
		//	ret += s.toString(indentBuffer + '\t') +'\n';

		return ret;
	}
}
