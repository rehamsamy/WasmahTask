package com.example.wasmahtask.ui;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.wasmahtask.models.JeffKellyModel;
import com.example.wasmahtask.utils.ClientInstance;
import com.example.wasmahtask.utils.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataSource extends PageKeyedDataSource<Integer,JeffKellyModel> {
    RetrofitInstance retrofitInstance;
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, JeffKellyModel> callback) {
        retrofitInstance= ClientInstance.getRetrofit();
        Call<List<JeffKellyModel>> call=retrofitInstance.getJeffKellyList(1,1);
        call.enqueue(new Callback<List<JeffKellyModel>>() {
            @Override
            public void onResponse(Call<List<JeffKellyModel>> call, Response<List<JeffKellyModel>> response) {
                if(response.isSuccessful()){
                   List<JeffKellyModel> models=response.body();
                  //  assert models != null;
                    callback.onResult(models,null,(Integer) 1);
                    Log.v("TAG","xxx 1 "+response.body().size());

                }
            }

            @Override
            public void onFailure(Call<List<JeffKellyModel>> call, Throwable t) {
                Log.v("TAG","xxx"+t.getMessage());
                //progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, JeffKellyModel> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, JeffKellyModel> callback) {

        retrofitInstance= ClientInstance.getRetrofit();
        Call<List<JeffKellyModel>> call=retrofitInstance.getJeffKellyList(params.key,2);
        call.enqueue(new Callback<List<JeffKellyModel>>() {
            @Override
            public void onResponse(Call<List<JeffKellyModel>> call, Response<List<JeffKellyModel>> response) {
                if(response.isSuccessful()){
                    List<JeffKellyModel> models=response.body();
                    callback.onResult(models,(params.key)+1);

                    Log.v("TAG","xxx 2 "+response.body().size());

                }
            }

            @Override
            public void onFailure(Call<List<JeffKellyModel>> call, Throwable t) {
                Log.v("TAG","xxx"+t.getMessage());
                //progressBar.setVisibility(View.GONE);
            }
        });

    }
}
