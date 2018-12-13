package com.peterleyva.examenmvvm.db;

import android.content.Context;
import android.os.AsyncTask;

import com.peterleyva.examenmvvm.SucursalDao;
import com.peterleyva.examenmvvm.model.Sucursal;
import com.peterleyva.examenmvvm.model.User;
import com.peterleyva.examenmvvm.UserDao;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class,Sucursal.class},version = 2)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;

    public static final String DATABASE_NAME = "doc-digitales-db";

    public abstract UserDao userDao();
    public abstract SucursalDao sucursalDao();

    public static synchronized UserDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                       UserDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;

    }
    private static RoomDatabase.Callback roomCallback = new  RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static  class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private UserDao userDao;

        private PopulateDbAsyncTask(UserDatabase db){
            userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            userDao.insert(new User("finalpets","pets.leya.bazan@gmail.com","BAFAJDALAK","2410pets","Gameloft",
//                    "2410pets"
//            ));
//            userDao.insert(new User("finalpets2","pets.leya.bazan@gmail.com","BAFAJDALAK","2410pets","Gameloft",
//                    "2410pets"
//            ));

            return null;
        }
    }
}
