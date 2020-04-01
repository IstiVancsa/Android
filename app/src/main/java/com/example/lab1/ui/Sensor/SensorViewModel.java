package com.example.lab1.ui.Sensor;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SensorViewModel extends ViewModel {
    private ArrayList<SensorModel> SensorList;

    public SensorViewModel() {
        SensorList = new ArrayList<>();
    }

    public void AddSensor(SensorModel sensor){
        SensorList.add(sensor);
    }

    public ArrayList<SensorModel> getSensorList() {
        return SensorList;
    }
}
