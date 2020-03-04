package com.example.lab1.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lab1.R;

public class KurtosDetail extends AppCompatActivity {
TextView Name;
TextView Price;
TextView Description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurtos_detail);

        setTitle("Kurtos");

        this.Name = findViewById(R.id.textView_Name);
        this.Price = findViewById(R.id.textView_Price);
        this.Description = findViewById(R.id.textView_Description);

        Intent intent = getIntent();

        Name.setText(intent.getStringExtra("name"));
        Price.setText(intent.getStringExtra("price"));
        Description.setText(intent.getStringExtra("description"));
    }
}
