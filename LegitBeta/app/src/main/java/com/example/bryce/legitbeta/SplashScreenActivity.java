package com.example.bryce.legitbeta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.Profile;

/**
 * Created by Bryce on 3/22/2016.
 */
public class SplashScreenActivity extends AppCompatActivity {

    public static String TAG = "SplashScreenActivity";
    public boolean active = true;
    private Thread splashScreenThread;
    public Profile profile;
    public static boolean firstTime = true;
    //Length of Timer for Splash Screen.
    protected int _time = 1500;

    boolean testingFirstTime = true;

    @Override
    public void onCreate(Bundle savedInstanceState){
        FacebookSdk.sdkInitialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        profile = Profile.getCurrentProfile();
        final boolean start = true;
        final String firstTime = Utils.getDefaults("isFirstTime",this);

        //Start A new Thread to increment timer
        splashScreenThread = new Thread(){
            @Override
            public void run(){
                try{
                    int waited = 0;
                    while(active && (waited < _time)){
                        sleep(100);
                        if(active){
                            //Increment timer until _time
                            waited += 100;
                        }
                    }
                }catch (InterruptedException e){
                    Log.e("Timer Exception", String.valueOf(e));
                    finish();
                } finally {
                    try{
                        if(testingFirstTime){
                            Log.e("Splash Screen Login", "Testing: Forcing User to new profile.");
                            Intent mNewLoginIntent = new Intent(SplashScreenActivity.this,NewLoginActivity.class);
                            startActivity(mNewLoginIntent);
                            finish();
                        }
                        else if(profile != null){
                            Log.e("Splash Screen Login", "User Logged in. Continuing to profile.");
                            Intent mToMain = new Intent(SplashScreenActivity.this,UserActivity.class);
                            startActivity(mToMain);
                            finish();
                        }
                        else if(profile == null && firstTime != null){
                            Log.e("Splash Screen Login", "User Recognized but not logged in. Continuing to Login.");
                            Intent mLoginIntent = new Intent(SplashScreenActivity.this,LoginActivity.class);
                            startActivity(mLoginIntent);
                            finish();
                        }
                        else{
                            Log.e("Splash Screen Login", "New User. Initiating Registration.");
                            Intent mNewLoginIntent = new Intent(SplashScreenActivity.this,NewLoginActivity.class);
                            startActivity(mNewLoginIntent);
                            finish();
                        }

                    }catch (Exception e2){
                        Log.e("Splash Screen Exception", "Exception " + String.valueOf(e2));
                    }
                }
            }
        };
        splashScreenThread.start();
    }

    //Handle on Back Button Press
    @Override
    public void onBackPressed(){
        splashScreenThread.interrupt();
        super.onBackPressed();
        Log.e("Splash Screen Error", "Back Button interrupt");
    }

}
