package br.mobfeel.nutripet.dao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    private static String dbName = "nutriped.db";
    private static String createTableDog = "CREATE TABLE dog" +
            "(dog_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name VARCHAR(30)," +
            "race varchar(30)," +
            "image_path VARCHAR(100))";
    private  static  String createTableMonth = "CREATE TABLE month" +
            "(month_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "month INTEGER NOT NULL," +
            "value DOUBLE NOT NULL," +
            "dog_id INTEGER NOT NULL)";

    public DataBaseOpenHelper(Context context) {
        super(context, dbName, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableDog);
        db.execSQL(createTableMonth);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
