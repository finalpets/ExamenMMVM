package com.peterleyva.examenmvvm.dao;

import com.peterleyva.examenmvvm.model.Sucursal;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SucursalDao {

    @Insert
    void insert(Sucursal sucursal);

    @Update
    void update(Sucursal sucursal);

    @Delete
    void delete(Sucursal sucursal);

    @Query("DELETE FROM sucursal_table")
    void deleteAllSucursales();

    @Query("SELECT * from sucursal_table ORDER BY name ASC")
    LiveData<List<Sucursal>> getAllSucursales();


}
