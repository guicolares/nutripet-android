package br.mobfeel.nutripet.model;

import java.io.Serializable;

public class Dog implements Serializable {

    private int id;
    private String name, race;
    private int imageR;
    private String imagePath;

    public Dog(int id, String name, String race, int imageR) {
        this.name = name;
        this.race = race;
        this.imageR = imageR;
        this.id = id;
    }

    public Dog(String name, String race) {
        this.name = name;
        this.race = race;
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

    public int getImageR() {
        return imageR;
    }

    public void setImageR(int imageR) {
        this.imageR = imageR;
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

