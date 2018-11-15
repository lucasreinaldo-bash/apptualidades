package vostore.apptualidade.Fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vostore.apptualidade.Inicio;
import vostore.apptualidade.Login;
import vostore.apptualidade.R;
import vostore.apptualidade.Simulado.QuizActivity;

public class Regras extends AppCompatActivity {

    private Button btncomecar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regras);

        btncomecar = findViewById(R.id.botao_regra);


        btncomecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btncomecar.setBackgroundResource(R.drawable.comecar_regra2);

                Intent intent = new Intent(Regras.this, QuizActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
