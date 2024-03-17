package entities;

import dataGenerator.DataGenerator;
import handbooks.AnimalHandbooks;
import lombok.Getter;

import java.sql.SQLException;
import java.util.List;

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
        DataGenerator dataGenerator = new DataGenerator();

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
                animalGroup.identificationNumber = dataGenerator.getNumber(15);
            }
            if (animalGroup.markerType.matches("Табло")) {
                animalGroup.identificationNumber = dataGenerator.getNumberWithFirst("2", 8);
            } else {
                animalGroup.identificationNumber = dataGenerator.getNumber(15);
            }
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setMarkerPlace() {
            animalGroup.markerPlace = handbooks.getRandomMarkerPlace(animalGroup.kind, animalGroup.markerType);
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setFirstMarkerDate() {
            animalGroup.firstMarkerDate = dataGenerator.getLocalDate();
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setRegistrationGround() {
            animalGroup.registrationGround = handbooks.getRandomRegistrationGround();
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setBirthDateRange() {
            if (animalGroup.kind.matches("Пчёлы")) {
                animalGroup.birthDateFrom = dataGenerator.getDateRange(animalGroup.registrationGround).get(0);
            } else {
                List<String> dateRange = dataGenerator.getDateRange(animalGroup.registrationGround);
                animalGroup.birthDateFrom = dateRange.get(0);
                animalGroup.birthDateBefore = dateRange.get(1);
            }
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setGender() {
            animalGroup.gender = handbooks.getRandomGenderForGroup();
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setCount() {
            animalGroup.count = dataGenerator.getNumberRange(2,20);
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setCountOfMale() {
            if (animalGroup.gender.matches("Самцы")) {
                animalGroup.countOfMale = animalGroup.count;
            } else
                animalGroup.countOfMale = dataGenerator.getNumberRange(1, Integer.parseInt(animalGroup.count));
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setCountOfFemale() {
            if (animalGroup.gender.matches("Самцы")) {
                animalGroup.countOfMale = animalGroup.count;
            } else {
                int bound = Integer.parseInt(animalGroup.count);
                int male = Integer.parseInt(animalGroup.countOfMale);
                animalGroup.countOfFemale = String.valueOf(bound - male);
            }
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setKeepType() {
            animalGroup.keepType = handbooks.getRandomKeepType(animalGroup.kind);
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setKeepPlace() {
            animalGroup.keepPlace = handbooks.getRandomKeepPlace(animalGroup.kind);
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setProductDirection() {
            animalGroup.productDirection = handbooks.getRandomProductDirection(animalGroup.kind);
            return this;
        }

        public AnimalGroup build() {
            return animalGroup;
        }
    }

    public static AnimalGroup createAnimalGroup(String kind) {
        if (kind.equals("Свиньи")) {
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
        if (kind.equals("Пчёлы")) {
            return new AnimalGroup.AnimalGroupBuilder()
                    .setAnimalKind(kind)
                    .setMarkerType(kind)
                    .setMarkerPlace()
                    .setIdentificationNumber()
                    .setFirstMarkerDate()
                    .setRegistrationGround()
                    .setBirthDateRange()
                    .setKeepType()
                    .setKeepPlace()
                    .setProductDirection()
                    .build();
        } else {
            return null;
        }
    }


}

