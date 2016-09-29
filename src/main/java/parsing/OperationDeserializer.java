package parsing;

import com.google.gson.*;
import executable.instructions.*;
import substance.Substance;

import java.lang.reflect.Type;

/**
 * Created by jason on 2016/09/29.
 */
public class OperationDeserializer extends Deserializer<Instruction> {

	public Instruction deserialize(JsonElement jsonElement, Type type,
	                               JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject obj = jsonElement.getAsJsonObject();
		String classification = obj.get(CLASSIFICATION).getAsString();

		String name = obj.get(SubstanceDeserializer.NAME).getAsString();
		int id = obj.get(SubstanceDeserializer.ID).getAsInt();
		Instruction instruction;
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

		if(obj.has(INPUTS)) {
			for(JsonElement elem : obj.get(INPUTS).getAsJsonArray()) {
				instruction.addInput((Substance) jsonDeserializationContext.deserialize(elem, Substance.class));
			}
		}

		if(obj.has(OUTPUTS)) {
			for(JsonElement elem : obj.get(OUTPUTS).getAsJsonArray()) {
				instruction.addOutput((Substance) jsonDeserializationContext.deserialize(elem, Substance.class));
			}
		}
		return instruction;
	}
}
