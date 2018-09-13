package vostore.apptualidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Login extends AppCompatActivity {

    ImageView top;
    Animation fromlogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        top = findViewById(R.id.logologin);
        fromlogo = AnimationUtils.loadAnimation(this, R.anim.fromlogo);
        top.setAnimation(fromlogo);
    }
}
