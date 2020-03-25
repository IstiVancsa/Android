package com.example.lab1.ui.About;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lab1.R;
import com.example.lab1.ui.MessagePopUp;

public class About extends AppCompatActivity {

final int SMS_Permission_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void phoneNumberClicked(View v){
        TextView phoneTextView = (TextView) v;
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneTextView.getText()));
        startActivity(phoneIntent);
    }

    public void OpenDialog(View v){
        if(checkPermission(Manifest.permission.SEND_SMS)) {
            MessagePopUp popUp = new MessagePopUp();
            popUp.show(getSupportFragmentManager(), "Message dialog");
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_Permission_Code);
        }
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return check == PackageManager.PERMISSION_GRANTED;
    }
}
