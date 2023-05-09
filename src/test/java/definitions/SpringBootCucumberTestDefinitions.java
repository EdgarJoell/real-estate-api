package definitions;

import com.example.realestate.RealEstateApplication;
import com.example.realestate.model.Property;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RealEstateApplication.class)
public class SpringBootCucumberTestDefinitions {
    private static final String BASE_URL = "http://localhost:";

    @LocalServerPort
    String port;

    private static Response response;

    @Given("A list of properties are available")
    public void aListOfPropertiesAreAvailable() {
        try {
            ResponseEntity<String> response = new RestTemplate().exchange(BASE_URL + port + "/api/properties/", HttpMethod.GET, null, String.class);
            List<Map<String, String>> categories = JsonPath
                    .from(String.valueOf(response
                            .getBody()))
                    .getList("$");
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            Assert.assertTrue(categories.size() > 0);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    @When("I search for one property by id")
    public void iSearchForOnePropertyById() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.get(BASE_URL + port + "/api/properties/1/");
        Assert.assertNotNull(response.body());
    }

    @Then("property is displayed")
    public void propertyIsDisplayed() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("I add a property to my property list")
    public void iAddAPropertyToMyPropertyList() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("address", "100 South State");
        requestBody.put("size", 900);
        requestBody.put("price", 1500.00);
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/properties/");
    }

    @Then("The property is added")
    public void thePropertyIsAdded() {
        Assert.assertEquals(201, response.getStatusCode());

    }
}
