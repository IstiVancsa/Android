package com.example.lab1.ui.Location;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab1.ui.home.HomeViewModel;

public class NavLoactionViewModelFactory implements ViewModelProvider.Factory {
    private Context CurrentContext;

    public NavLoactionViewModelFactory(Context context) {
        CurrentContext = context;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new NavLocationViewModel(CurrentContext);
    }
}
