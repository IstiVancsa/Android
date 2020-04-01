package com.example.lab1.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.lab1.R;

public class MessagePopUp extends AppCompatDialogFragment {
    private EditText messageEditText;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.message_pop_up, null);

        builder.setView(view).setTitle("Send Message")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText entry = view.findViewById(R.id.messageEditText);
                        String message = entry.getText().toString();

                        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                        sendIntent.setData(Uri.parse("0755853953"));
                        sendIntent.setType("vnd.android-dir/mms-sms");
                        sendIntent.putExtra("address", new String ("0755853953"));
                        sendIntent.putExtra("sms_body", message);

                        startActivity(sendIntent);
                    }
                });



        return builder.create();
    }
}
