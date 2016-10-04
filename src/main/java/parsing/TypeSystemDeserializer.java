package parsing;

import com.google.gson.*;
import executable.instructions.Instruction;
import manager.TypeSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import variable.Reference;
import variable.Variable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parses the JSON for a typesytem JSON request.
 */
public class TypeSystemDeserializer extends Deserializer<TypeSystem> {

	public static final Logger logger = LogManager.getLogger(TypeSystemDeserializer.class);

	public TypeSystem deserialize(JsonElement jsonElement, Type type,
	                            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

		JsonArray vars = jsonElement.getAsJsonObject()
		                            .get(TYPESYTEM).getAsJsonObject()
		                            .get(VARIABLES).getAsJsonArray();

		JsonArray ops = jsonElement.getAsJsonObject()
		                           .get(TYPESYTEM).getAsJsonObject()
		                           .get(OPERATIONS).getAsJsonArray();

		Map<String, ArrayList<JsonObject>> types = new HashMap<String, ArrayList<JsonObject>>();
		types.put(CHEMICAL, new ArrayList<JsonObject>());
		types.put(REFERENCE, new ArrayList<JsonObject>());
		types.put(COMPOUND, new ArrayList<JsonObject>());

		// We must work through the instances first

		JsonObject temp;
		for(JsonElement elem : vars) {
			temp = elem.getAsJsonObject().get(DECLARATION).getAsJsonObject();

			if(temp.get(TYPE).getAsString().equalsIgnoreCase(CHEMICAL)) {
				types.get(CHEMICAL).add(temp);
			} else if(temp.get(TYPE).getAsString().equalsIgnoreCase(REFERENCE)) {
				types.get(REFERENCE).add(temp);
			} else if(temp.get(TYPE).getAsString().equalsIgnoreCase(COMPOUND)) {
				for(JsonElement e : temp.get(CHEMICALS).getAsJsonArray()) {

				}
				types.get(COMPOUND).add(temp);
			}
		}

		for(JsonElement elem : ops) {
			//Instruction i = jsonDeserializationContext.deserialize(elem, Instruction.class);
			//TypeSystem.INSTANCE.addOperation(i);
			//logger.debug(elem);
		}

		return TypeSystem.INSTANCE;
	}
}
