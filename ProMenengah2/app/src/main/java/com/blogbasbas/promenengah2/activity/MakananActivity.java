package com.blogbasbas.promenengah2.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogbasbas.promenengah2.R;
import com.blogbasbas.promenengah2.adapter.CustomListMakanan;
import com.blogbasbas.promenengah2.helper.MyConstatnt;
import com.blogbasbas.promenengah2.helper.SessionManager;
import com.blogbasbas.promenengah2.model.DataMakanan;
import com.blogbasbas.promenengah2.model.ModelMakanan;
import com.blogbasbas.promenengah2.model.Respon;
import com.blogbasbas.promenengah2.network.RestAPI;
import com.squareup.picasso.Picasso;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.blogbasbas.promenengah2.helper.MyConstatnt.STORAGE_PERMISSION_CODE;


public class MakananActivity extends SessionManager implements SwipeRefreshLayout.OnRefreshListener , CustomListMakanan.OnItemClicked {
    RecyclerView.LayoutManager layoutManager;
    RecyclerView listmakanan;
    ProgressDialog progressDialog;
    List<DataMakanan> listdatamakanan;
    CustomListMakanan customListMakanan;
    Dialog dialog, dialog2;
    EditText edtnamamakanan, edtidmakanan;
    Button btnuploadmakanan, btninsert, btnresert, btnupdate, btndelete;
    String strnamamakanan, stridmakanan;
    Uri filepath;
    String path;
    Bitmap bitmap;
    ImageView imgupload;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listmakanan = (RecyclerView)findViewById(R.id.listmakanan);
        layoutManager= new LinearLayoutManager(this);
        listmakanan.setLayoutManager(layoutManager);

