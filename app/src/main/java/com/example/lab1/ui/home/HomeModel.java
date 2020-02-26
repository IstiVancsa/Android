package com.example.lab1.ui.home;

import androidx.lifecycle.MutableLiveData;

public class HomeModel {
    public MutableLiveData<Integer> ImagePath;
    public MutableLiveData<String> KurtosName;
    public MutableLiveData<String> KurtosPrice;

    public HomeModel(Integer imagePath, String kurtosName, String kurtosPrice){
        this.ImagePath.setValue(imagePath);
        this.KurtosPrice.setValue(kurtosPrice);
        this.KurtosName.setValue(kurtosName);
    }
}
