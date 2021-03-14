package com.example.employeesapp2.MyDatabase;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//Model Class containing fields for Staff Table
@Entity(tableName = "Staff")
public class Staff {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private  String name;
    private  String position;
    private  double salary;
    private String job_type;

    //Constructor used by RoomDatabase
    public Staff(int id, String name, String position, double salary, String job_type) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.job_type = job_type;
    }

    @Ignore
    //Constructor used by Programmer
    public Staff(String name, String position, double salary, String job_type) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.job_type = job_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }


}
