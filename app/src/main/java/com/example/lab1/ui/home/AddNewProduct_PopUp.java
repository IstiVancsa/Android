package com.example.lab1.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.lab1.R;

public class AddNewProduct_PopUp extends AppCompatDialogFragment {
    private EditText priceEditText;
    private EditText nameEditText;
    private EditText descriptioneEditText;
    private IAddNewProductListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.new_product_popup, null);
        nameEditText = view.findViewById(R.id.name);
        priceEditText = view.findViewById(R.id.price);
        descriptioneEditText = view.findViewById(R.id.description);

        builder.setView(view).setTitle("Send Message")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText name = view.findViewById(R.id.name);
                        EditText price = view.findViewById(R.id.price);
                        EditText description = view.findViewById(R.id.description);
                        String nameText = name.getText().toString();
                        String priceText = price.getText().toString();
                        String descriptionText = description.getText().toString();

                        listener.applyTexts(nameText, priceText, descriptionText);
                    }
                });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (IAddNewProductListener)context;
        }
        catch (ClassCastException e){
            e.printStackTrace();
        }

    }

    public interface IAddNewProductListener{
        void applyTexts(String name, String price, String description);
    }
}
