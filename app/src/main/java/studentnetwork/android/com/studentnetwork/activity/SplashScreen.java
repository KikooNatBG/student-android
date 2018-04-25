package studentnetwork.android.com.studentnetwork.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.bll.TokenService;
import studentnetwork.android.com.studentnetwork.bo.User;
import studentnetwork.android.com.studentnetwork.data.SharedPreferencesManager;

public class SplashScreen extends Activity{
    private static final String TAG = "SplashScreen => ";
    private static int SPLASH_TIME = 3000;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final User user = SharedPreferencesManager.getInstance(this).getUser();
        RequestQueue queueVolley = Volley.newRequestQueue(this);
        queueVolley.add(TokenService.initToken());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, user != null ? user.toString() : "no user");
                Intent i = new Intent(SplashScreen.this, user != null ? NetworkActivity.class : LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME);
    }


}
