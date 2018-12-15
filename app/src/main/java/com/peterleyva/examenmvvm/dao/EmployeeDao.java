package com.peterleyva.examenmvvm.dao;

import com.peterleyva.examenmvvm.model.Employee;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface EmployeeDao {

    @Insert
    void insert(Employee employee);

    @Update
    void update(Employee employee);

    @Delete
    void delete(Employee employee);

    @Query("DELETE FROM employee_table")
    void deleteAllEmployeees();

    @Query("SELECT * from employee_table ORDER BY name ASC")
    LiveData<List<Employee>> getAllEmployeees();


    @Query("SELECT * from employee_table WHERE sucursal_id = :id")
    List<Employee> getAllSucursales_ByUd(int id);

}
