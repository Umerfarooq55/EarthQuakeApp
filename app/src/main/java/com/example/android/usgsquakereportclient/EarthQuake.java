package com.example.android.usgsquakereportclient;

import java.io.Serializable;

@SuppressWarnings("serial")         //With this notation we are going to hide compiler warning.
public class EarthQuake implements Serializable {

    private double mMagnitude;
    private long mDate;
    private String mLocation;
    private String mUrl;

    /**
     * Construct {@link EarthQuake}
     *
     * @param magnitude
     * @param date
     * @param location
     * @param url
     */
    public EarthQuake(double magnitude, long date, String location, String url) {
        mMagnitude = magnitude;
        mDate = date;
        mLocation = location;
        mUrl = url;
    }

    /**
     * Return magnitude of the earthquake.
     *
     * @return
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /**
     * Return date and time of earthquake in Unix format.
     *
     * @return
     */
    public long getDate() {
        return mDate;
    }

    /**
     * Return location/title of the earthquake.
     *
     * @return
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Return url of the earthquake.
     *
     * @return
     */
    public String getUrl() {
        return mUrl;
    }


}
