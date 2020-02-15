package com.example.wasmahtask.models;

import com.google.android.gms.maps.model.LatLng;


public class ClusterItem  implements com.google.maps.android.clustering.ClusterItem {

    private  LatLng mPosition;
    private  String mTitle;
    private  String mSnippet;

    public ClusterItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    public ClusterItem(double lat, double lng, String title, String snippet) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
    }

    public ClusterItem(double lat, double lng, String title) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
       // mSnippet = snippet;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }


}
