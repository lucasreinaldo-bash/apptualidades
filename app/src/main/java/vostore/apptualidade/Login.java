package vostore.apptualidade;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
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
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mukeshsolanki.sociallogin.google.GoogleHelper;

import java.util.Arrays;

public class Login extends AppCompatActivity {

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
    private CallbackManager mCallBackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //verificarUsuarioLogado();


        //Fazendo Cast
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

        // Initialize Facebook Login button

        // Utilizando o OnClick
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithPublishPermissions(Login.this, Arrays.asList("email","public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                        // ...
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                        // ...
                    }
                });

            }
        });
        cadastre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Cadastro.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
                                       // Toast.makeText(Login.this,"Login bem sucedido",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this,Inicio.class);
                                        startActivity(intent);
                                        finish();
                                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                    } else{
                                        Toast toast = new Toast(Login.this);
                                        ImageView view = new ImageView(Login.this);
                                        view.setImageResource(R.drawable.toast_erro);
                                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                        toast.setView(view);
                                        toast.show();


                                       // Toast.makeText(Login.this,"Não foi possível entrar no ambiente",Toast.LENGTH_SHORT).show();
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

    public void onStart() {
                super.onStart();
                // Check if user is signed in (non-null) and update UI accordingly.
               // FirebaseUser currentUser = mAuth.getCurrentUser();
               //if (currentUser != null){
                 //  updateUI();
               }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       // mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private  void updateUI(){
       // Toast.makeText(Login.this, "Login Realizado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Login.this, Inicio.class);
        startActivity(intent);
        finish();

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



    //Utilizando Intent para estabelecer um ação no clique do botão "voltar", nativo dos dispositivos android
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
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI();
                        }

                        // ...
                    }
                });
    }


}