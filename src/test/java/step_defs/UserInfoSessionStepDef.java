package step_defs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class UserInfoSessionStepDef {
    private String authorizationToken;
    private Response userInfoResponse;

    public UserInfoSessionStepDef() {
        RestAssured.baseURI = "https://ayco69dbm3.execute-api.us-east-1.amazonaws.com/singular_qa";
    }

    @Given("a valid authorization token")
    public void givenValidToken() {
        authorizationToken = given()
                .contentType("application/json")
                .body("{ \"username\": \"your_username\", \"password\": \"your_password\" }")
                .when()
                .post("/authorize")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("token");
    }

    @Given("a invalid authorization token")
    public void givenInvalidToken() {
        authorizationToken = RandomStringUtils.randomAlphanumeric(50);
    }

    @When("they make a GET request to info endpoint")
    public void whenUserRequestsUserInfo() {
        userInfoResponse = given()
                .header("Authorization", authorizationToken)
                .when()
                .get("/info");
    }

    @Then("they should receive a response with status code {int}")
    public void thenResponseStatusShouldBe(int expectedStatusCode) {
        assertEquals(userInfoResponse.getStatusCode(), expectedStatusCode);
    }
}
