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
import android.widget.Toast;

import vostore.apptualidade.Simulado.QuizActivity;

public class Site extends AppCompatActivity {

    private ImageButton voltar;

    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);

       // android.support.v7.app.ActionBar bar = getSupportActionBar();
       // bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF8F00")));
        Bundle extras = getIntent().getExtras();
        voltar = findViewById(R.id.voltarapp);
        //Recebendo informação de outra Activity

        Intent intent = getIntent();
        int codigo = intent.getIntExtra("codigo", 0);

        mWebView = (WebView) findViewById(R.id.site);






        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("http://apptualidades.com.br/fake-news/");
        mWebView.setWebViewClient(new HelloWebViewClient());


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar.setBackgroundResource(R.drawable.btnvoltarapp2);
                Intent intent = new Intent(Site.this, QuizActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Site.this, QuizActivity.class);
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
