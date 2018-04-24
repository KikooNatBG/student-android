package studentnetwork.android.com.studentnetwork.bll;

import android.app.Activity;
import android.widget.EditText;

import studentnetwork.android.com.studentnetwork.R;

/**
 * Created by Administrateur on 23/04/2018.
 */

public class UserLoginService {
    private EditText emailText;
    private EditText passwordText;

    public UserLoginService(Activity activity) {
        emailText = activity.findViewById(R.id.login_txt);
        passwordText = activity.findViewById(R.id.password_txt);
    }
}
