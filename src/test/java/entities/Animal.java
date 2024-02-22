package entities;


import handbooks.AnimalHandbooks;

public class Animal {
    private String id;
    private String kind;
    private String identificationNumber;
    private String markerType;
    private String markerPlace;
    private String firstMarkerDate;
    private String foundation;
    private String suit;
    private String birthDate;
    private String gender;
    private String nickName;
    private String keepTypes;
    private String keepPlaces;
    private String productDirections;
    private String registrationDate;
    private boolean isGroup;
    private String enterprise;


    public class AnimalBuilder{
       private Animal animal;
       AnimalHandbooks handbooks = new AnimalHandbooks();
        public AnimalBuilder(){
           this.animal = new Animal();
       }

        public AnimalBuilder setAnimalKind(String kind) {
            animal.kind = kind;
            return this;
        }
        public AnimalBuilder setMarkerType(){
            animal.markerType = handbooks.getRandomMarkerType(animal.kind);
            return this;
        }

    }
}
