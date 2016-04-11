package com.hehejxw.sunshine.Volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;


import com.hehejxw.sunshine.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by XiaowenJiang on 4/9/16.
 */
public class WeatherData {
    Context context;
    Location loc;
    String country;
    String name;
    String[] weekdays = {"Mon","Tues","Wed","Thurs","Fri","Sat","Sun"};

    ArrayList<DailyDetail> mDailyDetails = new ArrayList<>();

    public class DailyDetail{
       double min,max;
       double pressure;
       int humidity;
        String date;
       String weather_main;
        String weather_des;
        String icon;



        public String getDate() {
            return date;
        }

        public double getMax() {
            return max;
        }

        public double getMin() {
            return min;
        }

        public double getPressure() {
            return pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public String getIcon() {
            return icon;
        }

        public String getWeather_des() {
            return weather_des;
        }

        public String getWeather_main() {
            return weather_main;
        }

    }

    WeatherData(JSONObject jsonObject,Context context) throws JSONException {
        //get location
        this.context = context;
        JSONObject city = jsonObject.getJSONObject(context.getString(R.string.city));
        JSONObject coord = city.getJSONObject(context.getString(R.string.coord));
        loc = new Location("dummyprovider");
        loc.setAltitude(coord.getDouble(context.getString(R.string.lat)));
        loc.setLongitude(coord.getDouble(context.getString(R.string.lon)));
        name = city.getString(context.getString(R.string.cityname));
        country = city.getString(context.getString(R.string.country));
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        DateTime current = new DateTime();
        JSONArray weatherarray = jsonObject.getJSONArray(context.getString(R.string.list));
        for(int i = 0;i<7;i++)
        {
            mDailyDetails.add(new DailyDetail());
            JSONObject object = weatherarray.getJSONObject(i);
            JSONObject tempobject = object.getJSONObject(context.getString(R.string.temp));
            //set min max temperature
            mDailyDetails.get(i).min = tempobject.getDouble(context.getString(R.string.min));
            mDailyDetails.get(i).max = tempobject.getDouble(context.getString(R.string.max));
            mDailyDetails.get(i).pressure = object.getDouble(context.getString(R.string.pressure));
            mDailyDetails.get(i).humidity = object.getInt(context.getString(R.string.humidity));
            JSONObject wea = object.getJSONArray(context.getString(R.string.weather)).getJSONObject(0);
            mDailyDetails.get(i).weather_main = wea.getString(context.getString(R.string.main));
            mDailyDetails.get(i).weather_des = wea.getString(context.getString(R.string.description));
            mDailyDetails.get(i).icon = wea.getString(context.getString(R.string.icon));
            DateTime idate = current.plusDays(i);
            mDailyDetails.get(i).date = weekdays[idate.getDayOfWeek()-1]+" ";
            mDailyDetails.get(i).date += formatter.print(idate);
        }

    }

    public ArrayList<DailyDetail> getDailyDetails() {
        return mDailyDetails;
    }

    public Location getLoc() {
        return loc;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }
}
