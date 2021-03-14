package com.example.employeesapp2.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.employeesapp2.MyDatabase.Staff;

import java.util.List;

public class StaffViewModel extends AndroidViewModel {
//ViewModels acts as a mediator between the Views and the DataSource
//It takes data and updates the UI and vice-versa
Repository repo;

    public StaffViewModel(@NonNull Application application) {
        super(application);
        repo = new Repository(application);
    }

    public LiveData<List<Staff>> getStaffList()
    {

        return repo.getStaffList();
    }

    public void insertStaff(Staff staff)
    {
        repo.insertStaff(staff);
    }

    public void deleteStaff(int id)
    {
        repo.deleteStaff(id);
    }
}

