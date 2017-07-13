package com.example.android.usgsquakereportclient;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EarthQuakeDetailActivity extends AppCompatActivity {

    TextView magnitudeView;
    TextView locationPrimaryView;
    TextView locationOffsetView;
    TextView dateView;
    TextView timeView;
    Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_quake_detail);

        Intent intent = getIntent();
        final EarthQuake currentEarthQuake = (EarthQuake) intent.getSerializableExtra("earthQuakeObject");

        magnitudeView = (TextView) findViewById(R.id.magnitude);
        magnitudeView.setText(formatMagnitude(currentEarthQuake.getMagnitude()));

        GradientDrawable drawable = (GradientDrawable) magnitudeView.getBackground();
        drawable.setColor(getMagnitudeColor(currentEarthQuake.getMagnitude()));

        String location = currentEarthQuake.getLocation();
        String location_offset;
        String location_primary;
        if (location.contains(" of ")) {
            String[] location_arr = location.split(" of ");
            location_offset = location_arr[0] + " of";
            location_primary = location_arr[1];
        } else {
            location_offset = getString(R.string.near_the);
            location_primary = location;
        }


        locationOffsetView = (TextView) findViewById(R.id.location_offset);
        locationOffsetView.setText(location_offset);

        locationPrimaryView = (TextView) findViewById(R.id.location_primary);
        locationPrimaryView.setText(location_primary);

        Date dateObject = new Date(currentEarthQuake.getDate());

        dateView = (TextView) findViewById(R.id.date);
        dateView.setText(convertToDate(dateObject));

        timeView = (TextView) findViewById(R.id.time);
        timeView.setText(convertToTime(dateObject));

        buttonView = (Button) findViewById(R.id.button);
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(currentEarthQuake.getUrl());
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(uri);
                startActivity(i);
            }
        });


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
        return ContextCompat.getColor(this, magnitudeResourceId);
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
