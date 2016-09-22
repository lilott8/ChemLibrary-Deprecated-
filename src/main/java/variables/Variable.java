package variables;

import common.Property;

import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/08/25.
 */
public interface Variable<T> {

	String getName();
	int getId();
	Map<String, Variable> getVariables();
	Property getVariableProperty();

}
