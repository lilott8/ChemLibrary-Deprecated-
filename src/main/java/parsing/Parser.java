package parsing;

import benchtop.Benchtop;
import com.google.gson.*;
import common.Property;
import common.Units;
import executable.Executable;
import executable.Experiment;
import executable.Subroutine;
import executable.instructions.Combine;
import executable.instructions.Instruction;
import executable.instructions.Split;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import variables.Chemical;
import variables.Compound;
import variables.Sensor;
import variables.Variable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jason on 2016/09/22.
 */
public class Parser {
	
	public static final Logger logger = LogManager.getLogger(Parser.class);

	/**
	 * Convenience wrapper for commonly named parser function
	 * @param json
	 * @return
	 */
	public static boolean parse(String json) {
		return addBenchtop(json);
	}

	/**
	 * Parse an entire benchtop
	 * @param json
	 * @return
	 */
	public static boolean addBenchtop(String json) {
		if(isFile(json)) {
			try {
				json = getFile(json);
			} catch(FileNotFoundException e) {
				logger.error("Cannot parse the file: " + json);
				logger.error(e.toString());
				return false;
			}
		}
		// our gson object to parse
		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
		if(jsonObject.has("purpose")) {
			logger.info(jsonObject.get("purpose").getAsString());
		}

		if(jsonObject.has("benchtop")) {
			jsonObject = jsonObject.get("benchtop").getAsJsonObject();
			if(jsonObject.has("inputs")) {
				Benchtop.INSTANCE.addInputs(addVariable(jsonObject.get("inputs").getAsJsonArray()));
			} else {
				logger.warn("There are no benchtop inputs defined");
			}
			if(jsonObject.has("experiments")) {
				Benchtop.INSTANCE.addExperiments(addExperiment(jsonObject.get("experiments").getAsJsonArray()));
			} else {
				logger.warn("there are no benchtop experiments to run");
			}
			if(jsonObject.has("instructions")) {
				Benchtop.INSTANCE.addInstructions(addOperation(jsonObject.get("instructions").getAsJsonArray()));
			}
			if(jsonObject.has("subroutines")) {
				Benchtop.INSTANCE.addInstructions(addOperation(jsonObject.get("subroutines").getAsJsonArray()));
			}
		} else {
			logger.fatal("There is no benchtop defined!");
			return false;
		}
		return true;
	}

	/**
	 * Add an experiment to the benchtop
	 * @param json
	 * @return
	 */
	public static List<Experiment> addExperiment(String json) {
		return addExperiment(new JsonParser().parse(json).getAsJsonArray());
	}

	public static List<Experiment> addExperiment(JsonArray jsonArray) {
		List<Experiment> results = new ArrayList<Experiment>();

		for(JsonElement elem : jsonArray) {
			JsonObject jsonObject = elem.getAsJsonObject().get("experiment").getAsJsonObject();
			List<Variable> inputs;
			List<Subroutine> subroutines;
			List<Variable> outputs;
			List<Instruction> instructions;
			String name;
			if(jsonObject.has("name")) {
				name = jsonObject.get("name").getAsString();
			} else {
				name = "Default";
			}
			Experiment e = new Experiment(name);
			if(jsonObject.has("inputs")) {
				e.addInputs(addVariable(jsonObject.getAsJsonArray("inputs")));
			}
			if(jsonObject.has("instructions")) {
				e.addInstructions(addOperation(jsonObject.getAsJsonArray("instructions")));
			}
			/*if(jsonObject.has("output")) {
				e.addOutputs(addVariable(jsonObject.getAsJsonArray("output")));
			}*/
			if(jsonObject.has("subroutines")) {
				e.addInstructions(addSubroutine(jsonObject.getAsJsonArray("subroutine")));
			}
			results.add(e);
		}

		return results;
	}

