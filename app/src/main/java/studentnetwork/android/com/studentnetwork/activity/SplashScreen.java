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
import studentnetwork.android.com.studentnetwork.activity.tuto.TutoActivity;
import studentnetwork.android.com.studentnetwork.bll.TokenService;
import studentnetwork.android.com.studentnetwork.bo.User;
import studentnetwork.android.com.studentnetwork.data.SharedPreferencesManager;

public class SplashScreen extends Activity {
    private static final String TAG = "SplashScreen => ";
    private static int SPLASH_TIME = 3000;
    private User user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        user = SharedPreferencesManager.getInstance(this).getUser();
        RequestQueue queueVolley = Volley.newRequestQueue(this);
        queueVolley.add(TokenService.initToken());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean authOk = user != null;
                Log.d(TAG, authOk ? user.toString() : "no user");
                Class activityClass = LoginActivity.class;
                if (authOk) {
                    boolean firstTime = user.getSchools() != null &&
                            (user.getSchools().size() == 0);
                    activityClass = firstTime ? TutoActivity.class : NetworkActivity.class;
                }
                Intent i = new Intent(SplashScreen.this, activityClass);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME);
    }


}
