package br.mobfeel.nutripet.dao;

import java.util.List;

import br.mobfeel.nutripet.model.Month;

public interface MonthDao {
    public void create(Month month);
    public void delete(Month month);
    public void update(Month month);
    public List<Month> listAll();
    public List<Month> findByDogId(int id);
    public void deleteByDogId(int id);
}
