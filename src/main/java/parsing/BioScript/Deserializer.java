package parsing.BioScript;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import substance.Property;
import substance.Substance;
import substance.Units;
import variable.Instance;
import variable.Reference;

/**
 * Created by jason on 2016/09/29.
 */

public abstract class Deserializer<T> implements JsonDeserializer<T> {
	protected static final String VOLUME = "VOLUME";
	protected static final String NAME = "NAME";
	protected static final String VALUE = "VALUE";
	protected static final String UNITS = "UNITS";
	protected static final String ID = "ID";
	protected static final String CHEMICALS = "CHEMICALS";
	protected static final String CLASSIFICATION = "CLASSIFICATION";
	protected static final String INPUTS = "INPUTS";
	protected static final String INPUT_TYPE = "INPUT_TYPE";
	protected static final String OUTPUTS = "OUTPUTS";
	protected static final String INSTRUCTIONS = "INSTRUCTIONS";
	protected static final String BENCHTOP = "BENCHTOP";
	protected static final String PURPOSE = "PURPOSE";
	protected static final String SUBROUTINES = "SUBROUTINES";
	protected static final String EXPERIMENTS = "EXPERIMENTS";
	protected static final String EXPERIMENT = "EXPERIMENT";
	protected static final String TYPESYTEM = "TYPESYSTEM";
	protected static final String DECLARATION = "VARIABLE_DECLARATION";
	protected static final String SUBSTANCE = "SUBSTANCE";
	protected static final String VARIABLES = "VARIABLES";
	protected static final String VARIABLE = "VARIABLE";
	protected static final String TYPE = "TYPE";
	protected static final String CHEMICAL = "CHEMICAL";
	protected static final String REFERENCE = "CHEMICAL_REFERENCE";
	protected static final String COMPOUND = "COMPOUND";
	protected static final String LIST = "CHEMICAL_LIST";
	protected static final String OPERATION = "OPERATION";
	protected static final String OPERATIONS = "OPERATIONS";
	protected static final String PROPERTY = "PROPERTY";
	protected static final String CONDITION = "CONDITION";
	protected static final String TRUE_BRANCH = "TRUE_BRANCH";
	protected static final String FALSE_BRANCH = "FALSE_BRANCH";
	protected static final String ELSEIF_BRANCH = "ELSEIF_BRANCH";
	protected static final String STATIONARY = "STATIONARY";
	protected static final String SENSOR = "SENSOR";
	protected static final String SENSOR_DECLARATION = "SENSOR_DECLARATION";
	protected static final String LOOP_NUM = "LOOP_NUM";

	public Deserializer() {}

	protected Property<Units.Volume> extractVolumeProperty(JsonObject volumeContainer) {
		if (volumeContainer == null || !volumeContainer.has(VOLUME))
			return null;

		JsonObject volumeObject = volumeContainer.getAsJsonObject("VOLUME");
		String units = volumeObject.get(UNITS).getAsString();

		if (units == "LITER")
			return new Property<Units.Volume>(Float.parseFloat(volumeObject.get(VALUE).getAsString()),Units.Volume.L);
		if (units == "ML")
			return new Property<Units.Volume>(Float.parseFloat(volumeObject.get(VALUE).getAsString()),Units.Volume.mL);
		if (units == "UL")
			return new Property<Units.Volume>(Float.parseFloat(volumeObject.get(VALUE).getAsString()),Units.Volume.uL);
		if (units == "NL")
			return new Property<Units.Volume>(Float.parseFloat(volumeObject.get(VALUE).getAsString()),Units.Volume.nL);
		if (units == "PL")
			return new Property<Units.Volume>(Float.parseFloat(volumeObject.get(VALUE).getAsString()),Units.Volume.pL);
		if (units == "CL")
			return new Property<Units.Volume>(Float.parseFloat(volumeObject.get(VALUE).getAsString()),Units.Volume.cL);
		if (units == "DAL")
			return new Property<Units.Volume>(Float.parseFloat(volumeObject.get(VALUE).getAsString()),Units.Volume.daL);
		if (units == "DL")
			return new Property<Units.Volume>(Float.parseFloat(volumeObject.get(VALUE).getAsString()),Units.Volume.dL);
		if (units == "HL")
			return new Property<Units.Volume>(Float.parseFloat(volumeObject.get(VALUE).getAsString()),Units.Volume.hL);
		if (units == "KL")
			return new Property<Units.Volume>(Float.parseFloat(volumeObject.get(VALUE).getAsString()),Units.Volume.kL);


		return null;
	}

}
