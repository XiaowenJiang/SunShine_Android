package com.hehejxw.sunshine.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.hehejxw.sunshine.Application.Weather;
import com.hehejxw.sunshine.R;
import com.hehejxw.sunshine.Volley.WeatherData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by XiaowenJiang on 4/10/16.
 */
public class WeatherListAdapter extends BaseAdapter {
    private Weather mWeather = Weather.getmInstance();
    private LayoutInflater mLayoutInflater;
    private ArrayList<WeatherData.DailyDetail> mDailyDetails = new ArrayList<>();

    public WeatherListAdapter(Context context)
    {
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void UpdateItems(ArrayList<WeatherData.DailyDetail> dailyDetails)
    {
        mDailyDetails = dailyDetails;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDailyDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return mDailyDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView!=null)
        {
            holder = (ViewHolder)convertView.getTag();
        }
        else
        {
            convertView = mLayoutInflater.inflate(R.layout.list_item_forecast,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        if(getItem(position)!=null)
        {
            WeatherData.DailyDetail temp = mDailyDetails.get(position);
            if(temp.getIcon()!=null)
            {
                String imgurl = "http://openweathermap.org/img/w/"+temp.getIcon()+".png";
                holder.iconview.setImageUrl(imgurl,mWeather.getImageLoader());
            }
            holder.date.setText(temp.getDate());
            String Celsius = "\u2103";
            holder.maxtemp.setText("Max: "+temp.getMax()+Celsius);
            holder.mintemp.setText("Min: "+temp.getMin()+Celsius);
            holder.weather_des.setText(temp.getWeather_des());
        }
        return convertView;
    }

    public static class ViewHolder{
        @Bind(R.id.weather_date) TextView date;
        @Bind(R.id.weather_icon)
        NetworkImageView iconview;
        @Bind(R.id.temp_max) TextView maxtemp;
        @Bind(R.id.temp_min) TextView mintemp;
        @Bind(R.id.weather_des) TextView weather_des;
        public ViewHolder(View view)
        {
            ButterKnife.bind(this,view);
        }
    }
}
