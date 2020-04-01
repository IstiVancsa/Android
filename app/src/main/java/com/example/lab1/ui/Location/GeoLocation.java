package com.example.lab1.ui.Location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeoLocation {
    public static void GetAddress(final String location, final Context context, final Handler handler){
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;

                try {
                    List addressList = geocoder.getFromLocationName(location, 1);
                    if(addressList != null && addressList.size() > 0){
                        Address address = (Address)addressList.get(0);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(address.getLatitude()).append("\n");
                        stringBuilder.append(address.getLongitude()).append("\n");
                        result = stringBuilder.toString();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    Bundle bundle = new Bundle();
                    if(result != null){
                        message.what = 1;
                        result = "\nAddress    :    " + location + "\nLatitude And Longitude\n\n\n" + result;
                        bundle.putString("address", result);
                        message.setData(bundle);
                    }
                    else{
                        message.what = 2;
                        bundle.putString("location", location);
                        message.setData(bundle);
                    }

                    message.sendToTarget();
                }

            }
}
