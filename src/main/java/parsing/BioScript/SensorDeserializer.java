package parsing.BioScript;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import variable.Sensor;

import java.lang.reflect.Type;

/**
 * Created by chriscurtis on 10/6/16.
 */
public class SensorDeserializer extends Deserializer<Sensor> {
    public static final Logger logger = LogManager.getLogger(SensorDeserializer.class);

    public Sensor deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject obj = jsonElement.getAsJsonObject().getAsJsonObject(SENSOR_DECLARATION);

        if(obj != null) {
            return new Sensor(obj.get(ID).getAsString(), obj.get(NAME).getAsString());
        }

        // {"SENSOR_DECLARATION":{"ID":"volumeWight","NAME":"volumeWight","TYPE":"SENSOR"}}
        // {"INPUT_TYPE":"VARIABLE","VARIABLE":{"NAME":"PCRMix"}}
        // {"INPUT_TYPE":"VARIABLE","CHEMICAL":{"VARIABLE":{"NAME":"PCRMasterMix"},"VOLUME":{"VALUE":"50","UNITS":"UL"}}}
        return null;
    }
}
