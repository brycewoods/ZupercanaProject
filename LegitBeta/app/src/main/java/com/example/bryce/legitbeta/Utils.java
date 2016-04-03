package com.example.bryce.legitbeta;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;

import java.util.Random;

/**
 * Created by Bryce on 3/22/2016.
 */
public class Utils {


    //Settings Getter
    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    //Settings saver
    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static LayoutInflater getInflater(Activity activity){
        LayoutInflater factory = activity.getLayoutInflater();

        return factory;
    }

    public static void updateUtilsPrefs(String key, String args, Activity activity){

        Utils.setDefaults(key,args,activity);

    }

    public static int randomNumberGenerator(int attr1 , int attr2){

        Random r = new Random();
        int result = r.nextInt(attr2-attr1)+attr1;

        return result;
    }




}