package api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonParseException;
import org.junit.Test;
import org.junit.Before;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import manager.Benchtop; 
import parsing.BenchtopDeserializer;


public class BenchtopDeserializerTest {
	
	
	JsonObject jsonObject;
	Gson gson;
	
	@Before
	public void setUp(){
	
	GsonBuilder gsonBuilder = new GsonBuilder();
	gsonBuilder.registerTypeAdapter(Benchtop.class, new BenchtopDeserializer());
	gson = gsonBuilder.setPrettyPrinting().create();

	}
	
	
	//Correct Case
	@Test 
	public void TestBenchtopDeserializer_01() throws JsonParseException{
		
		String json = "";

		try {
			json = getJsonFromFile("./src/main/resources/json_tests/test1.json");
		} catch(FileNotFoundException e) {
			System.err.println("Cannot find file: " + e.toString());
		}
		jsonObject = new JsonParser().parse(json).getAsJsonObject();
		
		Benchtop benchtop = gson.fromJson(jsonObject, Benchtop.class);
		//AssertEquals??

	}
	
	
	
	
	//Inputs not defined
	@Test (expected = JsonParseException.class )
	public void TestBenchtopDeserializer_02() throws JsonParseException{
		
		String json = "";

		try {
			json = getJsonFromFile("./src/main/resources/json_tests/test2.json");
		} catch(FileNotFoundException e) {
			System.err.println("Cannot find file: " + e.toString());
		}
		jsonObject = new JsonParser().parse(json).getAsJsonObject();
		
		Benchtop benchtop = gson.fromJson(jsonObject, Benchtop.class);

	}
	
	//Experiments not defined
	@Test (expected = JsonParseException.class )
	public void TestBenchtopDeserializer_03() throws JsonParseException{
		
		String json = "";

		try {
			json = getJsonFromFile("./src/main/resources/json_tests/test3.json");
		} catch(FileNotFoundException e) {
			System.err.println("Cannot find file: " + e.toString());
		}
		jsonObject = new JsonParser().parse(json).getAsJsonObject();
		
		Benchtop benchtop = gson.fromJson(jsonObject, Benchtop.class);

	}
	
	//Instructions not defined
	@Test (expected = JsonParseException.class )
	public void TestBenchtopDeserializer_04() throws JsonParseException{
		
		String json = "";

		try {
			json = getJsonFromFile("./src/main/resources/json_tests/test4.json");
		} catch(FileNotFoundException e) {
			System.err.println("Cannot find file: " + e.toString());
		}
		jsonObject = new JsonParser().parse(json).getAsJsonObject();
		
		Benchtop benchtop = gson.fromJson(jsonObject, Benchtop.class);

	}
	
	//Subroutine not defined
	@Test (expected = JsonParseException.class )
	public void TestBenchtopDeserializer_05() throws JsonParseException{
		
		String json = "";

		try {
			json = getJsonFromFile("./src/main/resources/json_tests/test5.json");
		} catch(FileNotFoundException e) {
			System.err.println("Cannot find file: " + e.toString());
		}
		jsonObject = new JsonParser().parse(json).getAsJsonObject();
		
		Benchtop benchtop = gson.fromJson(jsonObject, Benchtop.class);

	}
	
	

	public String getJsonFromFile(String filename) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(filename));
		String json = scanner.useDelimiter("\\A").next();
		scanner.close();
		return json;
	}

}
