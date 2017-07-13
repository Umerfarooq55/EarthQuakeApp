package com.example.android.usgsquakereportclient;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class EarthQuakeLoader extends AsyncTaskLoader<ArrayList<EarthQuake>> {

    private String mUrl;

    public EarthQuakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<EarthQuake> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        return EarthQuakeExtractData.initiateConnection(mUrl);
    }
}
