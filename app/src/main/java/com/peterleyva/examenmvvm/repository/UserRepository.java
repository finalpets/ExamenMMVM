package com.peterleyva.examenmvvm.repository;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;

import com.peterleyva.examenmvvm.dao.EmployeeDao;
import com.peterleyva.examenmvvm.dao.SucursalDao;
import com.peterleyva.examenmvvm.model.Employee;
import com.peterleyva.examenmvvm.model.Sucursal;
import com.peterleyva.examenmvvm.model.User;
import com.peterleyva.examenmvvm.dao.UserDao;
import com.peterleyva.examenmvvm.db.UserDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UserRepository {

    private UserDao userDao;
    private SucursalDao sucursalDao;
    private EmployeeDao employeeDao;
    private LiveData<List<User>> allUsers;
    private LiveData<List<Sucursal>> allSucursales;
    private LiveData<List<Employee>> allEmployees;
    private List<Sucursal> allSucursales_ByUserId;

    public UserRepository(Application application){
        UserDatabase database = UserDatabase.getInstance(application);
        userDao = database.userDao();
        sucursalDao = database.sucursalDao();
        employeeDao = database.employeeDao();
        allUsers = userDao.getAllUsers();
        allSucursales = sucursalDao.getAllSucursales();
        allEmployees = employeeDao.getAllEmployeees();
        //allSucursales_ByUserId = sucursalDao.getAllSucursales_ByUserId(id)

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

    public LiveData<User> getUser(int userId) {
        // Returns a LiveData object directly from the database.
        return userDao.getUserById(userId);
    }

    public LiveData<User> getUserLogin(String email,String password) {
        // Returns a LiveData object directly from the database.
        return userDao.getUserLogin(email,password);
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

    public void deleteSucursal(Sucursal sucursal){
        new DeleteSucursalAsyncTask(sucursalDao).execute(sucursal);

    }

    public void updateSucursal(Sucursal sucursal){
        new UpdateSucursalAsyncTask(sucursalDao).execute(sucursal);

    }


    public void deleteAllSucursales(){
        new DeleteAllSucursalesAsyncTask(sucursalDao).execute();

    }

    public LiveData<List<Sucursal>> getAllSucursales() {
        return allSucursales;
    }


//    public List<Sucursal> getAllSucursales_ByUserId(Integer id) {
//        return new getAllSucursales_ByUserId(sucursalDao).execute(id);
//        //return sucursalDao.getAllSucursales_ByUserId(id);
//    }
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


    private static class DeleteSucursalAsyncTask extends AsyncTask<Sucursal, Void, Void> {

        private SucursalDao mAsyncTaskDao;

        private DeleteSucursalAsyncTask(SucursalDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Sucursal... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
    private static class UpdateSucursalAsyncTask extends AsyncTask<Sucursal, Void, Void> {

        private SucursalDao mAsyncTaskDao;

        private UpdateSucursalAsyncTask(SucursalDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Sucursal... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }


    private static class DeleteAllSucursalesAsyncTask extends AsyncTask<Void,Void ,Void>{

        private SucursalDao sucursalDao;

        private DeleteAllSucursalesAsyncTask (SucursalDao sucursalDao){
            this.sucursalDao = sucursalDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            sucursalDao.deleteAllSucursales();
            return null;

        }
    }
//    private static class getAllSucursales_ByUserId extends AsyncTask<Integer, Void, Void> {
//
//        private SucursalDao mAsyncTaskDao;
//
//        private getAllSucursales_ByUserId(SucursalDao dao) {
//            this.mAsyncTaskDao = dao;
//        }
//
//
//        @Override
//        protected Void doInBackground(Integer... integers) {
//            mAsyncTaskDao.getAllSucursales_ByUserId(integers[0]);
//            return null;
//        }
//    }



    /// ========== Employees ===========

    public void insertEmployee(Employee employee){
        new InsertEmployeeAsyncTask(employeeDao).execute(employee);
    }

    private static class InsertEmployeeAsyncTask extends AsyncTask<Employee, Void, Void> {

        private EmployeeDao mAsyncTaskDao;

        private InsertEmployeeAsyncTask(EmployeeDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Employee... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public LiveData<List<Employee>> getAllEmployees() {
        return allEmployees;
    }

}
