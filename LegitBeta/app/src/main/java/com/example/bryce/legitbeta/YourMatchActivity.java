package com.example.bryce.legitbeta;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
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
public class YourMatchActivity extends AppCompatPreferenceActivity {

    ListPreference pref_Interests;
    ListPreference pref_Smart;
    ListPreference pref_Personality;
    ListPreference pref_Appearance;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_your_match);
        addPreferencesFromResource(R.xml.pref_dream_match);

        pref_Interests = (ListPreference) findPreference("match_interests");
        pref_Smart = (ListPreference) findPreference("match_smart");
        pref_Personality = (ListPreference) findPreference("match_personality");
        pref_Appearance = (ListPreference) findPreference("match_appearance");

        //Register all on preference change listeners
        pref_Interests.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
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

        pref_Personality.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
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


    public void toUserProfile(View view){


        try{
            //Start next activity.
            if(pref_Interests.getSummary() .equals("Nothing Selected") ||
                    pref_Smart.getSummary() .equals("Nothing Selected") ||
                    pref_Personality.getSummary() .equals("Nothing Selected") ||
                    pref_Appearance.getSummary() .equals("Nothing Selected")) {

                //Upload description choices to user profile.
                Utils.setDefaults("des_Match_Interests", String.valueOf(pref_Interests.getValue()),this);
                Utils.setDefaults("des_Match_Smart", String.valueOf(pref_Smart.getValue()),this);
                Utils.setDefaults("des_Match_Personality", String.valueOf(pref_Personality.getValue()),this);
                Utils.setDefaults("des_Appearance", String.valueOf(pref_Appearance.getValue()),this);

                Toast.makeText(this, "Please Ensure You Have Selected Each Option!", Toast.LENGTH_SHORT).show();

            }
            else {
                Log.e("Dream Match Activity", "Update Complete");
                Intent toUserProfile = new Intent(this, DiscoverySettings.class);
                startActivity(toUserProfile);
            }

        }
        catch(Exception complete){
            Log.e("Dream Match Activity", "Error on next " + String.valueOf(complete));
        }

    }


}
