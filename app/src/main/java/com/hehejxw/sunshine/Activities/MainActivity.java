package com.hehejxw.sunshine.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.hehejxw.sunshine.Application.Weather;
import com.hehejxw.sunshine.Fragments.WeatherFragment;
import com.hehejxw.sunshine.R;

public class MainActivity extends AppCompatActivity implements WeatherFragment.OnFragmentInteractionListener {


    //this singleton will be created even before MainActivity
    Weather mWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWeather = Weather.getmInstance();
        WeatherFragment WeatherFrag = new WeatherFragment();
        //in case the activity may be started from an intent
        WeatherFrag.setArguments(getIntent().getExtras());
        //use this to replace the default frame
        getSupportFragmentManager().beginTransaction().add(R.id.rootframe,WeatherFrag).commit();
    }

    //this method is required to implement
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
