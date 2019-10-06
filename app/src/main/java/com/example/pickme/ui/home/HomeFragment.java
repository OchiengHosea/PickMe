package com.example.pickme.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.pickme.R;
import com.example.pickme.data.DataUtil;
import com.example.pickme.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    FragmentHomeBinding fragmentHomeBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater);
        fragmentHomeBinding.swiper.setOnRefreshListener(this);
        getVehicleList();
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onRefresh() {

    }

    private void getVehicleList(){
        fragmentHomeBinding.setVehicles(DataUtil.vehicles);
    }
}