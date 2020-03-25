package com.example.lab1.ui.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.lab1.R;
import com.example.lab1.ui.About.About;
import com.example.lab1.ui.About.NavAboutViewModel;

public class Nav_Settings extends Fragment {

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {

            View root = inflater.inflate(R.layout.nav_settings_fragment, container, false);

            Button goToPageButton = root.findViewById(R.id.GoToSettingsButton);
            goToPageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), SettingsActivity.class);
                    startActivity(intent);
                }
            });

            return root;
        }
}
