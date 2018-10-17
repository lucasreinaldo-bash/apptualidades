package vostore.apptualidade.testeingles;


import android.content.Context;
import android.content.Intent;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import vostore.apptualidade.Inicio;
import vostore.apptualidade.R;

import vostore.apptualidade.SplashActivity;


public class QuizActivity extends AppCompatActivity {


    private QuestionBank mQuestionLibrary = new QuestionBank();
    private TextView mScoreView;   // view for current total score
    private TextView mQuestionView;  //current question to answer
    private TextView resposta_certa,resposta_descricao;
    private Button leia,btnAvancar;
    private ImageView progressBar;
    private Button mButtonChoice1; // multiple choice 1 for mQuestionView
    private Button mButtonChoice2; // multiple choice 2 for mQuestionView
    private Button mButtonChoice3; // multiple choice 3 for mQuestionView
    private Button mButtonChoice4; // multiple choice 4 for mQuestionView
    private String mAnswer;  // correct answer for question in mQuestionView

    private int mQuestionNumber = 0; // current question number
    private int quantidade =1;
    private int question = 2;
    public LinearLayout linear,linear2,linear3;
    private MediaPlayer mp1, mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

       /* android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF8F00")));
*/
        progressBar = findViewById(R.id.progress);
        linear2 = findViewById(R.id.linear_id2);
        linear3 = findViewById(R.id.linear_id3);
        linear = findViewById(R.id.linear_id);
        mScoreView = findViewById(R.id.score);
        mQuestionView = findViewById(R.id.question);
        mButtonChoice1 = findViewById(R.id.choice1);
        mButtonChoice2 = findViewById(R.id.choice2);
        btnAvancar = findViewById(R.id.btn_avancar);
        mButtonChoice3 = findViewById(R.id.choice3);
        mButtonChoice4 = findViewById(R.id.choice4);
        leia = findViewById(R.id.btn_leia);
        resposta_descricao = findViewById(R.id.descricao_resposta);



        mp1 = MediaPlayer.create(QuizActivity.this, R.raw.correct);
        mp2 = MediaPlayer.create(QuizActivity.this, R.raw.wrong);

        updateQuestion();








        leia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mButtonChoice1.setBackgroundResource(R.drawable.botao_atualidades);
                mButtonChoice2.setBackgroundResource(R.drawable.botao_atualidades);
                mButtonChoice3.setBackgroundResource(R.drawable.botao_atualidades);
                mButtonChoice4.setBackgroundResource(R.drawable.botao_atualidades);

                mButtonChoice1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.nomeBotoes));
                mButtonChoice2.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.nomeBotoes));
                mButtonChoice3.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.nomeBotoes));
                mButtonChoice4.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.nomeBotoes));



                updateQuestion();


                if (quantidade < mQuestionLibrary.getLength()){
                    quantidade++;
                    mScoreView.setText("" + quantidade + " de 10");
                }



            }
        });



    }

    private void updateQuestion() {
        // check if we are not outside array bounds for questions
        if (mQuestionNumber < mQuestionLibrary.getLength()) {

            switch (mQuestionNumber){
                case 1:
                    Drawable drawable1= getResources().getDrawable(R.drawable.progress2);
                    progressBar.setImageDrawable(drawable1);
                    break;
                case 2:
                    Drawable drawable2= getResources().getDrawable(R.drawable.progress2);
                    progressBar.setImageDrawable(drawable2);
                    break;
                case 3:
                    Drawable drawable3= getResources().getDrawable(R.drawable.progress3);
                    progressBar.setImageDrawable(drawable3);
                    break;
                case 4:
                    Drawable drawable4= getResources().getDrawable(R.drawable.progress4);
                    progressBar.setImageDrawable(drawable4);
                    break;
                case 5:
                    Drawable drawable5= getResources().getDrawable(R.drawable.progress5);
                    progressBar.setImageDrawable(drawable5);
                    break;
                case 6:
                    Drawable drawable6= getResources().getDrawable(R.drawable.progress6);
                    progressBar.setImageDrawable(drawable6);
                    break;
                case 7:
                    Drawable drawable7= getResources().getDrawable(R.drawable.progress7);
                    progressBar.setImageDrawable(drawable7);
                    break;
                case 8:
                    Drawable drawable8= getResources().getDrawable(R.drawable.progress8);
                    progressBar.setImageDrawable(drawable8);
                    break;
                case 9:
                    Drawable drawable9= getResources().getDrawable(R.drawable.progress9);
                    progressBar.setImageDrawable(drawable9);
                    break;
                case 10:
                    Drawable drawable10= getResources().getDrawable(R.drawable.progress10);
                    progressBar.setImageDrawable(drawable10);
                    break;
            }




            btnAvancar.setText("QUESTÃO "+ question);



            linear.setVisibility(View.INVISIBLE);
            // set the text for new question, and new 4 alternative to answer on four buttons
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            mButtonChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            mButtonChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            mButtonChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber, 4));


            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
            question++;
        } else {
           // Toast.makeText(QuizActivity.this, "Venha ser o nosso aluno ! Você terá acesso a novas questões diárias e ainda terá 'recompensas'", Toast.LENGTH_LONG).show();


        }
    }

    // show current total score for the user
        public void onClick(View view) {
        //all logic for all answers buttons in one method

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


            answer.setBackgroundResource(R.drawable.botao_quiz);
            answer.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));


            linear.setVisibility(View.VISIBLE);


            if (mQuestionNumber < mQuestionLibrary.getLength()){
                switch (mQuestionNumber){

                    case 1:
                        resposta_descricao.setText(R.string.descricao_1);
                        break;
                    case 2:
                        resposta_descricao.setText(R.string.descricao_2);
                        break;
                    case 3:
                        resposta_descricao.setText(R.string.descricao_3);
                        break;



                }
            }





            //Toast.makeText(QuizActivity.this, "Acertou!", Toast.LENGTH_SHORT).show();
            mp1.start();
            //updateScore(mScore);
            //updateQuestion();
           // Toast.makeText(QuizActivity.this, "Acertou!", Toast.LENGTH_SHORT).show();



        } else {
            mp2.start();
            linear.setVisibility(View.VISIBLE);




            if (alternativa1 == mAnswer) {

                answer.setBackgroundResource(R.drawable.botao_quiz2);
                answer.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));
                mButtonChoice1.setBackgroundResource(R.drawable.botao_quiz);
                mButtonChoice1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));

            }
            else if (alternativa2 == mAnswer) {
                mButtonChoice2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                mButtonChoice2.setBackgroundResource(R.drawable.botao_quiz);
                answer.setBackgroundResource(R.drawable.botao_quiz2);
                answer.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));

            }
            else if (alternativa3 == mAnswer) {
                mButtonChoice3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                mButtonChoice3.setBackgroundResource(R.drawable.botao_quiz);
                answer.setBackgroundResource(R.drawable.botao_quiz2);
                answer.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));


            }
            else if ( alternativa4 == mAnswer) {
                mButtonChoice4.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));
                mButtonChoice4.setBackgroundResource(R.drawable.botao_quiz);
                answer.setBackgroundResource(R.drawable.botao_quiz2);
                answer.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));

            }
           // Toast.makeText(QuizActivity.this, "Errou!", Toast.LENGTH_SHORT).show();



            // show current total score for the user
            vibrar();

            mp2.start();
            // once user answer the question, we move on to the next one, if any
            //updateQuestion();
        }
    }
    @Override
    public void onBackPressed () {
        Intent intent = new Intent(QuizActivity.this, Inicio.class);
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