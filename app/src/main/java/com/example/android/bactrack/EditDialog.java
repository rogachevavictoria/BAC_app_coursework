package com.example.android.bactrack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class EditDialog extends androidx.fragment.app.DialogFragment {
    private EditText editTextName;
    private EditText editTextPercent;
    private EditText editTextMl;
    private DialogListener listener;
    String name;
    double percent, ml;
    int position;

    public EditDialog(int position, String name, double percent, double ml) {
        this.name = name;
        this.percent = percent;
        this.ml = ml;
        this.position = position;
    }

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        editTextName = view.findViewById(R.id.editName);
        editTextPercent = view.findViewById(R.id.editPercent);
        editTextMl = view.findViewById(R.id.editMl);

        editTextName.setText(name);
        editTextPercent.setText("" + percent);
        editTextMl.setText("" + ml);


        builder.setView(view)
                .setTitle(R.string.edit_drink)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton(R.string.edit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editTextName.getText().toString();
                        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(editTextPercent.getText()) || TextUtils.isEmpty(editTextMl.getText())) {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("empty fields")
                                    .setTitle("Please fill all the fields.")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .show();
                        } else {
                            double percent = Double.parseDouble(editTextPercent.getText().toString());
                            double ml = Double.parseDouble(editTextMl.getText().toString());
                            listener.editApplyTexts(position, name, percent, ml);
                        }
                    }
                });


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
        void editApplyTexts(int position, String name, double percent, double ml);
    }
}
