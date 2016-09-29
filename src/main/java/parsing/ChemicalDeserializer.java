package parsing;

import com.google.gson.*;
import substance.Chemical;
import substance.Property;
import substance.Units;

import java.lang.reflect.Type;

/**
 * Created by jason on 2016/09/29.
 */
public class ChemicalDeserializer extends Deserializer<Chemical> {

	public Chemical deserialize(JsonElement jsonElement, Type type,
	                            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject obj = jsonElement.getAsJsonObject();

		int id = -1;
		if(obj.has(ID)) {
			id = obj.get(ID).getAsInt();
		}
		String name = obj.get(NAME).getAsString();
		JsonObject volume = obj.get(VOLUME).getAsJsonObject();
		Property<Units.Volume> property =
				new Property<Units.Volume>(volume.get(VALUE).getAsFloat(),
				Units.Volume.valueOf(volume.get(UNITS).getAsString()));

		return new Chemical(id, name, property);
	}
}
