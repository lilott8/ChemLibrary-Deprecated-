package executable;

import variables.Variable;

import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/09/21.
 */
public interface Executable {
	void execute();
	void execute(Variable... variables);
	Map<Integer, Variable> getOutputs();
	void addInstruction(Executable exec);
	void addInstructions(List<Executable> execs);
	String getName();
	int getId();
	Map<Integer, Variable> getInputs();
}
