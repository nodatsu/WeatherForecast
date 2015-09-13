package jp.ac.aomori_u.weatherforecasts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsunoda on 15/09/13.
 */

public class WeaterForecasts {
    public Location location;
    public List<Forecast> forecastList = new ArrayList<Forecast>();

    public WeaterForecasts(JSONObject jsonObject) throws JSONException {
        JSONObject locationObject = jsonObject.getJSONObject("location");
        location = new Location(locationObject);
        JSONArray forecastArray = jsonObject.getJSONArray("forecasts");

        int len = forecastArray.length();
        for (int i = 0; i < len; i++) {
            JSONObject forecastJSON = forecastArray.getJSONObject(i);
            Forecast forecast = new Forecast(forecastJSON);
            forecastList.add(forecast);
        }
    }

    public class Location {
        public String area;
        public String prefecture;
        public String city;

        public Location(JSONObject jsonObject) throws JSONException {
            area = jsonObject.getString("area");
            prefecture = jsonObject.getString("prefecture");
            city = jsonObject.getString("city");
        }
    }

    public class Forecast {
        public String date;
        public String dateLabel;
        public String telop;
        public Image image;
        public Temperature temperature;
        
        public Forecast(JSONObject jsonObject) throws JSONException {
            date = jsonObject.getString("date");
            dateLabel = jsonObject.getString("dateLabel");
            telop = jsonObject.getString("telop");
            image = new Image(jsonObject.getJSONObject("image"));
            temperature = new Temperature(jsonObject.getJSONObject("temperature"));
        }
        
        public class Image {
            public String title;
            public String link;
            public String url;
            public int width;
            public int height;
            
            public Image(JSONObject jsonObject) throws JSONException {
                title = jsonObject.getString("title");
                if (jsonObject.has("link")) {
                    link = jsonObject.getString("link");
                }
                else {
                    link = null;
                }
                url = jsonObject.getString("url");
                width = jsonObject.getInt("width");
                height = jsonObject.getInt("height");
            }
        }
        
        public class Temperature {
            public Temp min;
            public Temp max;
            
            public Temperature(JSONObject jsonObject) throws JSONException {
                if (!jsonObject.isNull("min")) {
                    min = new Temp(jsonObject.getJSONObject("min"));
                }
                else {
                    min = new Temp(null);
                }
                if (!jsonObject.isNull("max")) {
                    max = new Temp(jsonObject.getJSONObject("max"));
                }
                else {
                    max = new Temp(null);
                }
            }
        }

        public class Temp {
            public String celsius;
            public String fahrenheit;

            public Temp(JSONObject jsonObject) throws JSONException {
                if (jsonObject == null) {
                    celsius = null;
                    fahrenheit = null;
                    return;
                }
                celsius = jsonObject.getString("celsius");
                fahrenheit = jsonObject.getString("fahrenheit");
            }
        }
    }
}
