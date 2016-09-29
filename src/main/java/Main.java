import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import executable.Experiment;
import executable.Subroutine;
import executable.instructions.Instruction;
import manager.Benchtop;
import manager.TypeSystem;
import org.reflections.Reflections;
import parsing.*;
import substance.Chemical;
import substance.Property;
import substance.Substance;
import substance.Units;
import variable.Variable;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by jason on 2016/09/21.
 */
public class Main {

	public static void main(String... args) throws Exception {
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

		GsonBuilder gsonBuilder = new GsonBuilder();

		gsonBuilder.registerTypeAdapter(Chemical.class, new ChemicalDeserializer());
		gsonBuilder.registerTypeAdapter(Experiment.class, new ExperimentDeserializer());
		gsonBuilder.registerTypeAdapter(Variable.class, new VariableDeserializer());
		gsonBuilder.registerTypeAdapter(Substance.class, new SubstanceDeserializer());
		gsonBuilder.registerTypeAdapter(Subroutine.class, new SubroutineDeserializer());
		gsonBuilder.registerTypeAdapter(Instruction.class, new OperationDeserializer());
		gsonBuilder.registerTypeAdapter(TypeSystem.class, new TypeSystemDeserializer());
		gsonBuilder.registerTypeAdapter(Benchtop.class, new BenchtopDeserializer());
		//gsonBuilder.registerTypeAdapter();
		//gsonBuilder.registerTypeAdapter();

		Gson gson = gsonBuilder.setPrettyPrinting().create();
		gson.fromJson(new FileReader("/Users/jason/Projects/IntelliJ/ChemTrails/src/main/resources/json_tests/test1.json"), Benchtop.class);
		System.out.println(Benchtop.INSTANCE.toString());

		//Substance s = new Substance(-1, "water", new Property(10, Units.Volume.cL), "chemical");
	}
}
