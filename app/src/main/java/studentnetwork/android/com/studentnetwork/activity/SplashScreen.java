package studentnetwork.android.com.studentnetwork.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.bll.TokenService;

public class SplashScreen extends Activity{
    private static int SPLASH_TIME = 3000;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RequestQueue queueVolley = Volley.newRequestQueue(this);
        queueVolley.add(TokenService.initToken());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME);
    }


}
