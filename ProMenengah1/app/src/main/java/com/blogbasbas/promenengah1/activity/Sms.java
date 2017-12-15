package com.blogbasbas.promenengah1.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogbasbas.promenengah1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Sms extends AppCompatActivity {

    @BindView(R.id.edtnohp)
    EditText edtnohp;
    @BindView(R.id.edtmessage)
    EditText edtmessage;
    @BindView(R.id.btnsmsintent)
    Button btnsmsintent;
    @BindView(R.id.btnkirimsms)
    Button btnkirimsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnsmsintent, R.id.btnkirimsms})
    public void onViewClicked(View view) {
        String nohp = edtnohp.getText().toString();
        String pesan = edtmessage.getText().toString();
        switch (view.getId()) {
            case R.id.btnsmsintent:
                //validasi inputan no hp dan pesan
                if(TextUtils.isEmpty(nohp)){
                    edtnohp.setError("no hp tidak bisa kosong");
                    edtnohp.requestFocus();

                } else if (TextUtils.isEmpty(pesan)){
                    edtmessage.setError("pesan tidak bisa kosong");
                    edtmessage.requestFocus();

                } else {
                    //kirim data
                    Intent kirimdata = new Intent(Intent.ACTION_SENDTO);
                    kirimdata.setData(Uri.parse("smsto:"+Uri.encode(nohp)));
                    kirimdata.putExtra("sms_body",pesan);
                    startActivity(kirimdata);
                }
                break;
            case R.id.btnkirimsms:
                //validasi inputan no hp dan pesan
                if(TextUtils.isEmpty(nohp)){
                    edtnohp.setError("no hp tidak bisa kosong");
                    edtnohp.requestFocus();

                } else if (TextUtils.isEmpty(pesan)){
                    edtmessage.setError("pesan tidak bisa kosong");
                    edtmessage.requestFocus();

                } else {

                    //analisa error, pakai try catch  (e ad/ objek sebenarnya)
                    try{
                      SmsManager manager = SmsManager.getDefault();
                      manager.sendTextMessage(nohp,null,pesan,null,null);
                      Toast.makeText(this,"pesan terkirim",Toast.LENGTH_SHORT).show();

                  }catch (Exception e){
                      e.printStackTrace();
                      Toast.makeText(this, "gagal kirim pesan"+e.getMessage(), Toast.LENGTH_SHORT).show();

                  }
                }
                break;
        }
    }
}
