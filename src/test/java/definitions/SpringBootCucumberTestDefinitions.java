package definitions;

import com.example.realestate.RealEstateApplication;
import com.example.realestate.repository.AgentRepository;
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
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RealEstateApplication.class)
public class SpringBootCucumberTestDefinitions {
    private static final String BASE_URL = "http://localhost:";

    @LocalServerPort
    String port;

    private static Response response;


    public String getSecurityKey() throws Exception {
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "mail@gmail.com");
        requestBody.put("password", "123456");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/login/");
        return response.jsonPath().getString("message");
    }

    @Given("A list of properties are available")
    public void aListOfPropertiesAreAvailable() {
        try {
            ResponseEntity<String> response = new RestTemplate().exchange(BASE_URL + port + "/api/properties/", HttpMethod.GET, null, String.class);
            List<Map<String, String>> properties = JsonPath
                    .from(String.valueOf(response
                            .getBody()))
                    .getList("$");
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            Assert.assertTrue(properties.size() > 0);
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
    public void iAddAPropertyToMyPropertyList() throws Exception {
        RestAssured.baseURI = BASE_URL;
        String jwtKey = getSecurityKey();
        RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + jwtKey);
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

    @When("I update a property from my property list")
    public void iUpdateAPropertyFromMyPropertyList() throws Exception {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + getSecurityKey());
        JSONObject requestBody = new JSONObject();
        requestBody.put("address", "123 Programmer Ln");
        requestBody.put("size", 200);
        requestBody.put("price", 10000.00);
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/properties/1/");
    }

    @Then("The property is updated")
    public void thePropertyIsUpdated() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertNotNull(response.body());
    }

    @When("I delete a property from property list")
    public void iDeleteAPropertyFromPropertyList() throws Exception {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + getSecurityKey());
        request.header("Content-Type", "application/json");
        response = request.delete(BASE_URL + port + "/api/properties/1/");
    }

    @Then("the property is deleted")
    public void thePropertyIsDeleted() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertNotNull(response.body());
    }

    @Given("A list of sales are available")
    public void aListOfSalesAreAvailable() {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + getSecurityKey());
            HttpEntity<String> request = new HttpEntity<String>("", headers);

            ResponseEntity<String> response = new RestTemplate()
                    .exchange(BASE_URL + port + "/api/sales/", HttpMethod.GET, request, String.class);
            List<Map<String, String>> sales = JsonPath
                    .from(String.valueOf(response
                            .getBody()))
                    .getList("$");
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            Assert.assertTrue(sales.size() > 0);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @When("I search for one sale by id")
    public void iSearchForOneSaleById() throws Exception {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + getSecurityKey());
        request.header("Content-Type", "application/json");
        response = request.get(BASE_URL + port + "/api/sales/1/");
        Assert.assertNotNull(response.body());
    }

    @Then("The sale is displayed")
    public void theSaleIsDisplayed() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertNotNull(response.body());
    }

    @When("I add a sale to my sales list")
    public void iAddASaleToMySalesList() throws Exception {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + getSecurityKey());
        JSONObject requestBody = new JSONObject();
        requestBody.put("price", 100000.00);
        requestBody.put("Date", new Date(2022, 2, 2));
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/properties/1/sales/");
    }

    @Then("The sale is added")
    public void theSaleIsAdded() {
        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertNotNull(response.body());
    }

    @Given("That an agent is able to register")
    public void thatAnAgentIsAbleToRegister() {
        try {

            JSONObject requestBody = new JSONObject();
            requestBody.put("password", "123456");
            requestBody.put("email", "email@mail.com");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<String>(requestBody.toString(), headers);

            ResponseEntity<String> response = new RestTemplate().exchange(BASE_URL + port + "/auth/register/", HttpMethod.POST, request, String.class);

            Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @When("I login to my account")
    public void iLoginToMyAccount() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("password", "123456");
        requestBody.put("email", "email@mail.com");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/login/");
    }

    @Then("JWT key is returned")
    public void jwtKeyIsReturned() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertNotNull(response.body());
    }

}
