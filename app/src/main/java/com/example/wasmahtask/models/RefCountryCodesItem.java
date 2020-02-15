package com.example.wasmahtask.models;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "location")
public class RefCountryCodesItem implements Parcelable {

	@PrimaryKey (autoGenerate = true)
	private int id;


	public RefCountryCodesItem(int id, String country, double latitude, String alpha2, String alpha3, int numeric, double longitude) {
		this.id = id;
		this.country = country;
		this.latitude = latitude;
		this.alpha2 = alpha2;
		this.alpha3 = alpha3;
		this.numeric = numeric;
		this.longitude = longitude;
	}

	@Ignore
	public RefCountryCodesItem(String country, double latitude, double longitude) {
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@SerializedName("country")
	private String country;

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("alpha2")
	private String alpha2;

	@SerializedName("alpha3")
	private String alpha3;

	@SerializedName("numeric")
	private int numeric;

	@SerializedName("longitude")
	private double longitude;

	@Ignore
	protected RefCountryCodesItem(Parcel in) {
		id = in.readInt();
		country = in.readString();
		latitude = in.readDouble();
		alpha2 = in.readString();
		alpha3 = in.readString();
		numeric = in.readInt();
		longitude = in.readDouble();
	}

	public static final Creator<RefCountryCodesItem> CREATOR = new Creator<RefCountryCodesItem>() {
		@Override
		public RefCountryCodesItem createFromParcel(Parcel in) {
			return new RefCountryCodesItem(in);
		}

		@Override
		public RefCountryCodesItem[] newArray(int size) {
			return new RefCountryCodesItem[size];
		}
	};

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setLatitude(int latitude){
		this.latitude = latitude;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setAlpha2(String alpha2){
		this.alpha2 = alpha2;
	}

	public String getAlpha2(){
		return alpha2;
	}

	public void setAlpha3(String alpha3){
		this.alpha3 = alpha3;
	}

	public String getAlpha3(){
		return alpha3;
	}

	public void setNumeric(int numeric){
		this.numeric = numeric;
	}

	public int getNumeric(){
		return numeric;
	}

	public void setLongitude(int longitude){
		this.longitude = longitude;
	}

	public double getLongitude(){
		return longitude;
	}

	public int getId() {
		return id;
	}

	@Override
 	public String toString(){
		return 
			"RefCountryCodesItem{" + 
			"country = '" + country + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",alpha2 = '" + alpha2 + '\'' + 
			",alpha3 = '" + alpha3 + '\'' + 
			",numeric = '" + numeric + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(country);
		dest.writeDouble(latitude);
		dest.writeString(alpha2);
		dest.writeString(alpha3);
		dest.writeInt(numeric);
		dest.writeDouble(longitude);
	}
}