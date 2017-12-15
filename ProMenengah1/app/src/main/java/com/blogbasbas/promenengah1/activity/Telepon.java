package com.blogbasbas.promenengah1.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blogbasbas.promenengah1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Telepon extends AppCompatActivity {

    @BindView(R.id.btnCall)
    Button btnCall;
    @BindView(R.id.btnDialCall)
    Button btnDialCall;
    @BindView(R.id.btntListContact)
    Button btntListContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telepon);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnCall, R.id.btnDialCall, R.id.btntListContact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCall:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }


                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:085398038073")));
                break;
            case R.id.btnDialCall:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:085398038073")));
                break;
            case R.id.btntListContact:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                startActivityForResult(intent,1);
                break;
        }
    }
}
