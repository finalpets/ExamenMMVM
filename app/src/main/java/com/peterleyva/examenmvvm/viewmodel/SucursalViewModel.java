package com.peterleyva.examenmvvm.viewmodel;

import android.app.Application;

import com.peterleyva.examenmvvm.model.Sucursal;
import com.peterleyva.examenmvvm.repository.SucursalRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SucursalViewModel extends AndroidViewModel {

    private SucursalRepository mRepository;

    private LiveData<List<Sucursal>> mAllSucursales;

    public SucursalViewModel(Application application){
        super(application);
        mRepository = new SucursalRepository(application);
        mRepository.getAllSucursales();
    }

    LiveData<List<Sucursal>> getAllSucursales() { return mAllSucursales; }

    public void insert(Sucursal sucursal) { mRepository.insert(sucursal); }
}
