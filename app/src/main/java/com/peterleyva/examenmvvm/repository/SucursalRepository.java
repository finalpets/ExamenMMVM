package com.peterleyva.examenmvvm.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.peterleyva.examenmvvm.SucursalDao;
import com.peterleyva.examenmvvm.db.SucursalRoomDatabase;
import com.peterleyva.examenmvvm.model.Sucursal;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SucursalRepository {

    private SucursalDao sucursalDao;
    private LiveData<List<Sucursal>> mAllSucursal;

    public SucursalRepository(Application application) {
        SucursalRoomDatabase db = SucursalRoomDatabase.getDatabase(application);
        sucursalDao = db.sucursalDao();
        mAllSucursal = sucursalDao.getAllSucursales();
    }

    public LiveData<List<Sucursal>> getAllSucursales() {
        return mAllSucursal;
    }

    public void insert (Sucursal sucursal) {
        new insertAsyncTask(sucursalDao).execute(sucursal);
    }

    private static class insertAsyncTask extends AsyncTask<Sucursal, Void, Void> {

        private SucursalDao mAsyncTaskDao;

        insertAsyncTask(SucursalDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Sucursal... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