        requestStoragePermission();
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshlayout);
        refreshLayout.setOnRefreshListener(this);
        getdataMakanan();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.tambahdata) {
            dialog = new Dialog(c);
            dialog.setContentView(R.layout.tambahmakanan);
            dialog.setTitle("data makanan");
            dialog.setCanceledOnTouchOutside(false);
            //inisialisasi
            edtnamamakanan = (EditText) dialog.findViewById(R.id.edtnamamakanan);
            btnuploadmakanan = (Button) dialog.findViewById(R.id.btnuploadmakanan);
            imgupload = (ImageView) dialog.findViewById(R.id.imgupload);

            btninsert = (Button) dialog.findViewById(R.id.btninsert);
            btnresert = (Button) dialog.findViewById(R.id.btnreset);
            btnuploadmakanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showFileChooser(MyConstatnt.REQ_FILE_CHOOSE);
                }
            });
            btnresert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edtnamamakanan.setText("");
                }
            });
            btninsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    strnamamakanan = edtnamamakanan.getText().toString();
                    if (TextUtils.isEmpty(strnamamakanan)) {
                        edtnamamakanan.setError("nama makanan tidak boleh kosong");
                        edtnamamakanan.requestFocus();
                    } else {
                        insertdatamakanan();
                        dialog.dismiss();
                    }
                }
            });

            dialog.show();
        }else{
            sessionManager.logout();

        }

        return super.onOptionsItemSelected(item);
    }


    private void insertdatamakanan() {

        try{
        //mengambil nama path dari gambar
        path = getPath(filepath);
        }catch (Exception e){
            e.printStackTrace();
        }
        String iduser =sessionManager.getIdUser();


            //upload
        try {
            new MultipartUploadRequest(c, MyConstatnt.UPLOAD_MAKANAN)
                    .addFileToUpload(path, "image")
                    .addParameter("vsiduser", iduser)
                    .addParameter("vsnamamakanan", strnamamakanan)
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    //proses upload
                    .startUpload();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                    getdataMakanan();
                }
            }, 1000);
            getdataMakanan();
            //   aksesinten(LoginActivity.class);
            //   pesan(""+path+"\n"+strnfi+"\n"+strnama+"\n"+stralamat+"\n"+strnotelp+"\n"+strusername+"\n"+strpass+"\n"+strlevel+"\n"+strjenkel);
        } catch (Exception e) {
            e.printStackTrace();
            myToast(e.getMessage());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MyConstatnt.REQ_FILE_CHOOSE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filepath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imgupload.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }


    private void getdataMakanan() {
        progressDialog = ProgressDialog.show(c, "informasi", "proses get data");

        //inisialisasi retrofit
        Retrofit r = new Retrofit.Builder()
                .baseUrl(MyConstatnt.BASE_URL)
                //Gsonconverter untuk parsing json dari web service menjadi java object
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       // Intent intent =getIntent();
     //   String iduser =intent.getStringExtra("iu");
     //   sessionManager.createSession(iduser);
       String iduser =sessionManager.getIdUser();
        Toast.makeText(MakananActivity.this, "id"+iduser, Toast.LENGTH_SHORT).show();
        //untuk mendapatkan callback /balikan berupa model java object dari retrofit
        RestAPI api = r.create(RestAPI.class);
        Call<ModelMakanan> result = api.getmakanan(iduser);
        result.enqueue(new Callback<ModelMakanan>() {
            @Override
            public void onResponse(Call<ModelMakanan> call, Response<ModelMakanan> response) {
                progressDialog.dismiss();
                listdatamakanan = new ArrayList<DataMakanan>();
                listdatamakanan = response.body().getDataMakanan();
                final String[] namamakanan = new String[listdatamakanan.size()];
                final String[] fotomakanan = new String[listdatamakanan.size()];
                final String[] idmakanan = new String[listdatamakanan.size()];

                for (int i = 0; i < listdatamakanan.size(); i++) {
                    namamakanan[i] = listdatamakanan.get(i).getMakanan().toString();
                    fotomakanan[i] = listdatamakanan.get(i).getFotoMakanan().toString();
                    idmakanan[i] = listdatamakanan.get(i).getIdMakanan().toString();
                    stridmakanan = idmakanan[i];


                }

                //
                // (fotomakanan[1]);
                customListMakanan = new CustomListMakanan(c, listdatamakanan);
                listmakanan.setAdapter(customListMakanan);
                customListMakanan.setOnClick(MakananActivity.this);

            }

            @Override
            public void onFailure(Call<ModelMakanan> call, Throwable t) {
                progressDialog.dismiss();
                myToast(t.getMessage());
            }
        });
    }

    private void hapusDataMakanan() {
        Retrofit r = new Retrofit.Builder()
                .baseUrl(MyConstatnt.BASE_URL)
                //Gsonconverter untuk parsing json dari web service menjadi java object
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestAPI api = r.create(RestAPI.class);
        Call<Respon> result = api.deletedata(stridmakanan);
        result.enqueue(new Callback<Respon>() {
            @Override
            public void onResponse(Call<Respon> call, Response<Respon> response) {
                String result = response.body().getResult();
                String msg = response.body().getMsg();
                if (result.equals("1")) {
                    myToast(msg);
                    dialog2.dismiss();
                    getdataMakanan();
                } else {
                    myToast(msg);
                    dialog2.setCancelable(false);
                }
            }

            @Override
            public void onFailure(Call<Respon> call, Throwable t) {
                myToast("kesalahan koneksi data" + t.getMessage());
                dialog2.setCancelable(false);

            }
        });
    }

    public String getPath(Uri filepath) {
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
    public void onRefresh() {
        getdataMakanan();
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(int position) {
        dialog2 = new Dialog(c);
        dialog2.setTitle("Data Makanan");
        dialog2.setCanceledOnTouchOutside(false);
        dialog2.setContentView(R.layout.updatemakanan);
        edtnamamakanan = (EditText) dialog2.findViewById(R.id.edtnamamakanan);
        edtidmakanan = (EditText) dialog2.findViewById(R.id.edtidmakanan);

        btnuploadmakanan = (Button) dialog2.findViewById(R.id.btnuploadmakanan);
        imgupload = (ImageView) dialog2.findViewById(R.id.imgupload);
        btndelete = (Button) dialog2.findViewById(R.id.btndelete);
        btnupdate = (Button) dialog2.findViewById(R.id.btnupdate);
        edtnamamakanan.setText(listdatamakanan.get(position).getMakanan());
        edtidmakanan.setText(listdatamakanan.get(position).getIdMakanan());

        Picasso.with(c).load(listdatamakanan.get(position).getFotoMakanan().toString()).error(R.drawable.noimage).into(imgupload);
        btnuploadmakanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser(MyConstatnt.REQ_FILE_CHOOSE);
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stridmakanan = edtidmakanan.getText().toString();
                hapusDataMakanan();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                strnamamakanan = edtnamamakanan.getText().toString();
                stridmakanan = edtidmakanan.getText().toString();
                if (TextUtils.isEmpty(strnamamakanan)) {
                    edtnamamakanan.setError("nama tidak boleh kosong");
                    edtnamamakanan.requestFocus();
                } else {
                    path = getPath(filepath);

                    //upload
                    try {
                        new MultipartUploadRequest(c,MyConstatnt.UPLOAD_UPDATE_MAKANAN)
                                .addFileToUpload(path, "image")
                                //      .addParameter("name",strnfi)
                                .addParameter("vsnamamakanan", strnamamakanan)
                                .addParameter("vsidmakanan", stridmakanan)
                                .setNotificationConfig(new UploadNotificationConfig())
                                .setMaxRetries(2)
                                //proses upload
                                .startUpload();
                        getdataMakanan();
                        //   aksesinten(LoginActivity.class);
                        //             pesan("" + path + "\n" + strnamamakanan + "\n" + stridmakanan);
                    } catch (Exception e) {
                        e.printStackTrace();
                        myToast(e.getMessage());
                    }
                    dialog2.dismiss();
                }
            }
        });
        dialog2.show();
    }
}
