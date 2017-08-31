package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonParseException;
import org.junit.Test;
import org.junit.Before;
import java.lang.NullPointerException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import parsing.ChemicalDeserializer;
import substance.*;
import parsing.SubstanceDeserializer;

/**
 * Created by eldgb on 05-Oct-16.
 */


//Does not throw exception when there is no volume for chemical


public class SubstanceDeserializerTest {


    JsonObject jsonObject;
    Gson gson;

    @Before
    public void setUp(){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Substance.class, new SubstanceDeserializer());
        gsonBuilder.registerTypeAdapter(Chemical.class, new ChemicalDeserializer());
        gson = gsonBuilder.setPrettyPrinting().create();

    }


    //Correct Case
    @Test
    public void TestSubstanceDeserializer_01() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/SubstanceDeserializerTests/test1.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Substance substance = gson.fromJson(jsonObject, Substance.class);


    }


    //No chemicals
    @Test (expected = NullPointerException.class)
    public void TestSubstanceDeserializer_02() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/SubstanceDeserializerTests/test2.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Substance substance = gson.fromJson(jsonObject, Substance.class);


    }

    //No name
    @Test (expected = NullPointerException.class)
    public void TestSubstanceDeserializer_03() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/SubstanceDeserializerTests/test3.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Substance substance = gson.fromJson(jsonObject, Substance.class);


    }


    //No volume
    @Test (expected = NullPointerException.class)
    public void TestSubstanceDeserializer_04() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/SubstanceDeserializerTests/test4.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Substance substance = gson.fromJson(jsonObject, Substance.class);


    }


    //No value
    @Test (expected = NullPointerException.class)
    public void TestSubstanceDeserializer_05() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/SubstanceDeserializerTests/test6.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Substance substance = gson.fromJson(jsonObject, Substance.class);


    }


    //No units
    @Test (expected = NullPointerException.class)
    public void TestSubstanceDeserializer_06() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/SubstanceDeserializerTests/test6.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Substance substance = gson.fromJson(jsonObject, Substance.class);
    }

    //No name string
    @Test (expected = JsonParseException.class)
    public void TestSubstanceDeserializer_07() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/SubstanceDeserializerTests/test7.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Substance substance = gson.fromJson(jsonObject, Substance.class);
    }

    //Wrong value
    @Test (expected = NumberFormatException.class)
    public void TestSubstanceDeserializer_08() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/SubstanceDeserializerTests/test8.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Substance substance = gson.fromJson(jsonObject, Substance.class);
    }

    //Wrong units
    @Test (expected = IllegalArgumentException.class)
    public void TestSubstanceDeserializer_09() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/SubstanceDeserializerTests/test9.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Substance substance = gson.fromJson(jsonObject, Substance.class);
    }


    public String getJsonFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String json = scanner.useDelimiter("\\A").next();
        scanner.close();
        return json;
    }

}
