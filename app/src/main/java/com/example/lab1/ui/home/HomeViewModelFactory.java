package com.example.lab1.ui.home;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private Context mParam;


    public HomeViewModelFactory(Context param) {
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new HomeViewModel(mParam);
    }
}
