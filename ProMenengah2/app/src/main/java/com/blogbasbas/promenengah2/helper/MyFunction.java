package com.blogbasbas.promenengah2.helper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.blogbasbas.promenengah2.R;

public class MyFunction extends AppCompatActivity {
        public Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_function);

        c= MyFunction.this;
    }
    public void aksesKelas (Class kelaslanjutan){
        startActivity(new Intent(c, kelaslanjutan));
    }

    public void myToast (String isipesan){
        Toast.makeText(c, isipesan, Toast.LENGTH_SHORT).show();
    }


    //permission storage
    public void requestStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MyConstatnt.STORAGE_PERMISSION_CODE);


    }
    //memlih file gambar
    public void showFileChooser(int reqFileChoose) {

        //akses tombol choose
        Intent intentgalery = new Intent();
        intentgalery.setType("image/*");
        intentgalery.setAction(Intent.ACTION_GET_CONTENT);
        //intent ke pemilihan gambar di galaerry
        startActivityForResult(Intent.createChooser(intentgalery, "select Pictures"), reqFileChoose);

    }


}
