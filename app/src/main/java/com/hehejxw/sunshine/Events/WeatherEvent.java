package com.hehejxw.sunshine.Events;

import com.hehejxw.sunshine.Volley.WeatherData;

import java.util.ArrayList;

/**
 * Created by XiaowenJiang on 4/11/16.
 */
public class WeatherEvent {
    public ArrayList<WeatherData.DailyDetail> mDailyDetails;
    public WeatherEvent(ArrayList<WeatherData.DailyDetail> details)
    {
        this.mDailyDetails = details;
    }
}
