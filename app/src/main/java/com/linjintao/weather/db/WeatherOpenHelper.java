package com.linjintao.weather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *创建数据库表
 */
public class WeatherOpenHelper extends SQLiteOpenHelper {
    /**
     * 这是创建城市表的语句
     */
    public static final String CREATE_CITY = "create table City("
            + "id integer primary key autoincrement, "
            +"city_name text)";
    public WeatherOpenHelper(Context context, String name,
                             SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
