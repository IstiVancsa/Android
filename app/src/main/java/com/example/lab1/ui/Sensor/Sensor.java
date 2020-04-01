package com.example.lab1.ui.Sensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;

import com.example.lab1.R;
import com.example.lab1.ui.home.ProductListAdapter;

import java.util.List;

public class Sensor extends AppCompatActivity {
    private SensorManager mSensorManager;
    SensorListAdapter adapter;
    private SensorViewModel sensorViewModel;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorViewModel = ViewModelProviders.of(this).get(SensorViewModel.class);

        listView = findViewById(R.id.sensorsListView);


        List<android.hardware.Sensor> msensorList = mSensorManager.getSensorList(android.hardware.Sensor.TYPE_ALL);

        android.hardware.Sensor tmp;
        for (int i = 0; i < msensorList.size(); i++) {
            tmp = msensorList.get(i);
            sensorViewModel.AddSensor(new SensorModel(tmp.getName()));
        }

        adapter = new SensorListAdapter(this, R.layout.sensor_list_layout, sensorViewModel.getSensorList());
        listView.setAdapter(adapter);
    }
}
