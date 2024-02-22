package org.regagro.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.regagro.handbooks.MarkerTypes;
import org.regagro.helpers.DBHelper;


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
       MarkerTypes markerTypes = new MarkerTypes();
        public AnimalBuilder(){
           this.animal = new Animal();
       }

        public AnimalBuilder setAnimalKind(String kind) {
            animal.kind = kind;
            return this;
        }
        public AnimalBuilder setMarkerType(){
            animal.markerType = markerTypes.getRandomMarkerType(animal.kind);
            return this;
        }

    }
}
