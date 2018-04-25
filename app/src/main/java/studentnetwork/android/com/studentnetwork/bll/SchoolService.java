package studentnetwork.android.com.studentnetwork.bll;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import studentnetwork.android.com.studentnetwork.bo.School;
import studentnetwork.android.com.studentnetwork.requests.GsonRequestList;


public class SchoolService {
    public interface SchoolListener {
        void onResultOne(School school);

        void onFragmentResultList(ArrayList<School> schools, String fragment);
    }

    private SchoolListener listener;
    private String fragment;
    private static final String TAG = "SchoolService => ";

    public SchoolService(Context context) {
        listener = (SchoolListener) context;
    }

    public SchoolService(Context context, String fragment) {
        listener = (SchoolListener) context;
        this.fragment = fragment;
    }

    public void getList(Context context) {
        RequestQueue queueVolley = Volley.newRequestQueue(context);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + TokenService.TOKEN);
        Log.d(TAG, headers.toString());
        GsonRequestList<ArrayList<School>> createUser = new GsonRequestList<>(
                "schools", new TypeToken<ArrayList<School>>() {
        }.getType(), headers,
                new Response.Listener<ArrayList<School>>() {
                    @Override
                    public void onResponse(ArrayList<School> response) {
                        Log.d(TAG, response != null ? response.toString() : "OK");
                        listener.onFragmentResultList(response, fragment);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.getMessage() != null
                                ? error.getMessage() : error.toString());
                    }
                }, Request.Method.GET
        );
        queueVolley.add(createUser);
    }
}
