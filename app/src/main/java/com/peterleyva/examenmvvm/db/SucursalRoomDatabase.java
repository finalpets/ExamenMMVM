package com.peterleyva.examenmvvm.db;

import android.content.Context;

import com.peterleyva.examenmvvm.SucursalDao;
import com.peterleyva.examenmvvm.model.Sucursal;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Sucursal.class,version = 1)
public abstract class SucursalRoomDatabase extends RoomDatabase {

    private static SucursalRoomDatabase INSTANCE;
    public abstract SucursalDao sucursalDao();

    public static SucursalRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (SucursalRoomDatabase.class){
                if(INSTANCE == null){
                    //Create Database Here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SucursalRoomDatabase.class,"sucursal_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
