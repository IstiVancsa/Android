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

    private  ArrayList<Product> kurtosList;

    private MutableLiveData<Integer> ImagePath;
    private MutableLiveData<String> KurtosName;
    private MutableLiveData<String> KurtosPrice;

    public HomeViewModel() {
        ImagePath = new MutableLiveData<>();
        KurtosName = new MutableLiveData<>();
        KurtosPrice = new MutableLiveData<>();
        KurtosName.setValue("Test Kurtos");
        KurtosPrice.setValue("2 lei");
        ImagePath.setValue(R.drawable.kurtos_kalacs);

        this.kurtosList = new ArrayList<>();
        kurtosList.add(new Product("test price1", "test product1", "description1"));
        kurtosList.add(new Product("test price2", "test product2", "description2"));
        kurtosList.add(new Product("test price3", "test product3", "description3"));
        kurtosList.add(new Product("test price4", "test product4", "description4"));
        kurtosList.add(new Product("test price5", "test product5", "description5"));
        kurtosList.add(new Product("test price6", "test product6", "description6"));
        kurtosList.add(new Product("test price7", "test product7", "description7"));
        kurtosList.add(new Product("test price8", "test product8", "description8"));
        kurtosList.add(new Product("test price9", "test product9", "description9"));
        kurtosList.add(new Product("test price10", "test product10", "description10"));
        kurtosList.add(new Product("test price11", "test product11", "description11"));
        kurtosList.add(new Product("test price12", "test product12", "description12"));
        kurtosList.add(new Product("test price13", "test product13", "description13"));
        kurtosList.add(new Product("test price14", "test product14", "description14"));
        kurtosList.add(new Product("test price15", "test product15", "description15"));
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

    public ArrayList<Product> getHomeModelList()
    {
        return kurtosList;
    }
}