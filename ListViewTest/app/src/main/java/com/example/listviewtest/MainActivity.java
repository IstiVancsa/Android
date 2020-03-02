package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById((R.id.testListView));

        ArrayList<Product> testArrayList = new ArrayList<>();

        testArrayList.add(new Product("test price1", "test product1", "description1"));
        testArrayList.add(new Product("test price2", "test product2", "description2"));
        testArrayList.add(new Product("test price3", "test product3", "description3"));
        testArrayList.add(new Product("test price4", "test product4", "description4"));
        testArrayList.add(new Product("test price5", "test product5", "description5"));
        testArrayList.add(new Product("test price6", "test product6", "description6"));
        testArrayList.add(new Product("test price7", "test product7", "description7"));
        testArrayList.add(new Product("test price8", "test product8", "description8"));
        testArrayList.add(new Product("test price9", "test product9", "description9"));
        testArrayList.add(new Product("test price10", "test product10", "description10"));
        testArrayList.add(new Product("test price11", "test product11", "description11"));
        testArrayList.add(new Product("test price12", "test product12", "description12"));
        testArrayList.add(new Product("test price13", "test product13", "description13"));
        testArrayList.add(new Product("test price14", "test product14", "description14"));
        testArrayList.add(new Product("test price15", "test product15", "description15"));


        ProductListAdapter adapter = new ProductListAdapter(this, R.layout.adapter_view_layout, testArrayList);
        listView.setAdapter(adapter);
    }
}
