package studentnetwork.android.com.studentnetwork.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by quentin for VolleyJSON on 18/04/2018.
 */
public class GsonRequestList<T> extends Request<T> {
    private static String BASE_URL = "http://10.147.200.10:8080/api/";
    private final Gson gson = new Gson();
    private final Type type;
    private final Map<String, String> headers;
    private final Listener<T> listener;
    private final int method;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param type Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequestList(String url,
                           Type type,
                           Map<String, String> headers,
                           Listener<T> listener,
                           ErrorListener errorListener,int method) {
        super(Method.GET, BASE_URL + url, errorListener);
        this.type = type;
        this.headers = headers;
        this.listener = listener;
        this.method=method;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response){
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            ArrayList<T> elements = new ArrayList<>();
            elements = gson.fromJson(json, type);
            return (Response<T>) Response.success(elements,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}