	/**
	 * Add a subroutine to the experiment
	 * @param jsonArray
	 * @return
	 */
	public static List<Executable> addSubroutine(JsonArray jsonArray) {
		List<Executable> results = new ArrayList<Executable>();

		Subroutine subroutine;
		for(JsonElement elem : jsonArray) {
			JsonObject jsonObject = elem.getAsJsonObject().get("subroutine").getAsJsonObject();
			String name = jsonObject.get("name").getAsString();
			subroutine = new Subroutine(name);
			if(jsonObject.has("inputs")) {
				subroutine.addInputs(addVariable(jsonObject.get("inputs").getAsJsonArray()));
			}
			if(jsonObject.has("instructions")) {
				subroutine.addInstructions(addOperation(jsonObject.get("instructions").getAsJsonArray()));
			}
			/*if(jsonObject.has("outputs")) {
				subroutine.addOutputs(addVariable(jsonObject.get("outputs").getAsJsonArray()));
			}*/
			results.add(subroutine);
		}

		return results;
	}

	public static List<Executable> addSubroutine(String json) {
		return addSubroutine(new JsonParser().parse(json).getAsJsonArray());
	}

	/**
	 * add an operation to the current experiment
	 * @param jsonArray
	 * @return
	 */
	public static List<Executable> addOperation(JsonArray jsonArray) {
		List<Executable> results = new ArrayList<Executable>();
		Instruction instruction;
		for(JsonElement elem : jsonArray) {
			JsonObject jsonObject = elem.getAsJsonObject().get("operation").getAsJsonObject();
			String classification = jsonObject.get("classification").getAsString();
			String name = jsonObject.get("name").getAsString();
			int id = jsonObject.get("id").getAsInt();
			if(StringUtils.equalsIgnoreCase(classification, "mix")) {
				instruction = new Combine(name, Combine.class, id);
				if(jsonObject.has("inputs")) {
					instruction.addInputs(addVariable(jsonObject.get("inputs").getAsJsonArray()));
				}
			} else if(StringUtils.equalsIgnoreCase(classification, "split")) {
				instruction = new Split(name, Split.class, id);
				if(jsonObject.has("inputs")) {
					instruction.addInputs(addVariable(jsonObject.get("inputs").getAsJsonArray()));
				}
			} else {
				throw new NotImplementedException("No other instructions have been created");
			}
			results.add(instruction);
		}
		return results;
	}

	public static List<Executable> addOperation(String json) {
		return addOperation(new JsonParser().parse(json).getAsJsonArray());
	}


	/**
	 * Add a variable to the current experiment
	 * @param jsonArray
	 * @return list of created variables
	 */
	public static List<Variable> addVariable(JsonArray jsonArray) {
		List<Variable> results = new ArrayList<Variable>();

		for(JsonElement elem : jsonArray) {
			JsonObject jsonObject = elem.getAsJsonObject();
			String name;
			if(jsonObject.has("chemical")) {
				jsonObject = jsonObject.get("chemical").getAsJsonObject();
				name = jsonObject.get("name").getAsString();
				float value = jsonObject.get("volume").getAsJsonObject().get("value").getAsFloat();
				Units.Volume vol = Units.Volume.valueOf(jsonObject.get("volume")
				                                                  .getAsJsonObject().get("units").getAsString());
				results.add(new Chemical(name, new Property<Units.Volume>(value, vol)));
			} else if(jsonObject.has("sensor")) {
				jsonObject = jsonObject.get("sensor").getAsJsonObject();
				name = jsonObject.get("name").getAsString();
				results.add(new Sensor(name));
			} else if(jsonObject.has("compound")) {
				jsonObject = jsonObject.get("compound").getAsJsonObject();
				Compound compound = new Compound(jsonObject.get("name").getAsString());
				if(jsonObject.has("chemical_list")) {
					compound.addChemicals(addVariable(jsonObject.get("chemical_list").getAsJsonArray()));
				}
				compound.generateProperty();
				results.add(compound);

				// throw new NotImplementedException("Compound not implemented yet");
			} else {
				throw new JsonSyntaxException("Unknown key: " + jsonObject.toString());
			}
		}

		return results;
	}

	/**
	 * Add a variable to the current experiment
	 * @param json
	 * @return
	 */
	public static List<Variable> addVariable(String json) {
		return addVariable(new JsonParser().parse(json).getAsJsonArray());
	}

	/**
	 * Helper function to see if the input is a file path or json string
	 * @param path
	 * @return
	 */
	private static boolean isFile(String path) {
		File test = new File(path);
		return test.exists();
	}

	private static String getFile(String path) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(path));
		String json = scanner.useDelimiter("\\A").next();
		scanner.close();
		return json;
	}
}
