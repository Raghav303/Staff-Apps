package com.example.employeesapp2.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeesapp2.MainActivity;
import com.example.employeesapp2.MyDatabase.Staff;
import com.example.employeesapp2.R;
import com.example.employeesapp2.ViewModel.StaffViewModel;
import com.example.employeesapp2.databinding.ItemLayoutBinding;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter <StaffAdapter.StaffViewHolder>  {

    List<Staff> staffList;
    Context context;

    public StaffAdapter( Context context,List<Staff> staffList) {
        this.staffList = staffList;
        this.context = context;

    }

    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new StaffViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return staffList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {
       Staff staff = staffList.get(position);
       holder.binding.name.setText(staff.getName());
       holder.binding.position.setText(staff.getPosition());
       holder.binding.salary.setText("Rs. "+String.format("%.2f",staff.getSalary()));
       holder.binding.jobType.setText(staff.getJob_type());
       holder.binding.staffView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View view) {
               showConfirmDialog(staff.getId());
               return true;
           }
       });
    }

    //Method to show Confirmation Dialog for deleting list elements
    private void showConfirmDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Are you sure you want to delete?")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new ViewModelProvider((MainActivity)context).get(StaffViewModel.class)
                                .deleteStaff(id);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertdialog = builder.create();
        alertdialog.show();
    }

    //helper method to update list when data changes
    public void updateList(List<Staff> staffList)
    {
        this.staffList=staffList;
        notifyDataSetChanged();
    }

    //Custom ViewHolder
    public class StaffViewHolder extends RecyclerView.ViewHolder {
        ItemLayoutBinding binding;
        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemLayoutBinding.bind(itemView);
        }
    }
}
