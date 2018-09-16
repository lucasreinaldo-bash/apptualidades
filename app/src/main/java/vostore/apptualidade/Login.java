package vostore.apptualidade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Login extends AppCompatActivity {

    ImageView top;
    Animation fromlogo;
    Button botaoEntrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        botaoEntrar = findViewById(R.id.botaoentrar);


        top = findViewById(R.id.logologin);
        fromlogo = AnimationUtils.loadAnimation(this, R.anim.fromlogo);
        top.setAnimation(fromlogo);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Inicio.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
