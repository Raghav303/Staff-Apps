package com.example.employeesapp2.MyDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Staff.class} , version = 2)
public abstract class StaffDatabase extends RoomDatabase{

    private static final String DATABASE_NAME = "Staff_database";
    private static  volatile StaffDatabase instance;
    private static final Object LOCK = new Object();

    //method to return StaffDao
    public abstract  StaffDao getStaffDao();

    //Implementing Singleton Pattern
    public static StaffDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            synchronized (LOCK)
            {
                instance = Room.databaseBuilder(context,StaffDatabase.class,DATABASE_NAME)
                           .fallbackToDestructiveMigration()
                           .build();
            }
        }
        return instance;
    }
}
