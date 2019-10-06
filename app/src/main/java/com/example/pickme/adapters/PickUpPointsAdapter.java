package com.example.pickme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pickme.IMainActivity;
import com.example.pickme.R;
import com.example.pickme.databinding.PickupPointItemBinding;
import com.example.pickme.objects.PickUpPoint;
import com.example.pickme.objects.Vehicle;

import java.util.ArrayList;

public class PickUpPointsAdapter extends RecyclerView.Adapter<PickUpPointsAdapter.BindingHolder>{
    public ArrayList<PickUpPoint> pickUpPoints;
    public Context context;
    public Vehicle vehicle;

    public PickUpPointsAdapter(ArrayList<PickUpPoint> pickUpPoints, Context context) {
        this.pickUpPoints = pickUpPoints;
        this.context = context;
    }

    public void setVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PickupPointItemBinding pickupPointItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.pickup_point_item, parent, false);
        return new BindingHolder(pickupPointItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position) {
        PickUpPoint pickUpPoint = pickUpPoints.get(position);
        holder.pickupPointItemBinding.setVariable(BR.pickupPoint, pickUpPoint);
        holder.pickupPointItemBinding.executePendingBindings();
        holder.pickupPointItemBinding.setImainActivity((IMainActivity) context);
        holder.pickupPointItemBinding.setVariable(BR.vehicle, vehicle);
    }

    @Override
    public int getItemCount() {
        return pickUpPoints.size();
    }


    public class BindingHolder extends RecyclerView.ViewHolder{
        PickupPointItemBinding pickupPointItemBinding;

        public BindingHolder(@NonNull View itemView) {
            super(itemView);
            pickupPointItemBinding = DataBindingUtil.bind(itemView);
        }
    }
}
