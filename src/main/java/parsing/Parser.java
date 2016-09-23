package parsing;

import benchtop.Benchtop;
import com.google.gson.*;
import variables.Property;
import variables.Units;
import executable.Executable;
import executable.Experiment;
import executable.Subroutine;
import executable.instructions.*;
import variables.Chemical;
import variables.Compound;
import variables.Variable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by jason on 2016/09/22.
 */
public class Parser {

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
				System.err.println("Cannot parse the file: " + json);
				System.err.println(e.toString());
				return false;
			}
		}
		// our gson object to parse
		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
		if(jsonObject.has("purpose")) {
			System.out.println(jsonObject.get("purpose").getAsString());
		}

		if(jsonObject.has("benchtop")) {
			jsonObject = jsonObject.get("benchtop").getAsJsonObject();
			if(jsonObject.has("inputs")) {
				Benchtop.INSTANCE.addInputs(addVariable(jsonObject.get("inputs").getAsJsonArray()));
			} else {
				System.err.println("There are no benchtop inputs defined");
			}
			if(jsonObject.has("experiments")) {
				Benchtop.INSTANCE.addExperiments(addExperiment(jsonObject.get("experiments").getAsJsonArray()));
			} else {
				System.err.println("there are no benchtop experiments to run");
			}
			if(jsonObject.has("instructions")) {
				Benchtop.INSTANCE.addInstructions(addOperation(jsonObject.get("instructions").getAsJsonArray()));
			}
			if(jsonObject.has("subroutines")) {
				Benchtop.INSTANCE.addInstructions(addOperation(jsonObject.get("subroutines").getAsJsonArray()));
			}
		} else {
			System.err.println("There is no benchtop defined!");
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
			if(jsonObject.has("output")) {
				e.addOutputs(addVariable(jsonObject.getAsJsonArray("output")));
			}
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
			if(jsonObject.has("outputs")) {
				subroutine.addOutputs(addVariable(jsonObject.get("outputs").getAsJsonArray()));
			}
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
			if(classification.toLowerCase().equals("mix")) {
				instruction = new Combine(id, name);
			} else if(classification.toLowerCase().equals("split")) {
				instruction = new Split(id, name);
			} else if(classification.toLowerCase().equals("detect")) {
				instruction = new Detect(id, name);
			} else if(classification.toLowerCase().equals("heat")) {
				instruction = new Heat(id, name);
			} else if(classification.toLowerCase().equals("output")) {
				instruction = new Output(id, name);
			} else if(classification.toLowerCase().equals("store")) {
				instruction = new Store(id, name);
			} else if(classification.toLowerCase().equals("dispense")) {
				instruction = new Dispense(id, name);
			} else {
				throw new UnsupportedOperationException("No other instructions have been created");
			}

			if (jsonObject.has("inputs")) {
				instruction.addInputs(addVariable(jsonObject.get("inputs").getAsJsonArray()));
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
	public static Map<String, Variable> addVariable(JsonArray jsonArray) {
		Map<String, Variable> results = new HashMap<String, Variable>();

		Chemical chemical;
		Compound compound;
		Property property;
		for(JsonElement elem : jsonArray) {
			JsonObject jsonObject = elem.getAsJsonObject();
			String name;
			int id;
			if(jsonObject.has("chemical")) {
				jsonObject = jsonObject.get("chemical").getAsJsonObject();
				name = jsonObject.get("name").getAsString();
				property = addProperty(jsonObject.get("volume").getAsJsonObject());
				chemical = new Chemical(name, property);
				results.put(chemical.getName(), chemical);
			} else if(jsonObject.has("compound")) {
				jsonObject = jsonObject.get("compound").getAsJsonObject();
				property = addProperty(jsonObject.get("volume").getAsJsonObject());
				compound = new Compound(jsonObject.get("id").getAsInt(), jsonObject.get("name").getAsString(), property);
				if(jsonObject.has("chemical_list")) {
					compound.addChemicals(addVariable(jsonObject.get("chemical_list").getAsJsonArray()));
				}
				results.put(compound.getName(), compound);

				// throw new NotImplementedException("Compound not implemented yet");
			} else {
				throw new JsonSyntaxException("Unknown key: " + jsonObject.toString());
			}
		}

		return results;
	}

	public static Property addProperty(JsonObject jsonObject) {
		float value = jsonObject.get("volume").getAsJsonObject().get("value").getAsFloat();
		Units.Volume vol = Units.Volume.valueOf(jsonObject.get("volume")
		                                                  .getAsJsonObject().get("units").getAsString());
		return new Property<Units.Volume>(value, vol);
	}

	/**
	 * Add a variable to the current experiment
	 * @param json
	 * @return
	 */
	public static Map<String, Variable> addVariable(String json) {
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
