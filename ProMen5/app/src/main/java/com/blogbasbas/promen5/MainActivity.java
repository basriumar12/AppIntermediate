package com.blogbasbas.promen5;

import android.os.Bundle;
import android.view.View;

import com.blogbasbas.promen5.activity.ListMapActivity;
import com.blogbasbas.promen5.activity.MapsActivity;
import com.blogbasbas.promen5.activity.PlacePickerActivity;
import com.blogbasbas.promen5.helper.MyFunction;

public class MainActivity extends MyFunction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMap(View view) {
        aksesclass(MapsActivity.class);
    }

    public void onPlacePicker(View view) {

        aksesclass(PlacePickerActivity.class);
    }

    public void onListMap(View view) {
        aksesclass(ListMapActivity.class);
    }
}
