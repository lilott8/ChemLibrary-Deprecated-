package api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonParseException;
import executable.instructions.Instruction;
import org.junit.Test;
import org.junit.Before;
import parsing.ChemicalDeserializer;
import substance.Chemical;

import java.lang.NullPointerException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



/**
 * Created by eldgb on 05-Oct-16.
 */
public class ChemicalDeserializerTest {

    JsonObject jsonObject;
    Gson gson;

    @Before
    public void setUp(){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Chemical.class, new ChemicalDeserializer());
        gson = gsonBuilder.setPrettyPrinting().create();

    }
    //Correct Case
    @Test
    public void TestChemicalDeserializer_01() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/ChemicalDeserializerTests/test1.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Chemical chemical = gson.fromJson(jsonObject, Chemical.class);

    }

    //No name
    @Test (expected = NullPointerException.class)
    public void TestChemicalDeserializer_02() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/ChemicalDeserializerTests/test2.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Chemical chemical = gson.fromJson(jsonObject, Chemical.class);

    }

    //No volume
    @Test (expected = NullPointerException.class)
    public void TestChemicalDeserializer_03() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/ChemicalDeserializerTests/test3.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Chemical chemical = gson.fromJson(jsonObject, Chemical.class);

    }

    //No value
    @Test (expected = NullPointerException.class)
    public void TestChemicalDeserializer_04() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/ChemicalDeserializerTests/test4.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Chemical chemical = gson.fromJson(jsonObject, Chemical.class);

    }

    //No units
    @Test (expected = NullPointerException.class)
    public void TestChemicalDeserializer_05() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/ChemicalDeserializerTests/test5.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Chemical chemical = gson.fromJson(jsonObject, Chemical.class);

    }

    //Wrong value
    @Test (expected = NumberFormatException.class)
    public void TestChemicalDeserializer_06() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/ChemicalDeserializerTests/test6.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Chemical chemical = gson.fromJson(jsonObject, Chemical.class);

    }

    //Wrong units
    @Test (expected = IllegalArgumentException.class)
    public void TestChemicalDeserializer_07() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/ChemicalDeserializerTests/test7.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Chemical chemical = gson.fromJson(jsonObject, Chemical.class);

    }

    //No name value
    @Test (expected = IllegalArgumentException.class)
    public void TestChemicalDeserializer_08() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/ChemicalDeserializerTests/test8.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Chemical chemical = gson.fromJson(jsonObject, Chemical.class);

    }

    public String getJsonFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String json = scanner.useDelimiter("\\A").next();
        scanner.close();
        return json;
    }
}
