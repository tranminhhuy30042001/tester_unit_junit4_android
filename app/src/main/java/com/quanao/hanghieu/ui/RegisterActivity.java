package com.quanao.hanghieu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.data.User;
import com.quanao.hanghieu.data.Utils;
import com.quanao.hanghieu.service.APIHeroku;
import com.quanao.hanghieu.service.HerokuService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText username,password,email,repass;
    Button btnRegister,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        APIHeroku.getHerokuService();

        anhXa();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean chk = check();
                if(chk)
                {
                    HerokuService service = APIHeroku.herokuService;
                    User u = new User(username.getText().toString(),password.getText().toString(),email.getText().toString());
                    Call<User> createCall = service.createUser(u);
                    createCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                    finish();
                }
            }
        });


    }
    void anhXa()
    {
        email = (EditText) findViewById(R.id.email_register);
        username = (EditText) findViewById(R.id.username_register);
        password = (EditText) findViewById(R.id.password_register);
        repass = (EditText) findViewById(R.id.rePass_register);
        btnBack = (Button) findViewById(R.id.back_register);
        btnRegister = (Button) findViewById(R.id.register_register);
    }
    boolean check()
    {
        if(Utils.isEmpty(repass.getText().toString()))
        {
            Toast.makeText(this, "mật khẩu xác thực sai", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Utils.isEmpty(password.getText().toString())){
            Toast.makeText(this, "mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Utils.isEmpty(email.getText().toString()) && Utils.checkEmailForValidity(email.getText().toString())){
            Toast.makeText(this, "email không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Utils.isEmpty(username.getText().toString())){
            Toast.makeText(this, "username không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


}