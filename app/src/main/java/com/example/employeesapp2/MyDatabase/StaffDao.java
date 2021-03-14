package com.example.employeesapp2.MyDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

//Contains abstract method to perform trasactions with the Table in Database
@Dao
public interface StaffDao {

    @Query("Select * from Staff order by name")
    LiveData<List<Staff>> getAllStaff();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStaff(Staff new_staff);

    @Query("Delete from Staff where id = :id")
    void deleteStaff(int id);


}
