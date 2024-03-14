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
        authScript.put("Cookie", "laravel_session=eyJpdiI6Iko1amVCN2hxalJNMmY2eEk1Z29iSkE9PSIsInZhbHVlIjoiK0dqK3I3MmJqalFDbSsrWDRTL1hnaTJjN1psM2Vpc2NRZ2xEK1ZNL2x3WitBdW9BWG1JaHRNVVBkeUYvUjh4N014bTFSb0dxeTgybGlRV3V5Q09rN3BadXhvWFU2TEU1U0I5Ump6UndiZmV5cFVjcmtVYVdGQnhxdjJ1WWhQRXIiLCJtYWMiOiIzMWNkZjA4OTNlOGI5YzE4YTJkNjdkN2YwMDE0NTJmZmU4YzM1NWZmYmM4N2VhNDBiZmNlMWViOGUzNTJmNzIxIiwidGFnIjoiIn0%3D");
        authScript.put("X-XSRF-TOKEN", "eyJpdiI6IjY3VGRDbVErRWNxNk5obnpJVUNNbnc9PSIsInZhbHVlIjoiYVBIam1CQWZQRWNGdFgvSU5LWHJIdzQydkhlNzZyRXp2R01lZjFkblgxVURNaC9XT2pCT3lWOHQ5aU03OXJHaEZTY0xjZ2FaMW5pLzVqVWEwV2JYaTRHZnNFM1oxYzBPZjN5QitOV0VUdEhCOUtrWmxyekRETmhtL1lYOHZTSXoiLCJtYWMiOiJiZGRlOTQ1ZjYwNDllYzZjNDU4MTZjNjk3NmIzMjJmMzExN2JkOTQzYjk1YWMyZWNjMzAwYTgyZDI4NjJkY2NhIiwidGFnIjoiIn0%3D");
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

