package helpers;

import io.restassured.RestAssured;
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
        authScript.put("Cookie", "laravel_session=eyJpdiI6IlF1Slg3c09iZW9wUGxBNXFQUGszTVE9PSIsInZhbHVlIjoibUlNbXJ4OXVmS" +
                "TdvNUd5elZtL0lRWHFzeE5aaHJDK2dXZkE3QVhBK1duUm9oVE5ESUFoMVRzbnoxMTRUU2NiTWltK3JlRVFpRU03NGEydi94SXhQR05" +
                "jenlld2txbUVJeXRNSERRMHRmbEdaZW1iOVBvTDNpZ1hsUXd2N2N2dzMiLCJtYWMiOiI2MTU4MjE3ZjM3ZWNiZDE2YWI5M2EyODlmY" +
                "2FhZmUwYTg5ZmYwMTg2NjJjOGEwZTBkM2IyMTgxMTE3NTM1MjUzIiwidGFnIjoiIn0%3D");
        authScript.put("X-XSRF-TOKEN", "eyJpdiI6ImFiR1ZKZ1Yvb2RqT2FObXhVRXh6bUE9PSIsInZhbHVlIjoicjJ0TllHQ2MzM2h4YjBjWE5" +
                "jTkNkLzBHUGxQRE5LdVVqdjlUSzdMa2x5VnpIa2pkR2FxWHJVWDJYUGFyNjl6WHhPdVlxOGZqMU5yd2Job0NhUStVQmttYyt2QkNzb" +
                "nlSWEd6NUxYZW1FaVQrTklGVmFzaDRhaG9RWVl5STFrU3IiLCJtYWMiOiI0MTNhMTIyYzg0ZjM3MmE0NDA2NDExZjQxNTE4MGExOGI" +
                "wMTk2YTA4YWEzNjE2NDkxZTgzM2U2MTcyODg2N2NjIiwidGFnIjoiIn0%3D");
        return authScript;
    }

    public static RequestSpecification getReqSpec(String baseUri) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("Cookie", authScript.get("Cookie"))
                .addHeader("X-XSRF-TOKEN", authScript.get("X-XSRF-TOKEN"))
                .setAuth(RestAssured.basic("cheh@p.v", "33221100"))
                .setContentType(ContentType.JSON)
                .build();
    }
}

