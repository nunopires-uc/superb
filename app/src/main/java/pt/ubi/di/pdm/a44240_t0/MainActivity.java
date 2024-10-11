package pt.ubi.di.pdm.a44240_t0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import pt.ubi.di.pdm.a44240_t0.databinding.ActivityMainBinding;

//Superb

public class MainActivity extends AppCompatActivity {

    private TextView titulo;
    private ImageButton EditarAtributos, CriarMensagem;
    private androidx.appcompat.widget.AppCompatButton btnAcercaDe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        titulo = findViewById(R.id.titulo);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Confetti_Stream.ttf");
        titulo.setTypeface(customFont);

        CriarMensagem = findViewById(R.id.criar_mensagem);
        CriarMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CriarMensagem.class);
                startActivity(intent);
            }
        });


        EditarAtributos = findViewById(R.id.editar_atributos);
        EditarAtributos.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   //Intent intent = new Intent(MainActivity.this, CriarMensagem.class);
                   Intent intent = new Intent(MainActivity.this, Historico.class);
                   startActivity(intent);
               }
           }
        );

        btnAcercaDe = findViewById(R.id.btnAcercaDe);
        btnAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, pt.ubi.di.pdm.a44240_t0.EditarAtributos.class);
                startActivity(intent);
            }
        });
    }

    public void jmpCriarMensagem(){
        Intent intent = new Intent(MainActivity.this, CriarMensagem.class);
        startActivity(intent);
    }

    public void jmpHistorico(){
        Intent intent = new Intent(MainActivity.this, Historico.class);
        startActivity(intent);
    }

    public void mostrar(View v){
        Toast.makeText(MainActivity.this, "TextView Clicada", Toast.LENGTH_SHORT).show();
    }
}