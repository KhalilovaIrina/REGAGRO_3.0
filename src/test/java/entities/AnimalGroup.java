package entities;

import dataGenerator.DataGenerator;
import handbooks.AnimalHandbooks;

import java.sql.SQLException;

public class AnimalGroup {
    private String id;
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
    private String registrationDate;
    private boolean isGroup;
    private String enterprise;

    private AnimalGroup() {
    }

    public String getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getMarkerType() {
        return markerType;
    }

    public String getMarkerPlace() {
        return markerPlace;
    }

    public String getFirstMarkerDate() {
        return firstMarkerDate;
    }

    public String getRegistrationGround() {
        return registrationGround;
    }

    public String getBirthDateFrom() {
        return birthDateFrom;
    }

    public String getBirthDateBefore() {
        return birthDateBefore;
    }

    public String getGender() {
        return gender;
    }

    public String getCount() {
        return count;
    }

    public String getCountOfMale() {
        return countOfMale;
    }

    public String getCountOfFemale() {
        return countOfFemale;
    }

    public String getKeepType() {
        return keepType;
    }

    public String getKeepPlace() {
        return keepPlace;
    }

    public String getProductDirection() {
        return productDirection;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public static class AnimalGroupBuilder {
        private AnimalGroup animalGroup;
        AnimalHandbooks handbooks = new AnimalHandbooks();

        public AnimalGroupBuilder() {
            this.animalGroup = new AnimalGroup();
        }

        public AnimalGroup.AnimalGroupBuilder setAnimalKind(String kind) {
            animalGroup.kind = kind;
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setMarkerType() {
            animalGroup.markerType = ("Чип");
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setIdentificationNumber() {
            animalGroup.identificationNumber = DataGenerator.getNumber(15);
            return this;
        }

        public AnimalGroup.AnimalGroupBuilder setMarkerPlace() {
            animalGroup.markerPlace = handbooks.getRandomMarkerPlace(animalGroup.kind);
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
            animalGroup.countOfFemale = String.valueOf(bound-male);
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

