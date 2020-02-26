package com.example.wasmahtask.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.wasmahtask.models.JeffKellyModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainViewModel extends AndroidViewModel {

    //PhotoRepository photoRepository;
    DataSourceFactory dataSourceFactory;
    MutableLiveData<DataSource> modelMutableLiveData;
    Executor executor;
    LiveData<PagedList<JeffKellyModel>> pagedListLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);


        dataSourceFactory = new DataSourceFactory();
        modelMutableLiveData = dataSourceFactory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(3)
                .setPageSize(3)
                .setPrefetchDistance(1)
                .build();
        executor = Executors.newFixedThreadPool(5);
        pagedListLiveData = (new LivePagedListBuilder<Long,JeffKellyModel>(dataSourceFactory,config))
                .setFetchExecutor(executor)
                .build();

    }

    public LiveData<PagedList<JeffKellyModel>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
