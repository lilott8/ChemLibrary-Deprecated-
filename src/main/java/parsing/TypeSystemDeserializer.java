package parsing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import manager.TypeSystem;
import variable.Variable;

import java.lang.reflect.Type;

/**
 * Parses the JSON for a typesytem JSON request.
 */
public class TypeSystemDeserializer extends Deserializer<TypeSystem> {

	public TypeSystem deserialize(JsonElement jsonElement, Type type,
	                            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject obj = jsonElement.getAsJsonObject().get(TYPESYTEM)
		                            .getAsJsonObject().get(DECLARATION).getAsJsonObject();

		if(obj.has(VARIABLE)) {
			for(JsonElement elem : obj.get(VARIABLE).getAsJsonArray()) {
				TypeSystem.INSTANCE.addVariable((Variable) jsonDeserializationContext.deserialize(elem, VariableDeserializer.class));
			}
		}
		return TypeSystem.INSTANCE;
	}
}
