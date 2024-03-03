package entities;

import helpers.RequestSpecificationCreator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
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
        public int id;
        public int object_id;
        public String object_guid;
        public int change_id;
        public String name;
        public String type_name;
        public int level;
        public int oper_type_id;
        public int prev_id;
        public int next_id;
        public String update_date;
        public String start_date;
        public String end_date;
        public int is_actual;
        public int is_active;

    public List<Address> getAddresses(String baseUri, String path, String level) {
        RequestSpecification reqSpec =  RequestSpecificationCreator.getReqSpec(baseUri);
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

