package parsing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import manager.TypeSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parsing.Deserializer;
import substance.Substance;
import variable.Instance;
import variable.Reference;
import variable.Variable;

import java.lang.reflect.Type;

/**
 * Created by jason on 2016/09/29.
 */
public class VariableDeserializer extends Deserializer<Variable> {

	public static final Logger logger = LogManager.getLogger(VariableDeserializer.class);

	public Variable deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject obj = jsonElement.getAsJsonObject().get(DECLARATION).getAsJsonObject();

		logger.debug(obj.toString());

		Variable var = null;

		if(obj.get(TYPE).getAsString().equalsIgnoreCase(CHEMICAL)) {
			var = new Instance(obj.get(ID).getAsString(), obj.get(NAME).getAsString(),
					(Substance) jsonDeserializationContext.deserialize(obj.get(CHEMICAL).getAsJsonObject(),
							Substance.class));
		} else if(obj.get(TYPE).getAsString().equalsIgnoreCase(REFERENCE)) {
			var = new Reference(obj.get(ID).getAsString(),
					obj.get(NAME).getAsString(),
					TypeSystem.INSTANCE.getVariable(obj.get("TEST").getAsString()));
		} else if(obj.get(TYPE).getAsString().equalsIgnoreCase(COMPOUND)) {

		} else {
			throw new UnsupportedOperationException("Cannot instantiate a " + obj.get(TYPE).getAsString());
		}

		return var;
	}
}
