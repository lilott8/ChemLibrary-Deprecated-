package variable;

import substance.Substance;

import java.util.Map;

/**
 * Created by jason on 2016/09/29.
 */
public interface Variable {

	String getName();
	String getId();
	Map<String, Substance> getSubstance();
	Variable getReference();
	boolean setReference(Variable v);

}
