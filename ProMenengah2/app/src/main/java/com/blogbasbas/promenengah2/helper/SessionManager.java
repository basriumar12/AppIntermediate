package com.blogbasbas.promenengah2.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AlertDialog;

import com.blogbasbas.promenengah2.R;
import com.blogbasbas.promenengah2.activity.LoginActivity;
import com.blogbasbas.promenengah2.activity.MakananActivity;


public class SessionManager extends MyFunction {
    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    /*variable sharepreference*/
    SharedPreferences pref;

    public SharedPreferences.Editor editor;
    public SessionManager sessionManager;
    public Context context;

    /*mode share preference*/
    int mode = 0;

    /*nama dari share preference*/
    private static final String pref_name = "crudpref";

    /*kunci share preference*/
    private static final String is_login = "islogin";
    public static final String kunci_email = "keyemail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_main);
        context=SessionManager.this;
        sessionManager = new SessionManager(getApplicationContext());


    }
    public SessionManager(){

    }

    /*construktor*/
    public SessionManager(Context context) {
        /*mengakses class ini*/
        this.context = context;
        /*share preference dari class ini*/ /*(nama, mode)*/
        pref = context.getSharedPreferences(pref_name, mode);
        editor = pref.edit();
    }

    /*methode membuat session*/
    public void createSession(String email){
        /*login value menjadi true*/
        editor.putBoolean(is_login, true);
        /*memasukkan email ke dalam variable kunci email*/
        editor.putString(kunci_email, email);
        editor.commit();
    }
    public void setIdUser(String iduser) {
        editor.putBoolean(is_login, true);
        editor.putString("iduser", iduser);
        editor.commit();
    }

    public String getIdUser() {
        return pref.getString("iduser", "");
    }


    public void checkLogin(){
        /*jika is_login = false*/
        if (!this.islogin()){
            /*pergi ke loginactivity*/
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else {
            /*jika true, pergi ke mainactivity*/
            Intent i = new Intent(context, MakananActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    /*set is_login menjadi false*/
    public boolean islogin() {
        return pref.getBoolean(is_login, false);
    }


    public void logout(){

        /*hapus semua data dan kunci*/
        editor.clear();
        editor.commit();

        //gmail logout


        /*pergi ke loginactivity*/
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }




    public void keluar(){
        AlertDialog.Builder dialog =new AlertDialog.Builder(context);
        dialog.setTitle("infomasi");
        dialog.setMessage("Apakah anda yakin ingin keluar ? ");
        dialog.setPositiveButton("iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                System.exit(0);
                android.os.Process.killProcess(android.os.Process.myPid());

            }
        });
        dialog.setNegativeButton("tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();

    }


}
