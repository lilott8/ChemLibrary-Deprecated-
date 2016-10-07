package parsing;

import com.google.gson.*;
import executable.instructions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import substance.Substance;
import variable.Instance;
import variable.Reference;
import variable.Variable;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.Type;

/**
 * Created by jason on 2016/09/29.
 */
public class OperationDeserializer extends Deserializer<Instruction> {

	public static final Logger logger = LogManager.getLogger(OperationDeserializer.class);

	public Instruction deserialize(JsonElement jsonElement, Type type,
	                               JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

		JsonObject obj = jsonElement.getAsJsonObject().get(OPERATION).getAsJsonObject();

		String classification = obj.get(CLASSIFICATION).getAsString();

		String name = obj.get(SubstanceDeserializer.NAME).getAsString();
		String IDString = (obj.get(SubstanceDeserializer.ID).getAsString());

		Long id = Long.parseLong(IDString);
		Instruction instruction;
		if(classification.toLowerCase().equals("mix")) {
			instruction = new Combine(id, name);
		} else if(classification.toLowerCase().equals("split")) {
			instruction = new Split(id, name);
		} else if(classification.toLowerCase().equals("detect")) {
			instruction = new Detect(id, name);
		} else if(classification.toLowerCase().equals("heat")) {
			instruction = new Heat(id, name);
		} else if(classification.toLowerCase().equals("output")) {
			instruction = new Output(id, name);
		} else if(classification.toLowerCase().equals("store")) {
			instruction = new Store(id, name);
		} else if(classification.toLowerCase().equals("dispense")) {
			instruction = new Dispense(id, name);
		} else {
			throw new UnsupportedOperationException("No other instructions have been created");
		}

		if(obj.has(INPUTS)) {
			for(JsonElement elem : obj.get(INPUTS).getAsJsonArray()) {
				if (elem.getAsJsonObject().has(INPUT_TYPE)) {

					if ((elem.getAsJsonObject().get(INPUT_TYPE).getAsString()).equals(VARIABLE)) {
						instruction.addInput((Reference) jsonDeserializationContext.deserialize(elem, Reference.class));
					}
					else if ((elem.getAsJsonObject().get(INPUT_TYPE).getAsString()).equals(CHEMICAL)) {
						instruction.addInput((Instance) jsonDeserializationContext.deserialize(elem, Instance.class));
					}
				}
				else {
					logger.fatal("No Input Type specified by: " + elem.toString());
				}
				//instruction.addInput((Variable) jsonDeserializationContext.deserialize(elem, Variable.class));
			}
		}

		if(obj.has(OUTPUTS)) {
			for(JsonElement elem : obj.get(OUTPUTS).getAsJsonArray()) {
				if(elem.getAsJsonObject().has(DECLARATION))
					instruction.addOutput((Instance) jsonDeserializationContext.deserialize(elem, Instance.class));
				else if(elem.getAsJsonObject().has(VARIABLE))
					instruction.addOutput((Reference) jsonDeserializationContext.deserialize(elem, Reference.class));
			}
		}
		return instruction;
	}
}
