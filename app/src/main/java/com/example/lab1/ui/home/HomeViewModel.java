package com.example.lab1.ui.home;

import android.app.Application;
import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab1.R;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    public ArrayAdapter<List<HomeModel>> HomeModelList;

    public MutableLiveData<Integer> ImagePath;
    public MutableLiveData<String> KurtosName;
    public MutableLiveData<String> KurtosPrice;

    public HomeViewModel() {
        ImagePath = new MutableLiveData<>();
        KurtosName = new MutableLiveData<>();
        KurtosPrice = new MutableLiveData<>();
        KurtosName.setValue("Test Kurtos");
        KurtosPrice.setValue("2 lei");
        ImagePath.setValue(R.drawable.kurtos_kalacs);
    }


    public LiveData<String> getName() {
        return KurtosName;
    }

    public LiveData<String> getPrice() {
        return KurtosPrice;
    }

    public LiveData<Integer> getImagePath() {
        return ImagePath;
    }

    public ArrayAdapter<List<HomeModel>> getHomeModelList()
    {
        return HomeModelList;
    }
}