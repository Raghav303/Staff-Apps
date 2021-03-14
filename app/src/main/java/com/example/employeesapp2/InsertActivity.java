package com.example.employeesapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.employeesapp2.MyDatabase.Staff;
import com.example.employeesapp2.ViewModel.StaffViewModel;
import com.example.employeesapp2.databinding.ActivityInsertBinding;

public class InsertActivity extends AppCompatActivity {

    //Activity to insert new Employee Details
    ActivityInsertBinding binding;
    StaffViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Insert new Employee");

        model = new ViewModelProvider(this).get(StaffViewModel.class);

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = binding.firstNameView.getText().toString().trim();
                String lname = binding.lastNameView.getText().toString();
                String pos = binding.positionView.getText().toString();
                double salary = 0.0d;
                String job_type="";

                if(TextUtils.isEmpty(fname) || fname.matches(""))
                {
                    binding.firstNameView.setError("First Name cannot be empty");
                    return;
                }

                else if(TextUtils.isEmpty(lname))
                {
                    binding.lastNameView.setError("Last Name cannot be empty");
                    return;
                }
                else if(TextUtils.isEmpty(pos))
                {
                    binding.positionView.setError("Position cannot be empty");
                    return;
                }
                else if(TextUtils.isEmpty(binding.salaryView.getText().toString()))
                {
                    binding.salaryView.setError("Salary cannot be empty");
                    return;
                }
                else if(!binding.fullTimeBtn.isChecked() && !binding.partTimeBtn.isChecked())
                {
                    binding.fullTimeBtn.setError("Choose one of the options");
                    return;
                }
                else
                {
                    salary =  Double.parseDouble(binding.salaryView.getText().toString());
                    if(binding.fullTimeBtn.isChecked())
                        job_type=binding.fullTimeBtn.getText().toString();
                    else
                        job_type=binding.partTimeBtn.getText().toString();


                    Staff staff = new Staff(fname+" "+lname,pos,salary,job_type);
                    model.insertStaff(staff);
                    Toast.makeText(InsertActivity.this,"Insertion Successfull",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }
}