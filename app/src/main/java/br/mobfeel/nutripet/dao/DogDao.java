package br.mobfeel.nutripet.dao;

import java.util.List;

import br.mobfeel.nutripet.model.Dog;

public interface DogDao {
    public void create(Dog dog);
    public void delete(Dog dog);
    public void update(Dog dog);
    public List<Dog> listAll();
    public Dog findById(int id);
}
