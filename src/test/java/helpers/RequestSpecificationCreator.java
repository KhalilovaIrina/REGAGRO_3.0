package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.basic;

public class RequestSpecificationCreator {
    public static Map<String, String> authScript = initializeAuthScript();

    private static Map<String, String> initializeAuthScript() {
        Map<String, String> authScript = new HashMap<>();
        authScript.put("Cookie", "laravel_session=eyJpdiI6ImY2STJGcW5RQXYxbFFhQTdnNHNralE9PSIsInZhbHVlIjoiY3BpWXFNdTJCQVFkZmd" +
                "TbGc1a0U2d3I1Z1piSS9PMzBHNHE3Vms1UmVXMTROMHFrWkJqVkFiR0I0YlgwUmN4Rzd4bDBMTnBnU05hQjVzMUxndU5KWU9JN1QvSWFaZUc" +
                "rZmQ0anRXR3lCZGhKQzVWT2RuR3Rab2xCa2pMb09ObFgiLCJtYWMiOiI4NzViY2NkMWY5YThiMTI4ODU3YzlhMTU0OTQ4ODQ4NTI2MDQxMjE" +
                "wNGM1NDQ1M2E0ZGIwYzJiNmY3MjY0MTgwIiwidGFnIjoiIn0%3D");
        authScript.put("X-XSRF-TOKEN", "eyJpdiI6IlNoVzEycytJWVF0dm1jbFNCQlB2cVE9PSIsInZhbHVlIjoiQVV5TE03Ukt5cFlJZUthTlZxeEVhS" +
                "FVxL2JrNTNqSmNydHloUTlLSGRabzQ4SlVqOS93aUNpUEFqNkRkS3E4c1laVmUwa0Q3azUvdlhMMzlXUGpNNlpKaFNxTzltdHhWOXVueldYd" +
                "EtuRzBuVmo1eGNZeFFrTCtBT0pqZFlWcEciLCJtYWMiOiI5MjY4ZGQzMDExZTg3MWU5NzJlMWQ5ZjI5YWMxNjRmNDA1MjVhZTI3ZjQ0YTdkM" +
                "zliMzdkNDE3Zjg2YmFmZTMyIiwidGFnIjoiIn0%3D");
        return authScript;
    }
    public static RequestSpecification getReqSpec(String baseUri) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("Cookie", authScript.get("Cookie"))
                .addHeader("X-XSRF-TOKEN", authScript.get("X-XSRF-TOKEN"))
                .setAuth(basic("cheh@p.v", "33221100"))
                .setContentType(ContentType.JSON)
                .build();
    }
}
