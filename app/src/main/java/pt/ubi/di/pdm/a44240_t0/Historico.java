package pt.ubi.di.pdm.a44240_t0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Historico extends AppCompatActivity {

    private static final String TAG = "Historico";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_historico);

        Log.d(TAG, "onCreate: Started.");
        ListView listView = (ListView) findViewById(R.id.listView);

        //Mensagem a = new Mensagem("Boa-tarde,\n", "Existem Bicicletas à venda?,\n", "Gostava de saber se o modelo TREK EMONDA ainda se encontra disponivel para venda.", "boas vendas,\n", "Obrigado,\n", "Nuno Pires");
        //Mensagem b = new Mensagem("Bom-dia,\n", "A encomenda está disponível?,\n", "Venho por este meio interrogar se o exame já se encontra disponível para levantamento.", "Cumprimentos,\n", "Obrigado,\n", "Nuno Pires");


        ArrayList<Mensagem> mensagens = new ArrayList<>();
        //mensagens.add(a);
        //mensagens.add(b);

        //MensagemListAdapter adapter = new MensagemListAdapter(this, R.layout.listview_layout, mensagens);
        //listView.setAdapter(adapter);
        SharedPreferences sharedPreferences = getSharedPreferences("spMSG", 0);
        //sharedPreferences.edit().clear().commit();


        Gson gson = new Gson();
        String json = sharedPreferences.getString("SetMsg", "");




        if(!json.isEmpty()){
            Type type = new TypeToken<List<Mensagem>>(){
            }.getType();
            List<Mensagem> msgs = gson.fromJson(json, type);
            for(Mensagem msg: msgs){
                mensagens.add(msg);
            }
            //mensagens.addAll(msgs);
            MensagemListAdapter adapter = new MensagemListAdapter(this, R.layout.listview_layout, mensagens);
            listView.setAdapter(adapter);
        }


    }
}