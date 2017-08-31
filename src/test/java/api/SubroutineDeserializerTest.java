package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import executable.Subroutine;
import executable.instructions.Instruction;
import parsing.BioScript.OperationDeserializer;
import parsing.BioScript.SubstanceDeserializer;
import substance.Substance;

/**
 * Created by eldgb on 05-Oct-16.
 */


//Does not throw any exceptions


public class SubroutineDeserializerTest {


    JsonObject jsonObject;
    Gson gson;

    @Before
    public void setUp(){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Substance.class, new SubstanceDeserializer());
        gsonBuilder.registerTypeAdapter(Instruction.class, new OperationDeserializer());
        gson = gsonBuilder.setPrettyPrinting().create();

    }


    //Correct Case
    @Test
    public void TestSubroutineDeserializer_01() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/SubroutineDeserializerTests/test1.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Subroutine subroutine = gson.fromJson(jsonObject, Subroutine.class);
        //AssertEquals??

    }

    //No name defined
    @Test (expected = NullPointerException.class)
    public void TestSubroutineDeserializer_02() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/SubroutineDeserializerTests/test2.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Subroutine subroutine = gson.fromJson(jsonObject, Subroutine.class);




    }

    //No inputs defined
    @Test (expected = JsonParseException.class)
    public void TestSubroutineDeserializer_03() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/SubroutineDeserializerTests/test3.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Subroutine subroutine = gson.fromJson(jsonObject, Subroutine.class);




    }

    //No outputs defined
    @Test (expected = JsonParseException.class)
    public void TestSubroutineDeserializer_04() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/SubroutineDeserializerTests/test4.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Subroutine subroutine = gson.fromJson(jsonObject, Subroutine.class);




    }

    //No instructions defined
    @Test (expected = JsonParseException.class)
    public void TestSubroutineDeserializer_05() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/SubroutineDeserializerTests/test5.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Subroutine subroutine = gson.fromJson(jsonObject, Subroutine.class);




    }

    public String getJsonFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String json = scanner.useDelimiter("\\A").next();
        scanner.close();
        return json;
    }

}
