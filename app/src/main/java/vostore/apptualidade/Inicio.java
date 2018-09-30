package vostore.apptualidade;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import vostore.apptualidade.testeingles.FavoritosFragment;
import vostore.apptualidade.testeingles.SairFragment;
import vostore.apptualidade.testeingles.SimuladoFragment;
import vostore.apptualidade.testeingles.SobreFragment;

public class Inicio extends AppCompatActivity {

    BottomNavigationView mNavigation;
    private FrameLayout mFrame;

    private FavoritosFragment favoritosFragment;
    private SimuladoFragment simuladoFragment;
    private SobreFragment sobreFragment;
    private SairFragment sairFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        simuladoFragment = new SimuladoFragment();
        favoritosFragment = new FavoritosFragment();
        sobreFragment = new SobreFragment();
        sairFragment = new SairFragment();


        mFrame = findViewById(R.id.main_frame);
        mNavigation = findViewById(R.id.navigation);
        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_simulado:
                        selectedFragment = SimuladoFragment.newInstance();
                        break;

                }

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, selectedFragment);
                fragmentTransaction.commit();


                return true;


            }

        });

        setDefaultFragment();


    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, SimuladoFragment.newInstance());
        transaction.commit();
    }
}
