package br.mobfeel.nutripet.dao.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.mobfeel.nutripet.dao.MonthDao;
import br.mobfeel.nutripet.model.Dog;
import br.mobfeel.nutripet.model.Month;

public class MonthDaoBd implements MonthDao {

    private DataBaseOpenHelper bdOpenHelper;
    private static String TABLE = "month";

    public MonthDaoBd(Context context) {
        this.bdOpenHelper = new DataBaseOpenHelper(context);
    }

    @Override
    public void create(Month month) {
        SQLiteDatabase base = bdOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("month", month.getMonth());
        values.put("value", month.getValue());
        values.put("dog_id", month.getDog().getId());
        base.insert(MonthDaoBd.TABLE, null, values);
        base.close();
    }

    @Override
    public void delete(Month month) {
        SQLiteDatabase base = bdOpenHelper.getWritableDatabase();
        base.delete(MonthDaoBd.TABLE, "month_id=?",
                new String[]{String.valueOf(month.getId())}  );
        base.close();
    }

    @Override
    public void update(Month month) {
        SQLiteDatabase base = bdOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("month", month.getMonth());
        values.put("value", month.getValue());
        base.update(MonthDaoBd.TABLE, values, "month_id=?",
                new String[]{String.valueOf(month.getId())});
        base.close();
    }

    @Override
    public List<Month> listAll() {
        SQLiteDatabase base = bdOpenHelper.getReadableDatabase();
        Cursor cursor = base.query(MonthDaoBd.TABLE,
                new String[] {"month_id", "month", "value", "dog_id"},
                null, null, null, null, "month");
        List<Month> monthList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("month_id"));
            int dogId = cursor.getInt(cursor.getColumnIndex("dog_id"));
            int monthIndex = cursor.getInt(cursor.getColumnIndex("month"));
            Double value = cursor.getDouble(cursor.getColumnIndex("value"));

            Dog dog = new Dog(dogId);
            Month month = new Month(id,monthIndex, dog, value);
            monthList.add(month);
        }

        base.close();

        return monthList;
    }

    @Override
    public List<Month> findByDogId(int id) {
        SQLiteDatabase base = bdOpenHelper.getReadableDatabase();
        Cursor cursor = base.query(MonthDaoBd.TABLE,
                new String[]{"month_id", "month", "value", "dog_id"},
                "dog_id=?", new String[]{String.valueOf(id)}, null, null, "month" );
        List<Month> monthList = new ArrayList<>();
        while(cursor.moveToNext()) {
            int monthId = cursor.getInt(cursor.getColumnIndex("month_id"));
            int dogId = cursor.getInt(cursor.getColumnIndex("dog_id"));
            int monthIndex = cursor.getInt(cursor.getColumnIndex("month"));
            Double value = cursor.getDouble(cursor.getColumnIndex("value"));

            Dog dog = new Dog(dogId);
            Month month = new Month(monthId,monthIndex, dog, value);
            monthList.add(month);
        }

        return monthList;
    }
}
