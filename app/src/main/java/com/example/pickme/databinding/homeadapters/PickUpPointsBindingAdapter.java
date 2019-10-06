package com.example.pickme.databinding.homeadapters;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pickme.adapters.PickUpPointsAdapter;
import com.example.pickme.objects.PickUpPoint;

import java.util.ArrayList;

public class PickUpPointsBindingAdapter {
    @BindingAdapter("pickUpPointsList")
    public static void setPickUpPointsList(RecyclerView view, ArrayList<PickUpPoint> pickUpPoints) {
        if(pickUpPoints == null) {
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        }

        PickUpPointsAdapter pickUpPointsAdapter = (PickUpPointsAdapter) view.getAdapter();
        if (pickUpPointsAdapter == null){
            pickUpPointsAdapter = new PickUpPointsAdapter(pickUpPoints, view.getContext());
            DividerItemDecoration itemDecoration = new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL);
            view.addItemDecoration(itemDecoration);
            view.setAdapter(pickUpPointsAdapter);
        }

    }
}
