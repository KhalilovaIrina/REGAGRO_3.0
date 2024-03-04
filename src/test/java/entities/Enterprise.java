package entities;

import dataGenerator.DataGenerator;
import helpers.DBHelper;
import helpers.RequestSpecificationCreator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

@Getter
public class Enterprise {
    private Address address = new Address();
    Random random = new Random();
    DBHelper dbHelper = new DBHelper();
    private String uuid = "5e465691-de23-4c4e-9f46-f35a125b5970";

    private final String baseUri = "https://v3.dev.regagro.ru";
    private final String pathAddress = "/ajax/address/object/";
    private String path = pathAddress + uuid + "/children?levels=";

    private final String ownerInn = "7736280231";
    private final String name = DataGenerator.getEnterpriseName();
    private final String typeOfEnterprise = setEnterpriseType();
    ;
    private final String region = "Орловская";
    private final String district = setDistrict();
    private final String city = setCity();
    //private String planningStructure;
    private final String street = setStreet();
    private final String house = DataGenerator.getNumber(2);
    private final String serviceArea = "2";
    //private String countOfAnimals;

    public Address getRandomAddress(String level) {
        RequestSpecificationCreator recCreator = new RequestSpecificationCreator();
        RequestSpecification reqSpec = recCreator.getReqSpec(baseUri);
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(path + level);
        List<Address> addresses = response.jsonPath().getList(".", Address.class);
        Address randomAddress = null;
        if (!(addresses.isEmpty())) {
            randomAddress = addresses.get(random.nextInt(addresses.size()));
            uuid = randomAddress.getObject_guid();
            path = pathAddress + uuid + "/children?levels=";
        }

        return randomAddress;
    }

    private String setDistrict() {
        return getRandomAddress("district").getName();
    }

    private String setCity() {
        return getRandomAddress("city,locality").getName();
    }

    private String setStreet() {
        Address streetAddress = getRandomAddress("street");
        if (streetAddress != null) {
            return streetAddress.getName();
        } else {
            return null;
        }
    }

    private String setEnterpriseType() {
        int legalFormId = 1;
        List<Integer> enterpriseTypeIds = dbHelper.getIntValuesOfConditions("enterprise_type_id", "legal_form_id", "legal_form_enterprise_types", legalFormId);
        int randomEnterpriseTypeId = enterpriseTypeIds.get(random.nextInt(enterpriseTypeIds.size()));
        return dbHelper.getValuesOfConditions("name", "id", "enterprise_types", randomEnterpriseTypeId).get(0);
    }
}