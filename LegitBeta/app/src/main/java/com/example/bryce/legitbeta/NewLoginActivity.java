package com.example.bryce.legitbeta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.lang.reflect.Method;
import java.util.ArrayList;

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



    ViewPager viewPager;
        private ViewPageAdapter mViewPageAdapter;


    private double mLongitude,mLatitude;


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
                    Log.e("Facebook Login:","Login Successful");
                    startActivity(startUpSettings);
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
        startUpSettings = new Intent(this,StartupSettingsActivity.class);




    }

    public class ViewPageAdapter extends PagerAdapter {

        private LayoutInflater mlayoutInflater;
        private ArrayList<Integer> items;

        public ViewPageAdapter(ArrayList<Integer> listOfItems){
            this.items = listOfItems;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position){

            mlayoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = mlayoutInflater.inflate(R.id.galleryitem,container,false);

            ImageView galleryImageView = (ImageView) view.findViewById(R.id.galleryImageView);
            galleryImageView.setImageResource(listOfItems.get(position));

            ((ViewPager) container).addView(view);

            return  view;

        }

        @Override
        public int getCount(){
            return items.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj){
            return view == ((View)obj);
        }


        public void onDestroyItem(ViewGroup container, int position, Object object){

            View view = (View)object;
            ((ViewPager)container).removeView(view);

        }

    }


    //Handles Result from the CallBack Manager
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
