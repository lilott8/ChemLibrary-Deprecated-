package parsing.BioScript;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import executable.Experiment;
import executable.Subroutine;
import executable.instructions.Instruction;
import manager.Benchtop;
import manager.TypeSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import substance.Chemical;
import substance.Substance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by jason on 2016/09/30.
 */
public class TypeSystemParser {
	public static final Logger logger = LogManager.getLogger(BenchtopParser.class);

	public static void parse(String path) throws FileNotFoundException {
		GsonBuilder gsonBuilder = new GsonBuilder();

		gsonBuilder.registerTypeAdapter(Chemical.class, new ChemicalDeserializer());
		gsonBuilder.registerTypeAdapter(Experiment.class, new ExperimentDeserializer());
		gsonBuilder.registerTypeAdapter(Substance.class, new SubstanceDeserializer());
		gsonBuilder.registerTypeAdapter(Subroutine.class, new SubroutineDeserializer());
		gsonBuilder.registerTypeAdapter(Instruction.class, new OperationDeserializer());
		gsonBuilder.registerTypeAdapter(TypeSystem.class, new TypeSystemDeserializer());
		//gsonBuilder.registerTypeAdapter(Variable.class, new VariableDeserializer());

		Gson gson = gsonBuilder.setPrettyPrinting().create();
		if(isFile(path)) {
			FileReader fr = new FileReader(path);
			gson.fromJson(fr, Benchtop.class);
		} else {
			gson.fromJson(path, Benchtop.class);
		}
	}

	public static boolean isFile(String path) {
		File test = new File(path);
		return test.exists();
	}
}
