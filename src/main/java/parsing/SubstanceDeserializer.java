package parsing;

import com.google.gson.*;
import substance.Chemical;
import substance.Substance;
import substance.Property;
import substance.Units;

import java.lang.reflect.Type;

/**
 * Created by jason on 2016/09/29.
 */
public class SubstanceDeserializer extends Deserializer<Substance> {

	public Substance deserialize(JsonElement jsonElement, Type type,
	                             JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		//Gson gson = new Gson();
		JsonObject obj = jsonElement.getAsJsonObject();
		int id = -1;
		if(obj.has(ID)) {
			id = obj.get(ID).getAsInt();
		}
		String name = obj.get(NAME).getAsString();
		JsonObject volume = obj.get(VOLUME).getAsJsonObject();
		Property<Units.Volume> property = new Property<Units.Volume>(volume.get(VALUE).getAsFloat(),
				Units.Volume.valueOf(volume.get(UNITS).getAsString()));
		Substance s = new Substance(id, name, property, obj.getAsString());
		if(s.getMixtureType() == Substance.Synthetic.CHEMICAL) {
			return s;
		} else{
			if(obj.has(CHEMICALS)) {
				for(JsonElement elem : obj.get(CHEMICALS).getAsJsonArray()) {
					s.addChemical((Chemical) jsonDeserializationContext.deserialize(elem, Chemical.class));
				}
			}
			return s;
		}
	}
}
