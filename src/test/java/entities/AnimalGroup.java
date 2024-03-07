package entities;

import dataGenerator.DataGenerator;
import handbooks.AnimalHandbooks;
import lombok.Getter;

import java.sql.SQLException;

@Getter
public class AnimalGroup {
    private String kind;
    private String identificationNumber;
    private String markerType;
    private String markerPlace;
    private String firstMarkerDate;
    private String registrationGround;
    private String birthDateFrom;
    private String birthDateBefore;
    private String gender;
    private String count;
    private String countOfMale;
    private String countOfFemale;
    private String keepType;
    private String keepPlace;
    private String productDirection;

    private AnimalGroup() {
    }

    public static class AnimalGroupBuilder {
        private final AnimalGroup animalGroup;
        AnimalHandbooks handbooks = new AnimalHandbooks();

        public AnimalGroupBuilder() {
            this.animalGroup = new AnimalGroup();
        }

        public AnimalGroup.AnimalGroupBuilder setAnimalKind(String kind) {
            animalGroup.kind = kind;
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setMarkerType(String kind) {
            if (kind.matches("Пчёлы")) {
                animalGroup.markerType = ("Табло");
            } else {
                animalGroup.markerType = ("Чип");
            }
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setIdentificationNumber() {
            if (animalGroup.markerType.matches("Чип")) {
                animalGroup.identificationNumber = DataGenerator.getNumber(15);
            }
            if (animalGroup.markerType.matches("Табло")) {
                animalGroup.identificationNumber = DataGenerator.getNumberWithFirst("2", 8);
            } else {
                animalGroup.identificationNumber = DataGenerator.getNumber(15);
            }
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setMarkerPlace() {
            animalGroup.markerPlace = handbooks.getRandomMarkerPlace(animalGroup.kind, animalGroup.markerType);
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setFirstMarkerDate() {
            animalGroup.firstMarkerDate = DataGenerator.getLocalDate();
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setRegistrationGround() throws SQLException {
            animalGroup.registrationGround = handbooks.getRandomRegistrationGround();
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setBirthDateRange() throws SQLException {
            animalGroup.birthDateFrom = DataGenerator.getDateRange(animalGroup.registrationGround).get(0);
            animalGroup.birthDateBefore = DataGenerator.getDateRange(animalGroup.registrationGround).get(1);
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setGender() {
            animalGroup.gender = handbooks.getRandomGenderForGroup();
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setCount() {
            animalGroup.count = DataGenerator.getNumber(2);
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setCountOfMale() {
            animalGroup.countOfMale = DataGenerator.getNumber(0, Integer.parseInt(animalGroup.count));
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setCountOfFemale() {
            int bound = Integer.parseInt(animalGroup.count);
            int male = Integer.parseInt(animalGroup.countOfMale);
            animalGroup.countOfFemale = String.valueOf(bound - male);
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setKeepType() throws SQLException {
            animalGroup.keepType = handbooks.getRandomKeepType(animalGroup.kind);
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setKeepPlace() throws SQLException {
            animalGroup.keepPlace = handbooks.getRandomKeepPlace(animalGroup.kind);
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setProductDirection() throws SQLException {
            animalGroup.productDirection = handbooks.getRandomProductDirection(animalGroup.kind);
            return this;
        }

        public AnimalGroup build() {
            return animalGroup;
        }
    }
}

