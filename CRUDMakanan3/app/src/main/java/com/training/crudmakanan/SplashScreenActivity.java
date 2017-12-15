package com.training.crudmakanan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.training.crudmakanan.activity.Loginctivity;
import com.training.crudmakanan.helper.SessionManager;

public class SplashScreenActivity extends SessionManager {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, Loginctivity.class));
                sessionManager.checkLogin();
            }
        },4000);
    }
}
