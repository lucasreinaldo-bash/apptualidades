package vostore.apptualidade.Fragments;


import android.content.Intent;
import android.media.MediaCas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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

import vostore.apptualidade.ConfiguracaoFirebase;
import vostore.apptualidade.Consts.Const;
import vostore.apptualidade.Inicio;
import vostore.apptualidade.Login;
import vostore.apptualidade.R;
import vostore.apptualidade.Simulado.QuizActivity;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.facebook.GraphRequest.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimuladoFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private View myFragment;
    FirebaseAuth mAuth, auth;
    private Button comecar, voltar;
    GoogleApiClient googleApiClient;
    private CallbackManager mCallBackManager;


    public static SimuladoFragment newInstance() {
        // Required empty public constructor
        SimuladoFragment simuladoFragment = new SimuladoFragment();
        return simuladoFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_simulado,
                container, false);

        comecar = rootView.findViewById(R.id.btn_comecar);
        voltar = rootView.findViewById(R.id.btn_voltar);

        auth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        FacebookSdk.sdkInitialize(getApplicationContext());
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });


        comecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              updateDetail();

            }
        });
         return rootView;



    }


    public void updateDetail() {
        Intent intent = new Intent(getActivity(), Regras.class);
        startActivity(intent);
    }
    private void signOut() {
        // Firebase sign out

        mAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        mAuth.signOut();
        LoginManager.getInstance().logOut();

        // Google sign out


        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        //updateUI(null);
                        Intent intent = new Intent(getActivity(), Login.class);
                        startActivity(intent);
                    }
                });


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}



