
package com.peter.TelegramBot.test.WeatherData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.peter.TelegramBot.test.AbstractForest.AbstractMain;
import com.peter.TelegramBot.test.AbstractForest.AbstractWind;
//import org.apache.commons.lang3.builder.ToStringBuilder;

public class Main implements AbstractMain {

    @SerializedName("temp")
    @Expose
    private double temp;
    @SerializedName("pressure")
    @Expose
    private long pressure;
    @SerializedName("humidity")
    @Expose
    private long humidity;
    @SerializedName("temp_min")
    @Expose
    private double tempMin;
    @SerializedName("temp_max")
    @Expose
    private double tempMax;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Main() {
    }

    /**
     * 
     * @param tempMax
     * @param temp
     * @param humidity
     * @param pressure
     * @param tempMin
     */
    public Main(double temp, long pressure, long humidity, double tempMin, double tempMax) {
        super();
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    @Override
    public AbstractWind getWind() {
        return null;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public long getPressure() {
        return pressure;
    }

    public void setPressure(long pressure) {
        this.pressure = pressure;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }


}
