package com.example.wasmahtask.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wasmahtask.R;
import com.example.wasmahtask.database.AppDatabase;
import com.example.wasmahtask.models.RefCountryCodesItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationDetailsActivity extends AppCompatActivity {

    @BindView(R.id.location_value)
    TextView locationValue;
    @BindView(R.id.longitude_value)
    TextView longitudeValue;
    @BindView(R.id.latitude_value)
    TextView latitudeValue;
    Intent intent = getIntent();
    RefCountryCodesItem model;
    @BindView(R.id.delete_location)
    TextView deleteLocation;

    AppDatabase appDatabase;
    Dialog mDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);
        ButterKnife.bind(this);
        intent=getIntent();
        appDatabase=AppDatabase.getAppDatabase(getApplicationContext());
        mDailog=new Dialog(this);
        if (intent.hasExtra("data")) {
            model = intent.getParcelableExtra("data");
            locationValue.setText(model.getCountry());
            longitudeValue.setText(String.valueOf(model.getLongitude()));
            latitudeValue.setText(String.valueOf(model.getLatitude()));

            getSupportActionBar().setTitle(model.getCountry());

        }

    }

    @OnClick(R.id.delete_location)
    public void onClick() {
        mDailog.setContentView(R.layout.delete_popup);
        mDailog.setCancelable(false);
        mDailog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mDailog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDailog.show();
       Button delete= mDailog.findViewById(R.id.delete_btn_id);
       Button cancel= mDailog.findViewById(R.id.cancel_btn_id);

       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               appDatabase.getAction().deleteLocation(model);
               Toast.makeText(getApplicationContext(), "Delete is done Sucessfully", Toast.LENGTH_LONG).show();
               mDailog.dismiss();
               startActivity(new Intent(getApplicationContext(),FavoriteLocationActivity.class));
           }
       });

       cancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mDailog.dismiss();
           }
       });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),FavoriteLocationActivity.class));
    }
}
