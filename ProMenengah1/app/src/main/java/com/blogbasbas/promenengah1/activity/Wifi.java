package com.blogbasbas.promenengah1.activity;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.blogbasbas.promenengah1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Wifi extends AppCompatActivity {

    @BindView(R.id.switch1)
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        ButterKnife.bind(this);
        switch1.setChecked(status());
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                wifiStatus(isChecked);
            }
        });
    }
    //mwthod cek sttus
    private void wifiStatus(boolean isChecked) {
        WifiManager manager = (WifiManager) getApplicationContext()
                .getSystemService(WIFI_SERVICE);
        if ((isChecked==true &&!manager.isWifiEnabled())){
            Toast.makeText(this, "wifi aktiv", Toast.LENGTH_SHORT).show();
            manager.setWifiEnabled(true);
        }else {
            Toast.makeText(this, "wifi tidak aktiv", Toast.LENGTH_SHORT).show();
            manager.setWifiEnabled(false);
        }
    }

    private boolean status() {

        WifiManager manager = (WifiManager) getApplicationContext()
                .getSystemService(WIFI_SERVICE);
    return manager.isWifiEnabled();
    }
}
