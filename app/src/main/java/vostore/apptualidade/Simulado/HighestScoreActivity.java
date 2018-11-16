package vostore.apptualidade.Simulado;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import vostore.apptualidade.Fragments.Regras;
import vostore.apptualidade.Inicio;
import vostore.apptualidade.R;
import vostore.apptualidade.SitePrincipal;


public class HighestScoreActivity extends AppCompatActivity {
    private ImageView voltar,emoticon;
    private Button refazer,irsite;
    private TextView frasepontuacao;
    private ImageView fb;
    private int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);

        emoticon = (ImageView) findViewById(R.id.emoticon);
        refazer = findViewById(R.id.btn_refazer);
        irsite = findViewById(R.id.btn_irsite);
        fb = findViewById(R.id.fb);
        frasepontuacao = findViewById(R.id.frasepontuacao);


        refazer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refazer.setBackgroundResource(R.drawable.final_refazer2);
                Intent intent = new Intent(HighestScoreActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
            }
        });



        irsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irsite.setBackgroundResource(R.drawable.final_irsite2);
                Intent intent = new Intent(HighestScoreActivity.this, SitePrincipal.class);
                startActivity(intent);
                finish();
            }
        });



        TextView txtScore = (TextView) findViewById(R.id.textScore);
        TextView txtHighScore = (TextView) findViewById(R.id.textHighScore);
        // receive the score from last activity by Intent
        Intent intent = getIntent();
          score = intent.getIntExtra("score", 0);
        // display current score
        txtScore.setText(score+" pontos!");
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




        Intent sendIntent = new Intent ();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT , "Fiz "+ score+"no Apptualidades . " );

        sendIntent.setType( "text / plain" );

        startActivity(sendIntent);
     }
        });
        if (score <= 30){
            frasepontuacao.setText("Sua pontuação foi baixa.\nContinue estudando.");
            emoticon.setBackgroundResource(R.drawable.final_triste);
        }
        else if (score <= 70){
            frasepontuacao.setText("Você está na média,\nmas pode melhorar!");
            emoticon.setBackgroundResource(R.drawable.final_meiotriste);
        }
        else if (score < 100) {
            frasepontuacao.setText("Uau! Será que você consegue\nchegar aos 100 pontos?");

            emoticon.setBackgroundResource(R.drawable.final_feliz);
        }
        else if (score == 100){
            frasepontuacao.setText("Parabéns, você mandou bem!\nEstá atualizado sobre os principais\nassuntos. Continue acompanhando\n  fora de nada e continuar\naprendendo.");

            emoticon.setBackgroundResource(R.drawable.final_superfeliz);
        }

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

        Intent intent = new Intent(HighestScoreActivity.this,Inicio.class);
        startActivity(intent);
        finish();
    }
}

