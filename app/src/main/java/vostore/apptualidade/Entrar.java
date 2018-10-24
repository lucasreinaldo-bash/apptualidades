package vostore.apptualidade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Entrar extends AppCompatActivity {

    private Button btnEntrar, btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        //Fazendo Cast
        btnEntrar = findViewById(R.id.btn_entrar);
        btnCadastrar = findViewById(R.id.btn_cadastrar);


        //Utilizando um método OnClick para "escutar" a interação do usuário e leva-lo para a tela correspondente


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Entrar.this, Login.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.bottom_in, R.anim.top_out);


            }
        });
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnCadastrar.setBackgroundResource(R.drawable.botaocadastrar2);
                Intent intent = new Intent(Entrar.this, Cadastro.class);
                startActivity(intent);
                finish();


            }
        });
    }
}
