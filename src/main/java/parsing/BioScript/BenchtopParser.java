package parsing.BioScript;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import executable.Experiment;
import executable.Subroutine;
import executable.instructions.Instruction;
import manager.Benchtop;
import substance.Chemical;
import substance.Property;
import substance.Substance;
import variable.Instance;
import variable.Sensor;

/**
 * Created by jason on 2016/09/30.
 */
public class BenchtopParser {

	public static final Logger logger = LogManager.getLogger(BenchtopParser.class);

	private static GsonBuilder gsonBuilder = new GsonBuilder();

	static {
		gsonBuilder.registerTypeAdapter(Chemical.class, new ChemicalDeserializer());
		gsonBuilder.registerTypeAdapter(Experiment.class, new ExperimentDeserializer());
		gsonBuilder.registerTypeAdapter(Substance.class, new SubstanceDeserializer());
		gsonBuilder.registerTypeAdapter(Subroutine.class, new SubroutineDeserializer());
		gsonBuilder.registerTypeAdapter(Instruction.class, new OperationDeserializer());
		gsonBuilder.registerTypeAdapter(Benchtop.class, new BenchtopDeserializer());
		gsonBuilder.registerTypeAdapter(Instance.class, new InstanceDeserializer());
		gsonBuilder.registerTypeAdapter(Sensor.class, new SensorDeserializer());
		//gsonBuilder.registerTypeAdapter(Reference.class, new ReferenceDeserializer());
		gsonBuilder.registerTypeAdapter(Property.class, new PropertyDeserializer());

	}

	public static void parseFromFile(String path) throws FileNotFoundException {
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		if(isFile(path)) {
			FileReader fr = new FileReader(path);
			gson.fromJson(fr, Benchtop.class);
		} else {
			gson.fromJson(path, Benchtop.class);
		}
	}

	public static void parseFromString(String json) {
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		gson.fromJson(json, Benchtop.class);
	}


	public static boolean isFile(String path) {
		File test = new File(path);
		return test.exists();
	}
}
