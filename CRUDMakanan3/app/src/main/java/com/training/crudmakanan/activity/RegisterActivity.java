package com.training.crudmakanan.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.training.crudmakanan.R;
import com.training.crudmakanan.helper.MyConstant;
import com.training.crudmakanan.helper.MyFunction;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.training.crudmakanan.helper.MyConstant.STORAGE_PERMISSION_CODE;

public class RegisterActivity extends MyFunction {

    @BindView(R.id.edtnama)
    EditText edtnama;
    @BindView(R.id.edtalamat)
    EditText edtalamat;
    @BindView(R.id.edtnotelp)
    EditText edtnotelp;
    @BindView(R.id.spinjenkel)
    Spinner spinjenkel;
    @BindView(R.id.edtusername)
    EditText edtusername;
    @BindView(R.id.edtpassword)
    EditText edtpassword;
    @BindView(R.id.edtpasswordconfirm)
    EditText edtpasswordconfirm;
    @BindView(R.id.regAdmin)
    RadioButton regAdmin;
    @BindView(R.id.regUserbiasa)
    RadioButton regUserbiasa;
    @BindView(R.id.buttonChoose)
    Button buttonChoose;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.btnregister)
    Button btnregister;
    String jenkel[]={"Laki - Laki ","Wanita"};
    String strpath,strusername,strpassword,strnama,stralamat,strnohp,strpassconfirm,strlevel,strjenkel;
    boolean fc=false;
    Uri filepath;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        if (regAdmin.isChecked()){
            strlevel="admin";
        }else {
            strlevel="user biasa";
        }
        requestStoragePermision();

        ArrayAdapter adapter = new ArrayAdapter
                (c,android.R.layout.simple_spinner_item,jenkel);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinjenkel.setAdapter(adapter);
        spinjenkel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            strjenkel=jenkel[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @OnClick({R.id.buttonChoose, R.id.btnregister,R.id.regAdmin, R.id.regUserbiasa, })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regAdmin:
                strlevel="admin";
                break;
            case R.id.regUserbiasa:
                strlevel="user biasa";
                break;

            case R.id.buttonChoose:
                ShowFileChooser(MyConstant.REQ_FILE_CHOOSE);
                fc=true;
                break;
            case R.id.btnregister:
                strnama =edtnama.getText().toString();
                stralamat =edtalamat.getText().toString();
                strnohp =edtnotelp.getText().toString();
                strusername =edtusername.getText().toString();
                strpassword =edtpassword.getText().toString();
                strpassconfirm =edtpasswordconfirm.getText().toString();
                if (TextUtils.isEmpty(strnama)){
                    edtnama.setError("nama tidak boleh kosong");
                }else if(TextUtils.isEmpty(stralamat)){
                    edtalamat.setError("alamt tidak boleh kosong");
                }else if (TextUtils.isEmpty(strnohp)){
                    edtnotelp.setError("no hp tidak boleh kosong");
                }else if(TextUtils.isEmpty(strusername)){
                    edtusername.setError("username tidak boleh kosong");
                }else if (TextUtils.isEmpty(strpassword)){
                    edtpassword.setError("password tidak boleh kosong");
                }else if (strpassword.length()<6){
                    edtpassword.setError("password minimal 6 karakter");
                }else if (TextUtils.isEmpty(strpassconfirm)){
                    edtpasswordconfirm.setError("password confirm tidak boleh kosong");
                }else if (!strpassword.equals(strpassconfirm)){
                    edtpasswordconfirm.setError("password tidak sama");
                }else if (fc==false){
                    myToast("silahkan pilih file upload terlebih dahulu");
                }else{
                    simpandata();

                }

                break;
        }

    }

    private void simpandata() {
        strpath=getDataPath(filepath);

        //upload
        try {
            new MultipartUploadRequest(c,MyConstant.REGISTER)
            .addFileToUpload(strpath,"image")
            .addParameter("vsnama",strnama)
            .addParameter("vsalamat",stralamat)
            .addParameter("vsnotelp",strnohp)
                    .addParameter("vsusername",strusername)
                    .addParameter("vspassword",strpassword)
                    .addParameter("vslevel",strlevel)
                    .addParameter("vsjenkel",strjenkel)
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload();
            akseclass(Loginctivity.class);
            myToast("anda berhasil register");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getDataPath(Uri filepath) {
        Cursor cursor = getContentResolver().query(filepath, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
     if (requestCode==MyConstant.REQ_FILE_CHOOSE&&resultCode==RESULT_OK
             &&data!=null&&data.getData()!=null){
         filepath=data.getData();
         try {
             bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                imageView.setImageBitmap(bitmap);
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
}
