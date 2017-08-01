package Cryptonator.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lennard on 21-7-2017.
 */

public class Ticker {

    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("target")
    @Expose
    private String target;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("change")
    @Expose
    private String change;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public List<String> returnCSVWriteLine()
    {
        List<String> csvWriteLine = new ArrayList<>();
        csvWriteLine.add(getBase());
        csvWriteLine.add(getTarget());
        csvWriteLine.add(getPrice());
        csvWriteLine.add(getVolume());
        csvWriteLine.add(getChange());
        return csvWriteLine;
    }

}