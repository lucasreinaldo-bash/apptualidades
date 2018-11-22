package vostore.apptualidade;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.security.MessageDigest;

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

        printHashKey(Entrar.this);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Entrar.this, Login.class);
                startActivity(intent);
                finish();
                //as linhas overridePendingTransition(R.anim.bottom_in, R.anim.top_out);


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
    public static void printHashKey(Context context) {
        try {
            final PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                final MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                final String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("AppLog", "key:" + hashKey + "=");
            }
        } catch (Exception e) {
            Log.e("AppLog", "error:", e);
        }
    }
}
