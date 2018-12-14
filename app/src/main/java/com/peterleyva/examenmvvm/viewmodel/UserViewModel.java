package com.peterleyva.examenmvvm.viewmodel;

import android.app.Application;

import com.peterleyva.examenmvvm.model.User;
import com.peterleyva.examenmvvm.repository.UserRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);

        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
    }

    public void insert(User user){
        repository.insert(user);
    }
    public void delete(User user){
        repository.delete(user);
    }
    public void update(User user){
        repository.update(user);
    }
    public void deleteAllUsers(User user){
        repository.deleteAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<User> getUserById(int id) {
        return repository.getUser(id);
    }

}
