package entities;

import helpers.RequestSpecificationCreator;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static io.restassured.RestAssured.given;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    private int id;
    private int object_id;
    private String object_guid;
    private int change_id;
    private String name;
    private String type_name;
    private int level;
    private int oper_type_id;
    private int prev_id;
    private int next_id;
    private String update_date;
    private String start_date;
    private String end_date;
    private int is_actual;
    private int is_active;

    public List<Address> getAddresses(String baseUri, String path, String level) {
        RequestSpecification reqSpec = RequestSpecificationCreator.getReqSpec(baseUri);
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(path+level);
        String jsonResponse = response.asString(); // Преобразование ответа в строку

        System.out.println("JSON Response:");
        System.out.println(jsonResponse); // Печать JSON ответа


        List<Address> addresses = response.jsonPath().getList(".", Address.class);

        return addresses;
    }
}