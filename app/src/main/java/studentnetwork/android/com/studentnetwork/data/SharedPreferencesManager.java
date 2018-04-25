package studentnetwork.android.com.studentnetwork.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Date;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.bo.User;

public class SharedPreferencesManager {
    private static SharedPreferencesManager sharedPreferencesManager;
    private SharedPreferences sharedPref;

    private SharedPreferencesManager(Context context){
        sharedPref = context.getSharedPreferences(context.getString(R.string.user_key), Context.MODE_PRIVATE);
    }

    public static SharedPreferencesManager getInstance(Context context){
        if(sharedPreferencesManager == null){
            sharedPreferencesManager = new SharedPreferencesManager(context);
        }
        return sharedPreferencesManager;
    }

    public User getUser(){
        Gson gson = new Gson();
        String json = sharedPref.getString("User", null);
        User user = gson.fromJson(json, User.class);
        return user;
    }

    public void setUser(User user){
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("User", json);
        editor.commit();
    }
}
