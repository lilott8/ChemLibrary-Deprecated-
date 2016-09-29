package parsing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import parsing.Deserializer;
import variable.Instance;
import variable.Reference;
import variable.Variable;

import java.lang.reflect.Type;

/**
 * Created by jason on 2016/09/29.
 */
public class VariableDeserializer extends Deserializer<Variable> {

	public Variable deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject obj = jsonElement.getAsJsonObject();

		Variable var = null;

		if(obj.get(TYPE).getAsString().equalsIgnoreCase(REFERENCE)) {
			var = new Reference();
		} else if(obj.get(TYPE).getAsString().equalsIgnoreCase(CHEMICAL)) {
			var = new Instance();
		} else {
			throw new UnsupportedOperationException("Cannot instantiate a " + obj.get(TYPE).getAsString());
		}

		return var;
	}
}
