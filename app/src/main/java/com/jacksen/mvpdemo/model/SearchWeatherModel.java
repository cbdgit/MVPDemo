package com.jacksen.mvpdemo.model;

import com.jacksen.mvpdemo.bean.WeatherInfo;

/**
 * Created by Admin on 2016/4/21.
 */
public interface SearchWeatherModel {

    /**
     * @param cityName
     * @return
     */
    WeatherInfo searchWeather(String cityName);
}
