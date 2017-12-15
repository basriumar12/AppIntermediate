package com.blogbasbas.projectauthentication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogbasbas.projectauthentication.MainActivity;
import com.blogbasbas.projectauthentication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.logingoogle)
    Button logingoogle;
    @BindView(R.id.loginphone)
    Button loginphone;
    @BindView(R.id.btn_reset_password)
    Button btnResetPassword;
    @BindView(R.id.btn_signup)
    Button btnSignup;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    FirebaseUser user;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();
        listener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    if (user.isEmailVerified()){
                        Toast.makeText(LoginActivity.this, "selamat andra berhasil login", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "gagal login, versifikasi email terlebih dahulu", Toast.LENGTH_SHORT).show();
                        firebaseAuth.getInstance().signOut();
                        finish();
                    }

                }
            }
        };



    }

        @OnClick({R.id.btn_login, R.id.logingoogle,  R.id.loginphone, R.id.btn_reset_password, R.id.btn_signup})
        public void onViewClicked(View view) {
            String em=email.getText().toString();
            String pw=password.getText().toString();

            switch (view.getId()) {
                case R.id.btn_login:

                    if (TextUtils.isEmpty(em)){
                        email.setError("email harus di isi");

                    }else if (TextUtils.isEmpty(pw)){
                        password.setError("password harus di isi");
                    } else {
                        progressBar.setVisibility(View.VISIBLE);
                        auth.signInWithEmailAndPassword(em,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "gagal  "+task.getException(), Toast.LENGTH_SHORT).show();
                                }else {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });
                    }



                    break;
                case R.id.logingoogle:
                    startActivity(new Intent(this, GoogleSignInActivity.class));
                    break;

                case R.id.loginphone:
                    startActivity(new Intent(LoginActivity.this,LoginPhoneActivity.class));
                    break;
                case R.id.btn_reset_password:
                    break;
                case R.id.btn_signup:
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    break;
            }
        }


                //wajib ada untuk firebase
                @Override
                protected void onStart() {
                    super.onStart();

                    auth.addAuthStateListener(listener);
                }
                //wajib adad

                @Override
                protected void onStop() {
                    super.onStop();
                    if (listener!=null){
                        auth.removeAuthStateListener(listener);
                    }
                }

}
