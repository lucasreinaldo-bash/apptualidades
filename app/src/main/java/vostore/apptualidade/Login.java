package vostore.apptualidade;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mukeshsolanki.sociallogin.facebook.FacebookHelper;
import com.mukeshsolanki.sociallogin.facebook.FacebookListener;
import com.mukeshsolanki.sociallogin.google.GoogleHelper;
import com.mukeshsolanki.sociallogin.google.GoogleListener;

import java.util.Arrays;

import vostore.apptualidade.testeingles.SimuladoFragment;

public class Login extends AppCompatActivity implements FacebookListener, GoogleListener{

    ImageView top;
    Animation fromlogo;
    Button btnLogin,btnGoogle, btnFacebook;
    private CallbackManager mCallbackManager;
    private static final String TAG = "FACELOG";
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private EditText senhausuario,emailusuario;
    private TextView cadastre;
    HelperFacebook mFacebook;
    GoogleHelper mGoogle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //verificarUsuarioLogado();


        // Iniciando com o Facebook
        FacebookSdk.setApplicationId(getResources().getString(R.string.facebook_application_id));
        FacebookSdk.sdkInitialize(this);
        mFacebook = new HelperFacebook(this);

        //Iniciando com o Google
        mGoogle = new GoogleHelper(this,this,null);
        mAuth = FirebaseAuth.getInstance();

        cadastre = findViewById(R.id.textView5);

        btnLogin = findViewById(R.id.botaoentrar);
        top = findViewById(R.id.logologin);
        fromlogo = AnimationUtils.loadAnimation(this, R.anim.fromlogo);
        btnFacebook = findViewById(R.id.btn_facebook);
        btnGoogle = findViewById(R.id.btn_google);
        top.setAnimation(fromlogo);

        senhausuario = findViewById(R.id.senhaid);
        emailusuario = findViewById(R.id.emailid);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        cadastre.setPaintFlags(cadastre.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        //Instanciando o servidor de dados



        mCallbackManager = CallbackManager.Factory.create();

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoogle.performSignIn(Login.this);
            }
        });
        cadastre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Inicio.class);
                startActivity(intent);
                finish();
            }
        });
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             mFacebook.performSignIn(Login.this);

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailusuario.getText().toString();
                if (validaremail(email) && validarsenha()){
                    String senha = senhausuario.getText().toString();
                    mAuth.signInWithEmailAndPassword(email,senha)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){


                                        Toast.makeText(Login.this,"Login bem sucedido",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this,Inicio.class);
                                        startActivity(intent);
                                        finish();
                                    } else{
                                        Toast.makeText(Login.this,"Não foi possível entrar no ambiente",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(Login.this,"Erro de Login",Toast.LENGTH_SHORT).show();
                }


            }
        });
            }



            //Métodos

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mFacebook.onActivityResult(requestCode, resultCode, data);
        mGoogle.onActivityResult(requestCode,resultCode, data);
    }

    private  void updateUI(){
        Toast.makeText(this, "Login Realizado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Login.this, Inicio.class);
        startActivity(intent);

    }

    //Funçoes criadas
    private boolean validaremail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    public boolean validarsenha(){
        String contraseña;
        contraseña = senhausuario.getText().toString();
        if(contraseña.length()>=6 && contraseña.length()<=16){
            return true;
        }else return false;
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Login.this, Entrar.class);
        startActivity(intent);
        finish();


    }
    private void verificarUsuarioLogado(){
        mAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if(mAuth.getCurrentUser()!= null ){
            abrirTelaPrincipal();

        }
    }
    private void abrirTelaPrincipal() {


        Intent intent = new Intent(Login.this,Inicio.class);
        startActivity(intent);


    }

    @Override
    public void onFbSignInFail(String errorMessage) {
        Toast.makeText(this, ""+errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFbSignInSuccess(String authToken, String userId) {
        Toast.makeText(this, ""+userId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFBSignOut() {
        Toast.makeText(this, "Signout !!!", Toast.LENGTH_SHORT).show();

    }


    //Métodos do Google
    @Override
    public void onGoogleAuthSignIn(String authToken, String userId) {
        Toast.makeText(this, ""+userId, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onGoogleAuthSignInFailed(String errorMessage) {
        Toast.makeText(this, ""+errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onGoogleAuthSignOut() {
        Toast.makeText(this, "Desconectado", Toast.LENGTH_SHORT).show();

    }
}