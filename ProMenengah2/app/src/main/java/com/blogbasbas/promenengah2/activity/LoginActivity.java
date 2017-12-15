package com.blogbasbas.promenengah2.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.blogbasbas.promenengah2.R;
import com.blogbasbas.promenengah2.helper.MyConstatnt;
import com.blogbasbas.promenengah2.model.ModelUser;
import com.blogbasbas.promenengah2.helper.SessionManager;
import com.blogbasbas.promenengah2.network.RestAPI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends SessionManager {

    @BindView(R.id.regUsername)
    EditText regUsername;
    @BindView(R.id.regPass)
    EditText regPass;
    @BindView(R.id.regAdmin)
    RadioButton regAdmin;
    @BindView(R.id.regUserbiasa)
    RadioButton regUserbiasa;
    @BindView(R.id.regBtnLogin)
    Button regBtnLogin;
    @BindView(R.id.regBtnRegister)
    Button regBtnRegister;
    String strusername, strlevel, strpassword;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //cek user
        //akses level
        if(regAdmin.isChecked()){
            strlevel="admin";

        }else {
            strlevel="user biasa";
        }






    }

    @OnClick({R.id.regAdmin, R.id.regUserbiasa, R.id.regBtnLogin, R.id.regBtnRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regAdmin:
                
                strlevel="admin";
                break;
            case R.id.regUserbiasa:
                strlevel="user biasa";
                break;
            case R.id.regBtnLogin:
                strusername = regUsername.getText().toString();
                strpassword= regPass.getText().toString();
                
                if(TextUtils.isEmpty(strusername)){
                    regUsername.setError("username tidak bisa kosng");
                    regUsername.requestFocus();
                } else if (TextUtils.isEmpty(strpassword)) {
                    regPass.setError("opassword tidak bisa ksong");
                    regPass.requestFocus();
                }else if (strpassword.length() < 6){
                    regPass.setError("minimal pasword 6 karakter");
                    
                } else {
                    loginUser();
                    
                }
            
                
                break;
            case R.id.regBtnRegister:
                aksesKelas(RegisterActivity.class);
                break;
        }
    }

    private void loginUser() {
        final ProgressDialog dialog = ProgressDialog.show(c,"tunggu ya"," tunggu");


        //insialisasi retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyConstatnt.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestAPI api = retrofit.create(RestAPI.class);
        Call<ModelUser> userCall = api.loginuser(strusername,strpassword,strlevel);
        userCall.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                dialog.dismiss();
                String result= response.body().getResult();
                String message = response.body().getMsg();
                String iduser = response.body().getUser().getIdUser();
                if (result.equals("1")){
                    myToast(message);
                    aksesKelas(MakananActivity.class);
                    //session manager
                    sessionManager.createSession(strusername);
                    sessionManager.setIdUser(iduser);
                    finish();;
                }else {
                    myToast(message);

                }

            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                dialog.dismiss();
                myToast("informasi gagal "+t.getMessage());
            }
        });
    }
}
