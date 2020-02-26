package com.example.lab1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lab1.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        List<HomeModel> mockList = new ArrayList<>();
        mockList.add(new HomeModel(R.drawable.kurtos_kalacs, "kurtos1", "1"));
        mockList.add(new HomeModel(R.drawable.kurtos_kalacs, "kurtos2", "2"));
        mockList.add(new HomeModel(R.drawable.kurtos_kalacs, "kurtos3", "3"));
        mockList.add(new HomeModel(R.drawable.kurtos_kalacs, "kurtos4", "4"));
        mockList.add(new HomeModel(R.drawable.kurtos_kalacs, "kurtos5", "5"));
        //homeViewModel.HomeModelList = new ArrayAdapter<List<HomeModel>>(this, R.layout.fragment_home, mockList)

        final TextView kurtosName = root.findViewById(R.id.kurtosName);
        final TextView kurtosPrice = root.findViewById(R.id.kurtosPrice);
        final ImageView kurtosImage = root.findViewById(R.id.kurtosImage);
        final ListView kurtosListView = root.findViewById(R.id.kurtosList);
        kurtosImage.setBackgroundResource(R.drawable.kurtos_kalacs);


        homeViewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                kurtosName.setText(s);
            }
        });

        homeViewModel.getPrice().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                kurtosPrice.setText(s);
            }
        });

        homeViewModel.getImagePath().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                kurtosImage.setBackgroundResource(s);
            }
        });
        return root;
    }
}