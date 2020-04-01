package com.example.lab1.ui.home;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private Context CurrentContext;
    private Activity CurrentActivity;


    public HomeViewModelFactory(Context context, Activity activity) {
        CurrentContext = context;
        CurrentActivity = activity;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new HomeViewModel(CurrentContext, CurrentActivity);
    }
}
