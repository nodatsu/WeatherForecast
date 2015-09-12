package jp.ac.aomori_u.weatherforecasts;

/**
 * Created by tsunoda on 15/09/12.
 */

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

public class GetWeatherAPITask extends AsyncTask<String, Void, String> {
    private final Context context;
    Exception exception;

    public GetWeatherAPITask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return WeatherAPI.getWeather(context, params[0]);
        }
        catch (IOException e) {
            this.exception = e;
        }
        return null;
    }
}
