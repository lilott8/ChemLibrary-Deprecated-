import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by jason on 2016/09/21.
 */
public class Main {

	public static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String... args) throws Exception {
		logger.info("Nothing to see here...");

		// BenchtopParser.parseFromFile(Main.class.getClassLoader().getResource("json_tests/neurotransmitter_sensing.json").getPath());
		// BenchtopParser.parseFromString(readFile(Main.class.getClassLoader().getResource("json_tests/temptest.json").getPath(), Charset.defaultCharset()));
		// BenchtopParser.parseFromString(readFile(Main.class.getClassLoader().getResource("json_tests/functions.json").getPath(), Charset.defaultCharset()));
		// logger.info(Benchtop.INSTANCE.toString());
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}