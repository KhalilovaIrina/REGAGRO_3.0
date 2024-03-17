package entities;

import dataGenerator.DataGenerator;
import helpers.DBHelper;
import helpers.RequestSpecificationCreator;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import java.util.List;
import java.util.Random;

@Getter
public class Enterprise {
    private final Address address = new Address();
    Random random = new Random();
    DBHelper dbHelper = new DBHelper();
    DataGenerator dataGenerator = new DataGenerator();
    private String uuid = "5e465691-de23-4c4e-9f46-f35a125b5970";

    private final String baseUri = "https://v3.dev.regagro.ru";
    private final String pathAddress = "/ajax/address/object/";
    private String path = pathAddress + uuid + "/children?levels=";

    private final String ownerInn = "7736280231";
    private final String name = dataGenerator.getEnterpriseName();
    private final String typeOfEnterprise = setEnterpriseType();
    private final String region = "Орловская";
    private final String district = setDistrict();
    private final String city = setCity();
    //private String planningStructure;
    private final String street = setStreet();
    private final String house = dataGenerator.getNumberRange(1,30);
    private final String serviceArea = "2";

    public Address getRandomAddress(String level) {
        RequestSpecification reqSpec = RequestSpecificationCreator.getReqSpec(baseUri);
        Response response = RestAssured.given()
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
        List<String> enterpriseTypes = dbHelper.values(
                "SELECT enterprise_types.name\n" +
                "FROM enterprise_types\n" +
                "JOIN legal_form_enterprise_types\n" +
                "ON enterprise_types.id = legal_form_enterprise_types.enterprise_type_id\n" +
                "WHERE legal_form_enterprise_types.legal_form_id =" + legalFormId + " AND deleted_at IS NULL", "regagro_3_0_handbooks", "name");
        return enterpriseTypes.get(random.nextInt(enterpriseTypes.size()));
    }
//
//@Getter
//public class Enterprise {
//
//    private final Address address = new Address();
//    Random random = new Random();
//    DBHelper dbHelper = new DBHelper();
//    public String getRandomEnterpriseType() {
//        int legalFormId = 1;
//        List<Integer> enterpriseTypeIds = dbHelper.getIntValuesOfConditions("enterprise_type_id", "legal_form_id", "legal_form_enterprise_types", legalFormId);
//        int randomEnterpriseTypeId = enterpriseTypeIds.get(random.nextInt(enterpriseTypeIds.size()));
//        return dbHelper.getValuesOfConditions("name", "id", "enterprise_types", randomEnterpriseTypeId).get(0);
//    }
//    private String uuid = "5e465691-de23-4c4e-9f46-f35a125b5970";
//
//    private final String baseUri = "https://v3.dev.regagro.ru";
//    private final String pathAddress = "/ajax/address/object/";
//    private final String path = pathAddress + uuid + "/children?levels=";
//
//    private final String ownerInn = "7736280231";
//    private final String name = DataGenerator.getEnterpriseName();
//    private final String typeOfEnterprise = getRandomEnterpriseType();
//    ;
//    private final String region = "Орловская";
//    private final String district = setDistrict();
//    private final String city = setCity();
//    //private String planningStructure;
//    private final String street = setStreet();
//    private final String house = DataGenerator.getNumber(20);
//    private final String serviceArea = "2";
//    //private String countOfAnimals;
//
//    public Address getRandomAddress(String level) {
//        List<Address> addresses = address.getAddresses(baseUri, path, level);
//        return addresses.get(random.nextInt(addresses.size()));
//    }
//
//    private String setDistrict() {
//        String districtName = getRandomAddress("district").getName();
//        uuid = getRandomAddress("district").getObject_guid();
//        return districtName;
//    }
//
//    private String setCity() {
//        String cityName = getRandomAddress("city,locality").getName();
//        uuid = getRandomAddress("city,locality").getObject_guid();
//        return cityName;
//    }
//
//    private String setStreet() {
//        String streetName = getRandomAddress("street").getName();
//        uuid = getRandomAddress("street").getObject_guid();
//        return streetName;
//    }
}