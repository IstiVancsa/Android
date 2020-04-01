package com.example.lab1.ui.home;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab1.MainActivity;
import com.example.lab1.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static android.content.Context.MODE_PRIVATE;

public class HomeViewModel extends ViewModel {

    private  ArrayList<Product> kurtosList;
    public static final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/kurtosData";
    File dir = new File(path);
    private Context CurrentContext;
    private Activity CurrentActivity;
    private static final int PERMISSION_REQUEST_CODE = 100;

    private MutableLiveData<Integer> ImagePath;
    private MutableLiveData<String> KurtosName;
    private MutableLiveData<String> KurtosPrice;

    public HomeViewModel(Context context, Activity activity) {
        ImagePath = new MutableLiveData<>();
        KurtosName = new MutableLiveData<>();
        KurtosPrice = new MutableLiveData<>();
        KurtosName.setValue("Test Kurtos");
        KurtosPrice.setValue("2 lei");
        ImagePath.setValue(R.drawable.kurtos_kalacs);


        dir.mkdir();

        this.kurtosList = new ArrayList<>();
        this.CurrentContext = context;
        this.CurrentActivity = activity;
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

    public void ReadProducts(){
        File file = new File(dir, "/kurots.txt");
        String state = Environment.getExternalStorageState();
        String[] loadText = new String[100];
        if (Environment.MEDIA_MOUNTED.equals(state)) {
                if (!checkPermission())
                    requestPermission();
        }
        loadText = Load(file);
        if(loadText[0] != null)
            for(int i = 0; i < loadText.length; i++){
                String[] product = loadText[i].split(" ");
                kurtosList.add(new Product(product[1], product[0], product[2]));
        }
    }

    public String[] Load(File file){


//        final File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
//        for (final String fileEntry : folder.list()) {
//            System.out.println(fileEntry);
//        }


        FileInputStream fis = null;
        try{
            if(!file.exists())
                file.createNewFile();
            fis = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String product;
        String[] data = new String[1000];
        int count = 0;
        try {
            long test = file.length();
            while((product = br.readLine()) != null){
                if(product != "\n")
                    data[count++] = product;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        for(String elem : data)
            System.out.println(elem);
        return data;
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

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(CurrentContext, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(CurrentActivity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(CurrentContext, "Write External Storage permission allows us to create files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(CurrentActivity, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }
}