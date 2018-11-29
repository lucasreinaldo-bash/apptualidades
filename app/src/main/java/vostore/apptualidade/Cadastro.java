package vostore.apptualidade;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.tfb.fbtoast.FBToast;

import java.util.Arrays;

import vostore.apptualidade.Consts.Const;
import vostore.apptualidade.Firebase.Usuario;
import vostore.apptualidade.helper.Preferencias;

import static vostore.apptualidade.Consts.Const.RC_SIGN_IN;

public class Cadastro extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private EditText txtNome,txtEmail,txtSenha,txtSenhaRepetida,codigo,txtsobrenome,txtuserName;
    private FirebaseAuth mAuth, mAuth2;
    private Button btnRegistrar,btnGoogle, btnFacebook;
    private FirebaseDatabase database;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private CallbackManager mCallbackManager;
    private static final String TAG = "FACELOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // Fazendo Cast
        txtsobrenome = findViewById(R.id.rg_sobrenome);
        txtNome = (EditText) findViewById(R.id.rg_nome);
        txtEmail = (EditText) findViewById(R.id.rg_email);
        txtSenha = (EditText) findViewById(R.id.rg_senha);
        btnRegistrar = (Button) findViewById(R.id.botaocadastrar);
        btnFacebook = findViewById(R.id.btn_facebook);
        btnGoogle = findViewById(R.id.btn_google);
        // Instaciando o servidor
        // Instanciando servidor
        mAuth = FirebaseAuth.getInstance();
        mAuth2 = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();

        //Configurações do Login da Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        LoginManager.getInstance().logOut();



        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginGoogle();
            }
        });
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFacebook();
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = txtEmail.getText().toString();
                final String nomecompleto = txtNome.getText().toString() +" "+ txtsobrenome.getText().toString();
                final String nome = txtNome.getText().toString();
                final String senhausuario = txtSenha.getText().toString();



                if(isValidEmail(email) && validarContraseña() && validarNombre(nome)){
                    final String senha = txtSenha.getText().toString();
                    mAuth2.createUserWithEmailAndPassword(email, senha)
                            .addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FBToast.successToast(Cadastro.this,"Seja bem-vindo!",FBToast.LENGTH_SHORT);

                                        Usuario usuario = new Usuario();
                                        usuario.setNome(nome);
                                        usuario.setEmail(email);
                                        usuario.setSenha(senhausuario);
                                        usuario.setNomeCompleto(nomecompleto);
                                        FirebaseUser currentUser = mAuth2.getCurrentUser();
                                        DatabaseReference reference = database.getReference("Usuario/"+currentUser.getUid());
                                        reference.setValue(usuario);
                                        Intent intent = new Intent(Cadastro.this, Inicio.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        FBToast.errorToast(Cadastro.this, "Erro ao fazer o registro", Toast.LENGTH_SHORT);
                                    }
                                }
                            });
                }else{
                    FBToast.errorToast(Cadastro.this, "Algum erro foi detectado! Está com internet ?", Toast.LENGTH_SHORT);
                }

            }
        });



    }
    //método para validar e-mail
    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    //método para validar senha
    public boolean validarContraseña(){
        String contraseña;
        contraseña = txtSenha.getText().toString();

            if(contraseña.length()>=6 && contraseña.length()<=16){
                return true;
            }else return false;

    }
    //método para validar nome
    public boolean validarNombre(String nombre){
        return !nombre.isEmpty();
    }


    //Login Gogle
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
                            FBToast.errorToast(Cadastro.this, "Authentication with Google failed.",
                                    Toast.LENGTH_SHORT);
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

    //Login Facebook
    private void loginFacebook() {
        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(Cadastro.this, Arrays.asList("email", "public_profile"));
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
                            Toast.makeText(Cadastro.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI();
                        }

                        // ...
                    }
                });
    }
    //Salvar dos do usuário em uma classe
    private void saveUser (FirebaseUser firebaseUser, String email){
        FBToast.successToast(Cadastro.this,"Login efetuado com sucesso!", FBToast.LENGTH_SHORT);
        FirebaseUser currentUser = firebaseUser;
        Preferencias preferencias = new Preferencias(Cadastro.this);
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
        Intent intent = new Intent(Cadastro.this, Inicio.class);
        startActivity(intent);
        finish();

    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Cadastro.this, Entrar.class);
        startActivity(intent);
        finish();


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        FBToast.errorToast(Cadastro.this,"Erro no Google Play Services",FBToast.LENGTH_SHORT);
    }
}