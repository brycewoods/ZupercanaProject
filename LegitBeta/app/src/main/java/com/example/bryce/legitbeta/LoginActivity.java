package com.example.bryce.legitbeta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by Bryce on 3/22/2016.
 */
public class LoginActivity extends AppCompatActivity {


    LoginButton btn_facebookLogin;
    CallbackManager callbackManager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }


}
