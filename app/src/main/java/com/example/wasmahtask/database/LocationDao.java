package com.example.wasmahtask.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.wasmahtask.models.RefCountryCodesItem;

import java.util.List;

@Dao
public interface LocationDao {

    @Insert
    void insertLocation(RefCountryCodesItem model);

    @Query("SELECT * FROM location ORDER BY id")
    List<RefCountryCodesItem> getLocationList();


    @Delete()
    void deleteLocation(RefCountryCodesItem model);


}
