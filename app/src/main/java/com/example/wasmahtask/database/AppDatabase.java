package com.example.wasmahtask.database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.wasmahtask.models.RefCountryCodesItem;

@Database(entities = {RefCountryCodesItem.class},version = 2,exportSchema = false)
public abstract  class AppDatabase extends RoomDatabase {

    private static final String database_name="tasks";
    private static AppDatabase appDatabase=null;

    public static AppDatabase getAppDatabase(Context context){
        if(appDatabase==null){
            appDatabase= Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,database_name)
                    .allowMainThreadQueries().build();



        }
        return appDatabase;
    }

    public abstract LocationDao getAction();
}
