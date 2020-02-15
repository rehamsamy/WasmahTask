package com.example.wasmahtask.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wasmahtask.R;
import com.example.wasmahtask.adapter.FavoriteAdapter;
import com.example.wasmahtask.adapter.JeffKellyAdapter;
import com.example.wasmahtask.database.AppDatabase;
import com.example.wasmahtask.models.RefCountryCodesItem;
import com.example.wasmahtask.utils.OnRecyclerClick;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteLocationActivity extends AppCompatActivity {

    @BindView(R.id.recycler_id)
    RecyclerView recyclerId;
    @BindView(R.id.progress_id)
    ProgressBar progressId;
    @BindView(R.id.empty_data)
    TextView emptyData;

    @BindView(R.id.note_txt) TextView noteTxt;
    AppDatabase appDatabase;
    FavoriteAdapter adapter;
    List<RefCountryCodesItem> codesItemList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_location);
        ButterKnife.bind(this);
        appDatabase=AppDatabase.getAppDatabase(getApplicationContext());

        getFavoriteList();

        getSupportActionBar().setTitle("My Favorite");


    }

    private void getFavoriteList() {
        recyclerId.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerId.setHasFixedSize(true);
        codesItemList=appDatabase.getAction().getLocationList();
        if(codesItemList.size()==0){
            emptyData.setVisibility(View.VISIBLE);
            noteTxt.setVisibility(View.INVISIBLE);
        }else {
            adapter = new FavoriteAdapter(getApplicationContext(), codesItemList);
            recyclerId.setAdapter(adapter);

            adapter.setItemClickInterface(new OnRecyclerClick() {
                @Override
                public void onClick(int position) {
                    RefCountryCodesItem model=codesItemList.get(position);
                    Intent intent=new Intent(getApplicationContext(),LocationDetailsActivity.class);
                    intent.putExtra("data",model);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
