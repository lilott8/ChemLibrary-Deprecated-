package parsing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import executable.Executable;
import executable.Experiment;
import executable.Subroutine;
import executable.instructions.Instruction;
import manager.Benchtop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import substance.Substance;
import variable.Instance;
import variable.Variable;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by jason on 2016/09/29.
 */
public class BenchtopDeserializer extends Deserializer<Benchtop> {

	public static final Logger logger = LogManager.getLogger(BenchtopDeserializer.class);

	public Benchtop deserialize(JsonElement jsonElement, Type type,
	                            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

		JsonObject obj = jsonElement.getAsJsonObject().get(BENCHTOP).getAsJsonObject();

		Benchtop.INSTANCE.setId(obj.has(ID) ? obj.get(ID).getAsInt() : -1);

		if(obj.has(INPUTS)) {
			for(JsonElement elem : obj.get(INPUTS).getAsJsonArray()) {
				Variable s = jsonDeserializationContext.deserialize(elem, Instance.class);
				if(s != null) {
					Benchtop.INSTANCE.addInput(s);
				}
			}
		} else {
			logger.error("There are no benchtop inputs defined");
		}
		if(obj.has(EXPERIMENTS)) {
			for(JsonElement elem : obj.get(EXPERIMENTS).getAsJsonArray()) {
				Experiment experi =(Experiment) jsonDeserializationContext.deserialize(elem, Experiment.class);
				Benchtop.INSTANCE.addExperiment(experi);
			}

		} else {
			logger.error("there are no benchtop experiments to run");
		}
		if(obj.has(INSTRUCTIONS)) {
			for(JsonElement elem : obj.get(INSTRUCTIONS).getAsJsonArray()) {
				Benchtop.INSTANCE.addInstruction((Executable) jsonDeserializationContext
						.deserialize(elem, OperationDeserializer.class));
			}
		}
		if(obj.has(SUBROUTINES)) {
			for(JsonElement elem : obj.get(SUBROUTINES).getAsJsonArray()) {
				Benchtop.INSTANCE.addInstruction((Subroutine) jsonDeserializationContext
						.deserialize(elem, SubroutineDeserializer.class));
			}
		}

		return Benchtop.INSTANCE;
	}
}
