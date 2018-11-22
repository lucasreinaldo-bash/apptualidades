package vostore.apptualidade.Simulado;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Share;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.annotation.Target;
import java.net.URI;
import java.util.Date;

import github.nisrulz.screenshott.ScreenShott;
import vostore.apptualidade.BuildConfig;
import vostore.apptualidade.Fragments.Regras;
import vostore.apptualidade.Inicio;
import vostore.apptualidade.R;
import vostore.apptualidade.SitePrincipal;

import com.squareup.picasso.Picasso;



public class HighestScoreActivity extends AppCompatActivity {
    private ImageView voltar, emoticon;
    private Button refazer, irsite;
    private TextView frasepontuacao;
    private ImageView fb, insta, in;
    private int score;
    private Bitmap bitmap;
    private LinearLayout linear;
    private CallbackManager callbackManager;
    private ShareDialog sharedDialog;

    com.squareup.picasso.Target target = new com.squareup.picasso.Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            SharePhoto sharePhoto = new SharePhoto.Builder().setBitmap(bitmap).build();

            if (ShareDialog.canShow(SharePhotoContent.class)) {
                SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(sharePhoto).build();

                sharedDialog.show(content);
            }
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        emoticon = (ImageView) findViewById(R.id.emoticon);
        refazer = findViewById(R.id.btn_refazer);
        irsite = findViewById(R.id.btn_irsite);
        fb = findViewById(R.id.fb);
        insta = findViewById(R.id.insta);
        frasepontuacao = findViewById(R.id.frasepontuacao);
        linear = findViewById(R.id.linear_resultado);
        in = findViewById(R.id.in);


        callbackManager = CallbackManager.Factory.create();
        sharedDialog = new ShareDialog(this);


        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(HighestScoreActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(HighestScoreActivity.this, Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(HighestScoreActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createLinkedl();
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                takeScreenshot();

            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createInstagramIntent();

            }
        });



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
        txtScore.setText(score + " pontos!");


        if (score <= 30) {
            frasepontuacao.setText("Sua pontuação foi baixa.\nContinue estudando.");
            emoticon.setBackgroundResource(R.drawable.final_triste);
        } else if (score <= 70) {
            frasepontuacao.setText("Você está na média,\nmas pode melhorar!");
            emoticon.setBackgroundResource(R.drawable.final_meiotriste);
        } else if (score < 100) {
            frasepontuacao.setText("Uau! Será que você consegue\nchegar aos 100 pontos?");

            emoticon.setBackgroundResource(R.drawable.final_feliz);
        } else if (score == 100) {
            frasepontuacao.setText("Parabéns, você mandou bem!\nEstá atualizado sobre os principais\nassuntos. Continue acompanhando\n  fora de nada e continuar\naprendendo.");

            emoticon.setBackgroundResource(R.drawable.final_superfeliz);
        }

        // use Shared preferences to save the best score
        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        int highscore = mypref.getInt("highscore", 0);
        if (highscore >= score)
            txtHighScore.setText("Recorde: " + highscore + " questões corretas");
        else {
            txtHighScore.setText("Novo recorde: " + score + " acertos.");
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
    private void createInstagramIntent(){
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);
            Uri uri = Uri.fromFile(imageFile);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();



            // Create the new Intent using the 'Send' action.


            // Create the URI from the media

            // Add the URI to the Intent.
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.setPackage("com.instagram.android");
            shareIntent.setType("image/jpeg");
            startActivity(shareIntent);
            //openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
            }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(HighestScoreActivity.this, Inicio.class);
        startActivity(intent);
        finish();
    }






    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            Picasso.with(getBaseContext()).load(imageFile).into(target);
            //openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    private void createLinkedl(){
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);
            Uri uri = Uri.fromFile(imageFile);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();



            // Create the new Intent using the 'Send' action.


            // Create the URI from the media

            // Add the URI to the Intent.
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.setType("image/jpeg");
            startActivity(shareIntent);
            //openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }
}

