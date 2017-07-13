package com.example.android.usgsquakereportclient;


import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {

    /**
     * Construct {@link EarthQuakeAdapter}
     *
     * @param context
     * @param earthQuakes
     */
    public EarthQuakeAdapter(Context context, ArrayList<EarthQuake> earthQuakes) {
        super(context, 0, earthQuakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.quake_list_item, parent, false);
        }

        EarthQuake currentEarthQuake = getItem(position);

        TextView magnitudeView = (TextView) convertView.findViewById(R.id.magnitude);
        magnitudeView.setText(formatMagnitude(currentEarthQuake.getMagnitude()));

        /*
         * Get the Drawable background and set its color.
         */
        GradientDrawable drawable = (GradientDrawable) magnitudeView.getBackground();
        drawable.setColor(getMagnitudeColor(currentEarthQuake.getMagnitude()));

        /*
         * Splitting the location into offset and primary.
         */
        String location = currentEarthQuake.getLocation();
        String location_offset;
        String location_primary;
        if (location.contains(" of ")) {
            String[] location_arr = location.split(" of ");
            location_offset = location_arr[0] + " of";
            location_primary = location_arr[1];
        } else {
            location_offset = getContext().getString(R.string.near_the);
            location_primary = location;
        }

        TextView locationOffset = (TextView) convertView.findViewById(R.id.location_offset);
        locationOffset.setText(location_offset);

        TextView locationPrimary = (TextView) convertView.findViewById(R.id.location_primary);
        locationPrimary.setText(location_primary);

        Date dateObject = new Date(currentEarthQuake.getDate());

        TextView dateView = (TextView) convertView.findViewById(R.id.date);
        dateView.setText(convertToDate(dateObject));

        TextView timeView = (TextView) convertView.findViewById(R.id.time);
        timeView.setText(convertToTime(dateObject));

        return convertView;
    }

    /**
     * Return magnitude color
     *
     * @param magnitude
     * @return
     */
    private int getMagnitudeColor(double magnitude) {
        int magnitudeResourceId;
        switch ((int) Math.floor(magnitude)) {
            case 0:
                magnitudeResourceId = R.color.magnitude1;
                break;
            case 1:
                magnitudeResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeResourceId = R.color.magnitude9;
                break;
            case 10:
                magnitudeResourceId = R.color.magnitude10;
                break;
            default:
                magnitudeResourceId = R.color.magnitude10;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeResourceId);
    }


    /**
     * Convert and return magnitude in 0.0 format
     *
     * @param magnitude
     * @return
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat format = new DecimalFormat("0.0");
        return format.format(magnitude);
    }

    /**
     * Convert and return time in h:mm a format
     *
     * @param time
     * @return
     */
    private String convertToTime(Date time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(time);
    }

    /**
     * Convert and return date in MMM d, yyyy format
     *
     * @param date
     * @return
     */
    private String convertToDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        return dateFormat.format(date);
    }


}
