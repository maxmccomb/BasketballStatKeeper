package com.example.basketballstatkeeper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class AddPlayerDialog extends AppCompatDialogFragment {
    EditText editPlayerName;
    AddPlayerDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        editPlayerName = view.findViewById(R.id.editPlayerName);

        builder.setView(view)
                .setTitle("First Player")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editPlayerName.getText().toString();
                        if(!name.matches("")){
                            listener.applyText(name);
                        }
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AddPlayerDialogListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement AddPlayerDialogListener");
        }
    }

    public interface AddPlayerDialogListener{
        void applyText(String name);
    }
}
