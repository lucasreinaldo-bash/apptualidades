package vostore.apptualidade;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


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
   }

    //Usando intent no método run
    public void run(){
        startActivity(new Intent(this, Entrar.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}