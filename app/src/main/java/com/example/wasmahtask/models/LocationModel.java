package com.example.wasmahtask.models;

import androidx.room.Entity;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class LocationModel{

	@SerializedName("ref_country_codes")
	private List<RefCountryCodesItem> refCountryCodes;

	public void setRefCountryCodes(List<RefCountryCodesItem> refCountryCodes){
		this.refCountryCodes = refCountryCodes;
	}

	public List<RefCountryCodesItem> getRefCountryCodes(){
		return refCountryCodes;
	}

	@Override
 	public String toString(){
		return 
			"LocationModel{" + 
			"ref_country_codes = '" + refCountryCodes + '\'' + 
			"}";
		}
}