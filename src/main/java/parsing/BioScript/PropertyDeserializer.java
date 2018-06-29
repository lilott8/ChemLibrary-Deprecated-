package parsing.BioScript;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import substance.Property;
import substance.Units;

/**
 * Created by jason on 2016/09/29.
 */
public class PropertyDeserializer extends Deserializer<Property> {

	public Property deserialize(JsonElement jsonElement, Type type,
	                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

		JsonObject obj = jsonElement.getAsJsonObject();

		float value = -1;
		String units = "";
		if(obj.has(TIME)) {
			JsonObject time = obj.get(TIME).getAsJsonObject();
			if(time.has(VALUE)){
				String temp = time.get(VALUE).getAsString();
				value = Float.parseFloat(temp);
			}
			if(time.has(UNITS)){
				units = time.get(UNITS).getAsString();
			}
			if(units.equals("SECOND"))
				return new Property<Units.Time>(value,Units.Time.SECOND);
			if(units.equals("MINUTE"))
				return new Property<Units.Time>(value,Units.Time.MINUTE);
			if(units.equals("HOUR"))
				return new Property<Units.Time>(value,Units.Time.HOUR);
			if(units.equals("DAY"))
				return new Property<Units.Time>(value,Units.Time.DAY);
			if(units.equals("MILLISECOND"))
				return new Property<Units.Time>(value,Units.Time.MILLISECOND);
			if(units.equals("MICROSECOND"))
				return new Property<Units.Time>(value,Units.Time.MICROSECOND);
		}
		if(obj.has(TEMPERATURE)){
			JsonObject time = obj.get(TEMPERATURE).getAsJsonObject();
			if(time.has(VALUE)){
				String temp = time.get(VALUE).getAsString();
				value = Float.parseFloat(temp);
			}
			if(time.has(UNITS)){
				units = time.get(UNITS).getAsString();
			}
			if(units.equals("CELSIUS"))
				return new Property<Units.Temperature>(value,Units.Temperature.C);
			if(units.equals("KELVIN"))
				return new Property<Units.Temperature>(value,Units.Temperature.K);
			if(units.equals("FAHRENHEIT"))
				return new Property<Units.Temperature>(value,Units.Temperature.F);
		}
		if(obj.has(VOLUME)) {
			JsonObject time = obj.get(VOLUME).getAsJsonObject();
			if(time.has(VALUE)){
				String temp = time.get(VALUE).getAsString();
				value = Float.parseFloat(temp);
			}
			if(time.has(UNITS)){
				units = time.get(UNITS).getAsString();
			}
			if(units.equals("ML"))
				return new Property<Units.Volume>(value,Units.Volume.mL);
			if(units.equals("UL"))
				return new Property<Units.Volume>(value,Units.Volume.uL);
			if(units.equals("NL"))
				return new Property<Units.Volume>(value,Units.Volume.nL);
			if(units.equals("LITER"))
				return new Property<Units.Volume>(value,Units.Volume.L);

			if(units.equals("KL"))
				return new Property<Units.Volume>(value,Units.Volume.kL);
			if(units.equals("DAL"))
				return new Property<Units.Volume>(value,Units.Volume.daL);
			if(units.equals("DL"))
				return new Property<Units.Volume>(value,Units.Volume.dL);
			if(units.equals("CL"))
				return new Property<Units.Volume>(value,Units.Volume.cL);
			if(units.equals("PL"))
				return new Property<Units.Volume>(value,Units.Volume.pL);
			if(units.equals("HL"))
				return new Property<Units.Volume>(value,Units.Volume.hL);
		}
		return null;
	}
}
