package vostore.apptualidade;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Site extends AppCompatActivity {

    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb);

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF8F00")));

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.setWebViewClient(new MyAppWebViewClient());
        WebSettings webSettings = mWebView.getSettings ();
        webSettings.setJavaScriptEnabled( true );

        // String pdf = "https://www.infoenem.com.br/wp-content/uploads/2011/10/CAD_ENEM-2015_DIA-1_02_AMARELO.pdf";
        mWebView.loadUrl("http://engluizrodrigues.wixsite.com/baixinho" );
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Site.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
