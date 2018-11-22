package vostore.apptualidade;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
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
import com.mukeshsolanki.sociallogin.google.GoogleHelper;
import com.tfb.fbtoast.FBToast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import vostore.apptualidade.Consts.Const;
import vostore.apptualidade.Firebase.Usuario;
import vostore.apptualidade.helper.Preferencias;

import static vostore.apptualidade.Consts.Const.RC_SIGN_IN;

public class Login extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener {

    ImageView top;
    Animation fromlogo;
    Button btnLogin,btnGoogle, btnFacebook;
    private GoogleApiClient googleApiClient;
    private CallbackManager mCallbackManager;
    private static final String TAG = "FACELOG";

    private FirebaseAuth mAuth,firebaseAuth;
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

        cadastre = findViewById(R.id.textView5);
        btnLogin = findViewById(R.id.botaoentrar);
        top = findViewById(R.id.logologin);
        fromlogo = AnimationUtils.loadAnimation(this, R.anim.fromlogo);
        btnFacebook = findViewById(R.id.btn_facebook);
        btnGoogle = findViewById(R.id.btn_google);
        top.setAnimation(fromlogo);
        senhausuario = findViewById(R.id.senhaid);
        emailusuario = findViewById(R.id.emailid);

        // Instanciando servidor
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();

        //Aplicando uma linha inferior ao nome
        cadastre.setPaintFlags(cadastre.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //Configurações do Login da Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        //LoginManager.getInstance().logOut();






        //Ação do Botão Google
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginGoogle();


            }
        });
        //Ação do Botão Facebook
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loginFacebook();
            }
        });
        //Ação do nome Cadastre
        cadastre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              cadastrar();
            }
        });
        //Ação do Login Normal
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
}





    // *-------- MÉTODOS ----------


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





  private void verificarUsuarioLogado(){
        mAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if(mAuth.getCurrentUser()!= null ){
           updateUI();
        }
    }





    //Login com o Google
    private void loginGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                            Log.d(Const.TAG_LOGIN_GOOGLE, "Google login error", task.getException());

                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                            saveUser(currentUser, acct.getEmail());
                        } else
                            Toast.makeText(Login.this, "Authentication with Google failed.",
                                    Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Const.RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed
                Log.e(TAG, "Google Sign In failed.");
            }
        }
        //Sem essa instrução , o login do facebook não irá funcionar
        else
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        // mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }


    //Login Convencional
    private void login(){
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
                                //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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

    //Login com o Facebook
    private void loginFacebook() {
        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(Login.this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

                Log.d(Const.TAG_LOGIN_FACEBOOK, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(Const.TAG_LOGIN_FACEBOOK, "facebook:onError", error);
            }
        });
    }
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                            Log.e(Const.TAG_LOGIN_FACEBOOK, "Facebook error", task.getException());

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            saveUser(user,null);
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


    //Salvar dos do usuário em uma classe
    private void saveUser (FirebaseUser firebaseUser, String email){
        FBToast.successToast(Login.this,"Login efetuado com sucesso !", FBToast.LENGTH_SHORT);
        FirebaseUser currentUser = firebaseUser;
        Preferencias preferencias = new Preferencias(Login.this);
        preferencias.savePreferences(getString(R.string.id_user_app), firebaseUser.getUid());
        preferencias.savePreferences(getString(R.string.username_app), firebaseUser.getDisplayName());
        if (email != null) preferencias.savePreferences(getString(R.string.email_app), email);

        Usuario user = new Usuario();
        user.setId(firebaseUser.getUid());
        user.setNome(currentUser.getDisplayName());
        if (email != null)
            user.setEmail(email);
        user.saveUser();
        updateUI();


    }



    //Abrir tela seguinte
    private  void updateUI(){
        // Toast.makeText(Login.this, "Login Realizado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Login.this, Inicio.class);
        startActivity(intent);
        finish();

    }
    //Abrir tela Cadastro
    private void cadastrar(){
        Intent intent = new Intent(Login.this,Cadastro.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }



    //Utilizando Intent para estabelecer um ação no clique do botão "voltar", nativo dos dispositivos android
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Login.this, Entrar.class);
        startActivity(intent);
        finish();


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        FBToast.errorToast(Login.this,"Erro no Google Play Services",FBToast.LENGTH_SHORT);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public void onPause() {
        super.onPause();

    }




    }
