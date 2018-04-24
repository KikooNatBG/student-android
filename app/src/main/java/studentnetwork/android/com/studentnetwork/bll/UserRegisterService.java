package studentnetwork.android.com.studentnetwork.bll;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import studentnetwork.android.com.studentnetwork.LoginActivity;
import studentnetwork.android.com.studentnetwork.NetworkActivity;
import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.SplashScreen;
import studentnetwork.android.com.studentnetwork.bo.CustomVolleyError;
import studentnetwork.android.com.studentnetwork.bo.User;
import studentnetwork.android.com.studentnetwork.requests.GsonRequest;

public class UserRegisterService {
    private static final java.lang.CharSequence NOM_EMPTY = "Le Nom est obligatoire";
    private static final java.lang.CharSequence PRENOM_EMPTY = "Le Prénom est obligatoire";
    private static final java.lang.CharSequence MAIL_EMPTY = "L'Email est obligatoire";
    private static final java.lang.CharSequence MAIL_EXIST = "Cet Email est déja associé à un compte";
    private static final java.lang.CharSequence MAIL_INVALID = "L'Email doit être valide";
    private static final java.lang.CharSequence PASSWORD_EMPTY = "Le Mot de Passe est obligatoire";
    private static final java.lang.CharSequence PASSWORD_MIN_CONSTRAINT = "Le Mot de Passe doit contenir au moins 6 caractères";
    private static final java.lang.CharSequence PASSWORD_SAME_CONSTRAINT = "Le Mot de Passe doit être identique";
    private static final String TAG = "UserRegisterService =>";

    private EditText txtNom;
    private EditText txtPrenom;
    private EditText txtMail;
    private EditText txtPassword;
    private EditText txtPasswordConfirm;
    private User user;

    public UserRegisterService(Activity activity) {
        txtNom = activity.findViewById(R.id.register_txt_nom);
        txtPrenom = activity.findViewById(R.id.register_txt_prenom);
        txtMail = activity.findViewById(R.id.register_txt_mail);
        txtPassword = activity.findViewById(R.id.register_txt_password);
        txtPasswordConfirm = activity.findViewById(R.id.register_txt_password_confirm);
    }

    private String inputToString(EditText txt) {
        return txt.getText().toString();
    }

    private boolean isEmpty(EditText txt) {
        return inputToString(txt).isEmpty();
    }

    private void setError(VolleyError error) {
        Gson gson = new Gson();
        CustomVolleyError customError = gson.fromJson(error.getMessage(), CustomVolleyError.class);
        String errorKey = customError.getErrorKey();
        switch (errorKey) {
            case "emailexists":
                txtMail.setError(MAIL_EXIST);
                txtMail.requestFocus();
                break;
            default:
                Log.d(TAG, "errorKey inconnu");
        }
    }

    private boolean isOk() {
        boolean ok = true;
        if (isEmpty(txtNom)) {
            txtNom.setError(NOM_EMPTY);
            ok = false;
        }
        if (isEmpty(txtPrenom)) {
            txtPrenom.setError(PRENOM_EMPTY);
            ok = false;
        }
        if (isEmpty(txtMail)) {
            txtMail.setError(MAIL_EMPTY);
            ok = false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputToString(txtMail)).matches()) {
            txtMail.setError(MAIL_INVALID);
            ok = false;
        }
        if (isEmpty(txtPassword)) {
            txtPassword.setError(PASSWORD_EMPTY);
            ok = false;
        } else if (inputToString(txtPassword).length() < 6) {
            txtPassword.setError(PASSWORD_MIN_CONSTRAINT);
            ok = false;
        } else if (!inputToString(txtPassword).equals(inputToString(txtPasswordConfirm))) {
            txtPasswordConfirm.setError(PASSWORD_SAME_CONSTRAINT);
            ok = false;
        }
        return ok;
    }

    public void validate(Context context, Intent i) {
        boolean ok = isOk();
        user = new User();
        if (ok) {
            RequestQueue queueVolley = Volley.newRequestQueue(context);
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Authorization", "Bearer " + TokenService.TOKEN);
            Log.d(TAG, headers.toString());
            user.setLastName(inputToString(txtNom));
            user.setFirstName(inputToString(txtPrenom));
            user.setEmail(inputToString(txtMail));
            user.setPassword(inputToString(txtPassword));
            user.setUserType("STUDENT");
            user.setGender("MALE");
            GsonRequest<User> createUser = new GsonRequest<>(
                    "kiuser/register", User.class, headers, new Response.Listener<User>() {
                @Override
                public void onResponse(User response) {
                    Log.d(TAG, response != null ? response.toString() : "OK");
                    user=response;
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
