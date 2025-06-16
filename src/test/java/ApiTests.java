import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class ApiTests {
    private final String message = "This is expected to be sent back as part of response body.";
    private final String baseUrl = "https://postman-echo.com";

    @Test
    public void testGetRequest() {
        Response response = given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get(baseUrl + "/get");

        commonAsserts(response);
        assertEquals(200, response.getStatusCode());
        assertEquals("bar1", response.jsonPath().getString("args.foo1"));
        assertEquals("bar2", response.jsonPath().getString("args.foo2"));
    }

    @Test
    public void testPostRawRequest() {

        Response response = given()
                .body("{ \"foo1\": \"bar1\",\n" +
                        "\"foo2\": \"bar2\"}")
                .when()
                .post(baseUrl + "/post");
        commonAsserts(response);
        String dataString = response.jsonPath().getString("data");
        assertNotNull(dataString);
        assertTrue(dataString.contains("\"foo1\": \"bar1\""));
        assertTrue(dataString.contains("\"foo2\": \"bar2\""));
        assertEquals(200, response.getStatusCode());

    }

    @Test
    public void testPostFormDataRequest() {

        Response response = given()
                .contentType("multipart/form-data")
                .multiPart("foo1","bar1")
                .multiPart("foo2","bar2")
                .when()
                .post(baseUrl + "/post");
        commonAsserts(response);
        assertEquals("bar1", response.jsonPath().getString("form.foo1"));
        assertEquals("bar2", response.jsonPath().getString("form.foo2"));
        assertEquals(200, response.getStatusCode());

    }

    @Test
    public void testPutRequest() {

        Response response = given()
                .body(message)
                .when()
                .put(baseUrl + "/put");
        commonAsserts(response);
        assertEquals(message, response.jsonPath().getString("data"));
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testPatchRequest() {

        Response response = given()
                .body(message)
                .when()
                .patch(baseUrl + "/patch");
        commonAsserts(response);
        assertEquals(message, response.jsonPath().getString("data"));
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testDeleteRequest() {
        Response response = given()
                .body(message)
                .when()
                .delete(baseUrl + "/delete");
        commonAsserts(response);
        assertEquals(message, response.jsonPath().getString("data"));
        assertEquals(200, response.getStatusCode());
    }

    private void commonAsserts(Response response) {
        assertEquals("postman-echo.com", response.jsonPath().getString("headers.host"));
        assertEquals("*/*", response.jsonPath().getString("headers.accept"));
        //assertEquals("postman-echo.com", response.jsonPath().getString("headers.host"));
        //assertEquals("postman-echo.com", response.jsonPath().getString("headers.host"));
        assertEquals("https", response.jsonPath().getString("headers.x-forwarded-proto"));

    }
}