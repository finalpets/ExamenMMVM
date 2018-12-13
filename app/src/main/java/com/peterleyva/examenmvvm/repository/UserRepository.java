package com.peterleyva.examenmvvm.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.peterleyva.examenmvvm.SucursalDao;
import com.peterleyva.examenmvvm.model.Sucursal;
import com.peterleyva.examenmvvm.model.User;
import com.peterleyva.examenmvvm.UserDao;
import com.peterleyva.examenmvvm.db.UserDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UserRepository {

    private UserDao userDao;
    private SucursalDao sucursalDao;
    private LiveData<List<User>> allUsers;
    private LiveData<List<Sucursal>> allSucursales;

    public UserRepository(Application application){
        UserDatabase database = UserDatabase.getInstance(application);
        userDao = database.userDao();
        sucursalDao = database.sucursalDao();
        allUsers = userDao.getAllUsers();
        allSucursales = sucursalDao.getAllSucursales();

    }

    public  void  insert(User user){
        new InsertUserAsyncTask(userDao).execute(user);

    }



    public void update(User user){
        new UpdatetUserAsyncTask(userDao).execute(user);

    }

    public void delete(User user){
        new DeleteUserAsyncTask(userDao).execute(user);

    }

    public void deleteAllUsers(){
        new DeleteAllUsersAsyncTask(userDao).execute();

    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User,Void ,Void>{

        private UserDao userDao;

        private InsertUserAsyncTask (UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {

            userDao.insert(users[0]);
            return null;

        }
    }

    private static class UpdatetUserAsyncTask extends AsyncTask<User,Void ,Void>{

        private UserDao userDao;

        private UpdatetUserAsyncTask (UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {

            userDao.update(users[0]);
            return null;

        }
    }
    private static class DeleteUserAsyncTask extends AsyncTask<User,Void ,Void>{

        private UserDao userDao;

        private DeleteUserAsyncTask (UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {

            userDao.delete(users[0]);
            return null;


        }
    }
    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void,Void ,Void>{

        private UserDao userDao;

        private DeleteAllUsersAsyncTask (UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;

        }
    }

    public void insertSucursal(Sucursal sucursal){
        new InsertSucursalAsyncTask(sucursalDao).execute(sucursal);
    }

    public LiveData<List<Sucursal>> getAllSucursales() {
        return allSucursales;
    }

    /// ========== Sucursales ===========
    private static class InsertSucursalAsyncTask extends AsyncTask<Sucursal, Void, Void> {

        private SucursalDao mAsyncTaskDao;

        private InsertSucursalAsyncTask(SucursalDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Sucursal... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
