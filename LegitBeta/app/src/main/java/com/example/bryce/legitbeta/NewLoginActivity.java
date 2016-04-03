package com.example.bryce.legitbeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by Bryce on 3/22/2016.
 */
public class NewLoginActivity extends AppCompatActivity {


    //LoginButton btn_facebookLogin;
    CallbackManager callbackManager;


    @Override
    public void onCreate(Bundle savedInstanceState){
        FacebookSdk.sdkInitialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        //Inizilize Views
        LoginButton btn_facebookLogin = (LoginButton) findViewById(R.id.btn_facebookLogin);

        TextView txt_privatePolicy = (TextView) findViewById(R.id.txt_privatePolicy);


        //Create Intents
        final Intent startUpSettings = new Intent(this,StartupSettingsActivity.class);


        //Inizitlize callback manager
        callbackManager = CallbackManager.Factory.create();


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
    //Handles Result from the CallBack Manager
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
