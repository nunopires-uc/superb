package pt.ubi.di.pdm.a44240_t0;

import android.content.Context;
import android.content.DialogInterface;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

public class Dialog extends AppCompatDialogFragment {

    private EditText addItemDialog;
    private DialogListener listener;

    public static Dialog newInstance(String msg) {
        Dialog fragment = new Dialog();
        Bundle bundle = new Bundle();
        bundle.putString("msg", msg);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static Dialog newInstance(String msg, char tipo){
        Dialog fragment = new Dialog();
        Bundle bundle = new Bundle();
        bundle.putChar("{tipo}", tipo);
        bundle.putString("{@string-spinner}", msg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
   public android.app.Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        char tipo = getArguments().getChar("{tipo}");
        String txtSpinner = getArguments().getString("{@string-spinner}");

        addItemDialog = view.findViewById(R.id.addItemDialog);
        String oldValue = addItemDialog.getText().toString();
        builder.setView(view)
                .setTitle("Adicionar")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setPositiveButton("adicionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String currentText = addItemDialog.getText().toString();

                        if(tipo == 's') {
                            listener.addListValue(currentText, 'a', oldValue, "saudacao");
                        }

                        if(tipo == 'q'){
                            listener.addListValue(currentText, 'a', oldValue, "quebragelo");
                        }

                        if(tipo == 'v'){
                            listener.addListValue(currentText, 'a', oldValue, "votos");
                        }

                        if(tipo == 'd'){
                            listener.addListValue(currentText, 'a', oldValue, "despedida");
                        }
                    }
                })
                .setNeutralButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String currentText = addItemDialog.getText().toString();

                        if(tipo == 's') {
                            listener.addListValueSaudacao(currentText, 'r', oldValue);
                        }
                        if(tipo == 'q'){
                            listener.addListValue(currentText, 'r', oldValue, "quebragelo");
                        }

                        if(tipo == 'v'){
                            listener.addListValue(currentText, 'r', oldValue, "votos");
                        }

                        if(tipo == 'd'){
                            listener.addListValue(currentText, 'r', oldValue, "despedida");
                        }
                    }
                });

        addItemDialog.setText(txtSpinner);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
    }

    public interface DialogListener{
        void addListValueSaudacao(String texto, char tipo, String OldValor);
        void addListValue(String texto, char tipo, String OldValor, String COLUMN_NAME);
    }
}
