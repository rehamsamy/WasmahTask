package com.example.wasmahtask.utils;

import android.content.Context;
import android.util.Log;

import com.example.wasmahtask.models.RefCountryCodesItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonExtractor {

  static   List<RefCountryCodesItem> codesItemList;
   // static Context context;


    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("country_codes_lat-_ong_alpha3.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static  List<RefCountryCodesItem> getCountery(Context context){

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray m_jArry = obj.getJSONArray("ref_country_codes");
            codesItemList=new ArrayList<>();

            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
//                Log.d("Details-->", jo_inside.getString("formule"));
//                String formula_value = jo_inside.getString("formule");
//                String url_value = jo_inside.getString("url");
//
//                //Add your values in your `ArrayList` as below:
//                m_li = new HashMap<String, String>();
//                m_li.put("formule", formula_value);
//                m_li.put("url", url_value);
                RefCountryCodesItem item=new RefCountryCodesItem(jo_inside.getString("country"),jo_inside.getInt("latitude")
                ,jo_inside.getInt("longitude"));

                codesItemList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  codesItemList;
    }



}
