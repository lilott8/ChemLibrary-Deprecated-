package parsing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import substance.Property;
import substance.Substance;
import substance.Units;
import variable.Instance;
import variable.Reference;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Type;
import java.util.jar.Attributes;

/**
 * Created by chriscurtis on 10/6/16.
 */
public class ReferenceDeserializer extends Deserializer<Reference> {

    public static final Logger logger = LogManager.getLogger(ReferenceDeserializer.class);

    public Reference deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj;
        if (jsonElement.getAsJsonObject().has(DECLARATION))
            obj = jsonElement.getAsJsonObject().getAsJsonObject(DECLARATION);
        else
            obj = jsonElement.getAsJsonObject();

        List<Substance> substances = new ArrayList<Substance>();

        if (obj.has(VARIABLE)) {
            JsonObject referenceObject = obj.getAsJsonObject(VARIABLE);
            Property<Units.Volume> volume = extractVolumeProperty(referenceObject);

            if(volume == null)
                volume = new Property<Units.Volume>(-1, Units.Volume.nL);

            Substance substance = new Substance(-1,referenceObject.get(NAME).getAsString(),volume,CHEMICAL);
            substances.add(substance);

            String nameID;
            if (jsonElement.getAsJsonObject().has(DECLARATION))
                nameID = obj.get(NAME).getAsString();
            else
                nameID = referenceObject.get(NAME).getAsString();

            return new Reference(nameID,nameID,substances);
        }

        logger.warn("Reference: \"" +obj.get(NAME).getAsString() + "\" was not able to be converted");
        return null;
    }
}
