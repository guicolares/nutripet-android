package br.mobfeel.nutripet.model;

import java.io.Serializable;

public class Month implements Serializable {
    private int id;
    private int month;
    private Dog dog;
    private Double value; //Weight


    public Month(int id, int month, Dog dog, Double value) {
        this.id = id;
        this.month = month;
        this.dog = dog;
        this.value = value;
    }

    public Month(int month, Dog dog, Double value) {
        this.month = month;
        this.dog = dog;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
