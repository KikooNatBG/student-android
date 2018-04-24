package studentnetwork.android.com.studentnetwork.bll;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.bo.CustomVolleyError;
import studentnetwork.android.com.studentnetwork.bo.User;
import studentnetwork.android.com.studentnetwork.requests.GsonRequest;

public class UserLoginService {
    public interface UserLoginListener {
        void onResult(User user);
    }

    private static final String MAIL_INVALID = "Email Incorrect";
    private static final String PASSWORD_INVALID = "Mot de Passe Incorrect";
    private static final String TAG = "UserLoginService =>";

    private UserLoginListener listener;
    private EditText txtMail;
    private EditText txtPassword;

    public UserLoginService(Activity activity) {
        txtMail = activity.findViewById(R.id.login_txt);
        txtPassword = activity.findViewById(R.id.password_txt);
        listener = (UserLoginListener) activity;
    }

    private void setError(VolleyError error) {
        Gson gson = new Gson();
        CustomVolleyError customError = gson.fromJson(error.getMessage(), CustomVolleyError.class);
        String errorKey = customError.getErrorKey();
        switch (errorKey) {
            case "emailexists":
                txtMail.setError(MAIL_INVALID);
                txtMail.requestFocus();
                break;
            default:
                Log.d(TAG, "errorKey inconnu");
        }
    }

    private boolean isOk() {
        boolean ok = true;
        if (Utils.isEmpty(txtMail)) {
            txtMail.setError(MAIL_INVALID);
            ok = false;
        }
        if (Utils.isEmpty(txtPassword)) {
            txtPassword.setError(PASSWORD_INVALID);
            ok = false;
        }
        return ok;
    }

    public void validate(Context context) {
        boolean ok = isOk();
        User user = new User();
        if (ok) {
            RequestQueue queueVolley = Volley.newRequestQueue(context);
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Authorization", "Bearer " + TokenService.TOKEN);
            Log.d(TAG, headers.toString());
            user.setEmail(Utils.inputToString(txtMail));
            user.setPassword(Utils.inputToString(txtPassword));
            GsonRequest<User> createUser = new GsonRequest<>(
                    "kiuser/register", User.class, headers, new Response.Listener<User>() {
                @Override
                public void onResponse(User response) {
                    Log.d(TAG, response != null ? response.toString() : "OK");
                    listener.onResult(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, error.getMessage());
                    setError(error);
                }
            }, Request.Method.POST, user
            );
            queueVolley.add(createUser);
        }
    }
}
