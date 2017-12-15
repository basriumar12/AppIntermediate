package com.blogbasbas.promenengah1.activity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blogbasbas.promenengah1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioManagerActivity extends AppCompatActivity {

    @BindView(R.id.btnring)
    Button btnring;
    @BindView(R.id.btnvibrate)
    Button btnvibrate;
    @BindView(R.id.btnsilent)
    Button btnsilent;
    AudioManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        ButterKnife.bind(this);
        manager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

    }

    @OnClick({R.id.btnring, R.id.btnvibrate, R.id.btnsilent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnring:
            manager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(this, "aktive mode ring", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnvibrate:
                manager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(this, "aktive mode vibrete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnsilent:
                manager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(this, "aktive mode silent", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
