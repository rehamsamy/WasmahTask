package com.example.wasmahtask.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import com.example.wasmahtask.R;
import com.example.wasmahtask.database.AppDatabase;
import com.example.wasmahtask.models.ClusterItem;
import com.example.wasmahtask.models.RefCountryCodesItem;
import com.example.wasmahtask.utils.JsonExtractor;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.MarkerManager;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.ClusterRenderer;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.List;


public class LocationMapFragment extends Fragment {
    GoogleMap mMap;
    AppDatabase appDatabase;
    int flag;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_map_layout,container,false);
        appDatabase=AppDatabase.getAppDatabase(getContext());

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_id);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap)
            {
                mMap=googleMap;
                new Task().execute(mMap);
               // setUpClusterManager(mMap);
            }
        });


       return  view;

    }

    private void setUpClusterManager(GoogleMap googleMap,List<RefCountryCodesItem> refCountryCodesItems) {

        ClusterManager<ClusterItem> clusterManager = new ClusterManager(getContext(), googleMap);  // 3
        googleMap.setOnCameraIdleListener(clusterManager);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33, 65), 2));
        googleMap.setOnCameraIdleListener(clusterManager);
        googleMap.setOnMarkerClickListener(clusterManager);
        addItems(clusterManager,refCountryCodesItems);

        clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<ClusterItem>() {
            @Override
            public boolean onClusterItemClick(ClusterItem clusterItem) {
                clusterManager.getClusterMarkerCollection().addMarker(new MarkerOptions().position(clusterItem.getPosition())
                        .title(clusterItem.getTitle())).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

                Log.v("TAG","ccc ccc"+clusterItem.getTitle());
                RefCountryCodesItem item=new RefCountryCodesItem(clusterItem.getTitle(),clusterItem.getPosition().latitude,clusterItem.getPosition().longitude);
                List<RefCountryCodesItem> items=  appDatabase.getAction().getLocationList();
                if(items.size()>0){
                    for(int i=0;i<items.size();i++){
                        if(items.get(i).getCountry().equals(item.getCountry())){
                            Toast.makeText(getContext(), "this location is already added to favorite list", Toast.LENGTH_LONG).show();
                            Log.v("TAG","exp 1"+clusterItem.getTitle());
                            flag=1;
                            break;
                        }
                        else {
                            flag=0;
                        }
                    }

                    if(flag==1){
                        Toast.makeText(getContext(), "this location is already in favorite list", Toast.LENGTH_LONG).show();
                    }else if(flag==0){
                        appDatabase.getAction().insertLocation(item);
                        Toast.makeText(getContext(), "this location is added sucessfully to favorite list", Toast.LENGTH_LONG).show();
                    }

                }else if(items.size()==0) {
                    appDatabase.getAction().insertLocation(item);
                    Toast.makeText(getContext(), "this location is added sucessfully to favorite list", Toast.LENGTH_LONG).show();
                    Log.v("TAG","exp 3"+clusterItem.getTitle());

                }

                Log.v("TAG","items "+item.getCountry());

                return false;
            }
        });


    }


    private void addItems( ClusterManager<ClusterItem> clusterManager,List<RefCountryCodesItem> refCountryCodesItems) {


        double lat,lng;
        String name;

        Log.v("TAG","ccc ccc  "+refCountryCodesItems.size()+"  "+refCountryCodesItems.get(1).getCountry());
        // Add ten cluster items in close proximity, for purposes of this example.
        for (int i = 0; i < refCountryCodesItems.size(); i++) {
            double offset = i / 60d;

            lat=refCountryCodesItems.get(i).getLatitude();
            lng=refCountryCodesItems.get(i).getLongitude();
            name=refCountryCodesItems.get(i).getCountry();

            ClusterItem offsetItem = new ClusterItem(lat, lng,name);
            clusterManager.addItem(offsetItem);

        }
    }




    class Task extends AsyncTask<GoogleMap,Void,List<RefCountryCodesItem>>{

        @Override
        protected List<RefCountryCodesItem> doInBackground(GoogleMap... googleMaps) {
            List<RefCountryCodesItem> countryCodesItems= JsonExtractor.getCountery(getContext());
            return countryCodesItems;
        }

        @Override
        protected void onPostExecute(List<RefCountryCodesItem> refCountryCodesItems) {
            super.onPostExecute(refCountryCodesItems);
            if(refCountryCodesItems.size()!=0){
                setUpClusterManager(mMap,refCountryCodesItems);
            }
        }
    }



}
