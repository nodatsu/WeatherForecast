package jp.ac.aomori_u.weatherforecasts;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tsunoda on 15/09/12.
 */

public class WeatherAPI {
    private static final String USER_AGENT = "WeatherForecasts Sample";
    private static final String URL_WF = "http://weather.livedoor.com/forecast/webservice/json/v1?city=";

    public static WeaterForecasts getWeather(Context context, String pointID) throws IOException, JSONException {
        URL url = new URL(URL_WF + pointID);

        StringBuilder sb = new StringBuilder();
        try {
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            con.disconnect();
        }
        catch (Exception e) {}

        return new WeaterForecasts(new JSONObject(sb.toString()));
    }
}
