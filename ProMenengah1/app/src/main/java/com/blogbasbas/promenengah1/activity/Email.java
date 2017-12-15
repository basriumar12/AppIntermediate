package com.blogbasbas.promenengah1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.blogbasbas.promenengah1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Email extends AppCompatActivity {

    @BindView(R.id.edtto)
    EditText edtto;
    @BindView(R.id.edtsubject)
    EditText edtsubject;
    @BindView(R.id.edtmessage)
    EditText edtmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String to = edtto.getText().toString();
        String s = edtsubject.getText().toString();
        String m = edtmessage.getText().toString();

        if (id==R.id.mn_send){
            if (TextUtils.isEmpty(to)){
                edtto.setError("to tidak bisa kosong");
            } else if (TextUtils.isEmpty(s)){
                edtsubject.setError("subject tidak bisa kosng ");

            }else if (TextUtils.isEmpty(m)){
                edtmessage.setError("message tidak bisa kosong");
            } else {
                Intent kirim = new Intent(Intent.ACTION_SEND);
                kirim.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                kirim.putExtra(Intent.EXTRA_SUBJECT,s);
                kirim.putExtra(Intent.EXTRA_TEXT,m);
                kirim.setType("message/rfc822");
                startActivity(Intent.createChooser(kirim,"silahkan pilih email client"));

            }
        }else {
            edtto.setText("");
            edtsubject.setText("");
            edtmessage.setText("");
        }

        return super.onOptionsItemSelected(item);
    }
}
