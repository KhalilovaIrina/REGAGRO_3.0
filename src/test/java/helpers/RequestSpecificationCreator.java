package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class RequestSpecificationCreator {
    public static String uuid;

    private static Map<String, String> getAuthScript() {
        Map<String, String> authScript = new HashMap<>();
        authScript.put("Cookie", "laravel_session=eyJpdiI6ImNUa0NkcWtsV0ttczU3TU15UThrakE9PSIsInZhbHVlIjoiM2JJaU9hK1RzbkhYVk01STBxREU1K3MxWnVFU0M1b3JMZXV2dmFXQVlmWkZsV0QweTJvSmhXeC9BajF5eFdBWHp3dGxYS2lTeGVzZjFwYVJqeDFvMnc2M3Jwa0VFbzRzNk5ZQ2VTblBVczhmQUFyWFJVcE5jMllJZCtNbVVPNlUiLCJtYWMiOiI5MjNkZmYwM2QzMmJlNmNjM2Y4Y2M3MDNlNzc0Nzc1NGJhOTUzODFmNTE3ZTc2MGE4ZTE1NmFjZmEwMTAzOTZjIiwidGFnIjoiIn0%3D");
        authScript.put("X-XSRF-TOKEN", "eyJpdiI6ImJOUDcxY2VMNG4valNtamRrbnkxMmc9PSIsInZhbHVlIjoiOGE1eExMdFp0NEs1K2tTdlNVYmJWZW9oRXlzTHRob2plcVNXbWU4a0xPMjVwWmVtZENjYmZabDJ4UlNSdFV1aHgxWE1Ya3M2bUoycG4vcmVYanc3a1RZbmVuL2hOT3FxdDluVmRseHlLeGhUek5hb0l1UFduSDVsbDQ4ZEdpaWsiLCJtYWMiOiIyYTljN2ZlOGI2MWM4ZjUyNThjYjI0ZTNmYmU0ZDRiMGE3MzQxNTBkOGY0YTFlMzhiZjJlYWVmYWU3N2JiNWNhIiwidGFnIjoiIn0%3D");
        return authScript;
    }

    public static RequestSpecification getReqSpec(String baseUri) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("Cookie", getAuthScript().get("Cookie"))
                .addHeader("X-XSRF-TOKEN", getAuthScript().get("X-XSRF-TOKEN"))
                .setContentType(ContentType.JSON)
                .build();
    }

}
