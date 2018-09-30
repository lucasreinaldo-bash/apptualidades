package vostore.apptualidade.testeingles;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import vostore.apptualidade.R;
import vostore.apptualidade.Respostas;
import vostore.apptualidade.SplashActivity;


public class QuizActivity extends AppCompatActivity {

    private ImageView logo;
    private QuestionBank mQuestionLibrary = new QuestionBank();
    private TextView mScoreView;   // view for current total score
    private TextView mQuestionView;  //current question to answer
    private TextView leia,resposta;
    private Button btnAvancar;
    private Button mButtonChoice1; // multiple choice 1 for mQuestionView
    private Button mButtonChoice2; // multiple choice 2 for mQuestionView
    private Button mButtonChoice3; // multiple choice 3 for mQuestionView
    private Button mButtonChoice4; // multiple choice 4 for mQuestionView
    private String mAnswer;  // correct answer for question in mQuestionView
    private int mScore = 0;  // current total score
    private int mQuestionNumber = 0; // current question number
    private int quantidade =1;
    public LinearLayout linear;
    private MediaPlayer mp1, mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

       /* android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF8F00")));
*/

        linear = findViewById(R.id.linear_id);
        mScoreView = findViewById(R.id.score);
        mQuestionView = findViewById(R.id.question);
        mButtonChoice1 = findViewById(R.id.choice1);
        mButtonChoice2 = findViewById(R.id.choice2);
        btnAvancar = findViewById(R.id.btn_avancar);
        mButtonChoice3 = findViewById(R.id.choice3);
        mButtonChoice4 = findViewById(R.id.choice4);
        leia = findViewById(R.id.leiaMais);
        resposta = findViewById(R.id.resposta_text);

        mp1 = MediaPlayer.create(QuizActivity.this, R.raw.correct);
        mp2 = MediaPlayer.create(QuizActivity.this, R.raw.wrong);

        resposta.setText("");



        Typeface font = Typeface.createFromAsset(this.getAssets(), "p_black.OTF");
        mScoreView.setTypeface(font);




        updateQuestion();
        // show current total score for the user
        updateScore(mScore);

        leia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(QuizActivity.this, Respostas.class);
                startActivity(intent);
                finish();
            }
        });
        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mButtonChoice1.setBackgroundResource(R.drawable.botao_atualidades);
                mButtonChoice2.setBackgroundResource(R.drawable.botao_atualidades);
                mButtonChoice3.setBackgroundResource(R.drawable.botao_atualidades);
                mButtonChoice4.setBackgroundResource(R.drawable.botao_atualidades);
                updateScore(mScore);
                updateQuestion();


                if (quantidade < mQuestionLibrary.getLength()){
                    quantidade++;
                    mScoreView.setText("" + quantidade + " de 10");
                }



            }
        });

        resposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mQuestionNumber) {
                    case 1:


                        break;
                    case 2:
                                               break;
                    case 3:

                        break;
                    case 4:

                        break;
                }
            }
        });

    }

    private void updateQuestion() {
        // check if we are not outside array bounds for questions
        if (mQuestionNumber < mQuestionLibrary.getLength()) {





            linear.setVisibility(View.INVISIBLE);
            // set the text for new question, and new 4 alternative to answer on four buttons
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            mButtonChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            mButtonChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            mButtonChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber, 4));


            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        } else {
            Toast.makeText(QuizActivity.this, "Venha ser o nosso aluno ! Você terá acesso a novas questões diárias e ainda terá 'recompensas'", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(QuizActivity.this, HighestScoreActivity.class);
            intent.putExtra("score", mScore); // pass the current score to the second screen
            startActivity(intent);
            finish();
        }
    }

    // show current total score for the user
    private void updateScore(int point) {
      // + mQuestionLibrary.getLength());
    }

    public void onClick(View view) {
        //all logic for all answers buttons in one method
        int k = 1;
        Button answer = (Button) view;

        // if the answer is correct, increase the score




        // Extraindo texto dos botões e atribuindo a uma variavel do tipo String
       String alternativa1 = mButtonChoice1.getText().toString();
       String alternativa2 = mButtonChoice2.getText().toString();
       String alternativa3 = mButtonChoice3.getText().toString();
       String alternativa4 = mButtonChoice4.getText().toString();


        //Instrução if para indentificar qual das alternativas estao corretas
        // A que tiver correta, será destacada com a cor verde



        if (answer.getText() == mAnswer)
        {

            mScore = mScore + 1;
            answer.setBackgroundResource(R.drawable.botao_quiz);

            linear.setVisibility(View.VISIBLE);
            resposta.setText("Marielle era uma defensora dos\n" +
                    "direitos humanos e seus projetos de lei visavam maior\n" +
                    "atenção do Estado sobre o assédio, cuidados com a\n" +
                    "educação, entre outros aspectos sociais.");

            //Toast.makeText(QuizActivity.this, "Acertou!", Toast.LENGTH_SHORT).show();
            mp1.start();
            //updateScore(mScore);
            //updateQuestion();
            Toast.makeText(QuizActivity.this, "Acertou!", Toast.LENGTH_SHORT).show();



        } else {
            mp2.start();
            linear.setVisibility(View.VISIBLE);
            resposta.setText("Marielle era uma defensora dos\n" +
                    "direitos humanos e seus projetos de lei visavam maior\n" +
                    "atenção do Estado sobre o assédio, cuidados com a\n" +
                    "educação, entre outros aspectos sociais.");

            if (alternativa1 == mAnswer)
                mButtonChoice1.setBackgroundResource(R.drawable.botao_quiz);
            else if (alternativa2 == mAnswer)
                mButtonChoice2.setBackgroundResource(R.drawable.botao_quiz);
            else if (alternativa3 == mAnswer)
                mButtonChoice3.setBackgroundResource(R.drawable.botao_quiz);
            else if ( alternativa4 == mAnswer)
                mButtonChoice4.setBackgroundResource(R.drawable.botao_quiz);
            answer.setBackgroundResource(R.drawable.botao_quiz2);

            Toast.makeText(QuizActivity.this, "Errou!", Toast.LENGTH_SHORT).show();



            // show current total score for the user
            vibrar();
            updateScore(mScore);
            mp2.start();
            // once user answer the question, we move on to the next one, if any
            //updateQuestion();
        }
    }
    @Override
    public void onBackPressed () {
        Intent intent = new Intent(QuizActivity.this, SplashActivity.class);
        startActivity(intent);
        finish();

    }

    //Função para o celular vibrar
    private void vibrar () {
        Vibrator rr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long milisegundos =1000;
        rr.vibrate(milisegundos);

    }

  }