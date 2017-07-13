package com.example.android.usgsquakereportclient;


import java.util.ArrayList;

public interface EarthQuakeAsyncResponse {
    public void processFinish(ArrayList<EarthQuake> earthQuakes);
}
