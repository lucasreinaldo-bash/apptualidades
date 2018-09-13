package vostore.apptualidade;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vostore.apptualidade.testeingles.QuizActivity;

public class Respostas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respostas);

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF8F00")));
    }
    @Override
    public void onBackPressed () {
        Intent intent = new Intent(Respostas.this, QuizActivity.class);
        startActivity(intent);


    }
}
