package com.example.lab1.ui.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;

import android.Manifest;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lab1.ui.MessagePopUp;

public class SettingsActivity extends AppCompatActivity {
    public static final String KEY_canMessage = "canMessage";
    public static final String KEY_canCall = "canCall";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean message_switch = sharedPreferences.getBoolean(SettingsActivity.KEY_canMessage, false);
        Toast.makeText(this, message_switch.toString(), Toast.LENGTH_SHORT).show();
    }
}
