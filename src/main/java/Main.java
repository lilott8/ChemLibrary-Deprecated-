import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parsing.*;

/**
 * Created by jason on 2016/09/21.
 */
public class Main {

	public static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String... args) throws Exception {
		logger.info("Nothing to see here...");

		TypeSystemParser.parse("/Users/jason/Projects/IntelliJ/ChemTrails/src/main/resources/json_tests/typesystem_test.json");
		//BenchtopParser.parse("/Users/jason/Projects/IntelliJ/ChemTrails/src/main/resources/json_tests/test1.json");
	}
}
