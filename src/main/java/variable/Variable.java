package variable;

import substance.Substance;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by jason on 2016/09/29.
 */
public interface Variable extends Serializable{

	String getName();
	void setName(String name);
	String getID();
	void setID(String ID);
	Map<String, Substance> getSubstance();
	Variable getReference();
	boolean setReference(Map<String, Substance>  v);
	String toString(String s);
}
