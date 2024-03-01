package entities;

import dataGenerator.DataGenerator;
import handbooks.EnterpriseHandbooks;
import lombok.Getter;

@Getter
public class Enterprise {
    private String ownerInn;
    private String name;
    private String typeOfEnterprise;
    private String region;
    private String district;
    private String city;
    private String planningStructure;
    private String street;
    private String house;
    private String serviceArea;
    private String countOfAnimals;

    //    public String getOwnerInn() {
//        return ownerInn;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getTypeOfEnterprise() {
//        return typeOfEnterprise;
//    }
//
//    public String getRegion() {
//        return region;
//    }
//
//    public String getDistrict() {
//        return district;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public String getPlanningStructure() {
//        return planningStructure;
//    }
//
//    public String getStreet() {
//        return street;
//    }
//
//    public String getHouse() {
//        return house;
//    }
//
//    public String getServiceArea() {
//        return serviceArea;
//    }
//
//    public String getCountOfAnimals() {
//        return countOfAnimals;
//    }
    private Enterprise() {
    }

    ;

    public static class EnterpriseBuilder {
        private Enterprise enterprise;
        EnterpriseHandbooks enterpriseHandbooks = new EnterpriseHandbooks();
        private Address address = new Address();

        private String baseUri = "v3.dev.regagro.ru";
        private String pathAddress = "/ajax/address/object/";
        private String pathDistrict = pathAddress + address.getObject_guid() + "/children?levels=district";
        private String pathCity = pathAddress + address.getObject_guid() + "/children?levels=city,locality";
        private String pathStreet = pathAddress + address.getObject_guid() + "/children?levels=street";


        public EnterpriseBuilder setOwnerInn() {
            enterprise.ownerInn = "7736280231";
            return this;
        }

        public EnterpriseBuilder setName() {
            enterprise.name = DataGenerator.getEnterpriseName();
            return this;
        }

        public EnterpriseBuilder setTypeOfEnterprise() {
            enterprise.typeOfEnterprise = enterpriseHandbooks.getRandomEnterpriseType();
            return this;
        }

        public EnterpriseBuilder setDistrict() {
            enterprise.district = address.getAddress(baseUri, pathDistrict);
            address.setObjectGuid(baseUri,pathDistrict);
            return this;
        }

        public EnterpriseBuilder setCity() {
            enterprise.city = address.getAddress(baseUri, pathCity);
            address.setObjectGuid(baseUri, pathCity);
            return this;
        }

        public EnterpriseBuilder setStreet() {
            enterprise.street = address.getAddress(baseUri,pathStreet);
            address.setObjectGuid(baseUri,pathStreet);
            return this;
        }

        public EnterpriseBuilder setHouse() {
            enterprise.house = DataGenerator.getNumber(20);
            return this;
        }

        public EnterpriseBuilder setServiceArea() {
            enterprise.serviceArea = "2";
            return this;
        }

        public Enterprise build() {
            return enterprise;
        }
    }

}
