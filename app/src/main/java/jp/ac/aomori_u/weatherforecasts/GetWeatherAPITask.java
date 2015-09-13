package jp.ac.aomori_u.weatherforecasts;

/**
 * Created by tsunoda on 15/09/12.
 */

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

public class GetWeatherAPITask extends AsyncTask<String, Void, WeaterForecasts> {
    private final Context context;
    Exception exception;

    public GetWeatherAPITask(Context context) {
        this.context = context;
    }

    @Override
    protected WeaterForecasts doInBackground(String... params) {
        try {
            return WeatherAPI.getWeather(context, params[0]);
        }
        catch (IOException e) {
            this.exception = e;
        }
        catch (JSONException e) {
            this.exception = e;
        }
        return null;
    }
}
