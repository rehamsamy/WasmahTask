package com.example.wasmahtask.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class DataSourceFactory  extends  DataSource.Factory{
    DataSource dataSource;
    MutableLiveData<DataSource> mutableLiveData;

    public DataSourceFactory(){
        mutableLiveData=new MutableLiveData<>();
    }

    @NonNull
    @Override
    public androidx.paging.DataSource create() {
        dataSource=new DataSource();
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

   public MutableLiveData<DataSource> getMutableLiveData(){
        return  mutableLiveData;
   }
}
