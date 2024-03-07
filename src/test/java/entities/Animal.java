package entities;


import dataGenerator.DataGenerator;
import handbooks.AnimalHandbooks;
import lombok.Getter;

import java.sql.SQLException;

@Getter
public class Animal {
    private String kind;
    private String identificationNumber;
    private String markerType;
    private String markerPlace;
    private String firstMarkerDate;
    private String registrationGround;
    private String suit;
    private String birthDate;
    private String gender;
    private String nickName;
    private String keepType;
    private String keepPlace;
    private String productDirection;

    private Animal() {
    }

    public static class AnimalBuilder {
        private final Animal animal;
        AnimalHandbooks handbooks = new AnimalHandbooks();

        public AnimalBuilder() {
            this.animal = new Animal();
        }

        public AnimalBuilder setAnimalKind(String kind) {
            animal.kind = kind;
            return this;
        }

        public AnimalBuilder setMarkerType(String kind) {
            if (kind.matches("Пчёлы")) {
                animal.markerType = ("Табло");
            } else {
                animal.markerType = ("Чип");
            }
            return this;
        }

        public AnimalBuilder setIdentificationNumber() {
            if (animal.markerType.matches("Чип")) {
                animal.identificationNumber = DataGenerator.getNumber(15);
            }
            if (animal.markerType.matches("Табло")) {
                animal.identificationNumber = DataGenerator.getNumberWithFirst("2", 8);
            } else {
                animal.identificationNumber = DataGenerator.getNumber(15);
            }
            return this;
        }

        public AnimalBuilder setMarkerPlace() {
            animal.markerPlace = handbooks.getRandomMarkerPlace(animal.kind, animal.markerType);
            return this;
        }

        public AnimalBuilder setFirstMarkerDate() {
            animal.firstMarkerDate = DataGenerator.getLocalDate();
            return this;
        }

        public AnimalBuilder setRegistrationGround() throws SQLException {
            animal.registrationGround = handbooks.getRandomRegistrationGround();
            return this;
        }

        public AnimalBuilder setSuit() {
            animal.suit = handbooks.getRandomSuit(animal.kind);
            return this;
        }

        public AnimalBuilder setBirthDate() throws SQLException {
            if (animal.registrationGround.matches("Рождение животного")) {
                animal.birthDate = DataGenerator.getPastDateForBirthGround();
            } else {
                animal.birthDate = DataGenerator.getPastDate();
            }
            return this;
        }

        public AnimalBuilder setGender() {
            animal.gender = handbooks.getRandomGender();
            return this;
        }

        public AnimalBuilder setNickName() {
            animal.nickName = DataGenerator.getNickname();
            return this;
        }

        public AnimalBuilder setKeepType() throws SQLException {
            animal.keepType = handbooks.getRandomKeepType(animal.kind);
            return this;
        }

        public AnimalBuilder setKeepPlace() throws SQLException {
            animal.keepPlace = handbooks.getRandomKeepPlace(animal.kind);
            return this;
        }

        public AnimalBuilder setProductDirection() throws SQLException {
            animal.productDirection = handbooks.getRandomProductDirection(animal.kind);
            return this;
        }

        public Animal build() {
            return animal;
        }
    }
}
