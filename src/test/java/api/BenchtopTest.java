package api;

import benchtop.Benchtop;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;
import parsing.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by jason on 2016/08/26.
 */
public class BenchtopTest {

	@Test
	public void TestBenchtop() {
		String json = "";

		try {
			json = getJsonFromFile("./src/main/resources/json_tests/test1.json");
		} catch(FileNotFoundException e) {
			System.err.println("Cannot find file: " + e.toString());
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		gson.toJsonTree(json);

		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

		Parser.addBenchtop(jsonObject);
	}

	public String getJsonFromFile(String filename) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(filename));
		String json = scanner.useDelimiter("\\A").next();
		scanner.close();
		return json;
	}
}
