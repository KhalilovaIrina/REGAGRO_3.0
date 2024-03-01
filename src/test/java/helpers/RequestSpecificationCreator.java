//package helpers;
//
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.http.ContentType;
//import io.restassured.specification.RequestSpecification;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//public class RequestSpecificationCreator {
//    public static String uuid;
//    public static String baseUri = "v3.dev.regagro.ru";
//    public static String pathAddress = "/ajax/address/object/";
//    public static String pathDistrict = uuid + "/children?levels=district";
//    public static String pathCity = uuid + "/children?levels=city,locality";
//    public static String pathStreet = uuid + "/children?levels=street";
//
//    public static RequestSpecification getReqSpec(String path, String uuid,
//                                                  Map<String, String> headers) {
//        RequestSpecification requestSpecification = new RequestSpecBuilder()
//                .setBaseUri(baseUri)
//                .setBasePath(path)
//                .addHeaders(headers)
//                .setContentType(ContentType.JSON)
//                .build();
//        return requestSpecification;
//    }
//
//    public static <T> List<T> doGetReturnList(RequestSpecification specification,
//                                              int httpStatusCode,
//                                              Class<T[]> clazz) {
//        return Arrays.asList(given()
//                .spec(specification)
//                .when()
//                .log().all()
//                .get()
//                .then().statusCode(httpStatusCode)
//                .log().all()
//                .extract().body()
//                .as(clazz));
//    }
//
//
//}
