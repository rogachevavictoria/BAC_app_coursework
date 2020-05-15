package com.example.android.bactrack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class Dialog extends androidx.fragment.app.DialogFragment {
    private EditText editTextName;
    private EditText editTextPercent;
    private EditText editTextMl;
    private DialogListener listener;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        builder.setView(view)
                .setTitle(R.string.add_a_drink)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editTextName.getText().toString();
                        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(editTextPercent.getText()) || TextUtils.isEmpty(editTextMl.getText())) {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle(R.string.empty_fields)
                                    .setTitle(R.string.fill_the_fields)
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .show();
                        } else {
                            int percent = Integer.parseInt(editTextPercent.getText().toString());
                            int ml = Integer.parseInt(editTextMl.getText().toString());
                            listener.applyTexts(name, percent, ml);
                        }
                    }
                });
        editTextName = view.findViewById(R.id.editName);
        editTextPercent = view.findViewById(R.id.editPercent);
        editTextMl = view.findViewById(R.id.editMl);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
    }

    public interface DialogListener {
        void applyTexts(String name, int percent, int ml);
    }
}
