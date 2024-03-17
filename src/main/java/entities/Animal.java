package entities;


import dataGenerator.DataGenerator;
import handbooks.AnimalHandbooks;
import lombok.Getter;

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
        DataGenerator dataGenerator = new DataGenerator();

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
                animal.identificationNumber = dataGenerator.getNumber(15);
            }
            if (animal.markerType.matches("Табло")) {
                animal.identificationNumber = dataGenerator.getNumberWithFirst("2", 8);
            } else {
                animal.identificationNumber = dataGenerator.getNumber(15);
            }
            return this;
        }

        public AnimalBuilder setMarkerPlace() {
            animal.markerPlace = handbooks.getRandomMarkerPlace(animal.kind, animal.markerType);
            return this;
        }

        public AnimalBuilder setFirstMarkerDate() {
            animal.firstMarkerDate = dataGenerator.getLocalDate();
            return this;
        }

        public AnimalBuilder setRegistrationGround() {
            animal.registrationGround = handbooks.getRandomRegistrationGround();
            return this;
        }

        public AnimalBuilder setSuit() {
            animal.suit = handbooks.getRandomSuit(animal.kind);
            return this;
        }

        public AnimalBuilder setBirthDate() {
            if (animal.registrationGround.matches("Рождение животного")) {
                animal.birthDate = dataGenerator.getPastDateForBirthGround();
            } else {
                animal.birthDate = dataGenerator.getPastDate();
            }
            return this;
        }

        public AnimalBuilder setGender() {
            animal.gender = handbooks.getRandomGender();
            return this;
        }

        public AnimalBuilder setNickName() {
            animal.nickName = dataGenerator.getNickname();
            return this;
        }

        public AnimalBuilder setKeepType() {
            animal.keepType = handbooks.getRandomKeepType(animal.kind);
            return this;
        }

        public AnimalBuilder setKeepPlace() {
            animal.keepPlace = handbooks.getRandomKeepPlace(animal.kind);
            return this;
        }

        public AnimalBuilder setProductDirection() {
            animal.productDirection = handbooks.getRandomProductDirection(animal.kind);
            return this;
        }

        public Animal build() {
            return animal;
        }
    }

    public static Animal createAnimal(String kind) {
        if (kind.equals("Крупный рогатый скот")) {
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
        if (kind.equals("Куры")) {
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
//        if (kind.equals("Пчёлы")) {
//            return new Animal.AnimalBuilder()
//                    .setAnimalKind(kind)
//                    .setMarkerType(kind)
//                    .setMarkerPlace()
//                    .setIdentificationNumber()
//                    .setFirstMarkerDate()
//                    .setRegistrationGround()
//                    .setSuit()
//                    .setBirthDate()
//                    .setKeepType()
//                    .setKeepPlace()
//                    .setProductDirection()
//                    .build();
//        }
        else {
            return null;
        }
    }
}
