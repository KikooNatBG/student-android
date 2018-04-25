package studentnetwork.android.com.studentnetwork.activity.tuto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.bo.User;
import studentnetwork.android.com.studentnetwork.data.SharedPreferencesManager;

public class TutoExplainActivity extends AppCompatActivity {

    private static final String TAG = "TutoExplainActivity => ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuto_explain);
        User user = SharedPreferencesManager.getInstance(this).getUser();
        Log.d(TAG,user.toString());
    }
}
