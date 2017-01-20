package parsing.BioScript;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.JsonElement;
import substance.Chemical;
import substance.Property;
import substance.Units;
import variable.Instance;

import java.lang.reflect.Type;

/**
 * Created by chriscurtis on 10/6/16.
 */
public class InstanceDeserializer extends Deserializer<Instance> {
    public static final Logger logger = LogManager.getLogger(InstanceDeserializer.class);

    public Instance deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject obj = jsonElement.getAsJsonObject().getAsJsonObject(VARIABLE_DECLARATION);

        // {"VARIABLE_DECLARATION":{"ID":"PCRMasterMix","NAME":"PCRMasterMix","TYPE":"CHEMICAL"}}
        // {"INPUT_TYPE":"VARIABLE","VARIABLE":{"NAME":"PCRMix"}}
        // {"INPUT_TYPE":"VARIABLE","CHEMICAL":{"VARIABLE":{"NAME":"PCRMasterMix"},"VOLUME":{"VALUE":"50","UNITS":"UL"}}}
        if(obj != null) {

            return new Instance(obj.get("ID").getAsString(), obj.get("NAME").getAsString());
        }
        obj = jsonElement.getAsJsonObject();
        if(obj.has(CHEMICAL)){
            String ChemType = obj.get(INPUT_TYPE).getAsString();
            if (ChemType.equals(VARIABLE))
                ChemType = CHEMICAL;
            JsonObject chemcialObject = obj.getAsJsonObject(CHEMICAL);
           // JsonElement volumeElement = chemcialObject.get(VOLUME);

            Property volume = (Property) jsonDeserializationContext.deserialize(chemcialObject, Property.class);
            String chemicalName = chemcialObject.get(VARIABLE).getAsJsonObject().get(NAME).getAsString();


            Chemical chemical = new Chemical(chemicalName, volume);
            return new Instance(chemicalName, chemicalName, chemical);
        }
        else if(obj.has(STATIONARY) || obj.has(VARIABLE)){
            String ChemType = CHEMICAL;
            Property<Units.Volume> volume = null;
            String ChemName = "";
            if(obj.has(STATIONARY))
                ChemName = obj.getAsJsonObject(STATIONARY).get("NAME").getAsString();
            else if(obj.has(VARIABLE))
                ChemName = obj.getAsJsonObject(VARIABLE).get("NAME").getAsString();

            //substances.add(new Substance(-1, ChemName, volume, ChemType));
            Instance instance = new Instance(ChemName,ChemName);
            if(obj.has(STATIONARY))
                instance.setIsStationary(true);
            return instance;
        }


        /*
        LEGACY CODE
        if (obj != null) {
            if (obj.has(CHEMICAL)) {
                JsonObject chemcialObject = obj.getAsJsonObject(CHEMICAL);
                Property<Units.Volume> volume = this.extractVolumeProperty(chemcialObject);

                substances.add(new Substance(-1, chemcialObject.get(NAME).getAsString(), volume, obj.get(TYPE).getAsString()));
                return new Instance(obj.get("ID").getAsString(), obj.get("NAME").getAsString(), substances);
            } else if (obj.has(CHEMICALS)) {
                JsonArray chemicalListObject = obj.get(CHEMICALS).getAsJsonArray();
                for (JsonElement chemcialElement : chemicalListObject) {
                    JsonObject chemcialObject = null;
                    if (chemcialElement.getAsJsonObject().has(CHEMICAL)) {
                        chemcialObject = chemcialElement.getAsJsonObject().getAsJsonObject(CHEMICAL);
                    } else if (chemcialElement.getAsJsonObject().has(VARIABLE)) {
                        chemcialObject = chemcialElement.getAsJsonObject().getAsJsonObject(VARIABLE);
                    } else if (chemcialElement.getAsJsonObject().has(CHEMICALS)) {
                        logger.fatal("A compound should never be a child of a compound.");
                    }

                    if (chemcialObject == null)
                        logger.fatal("Could not find proper deserializer for:" + chemcialElement.toString());


                    Property<Units.Volume> volume = this.extractVolumeProperty(chemcialObject);

                    substances.add(new Substance(-1, chemcialObject.get(NAME).getAsString(), volume, obj.get(TYPE).getAsString()));
                }

                return new Instance(obj.get("ID").getAsString(), obj.get("NAME").getAsString(), substances);

            } else if (obj.has(VARIABLE)) {
                logger.warn("Reference object being deserializer in the Instance deserializer.");

            } else if (obj.has(TYPE)) {
                String objType = obj.get(TYPE).getAsString();
                if (objType.equals(VARIABLE)) {
                    return new Instance(obj.get("ID").getAsString(), obj.get("ID").getAsString(), substances);
                }
                else if(objType.equals(STATIONARY)) {
                    Instance instance  = new Instance(obj.get("ID").getAsString(),obj.get("ID").getAsString(), substances);
                    instance.setIsStationary(true);

                    return instance;
                }
                else if(objType.equals(SENSOR)){

                }

            }
        }
        obj = jsonElement.getAsJsonObject();
        if(obj.has(CHEMICAL)){
            String ChemType = obj.get(INPUT_TYPE).getAsString();
            if (ChemType.equals(VARIABLE))
                ChemType = CHEMICAL;
            JsonObject chemcialObject = obj.getAsJsonObject(CHEMICAL);
            Property<Units.Volume> volume = this.extractVolumeProperty(chemcialObject);

            String ChemName = chemcialObject.get(NAME).getAsString();


            substances.add(new Substance(-1, ChemName, volume, ChemType));
            return new Instance(ChemName,ChemName,substances);

        }
        else if(obj.has(STATIONARY) || obj.has(VARIABLE)){
            String ChemType = CHEMICAL;
            Property<Units.Volume> volume = null;
            String ChemName = "";
            if(obj.has(STATIONARY))
                ChemName = obj.getAsJsonObject(STATIONARY).get("NAME").getAsString();
            else if(obj.has(VARIABLE))
                ChemName = obj.getAsJsonObject(VARIABLE).get("NAME").getAsString();

            substances.add(new Substance(-1, ChemName, volume, ChemType));
            Instance instance = new Instance(ChemName,ChemName,substances);
            if(obj.has(STATIONARY))
                instance.setIsStationary(true);
            return instance;
        }

*/
       logger.warn("Instance: \"" + obj.toString() + "\" was not able to be converted");
       return null;

    }
}
