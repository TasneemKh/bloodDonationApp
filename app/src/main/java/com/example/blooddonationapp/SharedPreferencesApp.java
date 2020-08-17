package com.example.blooddonationapp;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;


public class SharedPreferencesApp {
    public static SharedPreferencesApp sharedPreferencesApp;
    private SharedPreferences prefs;
    public SharedPreferencesApp(Context context) {
        prefs = context.getSharedPreferences("saved", MODE_PRIVATE);
    }

    public static SharedPreferencesApp getInstance(Context context){
        if(sharedPreferencesApp == null){
            sharedPreferencesApp = new SharedPreferencesApp(context);
        }
        return sharedPreferencesApp;
    }

    public void saveData(String key , String Data){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key,Data);
        editor.apply();
    }

    public void saveData(String key , Boolean Data){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key,Data);
        editor.apply();
    }

    public Boolean getBooleanData(String key){
        return prefs.getBoolean(key,false);
    }

    public String getStringData(String key){
        return prefs.getString(key,"");
    }

    public void clearData(){
        SharedPreferences.Editor editor =prefs.edit();
        editor.clear();
        editor.apply();
    }
}