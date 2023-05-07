package ATApiTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class JsonComparator {
    public static boolean compareResponseWithJsonFile(String responseJson, String filePath) {
        try {
            // Read the JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode expectedJson = objectMapper.readTree(new File(filePath));

            // Parse the response body into a JSON object
            JsonNode actualJson = objectMapper.readTree(responseJson);

            // Compare the JSON objects
            return expectedJson.equals(actualJson);
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
}