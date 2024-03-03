package entities;

import dataGenerator.DataGenerator;
import handbooks.EnterpriseHandbooks;
import lombok.Getter;

import java.util.List;
import java.util.Random;

@Getter
public class Enterprise {
    EnterpriseHandbooks enterpriseHandbooks = new EnterpriseHandbooks();
    private final Address address = new Address();
    Random random = new Random();
    private String uuid = "5e465691-de23-4c4e-9f46-f35a125b5970";

    private final String baseUri = "v3.dev.regagro.ru";
    private final String pathAddress = "/ajax/address/object/";
    private final String path = pathAddress + uuid + "/children?levels=";

    private final String ownerInn = "7736280231";
    private final String name = DataGenerator.getEnterpriseName();
    private final String typeOfEnterprise = enterpriseHandbooks.getRandomEnterpriseType();
    ;
    private final String region = "Орловская";
    private final String district = setDistrict();
    private final String city = setCity();
    //private String planningStructure;
    private final String street = setStreet();
    private final String house = DataGenerator.getNumber(20);
    private final String serviceArea = "2";
    //private String countOfAnimals;

    public Address getRandomAddress(String level) {
        List<Address> addresses = address.getAddresses(baseUri, path, level);
        return addresses.get(random.nextInt(addresses.size()));
    }

    private String setDistrict() {
        String districtName = getRandomAddress("district").getName();
        uuid = getRandomAddress("district").getObject_guid();
        return districtName;
    }

    private String setCity() {
        String cityName = getRandomAddress("city,locality").getName();
        uuid = getRandomAddress("city,locality").getObject_guid();
        return cityName;
    }

    private String setStreet() {
        String streetName = getRandomAddress("street").getName();
        uuid = getRandomAddress("street").getObject_guid();
        return streetName;
    }
}
