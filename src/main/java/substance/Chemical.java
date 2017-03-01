package substance;

import java.io.Serializable;

/**
 * Created by jason on 2016/09/29.
 */
public class Chemical implements Serializable{

	private int groupId;
	private String name = "";
	private String smiles = "";
	private String raw = "";
	private Property volume = null;
	private int id = -1;

	public Chemical(int id, String name, Property property) {
		this.id = id;
		this.name = name;
		this.volume = property;
	}

	public Chemical(String name, Property property) {
		this.name = name;
		this.volume = property;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return this.toString("");
	}

	public String toString(String indentBuffer){
		String ret = indentBuffer;

		if(volume != null) {
			ret+=volume.toString() + " ";
		}
		ret+=name + '\n';

		if(!smiles.equals("")) {
			ret+= indentBuffer+'\t' + smiles;
		}
		if(!raw.equals("")) {
			ret+= indentBuffer+'\t' + raw;
		}

		return ret;
	}
}
