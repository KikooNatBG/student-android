package studentnetwork.android.com.studentnetwork.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.bll.UserRegisterService;
import studentnetwork.android.com.studentnetwork.bo.User;

public class RegisterActivity extends AppCompatActivity implements UserRegisterService.UserRegisterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onClickRegister(View view) {
        UserRegisterService service = new UserRegisterService(this);
        service.validate(this);
    }

    @Override
    public void onResult(User user) {
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
