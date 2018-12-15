package com.peterleyva.examenmvvm.viewmodel;

import android.app.Application;

import com.peterleyva.examenmvvm.model.Employee;
import com.peterleyva.examenmvvm.repository.UserRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class EmployeeViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    private LiveData<List<Employee>> mAllEmployees;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllEmployees = mRepository.getAllEmployees();
    }

    public LiveData<List<Employee>> getAllEmployees() { return mAllEmployees; }

    public void insert(Employee employee) { mRepository.insertEmployee(employee); }
}
