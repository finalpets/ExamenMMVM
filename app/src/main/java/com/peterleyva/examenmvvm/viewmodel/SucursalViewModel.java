package com.peterleyva.examenmvvm.viewmodel;

import android.app.Application;

import com.peterleyva.examenmvvm.model.Sucursal;
import com.peterleyva.examenmvvm.repository.UserRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SucursalViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    private LiveData<List<Sucursal>> mAllSucursales;

    public SucursalViewModel(Application application){
        super(application);
        mRepository = new UserRepository(application);
        mAllSucursales = mRepository.getAllSucursales();
    }

    public LiveData<List<Sucursal>> getAllSucursales() { return mAllSucursales; }

    public void insert(Sucursal sucursal) { mRepository.insertSucursal(sucursal); }
    public void delete(Sucursal sucursal){
        mRepository.deleteSucursal(sucursal);
    }
    public void deleteAllSucursales(){
        mRepository.deleteAllSucursales();
    }
}
