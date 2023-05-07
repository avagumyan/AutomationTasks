package ATApiTest;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


/**
 * Unit test for simple App.
 */
public class AppTest
{

    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeClass
    public static void setup() {
        extent = new ExtentReports();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/report.html");
        extent.attachReporter(htmlReporter);
    }

    @AfterClass
    public static void tearDown() {
        // Flush and close the report
        extent.flush();
    }

    String validUUID = "edeab824-178e-4fd7-9bf0-bd88a6fd114a";
    String invalidUUID = "edeab824-178e-4fd7-9f0-bd88a6fd114aInvalid1";
    String validCountry = "WWC";
    String validAsset = "ATUSD";

    private String getUrl(String uuid, String countries,String asset) {
        return "https://payments-api.armenotech.dev/api/v2/payments/"+uuid+"/sep0031/info?countries="+countries+"&asset="+asset;
    }


    @Test
    public void validUUIDAndParams(){
        test = extent.createTest("Get with valid parameters", "Test with valid uuid and query parameters.");
        String url = this.getUrl(validUUID,validCountry,validAsset);
        Response response = RestAssured.get(url);
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode,200);
    }

    @Test
    public void emptyParams() {
        test = extent.createTest("Get with empty parameters", "Test with empty parameters.");
        String url = this.getUrl(validUUID,"","");
        Response response = RestAssured.get(url);
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode,200);
    }
    @Test
    public void invalidParams() {
        test = extent.createTest("Get with invalid parameters", "Test with invalid parameters.");
        String invalidParam = "123";
        String url = this.getUrl(validUUID,invalidParam,invalidParam);
        Response response = RestAssured.get(url);
        int statusCode = response.getStatusCode();
        String filePath = "src/test/helper/json_files/empty-body.json";
        boolean isBodyEqual = JsonComparator.compareResponseWithJsonFile(response.getBody().asString(),filePath);
        Assert.assertTrue(isBodyEqual);
        Assert.assertEquals(statusCode,200);
    }

    @Test
    public void invalidUUID() {
        test = extent.createTest("Get with invalid uuid", "Test with invalid uuid.");
        String url = this.getUrl(invalidUUID,validCountry,validAsset);
        Response response = RestAssured.get(url);
        int statusCode = response.getStatusCode();

        String errorMessage = response.getBody().jsonPath().getString("error");
        Assert.assertEquals(errorMessage,"merchant not registered");
        Assert.assertEquals(statusCode,404);
    }

    @Test
    public void specificCountry() {
        test = extent.createTest("Get with Specific country", "Test specific country.");
        String specificCountry = "UKR";
        String specificAsset = "ATBLR";
        String url = this.getUrl(validUUID,specificCountry,specificAsset);
        Response response = RestAssured.get(url);
        int statusCode = response.getStatusCode();
        String filePath = "src/test/helper/json_files/ukr-atblr-body.json";
        boolean isBodyEqual = JsonComparator.compareResponseWithJsonFile(response.getBody().asString(),filePath);
        Assert.assertTrue(isBodyEqual);
        Assert.assertEquals(statusCode,200);
    }

    @Test
    public void notAllowedMethod(){
        test = extent.createTest("Get not allowed url", "Test getting not allowed url.");
        String url = "https://payments-api.armenotech.dev/api/v2/payments/edeab824-178e-4fd7-9bf0-bd88a6fd114a/sep0031/";
        Response response = RestAssured.get(url);
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode,405);
    }

}
