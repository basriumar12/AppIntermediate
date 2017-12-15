package com.blogbasbas.promenengah1.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.blogbasbas.promenengah1.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Camera extends AppCompatActivity {

    @BindView(R.id.cameraMulai)
    Button cameraMulai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.cameraMulai)
    public void onViewClicked() {
        File mediaFile =
                new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/myvideo.mp4");

         //video
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        //camera
        //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri videoUri = Uri.fromFile(mediaFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
        startActivityForResult(intent,5);
    }
}
