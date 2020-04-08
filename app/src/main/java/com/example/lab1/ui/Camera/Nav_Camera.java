package com.example.lab1.ui.Camera;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lab1.R;

public class Nav_Camera extends Fragment {


    private String mParam1;
    private String mParam2;

    public Nav_Camera() {
        // Required empty public constructor
    }
    public static Nav_Camera newInstance() {
        return new Nav_Camera();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_nav__camera, container, false);
        Button goToPageButton = root.findViewById(R.id.GoToCameraButton);
        goToPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CameraActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}
