package parsing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import executable.Executable;
import executable.Experiment;
import executable.Subroutine;
import manager.Benchtop;
import substance.Substance;
import variable.Variable;

import java.lang.reflect.Type;

/**
 * Created by jason on 2016/09/29.
 */
public class BenchtopDeserializer extends Deserializer<Benchtop> {

	public Benchtop deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

		JsonObject obj = jsonElement.getAsJsonObject();

		if(obj.has(INPUTS)) {
			for(JsonElement elem : obj.get(INPUTS).getAsJsonArray()) {
				Benchtop.INSTANCE.addInput((Substance) jsonDeserializationContext
						.deserialize(elem, VariableDeserializer.class));
			}
		} else {
			System.err.println("There are no benchtop inputs defined");
		}
		if(obj.has(EXPERIMENTS)) {
			for(JsonElement elem : obj.get(EXPERIMENTS).getAsJsonArray()) {
				Benchtop.INSTANCE.addExperiment((Experiment) jsonDeserializationContext.deserialize(elem, ExperimentDeserializer.class));
			}
		} else {
			System.err.println("there are no benchtop experiments to run");
		}
		if(obj.has(INSTRUCTIONS)) {
			for(JsonElement elem : obj.get(INSTRUCTIONS).getAsJsonArray()) {
				Benchtop.INSTANCE.addInstruction((Executable) jsonDeserializationContext
						.deserialize(elem, OperationDeserializer.class));
			}
		}
		if(obj.has(SUBROUTINES)) {
			for(JsonElement elem : obj.get(SUBROUTINES).getAsJsonArray()) {
				Benchtop.INSTANCE.addInstruction((Subroutine) jsonDeserializationContext
						.deserialize(elem, SubroutineDeserializer.class));
			}
		}

		return Benchtop.INSTANCE;
	}
}
