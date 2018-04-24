package studentnetwork.android.com.studentnetwork.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.bo.User;

/**
 * Created by Administrateur on 24/04/2018.
 */

public class SharedPreferencesManager {
    private static SharedPreferencesManager sharedPreferencesManager;
    private SharedPreferences sharedPref;
    public SharedPreferencesManager(Context context){
        sharedPref = context.getSharedPreferences(context.getString(R.string.user_key), Context.MODE_PRIVATE);
    }
    public static SharedPreferencesManager getInstance(Context context){
        if(sharedPreferencesManager == null){
            sharedPreferencesManager = new SharedPreferencesManager(context);
        }
        return sharedPreferencesManager;
    }
    public User getUserPreferences(){
        User user = new User();
        user.setId(sharedPref.getInt(String.valueOf(R.string.user_id_key), 0));
        user.setFirstName(sharedPref.getString(String.valueOf(R.string.user_firstName_key), null));
        user.setLastName(sharedPref.getString(String.valueOf(R.string.user_lastName_key), null));
        user.setEmail(sharedPref.getString(String.valueOf(R.string.user_email_key), null));
        user.setPassword(sharedPref.getString(String.valueOf(R.string.user_password_key), null));
        user.setAboutMe(sharedPref.getString(String.valueOf(R.string.user_aboutMe_key), null));
        user.setRegisterDate(new Date(sharedPref.getLong(String.valueOf(R.string.user_registerDate_key), 0L)));
        user.setBirthDate(new Date(sharedPref.getLong(String.valueOf(R.string.user_birthDate_key), 0L)));
        user.setPictureUrl(sharedPref.getString(String.valueOf(R.string.user_pictureUrl_key), null));
        user.setSmallPictureUrl(sharedPref.getString(String.valueOf(R.string.user_smallPictureUrl_key), null));
        user.setLargePictureUrl(sharedPref.getString(String.valueOf(R.string.user_largePictureUrl_key), null));
        user.setUserType(sharedPref.getString(String.valueOf(R.string.user_userType_key), null));
        user.setGender(sharedPref.getString(String.valueOf(R.string.user_gender_key), null));
        return user;
    }
    public void setUserPreferences(User user){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(String.valueOf(R.string.user_id_key), user.getId());
        editor.putString(String.valueOf(R.string.user_firstName_key), user.getFirstName());
        editor.putString(String.valueOf(R.string.user_lastName_key), user.getLastName());
        editor.putString(String.valueOf(R.string.user_email_key), user.getEmail());
        editor.putString(String.valueOf(R.string.user_password_key), user.getPassword());
        editor.putString(String.valueOf(R.string.user_aboutMe_key), user.getAboutMe());
        editor.putLong(String.valueOf(R.string.user_registerDate_key), user.getRegisterDate().getTime());
        editor.putLong(String.valueOf(R.string.user_birthDate_key), user.getBirthDate().getTime());
        editor.putString(String.valueOf(R.string.user_pictureUrl_key), user.getPictureUrl());
        editor.putString(String.valueOf(R.string.user_smallPictureUrl_key), user.getSmallPictureUrl());
        editor.putString(String.valueOf(R.string.user_largePictureUrl_key), user.getLargePictureUrl());
        editor.putString(String.valueOf(R.string.user_userType_key), user.getUserType());
        editor.putString(String.valueOf(R.string.user_gender_key), user.getGender());
        editor.commit();
    }
}
