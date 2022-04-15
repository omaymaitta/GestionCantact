package com.example.annuaire2.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={Contact.class}, version=1)
public abstract class  Appdatabase extends RoomDatabase {
    public abstract ContactDAO contactDAO();
    private static Appdatabase INSTANCE;
    public static Appdatabase getDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    Appdatabase.class,"contacts.db").allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
}
}
