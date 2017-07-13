package com.example.android.usgsquakereportclient;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class EarthQuakeExtractData {

    /**
     * This function initiates the process of making HTTP connection and returns ArrayList of earthquakes.
     *
     * @param stringUrl
     * @return
     */

    public static ArrayList<EarthQuake> initiateConnection(String stringUrl) {
        String jsonResponse = "";
        URL url = getURL(stringUrl);
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("EarthQuakeAsyncTask", "Error establishing Connection!!!");
        }
        ArrayList<EarthQuake> earthQuakes = extractFromJson(jsonResponse);
        return earthQuakes;
    }

    /**
     * This function take a string url and the URL Object.
     *
     * @param stringUrl
     * @return
     */
    public static URL getURL(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("EarthQuakeExtractData", "URL Exception => Not able to convert to url object.");
        }
        return url;
    }

    /**
     * Returns jsonresponse in String format.
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("EarthQuakeExtractData", "Error response code : " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("EarthQuakeExtractData", "Error IOExeception");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Return String from inputstream
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return arrayList of Earthquake Objects.
     *
     * @param jsonData
     * @return
     */
    public static ArrayList<EarthQuake> extractFromJson(String jsonData) {
        ArrayList<EarthQuake> earthQuakes = new ArrayList<>();
        try {
            JSONObject baseObject = new JSONObject(jsonData);
            JSONArray featuresArray = baseObject.getJSONArray("features");
            for (int i = 0; i < featuresArray.length(); i++) {
                JSONObject arrayObject = featuresArray.optJSONObject(i);
                JSONObject propertiesObject = arrayObject.optJSONObject("properties");
                earthQuakes.add(new EarthQuake(propertiesObject.optDouble("mag"),
                        propertiesObject.optLong("time"), propertiesObject.optString("place"),
                        propertiesObject.optString("url")));
            }
        } catch (JSONException e) {
            Log.e("EarthQuakeExtractData", "JSON data extract error.");
        }
        return earthQuakes;
    }

}
