package parsing.BioScript;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import executable.Experiment;
import executable.Subroutine;
import executable.instructions.Instruction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import variable.Instance;
import variable.Reference;

import java.lang.reflect.Type;

/**
 * Created by jason on 2016/09/29.
 */
public class ExperimentDeserializer extends Deserializer<Experiment> {

	public static final Logger logger = LogManager.getLogger(ExperimentDeserializer.class);

	public Experiment deserialize(JsonElement jsonElement, Type type,
	                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

		JsonObject obj = jsonElement.getAsJsonObject().get(EXPERIMENT).getAsJsonObject();

		String name;
		Experiment experiment;
		int id = obj.has(ID) ? obj.get(ID).getAsInt() : -1;

		name = obj.get(NAME).getAsString();
		experiment = new Experiment(id, name);

		if(obj.has(INPUTS)) {
			//logger.info("Starting inputs");
			for(JsonElement e : obj.get(INPUTS).getAsJsonArray()) {
				if(e.getAsJsonObject().has(VARIABLE_DECLARATION)){
					if (! e.getAsJsonObject().getAsJsonObject(VARIABLE_DECLARATION).has(VARIABLE))
						if(e.getAsJsonObject().getAsJsonObject(VARIABLE_DECLARATION).has(TYPE) &&
								(e.getAsJsonObject().getAsJsonObject(VARIABLE_DECLARATION).get(TYPE).getAsString().equals("CHEMICAL") ||
                                        e.getAsJsonObject().getAsJsonObject(VARIABLE_DECLARATION).get(TYPE).getAsString().equals("STATIONARY")))
							experiment.addInput((Instance) jsonDeserializationContext.deserialize(e, Instance.class));
					else
						experiment.addInput((Reference) jsonDeserializationContext.deserialize(e, Reference.class));
				}
			}
			//logger.info("Finished Inputs");
		}

		if(obj.has(INSTRUCTIONS)) {
			for(JsonElement e : obj.get(INSTRUCTIONS).getAsJsonArray()) {
				experiment.addInstruction((Instruction) jsonDeserializationContext.deserialize(e, Instruction.class));
			}
		}

		if(obj.has(SUBROUTINES)) {
			for(JsonElement e : obj.get(SUBROUTINES).getAsJsonArray()) {
				experiment.addInstruction((Subroutine) jsonDeserializationContext.deserialize(e, Subroutine.class));
			}
		}
		return experiment;
	}
}
