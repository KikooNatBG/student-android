package studentnetwork.android.com.studentnetwork.bll;

import android.content.Context;
import android.support.v4.app.Fragment;
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

import studentnetwork.android.com.studentnetwork.bo.Content;
import studentnetwork.android.com.studentnetwork.bo.enums.ContentType;
import studentnetwork.android.com.studentnetwork.requests.GsonRequestList;

/**
 * Created by Administrateur on 26/04/2018.
 */

public class ContentService {
    public interface ContentListener {
        void onContentResult(ArrayList<Content> contents, String fragment);
        void onContentResultByType(ArrayList<Content> contents, String fragment);
    }
    private ContentListener listener;
    private String fragment;
    private static final String TAG = "ContentService => ";


    public ContentService(Context context) {
        listener = (ContentService.ContentListener) context;
    }

    public ContentService(Context context, String fragment) {
        listener = (ContentService.ContentListener) context;
        this.fragment = fragment;
    }

    public void getContents(Context context) {
        RequestQueue queueVolley = Volley.newRequestQueue(context);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + TokenService.TOKEN);
        Log.d(TAG, headers.toString());
        GsonRequestList<ArrayList<Content>> contents = new GsonRequestList<>(
                "contents", new TypeToken<ArrayList<Content>>() {
        }.getType(), headers,
                new Response.Listener<ArrayList<Content>>() {
                    @Override
                    public void onResponse(ArrayList<Content> response) {
                        Log.d(TAG, response != null ? response.toString() : "OK");
                        listener.onContentResult(response, fragment);
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
        queueVolley.add(contents);
    }

    public void getContentsByType(ContentType contentType, Context context) {
        RequestQueue queueVolley = Volley.newRequestQueue(context);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + TokenService.TOKEN);
        Log.d(TAG, headers.toString());
        GsonRequestList<ArrayList<Content>> contents = new GsonRequestList<>(
                "contents/"+contentType, new TypeToken<ArrayList<Content>>() {
        }.getType(), headers,
                new Response.Listener<ArrayList<Content>>() {
                    @Override
                    public void onResponse(ArrayList<Content> response) {
                        Log.d(TAG, response != null ? response.toString() : "OK");
                        listener.onContentResult(response, fragment);
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
        queueVolley.add(contents);
    }

}
