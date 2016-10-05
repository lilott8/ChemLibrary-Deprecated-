package parsing.BioScript;

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

		JsonObject obj = jsonElement.getAsJsonObject();
		Substance.Synthetic syn;

		if(obj.has(CHEMICAL)) {
			syn = Substance.Synthetic.CHEMICAL;
			obj = obj.get(CHEMICAL).getAsJsonObject();
		} else {
			syn = Substance.Synthetic.COMPOUND;
			obj = obj.get(COMPOUND).getAsJsonObject();
		}

		int id = obj.has(ID) ? obj.get(ID).getAsInt() : -1;


		String name = obj.get(NAME).getAsString();


		Property<Units.Volume> property = new Property<Units.Volume>(-1, Units.Volume.hL);
		if(syn == Substance.Synthetic.CHEMICAL) {
			JsonObject volume = obj.get(VOLUME).getAsJsonObject();
			property = new Property<Units.Volume>(volume.get(VALUE).getAsFloat(),
					Units.Volume.valueOf(volume.get(UNITS).getAsString()));
		}
		Substance s = new Substance(id, name, property, syn);
		// Only go here if it's a compound
		if(obj.has(LIST)) {
			for(JsonElement elem : obj.get(LIST).getAsJsonArray()) {
				s.addChemical((Chemical) jsonDeserializationContext.deserialize(elem, Chemical.class));
			}
		}
		return s;
	}
}
