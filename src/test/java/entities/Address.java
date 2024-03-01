package entities;

import helpers.RequestSpecificationCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Address {

    private Integer id;
    private Integer object_id;
    private String object_guid;
    private Integer change_id;
    private String name;
    private String type_name;
    private Integer level;
    private Integer oper_type_id;
    private Integer prev_id;
    private Integer next_id;
    private String update_date;
    private String start_date;
    private String end_date;
    private Integer is_actual;
    private Integer is_active;

    public Address setObjectGuid(String baseUri, String path){
        object_guid = getUuid(baseUri, path);
        return this;
    }

    Random random = new Random();

    private String getUuid(String baseUri, String path){
        RequestSpecificationCreator.getReqSpec(baseUri);
        List<Address> addresses = given()
                .when()
                .get(path)
                .then()
                .extract().body().jsonPath().getList("object_guid", Address.class);

        List<String> uuids = addresses.stream().map(dis -> dis.getObject_guid()).collect(Collectors.toList());
        return uuids.get(random.nextInt(uuids.size()));
    }

    public String getAddress(String baseUri, String path){
        List<Address> addresses = given()
                .when()
                .get(path)
                .then()
                .extract().body().jsonPath().getList("name", Address.class);

        List<String> values = addresses.stream().map(val -> val.getName()).collect(Collectors.toList());
        return values.get(random.nextInt(values.size()));
    }


}

