package parsing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import executable.Experiment;
import executable.Subroutine;
import executable.instructions.Instruction;
import substance.Substance;

import java.lang.reflect.Type;

/**
 * Created by jason on 2016/09/29.
 */
public class ExperimentDeserializer extends Deserializer<Experiment> {

	public Experiment deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject obj = jsonElement.getAsJsonObject();

		String name;
		int id = -1;
		Experiment experiment;

		if(obj.has(ID)) {
			id = obj.get(ID).getAsInt();
		}
		name = obj.get(NAME).getAsString();
		experiment = new Experiment(id, name);

		if(obj.has(INPUTS)) {
			for(JsonElement e : obj.get(INPUTS).getAsJsonArray()) {
				experiment.addInput((Substance) jsonDeserializationContext.deserialize(e, SubstanceDeserializer.class));
			}
		}

		if(obj.has(INSTRUCTIONS)) {
			for(JsonElement e : obj.get(INSTRUCTIONS).getAsJsonArray()) {
				experiment.addInstruction((Instruction) jsonDeserializationContext.deserialize(e, OperationDeserializer.class));
			}
		}

		if(obj.has(SUBROUTINES)) {
			for(JsonElement e : obj.get(SUBROUTINES).getAsJsonArray()) {
				experiment.addInstruction((Subroutine) jsonDeserializationContext.deserialize(e, SubroutineDeserializer.class));
			}
		}
		return experiment;
	}
}
