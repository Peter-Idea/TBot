
package com.peter.TelegramBot.test.ForecastData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.peter.TelegramBot.test.AbstractForest.AbstractData;


public class ForecastData extends AbstractData {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private long message;
    @SerializedName("cnt")
    @Expose
    private long cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<com.peter.TelegramBot.test.ForecastData.List> list = null;
    @SerializedName("city")
    @Expose
    private City city;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ForecastData() {
    }

    /**
     * 
     * @param city
     * @param cnt
     * @param cod
     * @param message
     * @param list
     */
    public ForecastData(String cod, long message, long cnt, java.util.List<com.peter.TelegramBot.test.ForecastData.List> list, City city) {
        super();
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }


    public static Class GetMyClass() {
        return ForecastData.class;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public long getMessage() {
        return message;
    }

    public void setMessage(long message) {
        this.message = message;
    }

    public long getCnt() {
        return cnt;
    }

    public void setCnt(long cnt) {
        this.cnt = cnt;
    }

    public java.util.List<com.peter.TelegramBot.test.ForecastData.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.peter.TelegramBot.test.ForecastData.List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


}
