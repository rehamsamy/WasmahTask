package com.example.wasmahtask.ui;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wasmahtask.models.JeffKellyModel;
import com.example.wasmahtask.utils.ClientInstance;
import com.example.wasmahtask.utils.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JeffViewModel extends ViewModel {

    private MutableLiveData<List<JeffKellyModel>> jeffModels;


    public LiveData<List<JeffKellyModel>> getJeffModelsList(int current_page,ProgressBar progressBar){
        if(jeffModels==null){
            jeffModels=new MutableLiveData<>();
  progressBar.setVisibility(View.VISIBLE);
            getDataFromRetrofit(current_page,progressBar);
        }


        return jeffModels;
    }

    private void getDataFromRetrofit(int current_page, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        RetrofitInstance retrofitInstance= ClientInstance.getRetrofit();
        retrofitInstance= ClientInstance.getRetrofit();
        Call<List<JeffKellyModel>> call=retrofitInstance.getJeffKellyList(current_page,3);
        call.enqueue(new Callback<List<JeffKellyModel>>() {
            @Override
            public void onResponse(Call<List<JeffKellyModel>> call, Response<List<JeffKellyModel>> response) {
                if(response.isSuccessful()){

                    jeffModels.setValue(response.body());
                    Log.v("TAG","xxx "+response.body().size());
                    progressBar.setVisibility(View.GONE);

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
