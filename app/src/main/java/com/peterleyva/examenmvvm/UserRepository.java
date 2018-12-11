package com.peterleyva.examenmvvm;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application){
        UserDatabase database = UserDatabase.getInstance(application);
        userDao = database.userDao();
        allUsers = userDao.getAllUsers();

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
}
