package com.blogbasbas.promenengah1.activity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blogbasbas.promenengah1.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Ttd extends AppCompatActivity implements TextToSpeech.OnInitListener {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.btnSpeech)
    Button btnSpeech;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttd);
        ButterKnife.bind(this);

        //class tts
        tts = new TextToSpeech(this,this);
        if (tts == null){
            Toast.makeText(this, "perangkat ini tidak tersedia tts", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btnSpeech)
    public void onViewClicked() {
        bicara();
        Toast.makeText(this, "berhasil di klik", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS){
            Locale indo = new Locale("en", "EN");
            int result = tts.setLanguage(indo);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                     result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "bahasa tidak mendukung", Toast.LENGTH_SHORT).show();
            }else{
                btnSpeech.setEnabled(true);
                bicara();
            }
        }
    }

    private void bicara() {
        String tulisan = editText.getText().toString();
        tts.speak(tulisan,TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
