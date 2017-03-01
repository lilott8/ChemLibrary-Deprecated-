import manager.Benchtop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parsing.BioScript.BenchtopParser;

/**
 * Created by jason on 2016/09/21.
 */
public class Main {

	public static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String... args) throws Exception {
		logger.info("Nothing to see here...");

		BenchtopParser.parse(Main.class.getClassLoader().getResource("json_tests/test2.json").getPath());
		logger.info(Benchtop.INSTANCE.toString());
	}
}