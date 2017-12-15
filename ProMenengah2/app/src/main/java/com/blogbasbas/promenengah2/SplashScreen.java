package com.blogbasbas.promenengah2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.blogbasbas.promenengah2.activity.LoginActivity;
import com.blogbasbas.promenengah2.helper.SessionManager;

public class SplashScreen extends SessionManager {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    sessionManager.checkLogin();
                    finish();
            }
        },2000);

    }
}
