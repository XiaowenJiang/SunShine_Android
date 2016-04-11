package com.hehejxw.sunshine.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by XiaowenJiang on 4/8/16.
 */
public class CustomJsonRequest extends JsonObjectRequest {
    private Priority mPriority;

    public CustomJsonRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }


    public void setPriority(Priority priority) {
        mPriority = priority;
    }

    @Override
    public Priority getPriority() {
        return (mPriority==null)?Priority.NORMAL:mPriority;
    }
}
