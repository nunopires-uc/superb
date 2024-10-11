package pt.ubi.di.pdm.a44240_t0;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class EditarAtributos extends AppCompatActivity {

    private TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_editar_atributos);


        titulo = findViewById(R.id.titulo);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Confetti_Stream.ttf");
        titulo.setTypeface(customFont);
    }
}