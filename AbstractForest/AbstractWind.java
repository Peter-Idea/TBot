
package com.peter.TelegramBot.test.AbstractForest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AbstractWind {

    @SerializedName("speed")
    @Expose
    private double speed;
    @SerializedName("deg")
    @Expose
    private long deg;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AbstractWind() {
    }

    /**
     * 
     * @param deg
     * @param speed
     */
    public AbstractWind(double speed, long deg) {
        super();
        this.speed = speed;
        this.deg = deg;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public long getDeg() {
        return deg;
    }

    public void setDeg(long deg) {
        this.deg = deg;
    }


}
