package helpers;

import entities.Animal;

import java.sql.SQLException;

public class AnimalFactory {
    public Animal createChicken() throws SQLException {
        Animal chicken = new Animal.AnimalBuilder()
                .setAnimalKind("Куры")
                .setMarkerType()
                .setMarkerPlace()
                .setIdentificationNumber()
                .setFirstMarkerDate()
                .setRegistrationGround()
                .setSuit()
                .setBirthDate()
                .setGender()
                .setNickName()
                .setKeepType()
                .setKeepPlace()
                .setProductDirection()
                .build();
        return chicken;
    }

    public Animal createKRS() throws SQLException {
        Animal krs = new Animal.AnimalBuilder()
                .setAnimalKind("Крупный рогатый скот")
                .setMarkerType()
                .setMarkerPlace()
                .setIdentificationNumber()
                .setFirstMarkerDate()
                .setRegistrationGround()
                .setSuit()
                .setBirthDate()
                .setGender()
                .setNickName()
                .setKeepType()
                .setKeepPlace()
                .setProductDirection()
                .build();
        return krs;
    }

    public Animal createBees() throws SQLException {
        Animal bees = new Animal.AnimalBuilder()
                .setAnimalKind("Пчёлы")
                .setMarkerType()
                .setMarkerPlace()
                .setIdentificationNumber()
                .setFirstMarkerDate()
                .setRegistrationGround()
                .setSuit()
                .setBirthDate()
                .setKeepType()
                .setKeepPlace()
                .setProductDirection()
                .build();
        return bees;
    }

    public Animal createGroupOfPig() throws SQLException {
        Animal groupOfPig = new Animal.AnimalBuilder()
                .setAnimalKind("Свиньи")
                .setMarkerType()
                .setMarkerPlace()
                .setIdentificationNumber()
                .setFirstMarkerDate()
                .setRegistrationGround()
                .setBirthDate()
                .setGender()
                .setNickName()
                .setKeepType()
                .setKeepPlace()
                .setProductDirection()
                .build();
        return groupOfPig;
    }
}
