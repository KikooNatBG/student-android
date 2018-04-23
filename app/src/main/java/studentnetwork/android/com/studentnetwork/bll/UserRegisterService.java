package studentnetwork.android.com.studentnetwork.bll;


import android.view.View;
import android.widget.EditText;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.bo.User;

import static android.R.attr.password;

/*
First Name
Last Name
Email
Password
Register Date
User Type
Gender
 */
public class UserRegisterService {
    private static final java.lang.CharSequence NOM_EMPTY = "Le Nom est obligatoire.";
    private static final java.lang.CharSequence PRENOM_EMPTY = "Le Prenom est obligatoire?";
    private static final java.lang.CharSequence MAIL_EMPTY = "L'Email est obligatoire.";
    private static final java.lang.CharSequence PASSWORD_EMPTY = "Le Mot de Passe est obligatoire.";
    private static final java.lang.CharSequence PASSWORD_MIN_CONSTRAINT = "Le Mot de Passe doit contenir au moins 6 caractères";
    private static final java.lang.CharSequence PASSWORD_SAME_CONSTRAINT = "Le Mot de Passe doit être identique.";
    private static final java.lang.CharSequence MAIL_EXIST = "Cet Email est déja associé à un compte.";
    private EditText txtNom;
    private EditText txtPrenom;
    private EditText txtMail;
    private EditText txtPassword;
    private EditText txtPasswordConfirm;

    public UserRegisterService(View view) {
        txtNom = (EditText) view.findViewById(R.id.register_txt_nom);
        txtPrenom = (EditText) view.findViewById(R.id.register_txt_prenom);
        txtMail = (EditText) view.findViewById(R.id.register_txt_mail);
        txtPassword = (EditText) view.findViewById(R.id.register_txt_password);
        txtPasswordConfirm = (EditText) view.findViewById(R.id.register_txt_password_confirm);
    }

    private String inputToString(EditText txt) {
        return txt.getText().toString();
    }

    private boolean isEmpty(EditText txt) {
        return inputToString(txt).isEmpty();
    }

    private boolean isOk() {
        boolean ok = true;
        // Nom vide
        if (isEmpty(txtNom)) {
            txtNom.setError(NOM_EMPTY);
            ok = false;
        }
        // Prenom Vide
        if (isEmpty(txtPrenom)) {
            txtPrenom.setError(PRENOM_EMPTY);
            ok = false;
        }
        // Mail Vide
        if (isEmpty(txtMail)) {
            txtMail.setError(MAIL_EMPTY);
            ok = false;
            //UserService.getOneByEmail(inputToString(txtMail)) renvoie un user
        } else if (true) {
            txtMail.setError(MAIL_EXIST);
            ok = false;
        }
        // Password Vide
        if (isEmpty(txtPassword)) {
            txtPassword.setError(PASSWORD_EMPTY);
            ok = false;
            // Password > 6 caractère
        } else if (inputToString(txtPassword).length() < 6) {
            txtPassword.setError(PASSWORD_MIN_CONSTRAINT);
            ok = false;
            // Password Identique
        } else if (inputToString(txtPassword).equals(inputToString(txtPasswordConfirm))) {
            txtPasswordConfirm.setError(PASSWORD_SAME_CONSTRAINT);
            ok = false;
        }
        return ok;
    }

    public User validate() {
        boolean ok = isOk();
        User user=new User();
        if(ok){
            user.setLastName(inputToString(txtNom));
            user.setFirstName(inputToString(txtPrenom));
            user.setEmail(inputToString(txtMail));
            user.setPassword(inputToString(txtPassword));
            //UserService.create(user)
        }
        return user;
    }
}
