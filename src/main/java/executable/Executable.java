package executable;

import substance.Substance;

import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/09/21.
 */
public interface Executable {
	int getId();
	String getName();

	Map<String, Substance> getInputs();
	Map<String, Substance> getOutputs();

	void addInstruction(Executable exec);
	void addInstructions(List<Executable> execs);

	List<Executable> getInstructions();

	void execute(Substance... variables);
	void execute();
}
