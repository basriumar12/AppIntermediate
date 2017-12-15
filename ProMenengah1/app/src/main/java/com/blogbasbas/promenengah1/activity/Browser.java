package com.blogbasbas.promenengah1.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.blogbasbas.promenengah1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Browser extends AppCompatActivity {

    @BindView(R.id.btnBrowser)
    Button btnBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBrowser)
    public void onViewClicked() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com")));
    }
}
