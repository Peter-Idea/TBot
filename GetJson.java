package com.peter.TelegramBot.test;

import com.google.gson.Gson;
import com.peter.TelegramBot.test.ForecastData.ForecastData;
import com.peter.TelegramBot.test.WeatherData.WeatherData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetJson<T> {
    //      HttpURLConnection
    private WeatherData weatherData = new WeatherData();
    private ForecastData forecastData = new ForecastData();
    private T abstractData;


    public GetJson(StringBuffer s, Class<T> classOfT) {
        URL url = null;
        try {
            url = new URL(s.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String data = getJSON(url, 10);
//        System.out.println(data);

        abstractData = new Gson().fromJson(data, classOfT);
//        System.out.println(classOfT.toString() + " ");
    }



    public WeatherData getWeatherData() {
        return weatherData;
    }

    public T getAbstractData() {
        return abstractData;
    }

    public ForecastData forecastData() {
        return forecastData;
    }

    public String getJSON(URL url, int timeout) {
        HttpURLConnection c = null;
        try {
            c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
//                c.setConnectTimeout(timeout);
//                c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();
            switch (status) {
                case 200:
                case 201:

                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuffer sb = new StringBuffer();

                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }




}

