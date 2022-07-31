package com.quanao.hanghieu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.data.Cart;
import com.quanao.hanghieu.data.User;
import com.quanao.hanghieu.data.Utils;
import com.quanao.hanghieu.service.APIHeroku;
import com.quanao.hanghieu.service.HerokuService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    String TAG = "";
    TextView textView;
    EditText edtUsername,edtPassword;
    Button btnLogin,btnRegister,btnFinger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        APIHeroku.getHerokuService();

        textView = (TextView)findViewById(R.id.check_login);
        edtUsername = (EditText) findViewById(R.id.username_login);
        edtPassword = (EditText) findViewById(R.id.password_login);
        btnLogin = (Button) findViewById(R.id.login_login);
        btnRegister = (Button) findViewById(R.id.register_login);
        btnFinger = (Button) findViewById(R.id.fingerPrint);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                if(edtUsername.getText().toString() == "" || edtPassword.getText().toString()=="")
                {
                    Toast.makeText(LoginActivity.this, "không được để trống", Toast.LENGTH_SHORT).show();
                }
                else {
                    String user = edtUsername.getText().toString();
                    Utils.username = user;
                    String pass = edtPassword.getText().toString();
                    HerokuService service = APIHeroku.herokuService;
                    Call<List<User>> createCall = service.allUser();
                    createCall.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            boolean kt = false;
                            for(User i : response.body())
                            {
                                if(user.equals(i.getUsername())&&pass.equals(i.getPassword()))
                                {

                                    kt =true;
                                    Intent k = new Intent(LoginActivity.this,HomeActivity.class);
                                    textView.setText("");
                                    startActivity(k);
                                }
                            }
                            if(!kt) {
                                textView.setText("sai tk hoặc mật khẩu");
                                Toast.makeText(LoginActivity.this, "sai tk hoặc mật khẩu",Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {

                        }
                    });

                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        btnFinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,LoginFingerActivity.class);
                startActivity(i);
            }
        });
    }
}