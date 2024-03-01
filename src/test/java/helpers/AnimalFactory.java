package helpers;

import entities.Animal;
import entities.AnimalGroup;

import java.sql.SQLException;

public class AnimalFactory {
    public Animal createChicken() throws SQLException {
        String kind = "Куры";
        return new Animal.AnimalBuilder()
                .setAnimalKind(kind)
                .setMarkerType(kind)
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
    }

    public Animal createKRS() throws SQLException {
        String kind = "Крупный рогатый скот";
        return new Animal.AnimalBuilder()
                .setAnimalKind(kind)
                .setMarkerType(kind)
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
    }

    public Animal createBees() throws SQLException {
        String kind = "Пчёлы";
        return new Animal.AnimalBuilder()
                .setAnimalKind(kind)
                .setMarkerType(kind)
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
    }

    public AnimalGroup createGroupOfPig() throws SQLException {
        String kind = "Свиньи";
        return new AnimalGroup.AnimalGroupBuilder()
                .setAnimalKind(kind)
                .setMarkerType(kind)
                .setMarkerPlace()
                .setIdentificationNumber()
                .setFirstMarkerDate()
                .setRegistrationGround()
                .setBirthDateRange()
                .setGender()
                .setCount()
                .setCountOfMale()
                .setCountOfFemale()
                .setKeepType()
                .setKeepPlace()
                .setProductDirection()
                .build();
    }
}
