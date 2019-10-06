package com.example.pickme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pickme.IMainActivity;
import com.example.pickme.R;
import com.example.pickme.databinding.VehicleItemBinding;
import com.example.pickme.objects.Vehicle;

import java.util.ArrayList;

public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesAdapter.BindingHolder>{

    private ArrayList<Vehicle> vehicles;
    private Context context;

    public VehiclesAdapter(ArrayList<Vehicle> vehicles, Context context) {
        this.vehicles = vehicles;
        this.context = context;
    }

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VehicleItemBinding vehicleItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.vehicle_item, parent, false);

        return new BindingHolder(vehicleItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position) {
        Vehicle vehicle = vehicles.get(position);
        holder.vehicleItemBinding.setVariable(BR.vehicle, vehicle);
        holder.vehicleItemBinding.executePendingBindings();
        holder.vehicleItemBinding.setIMainActivity((IMainActivity) context);
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder{
        // ViewDataBinding viewDataBinding;
        VehicleItemBinding vehicleItemBinding;

        public BindingHolder(@NonNull View itemView) {
            super(itemView);
            vehicleItemBinding = DataBindingUtil.bind(itemView);
        }
    }
}
