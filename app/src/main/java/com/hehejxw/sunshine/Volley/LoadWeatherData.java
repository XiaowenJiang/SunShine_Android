package com.hehejxw.sunshine.Volley;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hehejxw.sunshine.Application.Weather;
import com.hehejxw.sunshine.Events.WeatherEvent;
import com.hehejxw.sunshine.R;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by XiaowenJiang on 4/8/16.
 */
public class LoadWeatherData {
    private static final String TAG = LoadWeatherData.class.getSimpleName();
    private String postcode;
    private String url;
    private Context mContext;
    private String APIKEY;
    private WeatherData mWeatherData;
    public LoadWeatherData(final Context context,String postcode)
    {
        this.mContext = context;
        this.postcode = postcode;
        APIKEY = context.getString(R.string.WEATHERAPIKEY);
        String mode = context.getString(R.string.format);
        String unit = context.getString(R.string.units);
        url = "http://api.openweathermap.org/data/2.5/forecast/daily?zip="+postcode+",us"+"&mode="+mode+"&units="+unit+"&cnt="+7+"&APPID="+APIKEY;
        CustomJsonRequest jsonRequest = new CustomJsonRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d(TAG,response.toString());
                    mWeatherData = new WeatherData(response,context);
                    EventBus.getDefault().post(new WeatherEvent(mWeatherData.getDailyDetails()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               error.printStackTrace();
            }
        });
        jsonRequest.setPriority(Request.Priority.HIGH);
        //add to request QUEUE
        Weather.getmInstance().add(jsonRequest);
    }

    public WeatherData getWeatherData() {
        return mWeatherData;
    }
}
