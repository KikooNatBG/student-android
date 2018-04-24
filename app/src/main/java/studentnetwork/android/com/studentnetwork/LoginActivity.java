package studentnetwork.android.com.studentnetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Administrateur on 23/04/2018.
 */

public class LoginActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void onClickLogin() {
        // GSON REQUEST
    }

    public void onClickRegisterInLogin(View view) {
        Intent i = new Intent(LoginActivity.this, NetworkActivity.class);
        startActivity(i);
    }
}
