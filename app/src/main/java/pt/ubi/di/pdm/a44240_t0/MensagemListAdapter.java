package pt.ubi.di.pdm.a44240_t0;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MensagemListAdapter extends ArrayAdapter<Mensagem> {
    private static final String TAG = "MensagemListAdapter";

    private Context lContext;
    int Resource;

    public MensagemListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Mensagem> objects) {
        super(context, resource, objects);
        lContext = context;
        Resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String corpo = getItem(position).getCorpo();
        String dataAtual = getItem(position).getDataAtual();

        LayoutInflater inflater = LayoutInflater.from(lContext);
        convertView = inflater.inflate(Resource, parent, false);

        TextView lblData = (TextView) convertView.findViewById(R.id.lblDataMensagem);
        TextView lblCorpo = (TextView) convertView.findViewById(R.id.lblSumarioMensagem);

        lblData.setText(dataAtual);
        lblCorpo.setText(corpo);

        lblData.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                AlertDialog.Builder builder = new AlertDialog.Builder(lContext);

                String Mensagem = getItem(position).getSaudacao() + ",\n" +
                        getItem(position).getQuebraGelo() + ",\n" +
                        getItem(position).getCorpo() + ",\n" +
                        getItem(position).getVotos() + ",\n"+
                        getItem(position).getDespedida() + ",\n" +
                        getItem(position).getAssinatura();
                builder.setMessage(Mensagem);
                builder.setCancelable(true);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


                AlertDialog alert = builder.create();
                alert.show();


                Toast.makeText(lContext, corpo, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        lblCorpo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                new AlertDialog.Builder(lContext)
                        .setTitle("Mensagem")
                        .setMessage(getItem(position).toString())
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                return false;
            }
        });

        return convertView;
    }
}
