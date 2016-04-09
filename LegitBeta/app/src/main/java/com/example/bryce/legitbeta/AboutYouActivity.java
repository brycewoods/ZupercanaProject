package com.example.bryce.legitbeta;


import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class AboutYouActivity extends PreferenceActivity  {

    private static String KEY_SUMMARY_FUN = "me_fun_answers";
    ListPreference pref_Fun;
    ListPreference pref_Smart;
    ListPreference pref_Creative;
    ListPreference pref_Character;
    ListPreference pref_Appearance;



    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.toolbar_nooptions);

        addPreferencesFromResource(R.xml.pref_about_you);

        //Instantiate Preferences
        pref_Fun = (ListPreference) findPreference("me_fun_answers");
        pref_Smart = (ListPreference) findPreference("me_smart_answers");
        pref_Creative = (ListPreference) findPreference("me_creative_answers");
        pref_Character = (ListPreference) findPreference("me_character_answers");
        pref_Appearance = (ListPreference) findPreference("me_appearance_answers");


        //Register all on preference change listeners
        //Potentially easier to do this
        pref_Fun.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary(String.valueOf(newValue));
                return true;
            }
        });

        pref_Smart.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary(String.valueOf(newValue));
                return true;
            }
        });

        pref_Character.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary(String.valueOf(newValue));
                return true;
            }
        });

        pref_Creative.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary(String.valueOf(newValue));
                return true;
            }
        });

        pref_Appearance.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary(String.valueOf(newValue));
                return true;
            }
        });

    }




    @Override
    public void onResume(){
        super.onResume();



    }


    public void toDreamMatch(View view){


        try{
            //Start next activity if all options have been selected.
            if(     pref_Smart.getSummary() .equals("Nothing Selected") ||
                    pref_Fun.getSummary() .equals("Nothing Selected") ||
                    pref_Creative.getSummary() .equals("Nothing Selected") ||
                    pref_Character.getSummary() .equals("Nothing Selected") ||
                    pref_Appearance.getSummary() .equals("Nothing Selected")) {


                    //Upload description choices to user profile.
                    Utils.setDefaults("des_Fun", String.valueOf(pref_Fun.getValue()),this);
                    Utils.setDefaults("des_Smart", String.valueOf(pref_Fun.getValue()),this);
                    Utils.setDefaults("des_Creative", String.valueOf(pref_Fun.getValue()),this);
                    Utils.setDefaults("des_Character", String.valueOf(pref_Fun.getValue()),this);
                    Utils.setDefaults("des_Appearance", String.valueOf(pref_Appearance.getValue()),this);

                    Toast.makeText(this,"Please Ensure You Have Selected Each Option!", Toast.LENGTH_SHORT).show();
            }
            else {
                Log.e("About You Activity","Update Complete");
                Intent toDreamMatchDes = new Intent(this, YourMatchActivity.class);
                startActivity(toDreamMatchDes);
            }

        }
        catch(Exception complete){
            Log.e("About You Activiy", "Error on next " + String.valueOf(complete));
        }

    }






}
