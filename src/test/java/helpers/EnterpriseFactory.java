package helpers;

import entities.Enterprise;

public class EnterpriseFactory {
    public Enterprise createEnterprise(){
        return new Enterprise.EnterpriseBuilder()
                .setOwnerInn()
                .setName()
                .setTypeOfEnterprise()
                .setDistrict()
                .setCity()
                .setStreet()
                .setHouse()
                .setServiceArea()
                .build();
    }
}
