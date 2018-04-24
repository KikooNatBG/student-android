package studentnetwork.android.com.studentnetwork.bll;

import android.widget.EditText;

public class Utils {
    public static String inputToString(EditText txt) {
        return txt.getText().toString();
    }

    public static boolean isEmpty(EditText txt) {
        return inputToString(txt).isEmpty();
    }
}
