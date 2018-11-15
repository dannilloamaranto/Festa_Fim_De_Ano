package com.example.danilo.festafimdeano.SecurityPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    private final SharedPreferences mSharedPrefereces;

    public SecurityPreferences(Context context){

        this.mSharedPrefereces = context.getSharedPreferences("FimDeAno", Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value){

       this.mSharedPrefereces.edit().putString(key, value).apply();
    }

    public String getStoredString(String key){
        return this.mSharedPrefereces.getString(key,"");
    }
}
