package pt.ubi.di.pdm.a44240_t0;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriarMensagem extends AppCompatActivity implements Dialog.DialogListener {

    private static final String TAG = "CriarMensagem";

    xdbHelper xdbhelper;

    private static final int RECOGNIZER_RESULT = 1;

    Button btnSaudacao, btnQuebraGelo, btnVotos, btnDespedida;

    private Spinner spinSaudacoes, spinQuebraGelo, SpinVotos, SpinDespedida, SpinCorpo;

    private EditText txtBoxCorpo;

    private String SpinDespedidaTxt, SpinSaudacaoTxt, SpinVotosTxt, SpinQuebraGeloTxt, SpinCorpoTxt;

    ArrayList<Mensagem> mensagens;

    private int idArrayList;

    private TextView titulo;

    private ArrayAdapter<String> adapter, adapterQuebraGelo, adapterVotos, adapterDespedida, adapterAssinatura;

    private ArrayList<String> SaudacoesList, QuebraGeloList, AssinaturaList, CorpoList, VotosList, DespedidaList;

    SharedPreferences sp;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == RECOGNIZER_RESULT && resultCode == RESULT_OK){
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            txtBoxCorpo.setText(matches.get(0).toString());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_criar_mensagem);

        titulo = findViewById(R.id.titulo);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Confetti_Stream.ttf");
        titulo.setTypeface(customFont);

        //mDatabaseHelper = new DatabaseHelper(this);
        final List<String> teste = Arrays.asList("Senhor", "Presidente", "Exmo");

        SaudacoesList = new ArrayList<>();
        //SaudacoesList.add("Bem-haja");
        //SaudacoesList.add("Bom-dia");
        //SaudacoesList.add("Olá");

        QuebraGeloList = new ArrayList<>();
        //QuebraGeloList.add("Presidente");
        //QuebraGeloList.add("Senhor");

        CorpoList = new ArrayList<>();
        //CorpoList.add("A nossa equipa de badminton de mini-saia está na primeira divisão");

        VotosList = new ArrayList<>();
        //VotosList.add("Votos de uma boa semana");

        DespedidaList  = new ArrayList<>();
        //DespedidaList.add("Grato pela atenção");

        AssinaturaList = new ArrayList<>();
        //AssinaturaList.add("Nuno Pires nº44240");


        mensagens = new ArrayList<>();

        spinSaudacoes = findViewById(R.id.SpinSaudacoes);

        xdbhelper = new xdbHelper(this);

        /*
        addData("bons-dias", "null", "null", "null", "null", "null");
        addData("null", "boa sorte" , "null" , "null" , "null" , "null");
        addData("null", "null", "null", "Obrigado", "null", "null");
        addData("null", "null", "null", "null", "Grato", "null");
        addData("null", "null", "null", "null", "null", "Nuno Pires");
        */


        Cursor data = xdbhelper.getData();
        /*

        Retirar da Base de Dados os Campos e assegurá-los na arraylist respetiva

         */
        while(data.moveToNext()){

            if(!data.getString(1).toString().equals("null")){
                SaudacoesList.add(data.getString(1));
            }

            if(!data.getString(2).toString().equals("null")) {
                QuebraGeloList.add(data.getString(2));
            }

            if(!data.getString(3).toString().equals("null")){
                CorpoList.add(data.getString(3));
            }

            if(!data.getString(4).toString().equals("null")){
                VotosList.add(data.getString(4));
            }

            if(!data.getString(5).toString().equals("null")){
                DespedidaList.add(data.getString(5));
            }

            if(!data.getString(6).toString().equals("null")){
                AssinaturaList.add(data.getString(6));
            }

        }

        /*

        Coloca os dados da ArrayList no Spinner respetivo

         */
        adapter = new ArrayAdapter<String>(this, R.layout.my_selected_item, SaudacoesList);
        adapter.setDropDownViewResource(R.layout.my_dropdown_item);
        spinSaudacoes.setAdapter(adapter);


        spinSaudacoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idArrayList = position;
                /*

                Assina-la o item escolhido no Spinner para uma variável

                 */
                SpinSaudacaoTxt = (String) parent.getItemAtPosition(position);
                //Toast.makeText(CriarMensagem.this, "Saudação: " + SpinSaudacaoTxt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinQuebraGelo = findViewById(R.id.SpinQuebraGelo);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.my_selected_item, QuebraGeloList);

        adapter1.setDropDownViewResource(R.layout.my_dropdown_item);
        spinQuebraGelo.setAdapter(adapter1);
        spinQuebraGelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinQuebraGeloTxt = (String) parent.getItemAtPosition(position);
                //Toast.makeText(CriarMensagem.this, "Quebra-Gelo: " + SpinQuebraGeloTxt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinCorpo = findViewById(R.id.SpinCorpo);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, R.layout.my_selected_item, CorpoList);
        adapter5.setDropDownViewResource(R.layout.my_dropdown_item);
        SpinCorpo.setAdapter(adapter5);
        SpinCorpo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinCorpoTxt = (String) parent.getItemAtPosition(position);
                txtBoxCorpo.setText(SpinCorpoTxt);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapter1.setDropDownViewResource(R.layout.my_dropdown_item);
        spinQuebraGelo.setAdapter(adapter1);


        SpinVotos = findViewById(R.id.SpinVotos);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.my_selected_item, VotosList);
        adapter3.setDropDownViewResource(R.layout.my_dropdown_item);
        SpinVotos.setAdapter(adapter3);

        SpinVotos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinVotosTxt = (String) parent.getItemAtPosition(position);
                //Toast.makeText(CriarMensagem.this, "Votos: " + SpinVotosTxt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnVoicetoText = (Button)findViewById(R.id.btnVoiceToText);
        btnVoicetoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-PT");
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "speech to text");
                startActivityForResult(speechIntent, RECOGNIZER_RESULT);

            }
        });

        SpinDespedida = findViewById(R.id.SpinDespedida);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.my_selected_item, DespedidaList);
        adapter4.setDropDownViewResource(R.layout.my_dropdown_item);
        SpinDespedida.setAdapter(adapter4);
        SpinDespedida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinDespedidaTxt = (String) parent.getItemAtPosition(position);
                //Toast.makeText(CriarMensagem.this, "Despedida: " + SpinDespedidaTxt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterAssinatura = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, AssinaturaList);
        AutoCompleteTextView txtBoxAssinatura = (AutoCompleteTextView) findViewById(R.id.txtBoxAssinatura);
        txtBoxAssinatura.setAdapter(adapterAssinatura);

        txtBoxCorpo = findViewById(R.id.txtBoxCorpo);

        Button btnEnviar = (Button)findViewById(R.id.btnEnviar);

        /*

        A aplicação usa uma Base de Dados e dados guardados no SharedPreferences.
        Lê as mensagens guardadas no SharedPreferences e coloca-as na arraylist mensagens

         */

        SharedPreferences sharedPreferences = getSharedPreferences("spMSG", 0);
        Gson gsonRead = new Gson();
        String jsonRead = sharedPreferences.getString("SetMsg", "");
        if(!jsonRead.isEmpty()){
            Type type = new TypeToken<List<Mensagem>>(){
            }.getType();
            List<Mensagem> msgs = gsonRead.fromJson(jsonRead, type);
            for(Mensagem msg: msgs){
                mensagens.add(msg);
            }
            //mensagens.addAll(msgs);
        }

        btnEnviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /*

                Verifica se os campos estão em branco

                 */

                if(SpinSaudacaoTxt.isEmpty()){
                    SpinDespedidaTxt = "";
                }

                if(!txtBoxCorpo.getText().toString().isEmpty()){
                    //adiciona o texto do corpo no spinner e na base de dados
                    CorpoList.add(txtBoxCorpo.getText().toString());
                    ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(CriarMensagem.this, R.layout.my_selected_item, CorpoList);
                    adapter5.setDropDownViewResource(R.layout.my_dropdown_item);
                    SpinCorpo.setAdapter(adapter5);
                    addData("null", "null", txtBoxCorpo.getText().toString(), "null", "null", "null");
                }

                if(SpinQuebraGeloTxt.isEmpty()){
                    SpinQuebraGeloTxt = "";
                }

                if(SpinVotosTxt.isEmpty()){
                    SpinVotosTxt = "";
                }

                if(SpinDespedidaTxt.isEmpty()){
                    SpinDespedidaTxt = "";
                }

                if(!txtBoxAssinatura.getText().toString().isEmpty()){
                    if(!AssinaturaList.contains(txtBoxAssinatura.getText().toString())){
                        //Adiciona a assinatura na base de dados para funcionar com o AutoComplete Edit Text
                        addData("null", "null", "null", "null", "null", txtBoxAssinatura.getText().toString());
                        AssinaturaList.add(txtBoxAssinatura.getText().toString());
                        adapterAssinatura = new ArrayAdapter<String>(CriarMensagem.this, android.R.layout.simple_dropdown_item_1line, AssinaturaList);
                        txtBoxAssinatura.setAdapter(adapterAssinatura);
                    }
                }

                String ComposedMsg =
                        SpinSaudacaoTxt + "," + "\n" +
                        SpinQuebraGeloTxt + ",\n" +
                        txtBoxCorpo.getText().toString() + "\n" +
                        SpinVotosTxt + ",\n" +
                        SpinDespedidaTxt + ",\n" +
                        txtBoxAssinatura.getText().toString();



                Mensagem msg = new Mensagem(SpinSaudacaoTxt, SpinQuebraGeloTxt, txtBoxCorpo.getText().toString(), SpinVotosTxt, SpinDespedidaTxt, txtBoxAssinatura.getText().toString());
                mensagens.add(msg);

                /*

                Guarda as mensagens no SharedPreferences

                 */


                Gson gson = new Gson();
                String json = gson.toJson(mensagens);
                SharedPreferences sharedprefs = getSharedPreferences("spMSG", 0);
                SharedPreferences.Editor editor = sharedprefs.edit();
                editor.putString("SetMsg", json);
                editor.commit();

                enviarMensagemIntento(ComposedMsg);
            }
        });


        btnSaudacao = findViewById(R.id.btnSaudacao);
        btnSaudacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //abre um dialogo com o texto do spinner Saudacao
                Dialog dialog = new Dialog().newInstance(SpinSaudacaoTxt,'s');
                dialog.show(getSupportFragmentManager(), "Frag");
            }
        });

        btnQuebraGelo = findViewById(R.id.btnQuebraGelo);
        btnQuebraGelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog().newInstance(SpinQuebraGeloTxt, 'q');
                dialog.show(getSupportFragmentManager(), "Frag");
            }
        });

        btnVotos = findViewById(R.id.btnVotos);
        btnVotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog().newInstance(SpinVotosTxt, 'v');
                dialog.show(getSupportFragmentManager(), "Frag");
            }
        });

        btnDespedida = findViewById(R.id.btnDespedida);
        btnDespedida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog().newInstance(SpinDespedidaTxt, 'd');
                dialog.show(getSupportFragmentManager(), "Frag");
            }
        });

    }

    public void addListValueSaudacao(String valor, char tipo, String oldValor){

        Cursor data = xdbhelper.getID(oldValor, "saudacao"); //get the id associated with that name
        Cursor dataNew = xdbhelper.getID(valor, "saudacao");
        int ID = 0;
        int IDnew = -2;
        while(data.moveToFirst()){
            ID = data.getInt(0);
        }

        while(dataNew.moveToFirst()){
            IDnew = data.getInt(0);
        }

        Log.d("ID ====", Integer.toString(ID));
        Log.d("IDNEW === ", Integer.toString(IDnew));


        //Verifica se o item existe na Base de Dados
        if(ID > -1) {
            //toastMessage("ID === " + ID);
            if(valor.equals("")){
                xdbhelper.deleteItem(ID);
                SaudacoesList.remove(idArrayList);
            }else if(tipo == 'r'){
                xdbhelper.updateItem(oldValor, valor, ID, "saudacao");
                SaudacoesList.set(idArrayList, valor);
            }

        }else{
            if(tipo == 'a' && (!valor.equals(""))) {
                addData(valor, "null", "null", "null", "null", "null");
                SaudacoesList.add(valor);
            }
        }
            adapter = new ArrayAdapter<String>(this, R.layout.my_selected_item, SaudacoesList);
            adapter.setDropDownViewResource(R.layout.my_dropdown_item);
            spinSaudacoes.setAdapter(adapter);
    }

    @Override
    public void addListValue(String valor, char tipo, String OldValor, String COLUMN_NAME) {
        ArrayList<String> arrayList = new ArrayList<>();

        if(COLUMN_NAME.equals("saudacao")){
            arrayList = SaudacoesList;

        }else if(COLUMN_NAME.equals("quebragelo")){
            arrayList = QuebraGeloList;

        }else if(COLUMN_NAME.equals("corpo")){
            arrayList = CorpoList;

        }else if(COLUMN_NAME.equals("votos")){
            arrayList = VotosList;

        }else if(COLUMN_NAME.equals("despedida")){
            arrayList = DespedidaList;

        }else if(COLUMN_NAME.equals("assinatura")){
            arrayList = AssinaturaList;
        }

        Cursor data = xdbhelper.getID(OldValor, COLUMN_NAME);
        int ID = -1;
        while(data.moveToFirst()){
            //Apanha o ID se existir na Base de Dados.
            ID = data.getInt(0);
        }

        if(valor.equals("")){
            xdbhelper.deleteItem(ID);
            arrayList.remove(idArrayList);
        }else if(tipo == 'r'){
            xdbhelper.updateItem(OldValor, valor, ID, COLUMN_NAME);
            arrayList.set(idArrayList, valor);
        }
        if(tipo == 'a' && (!valor.equals(""))) {
            arrayList.add(valor);
            if(COLUMN_NAME.equals("saudacao")){
                addData(valor, "null", "null", "null", "null", "null");
                SaudacoesList = arrayList;
                spinSaudacoes.setAdapter(adapter);
            }else if(COLUMN_NAME.equals("quebragelo")){
                addData("null", valor , "null" , "null" , "null" , "null");
                QuebraGeloList = arrayList;
                spinQuebraGelo.setAdapter(adapter);
            }else if(COLUMN_NAME.equals("corpo")){
                addData("null", "null", valor, "null", "null", "null");
                CorpoList = arrayList;

            }else if(COLUMN_NAME.equals("votos")){
                addData("null", "null", "null", valor, "null", "null");
                VotosList = arrayList;
                SpinVotos.setAdapter(adapter);
            }else if(COLUMN_NAME.equals("despedida")){
                addData("null", "null", "null", "null", valor, "null");
                DespedidaList = arrayList;
                SpinDespedida.setAdapter(adapter);
            }else if(COLUMN_NAME.equals("assinatura")){
                addData("null", "null", "null", "null", "null", valor);
                AssinaturaList = arrayList;
            }
        }

        adapter = new ArrayAdapter<String>(this, R.layout.my_selected_item, arrayList);
        adapter.setDropDownViewResource(R.layout.my_dropdown_item);
        if(COLUMN_NAME.equals("saudacao")){
            spinSaudacoes.setAdapter(adapter);
        }else if(COLUMN_NAME.equals("quebragelo")){
            spinQuebraGelo.setAdapter(adapter);
        }else if(COLUMN_NAME.equals("corpo")){
            //
        }else if(COLUMN_NAME.equals("votos")){
            SpinVotos.setAdapter(adapter);
        }else if(COLUMN_NAME.equals("despedida")){
            SpinDespedida.setAdapter(adapter);
        }else if(COLUMN_NAME.equals("assinatura")){
           //
        }
    }

    private void enviarMensagemIntento(String MensagemComposta){
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, MensagemComposta);
        startActivity(intent);
    }

    public void addData(String newEntry, String newEntryQuebraGelo, String neCorpo, String neVotos, String neDespedida, String neAssinatura){
        boolean insertData = xdbhelper.addData(newEntry, newEntryQuebraGelo, neCorpo, neVotos, neDespedida, neAssinatura);
        if(insertData){
            toastMessage("Mensagem criada com sucesso.");
        }else{
            toastMessage("{CriarMensagem->addData->" + "{" + "}: Algo não funcionou}");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}