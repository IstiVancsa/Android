package com.example.lab1;

import android.content.Context;
import android.os.Bundle;

import com.example.lab1.ui.home.AddNewProduct_PopUp;
import com.example.lab1.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MainActivity extends AppCompatActivity implements AddNewProduct_PopUp.IAddNewProductListener {

    private AppBarConfiguration mAppBarConfiguration;
    public static final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/kurtosData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send,
                R.id.nav_about, R.id.nav_settings, R.id.nav_sensors,
                R.id.nav_location)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        Log.d("OnCreate", " OnCreate was executed!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onStart", " onStart was executed!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", " onResume was executed!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause", " onPause was executed!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop", " onStop was executed!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", " onDestroy was executed!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("onRestart", " onRestart was executed!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public void applyTexts(String name, String price, String description) {
        File file = new File(path + "/kurtos.txt");

        Save(file, name + " " + price + " " + description + "\n");
    }

    public void Save(File file, String product){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try {
            try {
                Files.write(Paths.get(file.getAbsolutePath()), product.getBytes(), StandardOpenOption.APPEND);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        finally {
            try{
                fos.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
