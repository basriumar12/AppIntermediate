package com.blogbasbas.promenengah1.activity;


import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blogbasbas.promenengah1.R;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioRec extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.btnPlay)
    Button btnPlay;
    @BindView(R.id.btnRecordStop)
    Button btnRecordStop;
    MediaRecorder recorder;
    String output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_rec);
        ButterKnife.bind(this);
        btnPlay.setEnabled(false);
    }

    @OnClick({R.id.btnPlay, R.id.btnRecordStop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPlay:
                MediaPlayer player = new MediaPlayer();
                try{
                    player.setDataSource(output);
                    player.prepare();
                    player.start();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                //btnRecordStop.setText("Stop");
                break;
            case R.id.btnRecordStop:
                if(btnRecordStop.getText().toString().equalsIgnoreCase("RECORD")){
                    try{
                        recorder = new MediaRecorder();
                        output = Environment.getExternalStorageDirectory().getAbsolutePath()+"/REC"+curentdate()+".3gp";

                        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                        recorder.setOutputFile(output);
                        recorder.prepare();

                    }catch (IOException e){
                        e.printStackTrace();
                    }

                    recorder.start();
                    btnRecordStop.setText("STOP");
                } else if (btnRecordStop.getText().toString().equalsIgnoreCase("STOP")){
                    recorder.stop();
                    recorder.release();
                    recorder=null;
                    btnPlay.setEnabled(true);
                    btnRecordStop.setText("RECORD");

                }

                break;
        }
    }

    private String curentdate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();
        return dateFormat.format(date);

    }


}
