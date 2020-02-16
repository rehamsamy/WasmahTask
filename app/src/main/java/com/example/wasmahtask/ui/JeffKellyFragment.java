package com.example.wasmahtask.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wasmahtask.R;
import com.example.wasmahtask.adapter.JeffKellyAdapter;
import com.example.wasmahtask.models.JeffKellyModel;
import com.example.wasmahtask.utils.ClientInstance;
import com.example.wasmahtask.utils.EndlessRecyclerViewScrollListener;
import com.example.wasmahtask.utils.NetworkAvailable;
import com.example.wasmahtask.utils.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JeffKellyFragment extends Fragment {
    RecyclerView recyclerView;
    TextView emptyData;
    ProgressBar progressBar;
    RetrofitInstance retrofitInstance;
    NetworkAvailable networkAvailable;
    JeffKellyAdapter adapter;
    int current_page = 0;
    List<JeffKellyModel> jeffModels=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jeffkelly_layout, container, false);
        recyclerView = view.findViewById(R.id.recycler_id);
        progressBar = view.findViewById(R.id.progress_id);
        emptyData = view.findViewById(R.id.empty_data);
        networkAvailable = new NetworkAvailable(getContext());

        if(networkAvailable.isNetworkAvailable()){
            jeffModels.clear();
            buildRecyclerForJeff();
            getJeffKellyModels(current_page);
        }else {
            Toast.makeText(getContext(), getString(R.string.error_connection), Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void buildRecyclerForJeff() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new JeffKellyAdapter(getContext(), jeffModels);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                current_page++;
                getJeffKellyModels(current_page);
            }
        });


    }

    private void getJeffKellyList(final int current_pagee) {
       // progressBar.setVisibility(View.VISIBLE);
        JeffViewModel model = ViewModelProviders.of(getActivity()).get(JeffViewModel.class);
        model.getJeffModelsList(current_pagee, progressBar,3,  adapter,jeffModels).observe(getActivity(), new Observer<List<JeffKellyModel>>() {
            @Override
            public void onChanged(List<JeffKellyModel> jeffKellyModels) {
                if(jeffKellyModels.size()>0) {
                    Log.v("TAG", "ccc" + jeffKellyModels.size());
                    jeffModels.addAll(jeffKellyModels);
                    //adapter.notifyDataSetChanged();
                    jeffModels=jeffKellyModels;
                    adapter.notifyDataSetChanged();
                }else if(current_page==1&&jeffKellyModels.size()==0){
                    return;
                }

            }
        });
    }


   void  getJeffKellyModels(int current_page){
       progressBar.setVisibility(View.VISIBLE);
       RetrofitInstance retrofitInstance= ClientInstance.getRetrofit();
       retrofitInstance= ClientInstance.getRetrofit();
       Call<List<JeffKellyModel>> call=retrofitInstance.getJeffKellyList(current_page,3);
       call.enqueue(new Callback<List<JeffKellyModel>>() {
           @Override
           public void onResponse(Call<List<JeffKellyModel>> call, Response<List<JeffKellyModel>> response) {
               if(response.isSuccessful()){
                  jeffModels.addAll(response.body());
                   adapter.notifyDataSetChanged();
                   Log.v("TAG","xxx "+current_page);
                   progressBar.setVisibility(View.GONE);

                   if(response.body().size()==0){
                       Toast.makeText(getContext(), "no item more", Toast.LENGTH_LONG).show();
                       progressBar.setVisibility(View.GONE);
                   }

               }
           }

           @Override
           public void onFailure(Call<List<JeffKellyModel>> call, Throwable t) {
               Log.v("TAG","xxx"+t.getMessage());
               progressBar.setVisibility(View.GONE);
               //progressBar.setVisibility(View.GONE);
           }
       });

   }
}

