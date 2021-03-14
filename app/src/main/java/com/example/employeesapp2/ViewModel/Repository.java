package com.example.employeesapp2.ViewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.employeesapp2.MyDatabase.Staff;
import com.example.employeesapp2.MyDatabase.StaffDao;
import com.example.employeesapp2.MyDatabase.StaffDatabase;

import java.util.List;

public class Repository {
    private final StaffDatabase db;
    private final StaffDao dao;
    private  LiveData<List<Staff>> staffList;
    public Repository(Application application) {
        db = StaffDatabase.getInstance(application);
        dao=db.getStaffDao();
    }

    public LiveData<List<Staff>> getStaffList()
    {
        staffList = dao.getAllStaff();
        return staffList;
    }

    public void deleteStaff(final int id)
    {
      new Thread(new Runnable() {
          @Override
          public void run() {
             dao.deleteStaff(id);
          }
      }).start();
    }

    public void insertStaff(Staff staff)
    {
        new  MyAsyncTask().execute(staff);
    }

  public class MyAsyncTask extends AsyncTask<Staff, Void, Void>
  {


      @Override
      protected Void doInBackground(Staff... staff) {
          dao.insertStaff(staff[0]);
          return null;
      }

      @Override
      protected void onPostExecute(Void aVoid) {
          super.onPostExecute(aVoid);
          //Toast.makeText(this,"Insertion Successfull",Toast.LENGTH_SHORT).show();
      }
  }
}
