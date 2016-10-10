import manager.Benchtop;
import manager.TypeSystem;
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

//		System.out.println();
//		System.out.println(System.class.getResource("test1.json").getPath());
//		TypeSystemParser.parse(Main.class.getClassLoader().getResource("json_tests/test1.json").getPath());
		BenchtopParser.parse(Main.class.getClassLoader().getResource("json_tests/test3.json").getPath());
		logger.info(Benchtop.INSTANCE.toString());
	}
}
