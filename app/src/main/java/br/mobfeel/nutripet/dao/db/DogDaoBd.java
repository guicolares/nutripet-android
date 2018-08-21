package br.mobfeel.nutripet.dao.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.mobfeel.nutripet.dao.DogDao;
import br.mobfeel.nutripet.model.Dog;

public class DogDaoBd implements DogDao{

    private DataBaseOpenHelper bdOpenHelper;
    private static String TABLE = "dog";

    public DogDaoBd(Context context) {
        this.bdOpenHelper = new DataBaseOpenHelper(context);
    }

    @Override
    public void create(Dog dog) {
        SQLiteDatabase base = bdOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", dog.getName());
        values.put("race", dog.getRace());
        base.insert(DogDaoBd.TABLE, null, values);
        base.close();
    }

    @Override
    public void delete(Dog dog) {
        SQLiteDatabase base = bdOpenHelper.getWritableDatabase();
        base.delete(DogDaoBd.TABLE, "dog_id=?",
                new String []{String.valueOf(dog.getId())});
        base.close();
    }

    @Override
    public void update(Dog dog) {
        SQLiteDatabase base = bdOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", dog.getName());
        values.put("race", dog.getRace());
        base.update(DogDaoBd.TABLE, values, "dog_id=?",
                new String[] {String.valueOf(dog.getId())});
        base.close();

        Log.d("EditDog", Integer.toString(dog.getId()));
    }

    @Override
    public List<Dog> listAll() {
        SQLiteDatabase base = bdOpenHelper.getReadableDatabase();
        Cursor cursor = base.query(DogDaoBd.TABLE,
                new String[] {"dog_id", "name", "race"},
                null, null, null, null, "name");
        List<Dog> dogList = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("dog_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String race = cursor.getString(cursor.getColumnIndex("race"));
            Dog dog = new Dog(id, name, race, 10);
            dogList.add(dog);
        }
        base.close();
        return dogList;
    }

    @Override
    public Dog findById(int id) {
        return null;
    }
}
