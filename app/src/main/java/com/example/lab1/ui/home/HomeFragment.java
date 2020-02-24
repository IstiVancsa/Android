package com.example.lab1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lab1.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);
        final TextView kurtosName = root.findViewById(R.id.kurtosName);
        final TextView kurtosPrice = root.findViewById(R.id.kurtosPrice);
        final ImageView kurtosImage = root.findViewById(R.id.kurtosImageView);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

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

        return root;
    }
}