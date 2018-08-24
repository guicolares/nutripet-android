package br.mobfeel.nutripet.model;

import java.io.Serializable;

public class Dog implements Serializable {

    private int id;
    private String name, race;
    private int imageId;

    public Dog(int id, String name, String race, int imageId) {
        this.name = name;
        this.race = race;
        this.id = id;
        this.imageId = imageId;
    }

    public Dog(String name, String race, int imageId) {
        this.name = name;
        this.race = race;
        this.imageId = imageId;
    }

    public Dog(int id){
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageR) {
        this.imageId = imageR;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }


}

