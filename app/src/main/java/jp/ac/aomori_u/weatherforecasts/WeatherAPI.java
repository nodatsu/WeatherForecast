package jp.ac.aomori_u.weatherforecasts;

import android.content.Context;
import android.net.http.AndroidHttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by tsunoda on 15/09/12.
 */

public class WeatherAPI {
    private static final String USER_AGENT = "WeatherForecasts Sample";
    private static final String URL = "http://weather.livedoor.com/forecast/webservice/jason/v1?city=";

    public static String getWeather(Context context, String pointID) throws IOException {
        AndroidHttpClient client = AndroidHttpClient.newInstance(USER_AGENT, context);
        HttpGet get = new HttpGet(URL + pointID);

        StringBuilder sb = new StringBuilder();
        try {
            HttpResponse response = client.execute(get);
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        finally {
                client.close();
        }

        return sb.toString();
    }

}
