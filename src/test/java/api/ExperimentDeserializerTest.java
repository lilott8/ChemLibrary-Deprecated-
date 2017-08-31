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

import executable.Experiment;
import executable.Subroutine;
import executable.instructions.Instruction;
import parsing.BioScript.ExperimentDeserializer;
import parsing.BioScript.OperationDeserializer;
import parsing.BioScript.SubroutineDeserializer;

/**
 * Created by eldgb on 05-Oct-16.
 */

//The correct case does not work
public class ExperimentDeserializerTest {


    JsonObject jsonObject;
    Gson gson;

    @Before
    public void setUp(){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Experiment.class, new ExperimentDeserializer());
        gsonBuilder.registerTypeAdapter(Instruction.class, new OperationDeserializer());
        gsonBuilder.registerTypeAdapter(Subroutine.class, new SubroutineDeserializer());
        gson = gsonBuilder.setPrettyPrinting().create();

    }


    //Correct Case
    @Test
    public void TestExperimentDeserializer_01() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/ExperimentDeserializerTests/test1.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Experiment experiment = gson.fromJson(jsonObject, Experiment.class);

    }


    //No inputs defined
    @Test (expected = NullPointerException.class)
    public void TestExperimentDeserializer_02() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/ExperimentDeserializerTests/test2.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Experiment experiment = gson.fromJson(jsonObject, Experiment.class);

    }

    //No instructions defined
    @Test (expected = JsonParseException.class)
    public void TestExperimentDeserializer_03() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/ExperimentDeserializerTests/test3.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Experiment experiment = gson.fromJson(jsonObject, Experiment.class);

    }

    //No subroutines defined
    @Test (expected = NullPointerException.class)
    public void TestExperimentDeserializer_04() throws JsonParseException{

        String json = "";

        try {
            json = getJsonFromFile("./src/main/resources/json_tests/ExperimentDeserializerTests/test4.json");
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find file: " + e.toString());
        }
        jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Experiment experiment = gson.fromJson(jsonObject, Experiment.class);




    }

    public String getJsonFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String json = scanner.useDelimiter("\\A").next();
        scanner.close();
        return json;
    }



}
