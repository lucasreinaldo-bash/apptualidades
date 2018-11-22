package vostore.apptualidade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import vostore.apptualidade.Fragments.SobreFragment;
import vostore.apptualidade.Simulado.QuizActivity;


public class facebook extends AppCompatActivity {

    private WebView mWebView;
    private ImageButton voltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb);

        Bundle extras = getIntent().getExtras();
        voltar = findViewById(R.id.voltarapp);
        //Recebendo informação de outra Activity

        Intent intent = getIntent();
        int codigo = intent.getIntExtra("codigo", 0);

        mWebView = (WebView) findViewById(R.id.site);






        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("https://www.facebook.com/apptualidades/");
        mWebView.setWebViewClient(new facebook.HelloWebViewClient());


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar.setBackgroundResource(R.drawable.btnvoltarapp2);
                Intent intent = new Intent(facebook.this, Inicio.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(facebook.this, SobreFragment.class);
        startActivity(intent);
        finish();

    }

    private class HelloWebViewClient extends WebViewClient {


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url)
        {
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            //progressBar.setVisibility(view.GONE);
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    { //if back key is pressed
        if((keyCode == KeyEvent.KEYCODE_BACK)&& mWebView.canGoBack())
        {
            mWebView.goBack();
            return true;

        }

        return super.onKeyDown(keyCode, event);

    }
}
