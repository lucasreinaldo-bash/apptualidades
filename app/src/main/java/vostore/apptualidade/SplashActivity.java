package vostore.apptualidade;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import vostore.apptualidade.testeingles.QuizActivity;


/**
 * Essa classe é utilizada como tela inicial. Possui uma animação e faz transição após 3 segundos para a MainActivity
 */
public class SplashActivity extends AppCompatActivity implements Runnable {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

       //Fazendo cast e instanciando a animação
        //comecar = findViewById(R.id.idComecar);

    //Determinando o tempo de 3 segundos para entrar na próxima activity
        Handler handler = new Handler();
        handler.postDelayed(this, 5000);

       /*comecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this, QuizActivity.class);
                startActivity(intent);


            }
        }); */
    }

    //Usando intent no método run
    public void run(){
        startActivity(new Intent(this, Entrar.class));
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}