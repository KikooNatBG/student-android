package studentnetwork.android.com.studentnetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import studentnetwork.android.com.studentnetwork.bll.UserLoginService;
import studentnetwork.android.com.studentnetwork.bo.User;

public class LoginActivity extends Activity implements UserLoginService.UserLoginListener {
    private static final String TAG = "LoginActivity => ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onClickRegisterInLogin(View view) {
        Intent i = new Intent(LoginActivity.this, NetworkActivity.class);
        startActivity(i);
    }

    public void onClickLogin(View view) {
        UserLoginService service = new UserLoginService(this);
        service.validate(this);
    }

    @Override
    public void onResult(User user) {
        Log.d(TAG, user.toString());
    }
}
