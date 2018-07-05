package executable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import substance.Substance;
import variable.Variable;

/**
 * Created by jason on 2016/09/21.
 */
public interface Executable extends Serializable {
	long getId();
	String getName();

    // Map<String, Variable> getInputs();
    List<Variable> getInputsAsList();
	Map<String, Variable> getOutputs();

    Variable getVariableByName(String input);

	void addInstruction(Executable exec);
	void addInstructions(List<Executable> execs);

	void execute(Substance... variables);
	void execute();

	String toString(String s);
}
