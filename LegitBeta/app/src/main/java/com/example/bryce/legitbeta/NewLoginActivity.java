package com.example.bryce.legitbeta;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Bryce on 3/22/2016.
 */
public class NewLoginActivity extends AppCompatActivity {


    private String TAG = "NewLogin";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static final String PROPERTY_REG_ID = "registration_id";
    public static final String APP_VERSION = "appversion";
    private ArrayList<Gallery> imageList;
    private Location LocationFinder;
    CallbackManager callbackManager;
    LoginButton btn_facebookLogin;
    Intent startUpSettings;
    TextView txt_privatePolicy;

    Calendar calendar = Calendar.getInstance();
    Calendar userInputCalendar;
    int currentDate = calendar.DATE;


    Intent under18;



    ViewPager viewPager;
        //private ViewPageAdapter mViewPageAdapter;
        private ArrayList<Integer> listOfItems;


    private double mLongitude,mLatitude;


    private LinearLayout dotsLayout;
    private int dotCount;
    private TextView[] dots;

    @Override
    public void onCreate(Bundle savedInstanceState){
        FacebookSdk.sdkInitialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        try{
            @SuppressWarnings("rawtypes")
                Class strictModeClass = Class.forName("android.os.StrictMode");
                Class strictModeThreadPolicyClass = Class.forName("android.os.StrictMode$ThreadPolicy");
                Object laxPolicy = strictModeThreadPolicyClass.getField("LAX").get(null);

            @SuppressWarnings("unchecked")
                Method method_setThreadPolicy = strictModeClass.getMethod("setThreadPolicy", strictModeThreadPolicyClass);
                method_setThreadPolicy.invoke(null,laxPolicy);


        }
        catch (Exception devToolError){
            Log.e("Developer Tool Error",String.valueOf(devToolError));
        }

        initData();


        try {
            //Register callback handeler Facebook Login
            btn_facebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {



                //Handel Result On Succes,Cencel and Failure.
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.e("Facebook Login:", "Login Successful");
                    startActivity(startUpSettings);
                    //Add Private Policy Here

                    //startActivity(startUpSettings);
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException error) {
                    Log.e("Facebook Login","Error");
                }
            });
       }
        catch(Exception E){
           Log.e("Login Button Error","Exception" + String.valueOf(E));


       }


    }


    //Initialize all variables.
    public void initData(){

        //Inizitlize callback manager
        callbackManager = CallbackManager.Factory.create();

        //Inizilize Views
        btn_facebookLogin = (LoginButton) findViewById(R.id.btn_facebookLogin);
        txt_privatePolicy = (TextView) findViewById(R.id.txt_privatePolicy);

        //Create Intents
        startUpSettings = new Intent(this,AboutYouActivity.class);




    }





    ViewPager.OnPageChangeListener viewPagerChangeListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageSelected(int position){

            for(int i = 0; i< dotCount; i++){
                dots[i].setTextColor(Color.GRAY);

            }
            dots[position].setTextColor(Color.BLUE);
        }

        @Override
        public void onPageScrollStateChanged(int arg0){

        }

        @Override
        public  void onPageScrolled(int arg0, float arg1, int arg2){

        }
    };


    //Handles Result from the CallBack Manager
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    public void registerGCM(){

    }


}
