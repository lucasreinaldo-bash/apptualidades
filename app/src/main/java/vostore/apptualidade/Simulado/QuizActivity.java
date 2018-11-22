package vostore.apptualidade.Simulado;


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
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import vostore.apptualidade.Inicio;
import vostore.apptualidade.R;
import vostore.apptualidade.Site;
import android.view.MenuItem;


public class QuizActivity extends AppCompatActivity {


    private QuestionBank mQuestionLibrary = new QuestionBank();
    public TextView mScoreView;   // view for current total score
    private TextView mQuestionView,mQuestionView2;  //current question to answer
    private TextView resposta_certa,resposta_descricao;
    private Button HG,btnAvancar;
    private ImageView questao,progressBar;
    private ImageView resp_certa;
    private Button mButtonChoice1; // multiple choice 1 for mQuestionView
    private Button mButtonChoice2; // multiple choice 2 for mQuestionView
    private Button mButtonChoice3; // multiple choice 3 for mQuestionView
    private Button mButtonChoice4; // multiple choice 4 for mQuestionView
    private Button questao6_2,leia, questao6_4; // multiple choice 4 for mQuestionView
    private ScrollView scroolquestion;
    private String mAnswer;  // correct answer for question in mQuestionView
    public int mScore = 0;
    private int mQuestionNumber = 0; // current question number
    private int quantidade =1;
    private int question = 2;
    private int cod = 0;
    public LinearLayout linear,linear2,linear3;
    private MediaPlayer mp1, mp2;
    private LinearLayout linear6;
     private static final String TAG = "FACELOG";

      int STATE_SCORE ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

       /* android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF8F00")));
*/
     //Fazendo Cast

        scroolquestion = findViewById(R.id.scroolviewquestion);
        progressBar = findViewById(R.id.progress);
        questao = findViewById(R.id.questao_id);
        linear2 = findViewById(R.id.linear_id2);
        linear3 = findViewById(R.id.linear_id3);
        linear = findViewById(R.id.linear_id);
        linear6 = findViewById(R.id.linear_questao6);
        mScoreView = findViewById(R.id.score);
         mQuestionView = findViewById(R.id.question);
         mQuestionView2 = findViewById(R.id.questionparte2);
         resp_certa = findViewById(R.id.resp_certa);

         questao6_2 = findViewById(R.id.questao6_2);
         questao6_4 = findViewById(R.id.questao6_4);

        mButtonChoice1 = findViewById(R.id.choice1);
        mButtonChoice2 = findViewById(R.id.choice2);
        mButtonChoice3 = findViewById(R.id.choice3);
        mButtonChoice4 = findViewById(R.id.choice4);

        btnAvancar = findViewById(R.id.btn_avancar);
        leia = findViewById(R.id.btn_leia);
        //resposta_descricao = findViewById(R.id.descricao_resposta);






        //Instanciando sons para serem reproduzidos de acordo com resposta do usuário
        //mp1 = MediaPlayer.create(QuizActivity.this, R.raw.correct);
        //mp2 = MediaPlayer.create(QuizActivity.this, R.raw.wrong);

        //Método que carrega as primeiras questões do Simulado
        updateQuestion();
        //Salvar pontuação do usuário
        updateScore(mScore);


        leia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mScore = mScore + 5;

                switch (mQuestionNumber) {

                    case 0:

                        break;
                    case 1:

                        String site1="http://apptualidades.com.br/agrotoxicos/";
                        Intent intent = new Intent(QuizActivity.this, Site.class);
                        intent.putExtra("site","http://apptualidades.com.br/agrotoxicos/");
                        startActivity(intent);





                        //overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                        break;

                    case 2:

                        Intent intent2 = new Intent(QuizActivity.this, Site.class);
                        intent2.putExtra("site"," http://apptualidades.com.br/envelhecimento-populacional/");
                        startActivity(intent2);



                        //overridePendingTransition(R.anim.bottom_in, R.anim.top_out);


                        break;
                    case 3:

                        Intent intent3 = new Intent(QuizActivity.this, Site.class);
                        intent3.putExtra("site","http://apptualidades.com.br/crise-venezuelana/");
                        startActivity(intent3);

                        break;

                    case 4:

                        Intent intent4 = new Intent(QuizActivity.this, Site.class);
                        intent4.putExtra("site","http://apptualidades.com.br/martin-luther-king/");
                        startActivity(intent4);

                        break;
                    case 5:

                        Intent intent5 = new Intent(QuizActivity.this, Site.class);
                        intent5.putExtra("site","http://apptualidades.com.br/febre-amarela/");
                        startActivity(intent5);

                        break;

                    case 6:
                              Intent intent6 = new Intent(QuizActivity.this, Site.class);
                        intent6.putExtra("site","http://apptualidades.com.br/greve-dos-caminhoneiros/");
                        startActivity(intent6);

                        break;
                    case 7:
                            Intent intent7 = new Intent(QuizActivity.this, Site.class);
                        intent7.putExtra("site","http://apptualidades.com.br/crise-no-sistema-penitenciario/");
                        startActivity(intent7);

                        break;

                    case 8:
                            Intent intent8 = new Intent(QuizActivity.this, Site.class);
                        intent8.putExtra("site","http://apptualidades.com.br/relacao-nuclear/");
                        startActivity(intent8);

                        break;
                    case 9:
                            Intent intent9 = new Intent(QuizActivity.this, Site.class);
                        intent9.putExtra("site","http://apptualidades.com.br/crise-venezuelana/");
                        startActivity(intent9);

                        break;

                    case 10:
                           Intent intent10 = new Intent(QuizActivity.this, Site.class);
                        intent10.putExtra("site","http://apptualidades.com.br/malala-yousafazai-no-brasil/");
                        startActivity(intent10);

                        break;
                }



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

