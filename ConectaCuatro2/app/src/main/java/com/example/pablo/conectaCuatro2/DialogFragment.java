package com.example.pablo.conectaCuatro2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by pablo on 17/01/18.
 */

public class DialogFragment extends android.app.DialogFragment {

    public static DialogFragment newInstance(int title) {
        DialogFragment fragment = new DialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Identificamos la actividad a la que va a repercutir la elección en el cuadro de diálogo
        final MainActivity main = (MainActivity) getActivity();
        int title = getArguments().getInt("title");
        //Creamos el builder para el diálogo
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());
        //introducimos un título
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(R.string.exit_message);
        //definimos la acción del botón positivo (Yes)
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        main.restart();
                        main.pintarTablero();
                        dialog.dismiss();
                    }
                });
        //definimos la acción del botón negativo (No)
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        main.finish();
                        dialog.dismiss();
                    }
                });
        //Creamos y devolvemos el diálogo  --> mirar método pulsado() en el mainActivity
        return alertDialogBuilder.create();
    }
}