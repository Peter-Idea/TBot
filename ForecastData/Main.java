
package com.peter.TelegramBot.test.ForecastData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.peter.TelegramBot.test.AbstractForest.AbstractMain;
import com.peter.TelegramBot.test.AbstractForest.AbstractWind;

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
    @SerializedName("sea_level")
    @Expose
    private long seaLevel;
    @SerializedName("grnd_level")
    @Expose
    private long grndLevel;
    @SerializedName("temp_kf")
    @Expose
    private double tempKf;

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
     * @param seaLevel
     * @param humidity
     * @param pressure
     * @param tempKf
     * @param grndLevel
     * @param tempMin
     */
    public Main(double temp, double tempMin, double tempMax, long pressure, long seaLevel, long grndLevel, long humidity, double tempKf) {
        super();
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.seaLevel = seaLevel;
        this.grndLevel = grndLevel;
        this.humidity = humidity;
        this.tempKf = tempKf;
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

    public long getPressure() {
        return pressure;
    }

    public void setPressure(long pressure) {
        this.pressure = pressure;
    }

    public long getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(long seaLevel) {
        this.seaLevel = seaLevel;
    }

    public long getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(long grndLevel) {
        this.grndLevel = grndLevel;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    public double getTempKf() {
        return tempKf;
    }

    public void setTempKf(double tempKf) {
        this.tempKf = tempKf;
    }


}
