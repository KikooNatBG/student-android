package studentnetwork.android.com.studentnetwork.bll;

import android.app.Activity;
import android.util.Patterns;
import android.widget.EditText;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.bo.User;

public class UserRegisterService {
    private static final java.lang.CharSequence NOM_EMPTY = "Le Nom est obligatoire";
    private static final java.lang.CharSequence PRENOM_EMPTY = "Le Prenom est obligatoire";
    private static final java.lang.CharSequence MAIL_EMPTY = "L'Email est obligatoire";
    private static final java.lang.CharSequence MAIL_EXIST = "Cet Email est déja associé à un compte";
    private static final java.lang.CharSequence MAIL_INVALID = "L'Email doit être valide";
    private static final java.lang.CharSequence PASSWORD_EMPTY = "Le Mot de Passe est obligatoire";
    private static final java.lang.CharSequence PASSWORD_MIN_CONSTRAINT = "Le Mot de Passe doit contenir au moins 6 caractères";
    private static final java.lang.CharSequence PASSWORD_SAME_CONSTRAINT = "Le Mot de Passe doit être identique";
    
    private EditText txtNom;
    private EditText txtPrenom;
    private EditText txtMail;
    private EditText txtPassword;
    private EditText txtPasswordConfirm;

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
        } else if (true /*UserService.getOneByEmail(inputToString(txtMail)) renvoie un user*/) {
            txtMail.setError(MAIL_EXIST);
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

    public User validate() {
        boolean ok = isOk();
        User user = new User();
        if (ok) {
            user.setLastName(inputToString(txtNom));
            user.setFirstName(inputToString(txtPrenom));
            user.setEmail(inputToString(txtMail));
            user.setPassword(inputToString(txtPassword));
            //UserService.create(user)
        }
        return user;
    }
}
