package com.jacksen.mvpdemo.model;

import com.jacksen.mvpdemo.bean.WeatherInfo;
import com.jacksen.mvpdemo.presenter.WeatherPresenter;
import com.jacksen.mvpdemo.util.Constants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Admin on 2016/4/21.
 */
public class SearchWeatherImpl implements SearchWeatherModel {

    private WeatherPresenter weatherPresenter;

    public SearchWeatherImpl(WeatherPresenter weatherPresenter) {
        this.weatherPresenter = weatherPresenter;
    }

    @Override
    public WeatherInfo searchWeather(String cityName) {
        String jsonResult = requestWeatherInfo(Constants.API_URL, "city=" + cityName);
//        Log.d("SearchWeatherImpl", jsonResult);
        System.out.println(jsonResult);
        return null;
    }

    /**
     * @param httpUrl
     * @param cityName
     * @return
     */
    public String requestWeatherInfo(String httpUrl, String cityName) {
        BufferedReader reader = null;
        String result = "";
        StringBuffer sb = new StringBuffer();
        httpUrl = httpUrl + "?" + cityName;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("apikey", Constants.API_KEY);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
                sb.append("\r\n");
            }
            reader.close();
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
