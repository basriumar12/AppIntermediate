package com.blogbasbas.promen5.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.blogbasbas.promen5.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlacePickerActivity extends AppCompatActivity {

    @BindView(R.id.bt_ppicker)
    Button btPpicker;
    @BindView(R.id.tv_place_id)
    TextView tvPlaceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_picker);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_ppicker)
    public void onViewClicked() {
        //membuat intent atau tampilan baru di dalam activity yg sama
        PlacePicker.IntentBuilder builder =new PlacePicker.IntentBuilder();
        try{
            //untuk menjalankan place picker
            startActivityForResult(builder.build(PlacePickerActivity.this),2);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }
//menjalankan intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2 && resultCode==RESULT_OK){
            Place place = PlacePicker.getPlace(data,this);

           //mengambil informasi
            String informasilengkap = String.format("place : %s \n"+ "Alamat : %s \n"+
            "LATLON %s \n",place.getName(),place.getAddress(),place.getLatLng().latitude
            +",+"+place.getLatLng().longitude);

            tvPlaceId.setText(informasilengkap);
        }

    }
}
