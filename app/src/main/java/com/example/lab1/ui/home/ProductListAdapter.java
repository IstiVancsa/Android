package com.example.lab1.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab1.R;

import java.util.List;

public class ProductListAdapter extends ArrayAdapter<Product> {
    private static final String TAG = "ProductListAdapter";
    private Context mContext;
    private int mResource;

    public ProductListAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String price = getItem(position).getPrice();
        String description = getItem(position).getDescription();

        Product product = new Product(price, name, description);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView nameTextView = (TextView)convertView.findViewById(R.id.textView_Name);
        TextView priceTextView = (TextView)convertView.findViewById(R.id.textView_Price);
        TextView descriptionTextView = (TextView)convertView.findViewById(R.id.textView_Description);

        nameTextView.setText(name);
        priceTextView.setText(price);
        descriptionTextView.setText(description);

        return convertView;
    }
}
