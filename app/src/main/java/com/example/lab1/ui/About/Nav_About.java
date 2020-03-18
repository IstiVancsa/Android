package com.example.lab1.ui.About;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab1.R;
import com.example.lab1.ui.home.KurtosDetail;

public class Nav_About extends Fragment {

    private NavAboutViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(NavAboutViewModel.class);

        View root = inflater.inflate(R.layout.nav__about_fragment, container, false);

        Button goToPageButton = root.findViewById(R.id.GoToAboutButton);
        goToPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), About.class);
                startActivity(intent);
            }
        });

        return root;
    }

}
