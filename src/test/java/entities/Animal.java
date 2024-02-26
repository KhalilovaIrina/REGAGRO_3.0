package entities;


import dataGenerator.DataGenerator;
import handbooks.AnimalHandbooks;
import lombok.Getter;

import java.sql.SQLException;

public class Animal {
    private String id;
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
    private String registrationDate;
    private boolean isGroup;
    private String enterprise;

    public String getId() {
        return this.id;
    }

    public String getKind() {
        return  this.kind;
    }

    public String getIdentificationNumber() {
        return  this.identificationNumber;
    }

    public String getMarkerType() {
        return  this.markerType;
    }

    public String getMarkerPlace() {
        return  this.markerPlace;
    }

    public String getFirstMarkerDate() {
        return  this.firstMarkerDate;
    }

    public String getRegistrationGround() {
        return  this.registrationGround;
    }

    public String getSuit() {
        return  this.suit;
    }

    public String getBirthDate() {
        return  this.birthDate;
    }

    public String getGender() {
        return  this.gender;
    }

    public String getNickName() {
        return  this.nickName;
    }

    public String getKeepType() {
        return  this.keepType;
    }

    public String getKeepPlace() {
        return  this.keepPlace;
    }

    public String getProductDirection() {
        return  this.productDirection;
    }

    public String getRegistrationDate() {
        return  this.registrationDate;
    }

    public boolean isGroup() {
        return  this.isGroup;
    }

    public String getEnterprise() {
        return  this.enterprise;
    }

    private Animal() {}

    public static class AnimalBuilder {
        private Animal animal;
        AnimalHandbooks handbooks = new AnimalHandbooks();

        public AnimalBuilder() {
            this.animal = new Animal();
        }

        public AnimalBuilder setAnimalKind(String kind) {
            animal.kind = kind;
            return this;
        }

        public AnimalBuilder setMarkerType() {
            animal.markerType = ("Чип");
            return this;
        }

        public AnimalBuilder setIdentificationNumber() {
            animal.identificationNumber = DataGenerator.getNumber(15);
            return this;
        }
        public AnimalBuilder setMarkerPlace(){
            animal.markerPlace = handbooks.getRandomMarkerPlace(animal.kind);
            return this;
        }
        public AnimalBuilder setFirstMarkerDate(){
            animal.firstMarkerDate = DataGenerator.getLocalDate();
            return this;
        }
        public AnimalBuilder setRegistrationGround() throws SQLException {
            animal.registrationGround = handbooks.getRandomRegistrationGround();
            return this;
        }
        public AnimalBuilder setSuit(){
            animal.suit = handbooks.getRandomSuit(animal.kind);
            return this;
        }
        public AnimalBuilder setBirthDate() throws SQLException {
            if (animal.registrationGround.matches("Рождение животного")){
                animal.birthDate = DataGenerator.getPastDateForBirthGround();
            } else {
                animal.birthDate = DataGenerator.getPastDate();
            }
            return this;
        }
        public AnimalBuilder setGender(){
            animal.gender = handbooks.getRandomGender();
            return this;
        }
        public AnimalBuilder setNickName(){
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
        public Animal build(){
            return animal;
        }
    }
}
