package com.example.android.usgsquakereportclient;


import android.os.AsyncTask;
import java.util.ArrayList;

public class EarthQuakeAsyncTask extends AsyncTask<String, Void, ArrayList<EarthQuake>> {

    private EarthQuakeAsyncResponse mEarthQuakeAsyncResponse = null;

    public EarthQuakeAsyncTask(EarthQuakeAsyncResponse earthQuakeAsyncResponse) {
        mEarthQuakeAsyncResponse = earthQuakeAsyncResponse;
    }

    @Override
    protected ArrayList<EarthQuake> doInBackground(String... strings) {
        return EarthQuakeExtractData.initiateConnection(strings[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<EarthQuake> earthQuakes) {
        if (earthQuakes == null) {
            return;
        }
        mEarthQuakeAsyncResponse.processFinish(earthQuakes);
    }

}
