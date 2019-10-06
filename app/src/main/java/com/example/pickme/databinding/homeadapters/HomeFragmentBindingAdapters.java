package com.example.pickme.databinding.homeadapters;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pickme.adapters.VehiclesAdapter;
import com.example.pickme.objects.Vehicle;

import java.util.ArrayList;

public class HomeFragmentBindingAdapters {
    @BindingAdapter("vehiclesList")
    public static void setVehiclesList(RecyclerView view, ArrayList<Vehicle> vehicles){
        if(vehicles == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        if (layoutManager == null) {
            view.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        }
        VehiclesAdapter vehiclesAdapter = (VehiclesAdapter) view.getAdapter();
        if (vehiclesAdapter == null){
            vehiclesAdapter = new VehiclesAdapter(vehicles, view.getContext());
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(),
                    LinearLayoutManager.VERTICAL);
            view.addItemDecoration(dividerItemDecoration);
            view.setAdapter(vehiclesAdapter);
        }
    }
}
