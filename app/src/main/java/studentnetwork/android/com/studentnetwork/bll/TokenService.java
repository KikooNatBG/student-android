package studentnetwork.android.com.studentnetwork.bll;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import studentnetwork.android.com.studentnetwork.bo.Token;
import studentnetwork.android.com.studentnetwork.requests.GsonRequest;

public class TokenService {
    private static final String TAG = "TokenService => ";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    public static String TOKEN;

    private static class Auth {
        private String username;
        private String password;
        private boolean rememberMe;

        private Auth(String username, String password, boolean rememberMe){
            this.username = username;
            this.password = password;
            this.rememberMe = rememberMe;
        }
    }

    public static GsonRequest<Token> initToken(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return new GsonRequest<>(
                "authenticate", Token.class, headers, new Response.Listener<Token>() {
            @Override
            public void onResponse(Token response) {
                Log.d(TAG, response.getId_token());
                TOKEN = response.getId_token();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
            }
        }, Request.Method.POST, new Auth(TokenService.USERNAME,TokenService.PASSWORD,true)
        );
    }
}
