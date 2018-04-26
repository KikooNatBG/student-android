package studentnetwork.android.com.studentnetwork.bll;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import studentnetwork.android.com.studentnetwork.bo.User;
import studentnetwork.android.com.studentnetwork.data.SharedPreferencesManager;
import studentnetwork.android.com.studentnetwork.requests.GsonRequest;

public class UserService {

    public interface UserServiceListener {
        void onUserResult(User user);
    }

    private static final String TAG = "UserService =>";

    private UserServiceListener listener;

    public UserService(Activity activity) {
        listener = (UserServiceListener) activity;
    }

    public void update(Context context) {
        User user = SharedPreferencesManager.getInstance(context).getUser();
        if (user != null) {
            Log.d(TAG, "start => " + user.toString());
            RequestQueue queueVolley = Volley.newRequestQueue(context);
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Authorization", "Bearer " + TokenService.TOKEN);
            Log.d(TAG, headers.toString());
            GsonRequest<User> createUser = new GsonRequest<>(
                    "kiuser", User.class, headers, new Response.Listener<User>() {
                @Override
                public void onResponse(User response) {
                    Log.d(TAG, response != null ? response.toString() : "OK");
                    listener.onUserResult(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, error.getMessage() != null ? error.getMessage() : error.toString());
                }
            }, Request.Method.PUT, user
            );
            queueVolley.add(createUser);
        }
    }
}
