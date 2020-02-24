package com.example.lab1.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<HomeModel> HomeModel;

    public MutableLiveData<Integer> ImagePath;
    public MutableLiveData<String> KurtosName;
    public MutableLiveData<String> KurtosPrice;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        ImagePath = new MutableLiveData<>();
        KurtosName = new MutableLiveData<>();
        KurtosPrice = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        KurtosName.setValue("Test Kurtos");
        KurtosPrice.setValue("2 lei");
    }

    public LiveData<String> getText() {
        return mText;
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
}