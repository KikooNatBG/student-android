package studentnetwork.android.com.studentnetwork.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.activity.tuto.TutoActivity;
import studentnetwork.android.com.studentnetwork.bll.UserLoginService;
import studentnetwork.android.com.studentnetwork.bo.User;
import studentnetwork.android.com.studentnetwork.data.SharedPreferencesManager;

public class LoginActivity extends Activity implements UserLoginService.UserLoginListener {
    private static final String TAG = "LoginActivity => ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onClickRegisterInLogin(View view) {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }

    public void onClickLogin(View view) {
        UserLoginService service = new UserLoginService(this);
        service.validate(this);
    }

    @Override
    public void onResult(User user) {
        Log.d(TAG, user.toString());
        SharedPreferencesManager.getInstance(this).setUser(user);
        Class clazz = user.getSchools().isEmpty() ?
                TutoActivity.class : NetworkActivity.class;

        Intent i = new Intent(LoginActivity.this, clazz);
        startActivity(i);
        finish();
    }
}
