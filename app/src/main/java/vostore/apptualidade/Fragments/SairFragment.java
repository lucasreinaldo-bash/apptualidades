package vostore.apptualidade.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

import vostore.apptualidade.ConfiguracaoFirebase;
import vostore.apptualidade.Login;
import vostore.apptualidade.R;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
/**
 * A simple {@link Fragment} subclass.
 */
public class SairFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    private View myFragment;
    FirebaseAuth mAuth, auth;
    GoogleApiClient googleApiClient1;
    private CallbackManager mCallBackManager;

    public static SairFragment newInstance(){
        // Required empty public constructor

        SairFragment sairFragment = new SairFragment();
        return sairFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_sair,
                container, false);




        auth = FirebaseAuth.getInstance();



        //FacebookSdk.sdkInitialize(getApplicationContext());



        return rootView;
    }

    private void signOut() {
        // Firebase sign out

        mAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        mAuth.signOut();


        // Google sign out


        Auth.GoogleSignInApi.signOut(googleApiClient1).setResultCallback(
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


    @Override
    public void onStart() {
        super.onStart();

    }


}
