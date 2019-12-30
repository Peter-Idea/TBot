
package com.peter.TelegramBot.test.AbstractForest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AbstractClouds {

    @SerializedName("all")
    @Expose
    private long all;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AbstractClouds() {
    }

    /**
     * 
     * @param all
     */
    public AbstractClouds(long all) {
        super();
        this.all = all;
    }

    public long getAll() {
        return all;
    }

    public void setAll(long all) {
        this.all = all;
    }

}
