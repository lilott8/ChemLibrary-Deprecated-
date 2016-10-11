package executable;

import substance.Substance;
import variable.Variable;

import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/09/21.
 */
public interface Executable {
	long getId();
	String getName();

	Map<String, Variable> getInputs();
	Map<String, Variable> getOutputs();

	void addInstruction(Executable exec);
	void addInstructions(List<Executable> execs);

	void execute(Substance... variables);
	void execute();
}
