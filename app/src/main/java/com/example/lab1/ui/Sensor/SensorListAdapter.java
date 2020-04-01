package com.example.lab1.ui.Sensor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab1.R;
import com.example.lab1.ui.home.Product;

import java.util.List;

public class SensorListAdapter extends ArrayAdapter<SensorModel> {
    private static final String TAG = "SensorListAdapter";
    private Context mContext;
    private int mResource;

    public SensorListAdapter(@NonNull Context context, int resource, @NonNull List<SensorModel> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();

        SensorModel sensor = new SensorModel(name);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView nameTextView = convertView.findViewById(R.id.textView_SensorName);

        nameTextView.setText(name);

        return convertView;
    }
}
