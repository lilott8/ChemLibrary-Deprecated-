import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Set;

/**
 * Created by jason on 2016/09/21.
 */
public class Main {

	public static void main(String... args) {
		/*Experiment e = new Experiment(1, "test");
		Chemical water = new Chemical("water", new Property<Units.Volume>(30, Units.Volume.mL));
		Chemical oil = new Chemical("oil", new Property<Units.Volume>(30, Units.Volume.mL));
		Combine c = new Combine(1, "mix");
		Heat h = new Heat(2, "heat");

		e.addInput(water);
		e.addInput(oil);

		// Add to combine instruction
		c.addInput(water);
		c.addInput(oil);

		// Execute the instruction
		c.execute();

		h.addInputs(c.getOutputs());

		e.execute();


		e.addInstruction(h);*/

		//TypeSystem.parse("/Users/jason/Projects/IntelliJ/ChemTrails/src/main/resources/json_tests/typesystem_test.json");
		GsonBuilder gson = new GsonBuilder();
		

	}
}
