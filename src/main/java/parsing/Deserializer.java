package parsing;

import com.google.gson.JsonDeserializer;

/**
 * Created by jason on 2016/09/29.
 */
public abstract class Deserializer<T> implements JsonDeserializer<T> {
	protected static final String VOLUME = "volume";
	protected static final String NAME = "name";
	protected static final String VALUE = "value";
	protected static final String UNITS = "units";
	protected static final String ID = "id";
	protected static final String CHEMICALS = "chemicals";
	protected static final String CLASSIFICATION = "classification";
	protected static final String INPUTS = "inputs";
	protected static final String OUTPUTS = "outputs";
	protected static final String INSTRUCTIONS = "instructions";
	protected static final String BENCHTOP = "benchtop";
	protected static final String PURPOSE = "purpose";
	protected static final String SUBROUTINES = "subroutines";
	protected static final String EXPERIMENTS = "experiments";
	protected static final String EXPERIMENT = "experiment";
	protected static final String TYPESYTEM = "typesystem";
	protected static final String DECLARATION = "variable_declaration";
	protected static final String SUBSTANCE = "substance";
	protected static final String VARIABLES = "variables";
	protected static final String TYPE = "type";
	protected static final String CHEMICAL = "chemical";
	protected static final String REFERENCE = "chemical_reference";
	protected static final String COMPOUND = "compound";
	protected static final String LIST = "chemical_list";
	protected static final String OPERATION = "operation";
	protected static final String OPERATIONS = "operations";

	public Deserializer() {}
}
