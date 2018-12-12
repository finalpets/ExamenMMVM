package com.peterleyva.examenmvvm.db;

import android.content.Context;
import android.os.AsyncTask;

import com.peterleyva.examenmvvm.SucursalDao;
import com.peterleyva.examenmvvm.model.Sucursal;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new  RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static  class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private SucursalDao sucursalDao;

        private PopulateDbAsyncTask(SucursalRoomDatabase db){
            sucursalDao = db.sucursalDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            sucursalDao.insert(new Sucursal("Gameloft","Madero","Nueva",1020,21396,"Mexicali","Mexico"
            ));
            return null;
        }
    }


}
