package parsing;

import com.google.gson.*;
import executable.Executable;
import executable.Subroutine;
import executable.instructions.Instruction;
import substance.Substance;

import java.lang.reflect.Type;

/**
 * Created by jason on 2016/09/29.
 */
public class SubroutineDeserializer extends Deserializer<Executable> {

	public Executable deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject obj = jsonElement.getAsJsonObject();

		String name = obj.get(NAME).getAsString();
		int id = -1;
		if(obj.has(ID)) {
			id = obj.get(ID).getAsInt();
		}
		Subroutine subroutine = new Subroutine(id, name);

		if(obj.has(INPUTS)) {
			for(JsonElement elem : obj.get(INPUTS).getAsJsonArray()) {
				subroutine.addInput((Substance) jsonDeserializationContext.deserialize(elem, Substance.class));
			}
		}

		if(obj.has(OUTPUTS)) {
			for(JsonElement elem : obj.get(OUTPUTS).getAsJsonArray()) {
				subroutine.addOutput((Substance) jsonDeserializationContext.deserialize(elem, Substance.class));
			}
		}

		if(obj.has(INSTRUCTIONS)) {
			for(JsonElement elem : obj.get(OUTPUTS).getAsJsonArray()) {
				subroutine.addInstruction((Instruction) jsonDeserializationContext.deserialize(elem, Instruction.class));
			}
		}

		return subroutine;
	}
}
