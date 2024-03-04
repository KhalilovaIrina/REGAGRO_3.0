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
        authScript.put("Cookie", "laravel_session=eyJpdiI6Ikl0eDNIaEpCVFRqTmVUV2kwMFBHNGc9PSIsInZhbHVlIjoiUXJUVzNKQ3p3c0k3QVY" +
                "5UHZhSmIzbW9HZDNlaHQxTjdEc1JPUTZuWlRnTVF1dVBaeU10YytpOURoUUg0SjZ6TlJ3cXMyaEJpcHhtWU5TK3c4dHd6dVZwbEhWQ2tjTDk" +
                "2anBMdmQ4eGtabnVMWFEveWhZRkVQYklSb2M2aElLQW4iLCJtYWMiOiI5YTQ3ODljYTAwY2M3ZjE2MzQ2ZGMwMmYxYzQzMTQ5MzI4NzhkYjM" +
                "3NmE1ZTVkNjZjZGEzNTViMDdiNmNmZTQ0IiwidGFnIjoiIn0%3D");
        authScript.put("X-XSRF-TOKEN", "eyJpdiI6IlhHY0tpQXRVaThIcko2cDNSMDhEaWc9PSIsInZhbHVlIjoibWwraG45bWlKeld0bzVWVHY2WjZRe" +
                "jBBVWV0Y29BVkpyUURDaDBLNXA3djdrcHRmR2FsUkd3VVlQZWtmQ2NVNkRCamllaGVOV3V0aFpyalg1OTdKVUdGR1pBZGJUUDZ0WnFSS3JYR" +
                "Th0d1k2SDlleU1pZHNySWR0MmJGSmhWNUoiLCJtYWMiOiI2NjRlZjFiMTVlY2UyNmY1MDc2ZGZiMjUxNmQwNTNkODI3N2UwNmI1MTdkZDc5Y" +
                "2Q0MWEzNjcwNTdiM2JiYjA4IiwidGFnIjoiIn0%3D");
        return authScript;
    }
    public RequestSpecification getReqSpec(String baseUri) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("Cookie", authScript.get("Cookie"))
                .addHeader("X-XSRF-TOKEN", authScript.get("X-XSRF-TOKEN"))
                .setAuth(basic("cheh@p.v", "33221100"))
                .setContentType(ContentType.JSON)
                .build();
    }
}