    private void updateScore(int mScore) {
    }

    private void updateQuestion() {
        // check if we are not outside array bounds for questions
        if (mQuestionNumber < mQuestionLibrary.getLength()) {

            scroolquestion.pageScroll(View.FOCUS_UP);
            switch (mQuestionNumber){
                case 0:

                    mQuestionView.setVisibility(View.VISIBLE);
                    mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
                    mQuestionView2.setVisibility(View.GONE);
                    resp_certa.setBackgroundResource(R.drawable.resp_1);

                    break;

                case 1:
                    //mQuestionView.setVisibility(View.VISIBLE);

                    resp_certa.setBackgroundResource(R.drawable.resp_2);
                    mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
                    Drawable drawable1= getResources().getDrawable(R.drawable.progress2);
                    //Drawable questao2= getResources().getDrawable(R.drawable.question2);
                    //questao2.setBackground(questao2);
                    progressBar.setImageDrawable(drawable1);
                    break;
                case 2:
                   // resp_certa.getLayoutParams().height = 260;
                    resp_certa.setBackgroundResource(R.drawable.resp_3);
                    mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
                    questao.setVisibility(View.VISIBLE);
                    Drawable drawable2= getResources().getDrawable(R.drawable.progress3);
                    progressBar.setImageDrawable(drawable2);
                    Drawable questao3= getResources().getDrawable(R.drawable.img_questao3);
                    questao.setBackground(questao3);
                    mQuestionView2.setVisibility(View.VISIBLE);
                    mQuestionView2.setText("O principal motivo para o êxodo venezuelano é a:");

                    break;
                case 3:

                  resp_certa.setBackgroundResource(R.drawable.respostacerta4);
                    mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
                    Drawable drawable3= getResources().getDrawable(R.drawable.progress4);
                    progressBar.setImageDrawable(drawable3);
                    //Drawable questao4= getResources().getDrawable(R.drawable.questao4);
                    questao.setVisibility(View.GONE);
                    mQuestionView2.setVisibility(View.GONE);
                    break;
                case 4:
                    resp_certa.getLayoutParams().height = 130;
                    resp_certa.setBackgroundResource(R.drawable.respostacerta556);
                    mQuestionView.setVisibility(View.GONE);
                    questao.setVisibility(View.VISIBLE);
                    Drawable questao4= getResources().getDrawable(R.drawable.img_questao5);
                    questao.setBackground(questao4);
                    mQuestionView2.setVisibility(View.VISIBLE);
                    mQuestionView2.setText("É correto afirmar que o aumento no número de casos deve-se principalmente:");
                    Drawable drawable4= getResources().getDrawable(R.drawable.progress5);
                    progressBar.setImageDrawable(drawable4);
                    break;
                case 5:
                    resp_certa.getLayoutParams().height = 300;
                    resp_certa.setBackgroundResource(R.drawable.resp_6);
                    mQuestionView.setVisibility(View.VISIBLE);
                    questao.setVisibility(View.GONE);
                    mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
                    Drawable drawable5= getResources().getDrawable(R.drawable.progress6);
                    progressBar.setImageDrawable(drawable5);
                    linear6.setVisibility(View.VISIBLE);
                    mQuestionView2.setVisibility(View.VISIBLE);
                    mQuestionView2.setText("Quais alternativas são verdadeiras?");
                    break;
                case 6:
                    resp_certa.setBackgroundResource(R.drawable.resp_7);
                    questao.setVisibility(View.VISIBLE);
                    linear6.setVisibility(View.GONE);
                    Drawable drawable6= getResources().getDrawable(R.drawable.progress7);
                    progressBar.setImageDrawable(drawable6);
                    mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
                    mQuestionView2.setText("O Brasil tem a 3ª maior população carcerária do mundo.A alta taxa de prisões pode ser explicada pela");
                    Drawable questao6= getResources().getDrawable(R.drawable.img_questao7);
                    questao.setBackground(questao6);
                    break;
                case 7:
                    resp_certa.setBackgroundResource(R.drawable.resp_8);
                    mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
                    questao.getLayoutParams().height = 1100;
                    Drawable questao7= getResources().getDrawable(R.drawable.questao8);
                    questao.setBackground(questao7);
                    Drawable drawable7= getResources().getDrawable(R.drawable.progress8);
                    progressBar.setImageDrawable(drawable7);
                    mQuestionView2.setText("Com relação ao programa nuclear norte-coreano.Está correto afirmar que:");
                    break;
                case 8:
                    resp_certa.setBackgroundResource(R.drawable.resp_9);
                    mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
                    mQuestionView2.setText("Uma série de fatores culminou na crise humanitária da Venezuela, mas o que desencadeou o problema foi a:");
                    questao.getLayoutParams().height = 240;
                    Drawable questao8= getResources().getDrawable(R.drawable.img_questao9);
                    questao.setBackground(questao8);
                    Drawable drawable8= getResources().getDrawable(R.drawable.progress9);
                    progressBar.setImageDrawable(drawable8);
                    break;
                case 9:
                    resp_certa.setBackgroundResource(R.drawable.resp_10);
                    Drawable questao9= getResources().getDrawable(R.drawable.img_questao10);
                    questao.setBackground(questao9);
                    mQuestionView.setVisibility(View.GONE);
                    mQuestionView2.setText("Após ser baleada aos 15 anos pelo talibã, no Afeganistão, enquanto lutava pelo direito de estudar, a ativista Malala Yousafazai começou a percorrer o mundo à favor da luta pelo direito à educação. Em 2018 ela fez uma visita ao Brasil, onde é correto afirmar que:");
                    Drawable drawable9= getResources().getDrawable(R.drawable.progress10);
                    progressBar.setImageDrawable(drawable9);
                    break;


            }

            questao6_2.setBackgroundResource(R.drawable.btn_questao6);
            questao6_4.setBackgroundResource(R.drawable.btn_questao6);
            questao6_2.setTextColor(getResources().getColor(R.color.nomeBotoes));
            questao6_4.setTextColor(getResources().getColor(R.color.nomeBotoes));



            if (question <= 10)
                btnAvancar.setText("QUESTÃO "+ question);
            else{
                btnAvancar.setText("RESULTADO");
            }


            linear.setVisibility(View.INVISIBLE);
            // set the text for new question, and new 4 alternative to answer on four buttons

            mButtonChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            mButtonChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            mButtonChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            mButtonChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber, 4));


            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
            question++;
        } else {
           // Toast.makeText(QuizActivity.this, "Venha ser o nosso aluno ! Você terá acesso a novas questões diárias e ainda terá 'recompensas'", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(QuizActivity.this, HighestScoreActivity.class);
            intent.putExtra("score",mScore);
            startActivity(intent);
            finish();

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
            mScore = mScore + 5;

            answer.setBackgroundResource(R.drawable.botao_quiz);
            answer.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));
            questao6_2.setBackgroundResource(R.drawable.botao_quiz);
            questao6_4.setBackgroundResource(R.drawable.botao_quiz);
            questao6_2.setTextColor(getResources().getColor(R.color.colorWhite));
            questao6_4.setTextColor(getResources().getColor(R.color.colorWhite));


            linear.setVisibility(View.VISIBLE);


            switch (mQuestionNumber) {

                case 0:

                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
            }











            //Toast.makeText(QuizActivity.this, "Acertou!", Toast.LENGTH_SHORT).show();
        //    mp1.start();
            //updateScore(mScore);
            //updateQuestion();
           // Toast.makeText(QuizActivity.this, "Acertou!", Toast.LENGTH_SHORT).show();



        } else {

            questao6_2.setBackgroundResource(R.drawable.botao_quiz);
            questao6_4.setBackgroundResource(R.drawable.botao_quiz);
            questao6_2.setTextColor(getResources().getColor(R.color.colorWhite));
            questao6_4.setTextColor(getResources().getColor(R.color.colorWhite));
            //mp2.start();
            linear.setVisibility(View.VISIBLE);
            switch (mQuestionNumber) {

                case 0:

                    break;
                case 1:


                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
            }



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

           // mp2.start();
            // once user answer the question, we move on to the next one, if any
            //updateQuestion();
        }
    }
    @Override
    public void onBackPressed () {
        Intent intent = new Intent(QuizActivity.this, Inicio.class);
        startActivity(intent);


    }

    //Função para o celular vibrar
    private void vibrar () {
        Vibrator rr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long milisegundos =1000;
        rr.vibrate(milisegundos);

    }



    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        savedInstanceState.putInt("questao",mQuestionNumber);

        // etc.
         super.onSaveInstanceState(savedInstanceState);
    }
    public void onRestoreInstanceState(Bundle savedInstanceState) {
    // Always call the superclass so it can restore the view hierarchy
    super.onRestoreInstanceState(savedInstanceState);

    // Restore state members from saved instance
    mQuestionNumber = savedInstanceState.getInt("questao");
}
}