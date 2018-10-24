package vostore.apptualidade.Simulado;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vostore.apptualidade.Inicio;
import vostore.apptualidade.R;
import vostore.apptualidade.SplashActivity;


public class HighestScoreActivity extends AppCompatActivity {
    private ImageView voltar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);

        voltar = (ImageView) findViewById(R.id.voltar_id);




        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighestScoreActivity.this, Inicio.class);
                startActivity(intent);
            }
        });

        TextView txtScore = (TextView) findViewById(R.id.textScore);
        TextView txtHighScore = (TextView) findViewById(R.id.textHighScore);
        // receive the score from last activity by Intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        // display current score
        txtScore.setText(score+" pontos!");

        // use Shared preferences to save the best score
        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        int highscore = mypref.getInt("highscore",0);
        if(highscore>= score)
            txtHighScore.setText("Recorde: "+highscore+" questões corretas");
        else
        {
            txtHighScore.setText("Novo recorde: "+score + " acertos.");
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore", score);
            editor.commit();
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(HighestScoreActivity.this, QuizActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed () {

        Intent intent = new Intent(HighestScoreActivity.this,SplashActivity.class);
        startActivity(intent);
        finish();
    }
}

