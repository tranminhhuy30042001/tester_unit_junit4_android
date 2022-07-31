package com.quanao.hanghieu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.data.Cart;
import com.quanao.hanghieu.data.CartItem;
import com.quanao.hanghieu.data.Product;
import com.quanao.hanghieu.data.ProductItem;
import com.quanao.hanghieu.data.Utils;
import com.quanao.hanghieu.service.APIHeroku;
import com.quanao.hanghieu.service.HerokuService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThanhToanActivity extends AppCompatActivity {

    EditText address, email, username, date, phone;
    Button btnBuy,btnGiamGia;
    HerokuService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        APIHeroku.getHerokuService();

        Date currentTime = Calendar.getInstance().getTime();
        anhxa();

        date.setText(currentTime.toString());
        username.setText(Utils.username);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check()) {
                    muaHang();
                    finish();
                }
            }
        });
        btnGiamGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ThanhToanActivity.this,Barcode.class);
                startActivity(i);

            }
        });


    }

    void anhxa() {
        address = (EditText) findViewById(R.id.address_thanhtoan);
        email = (EditText) findViewById(R.id.email_thanhtoan);
        username = (EditText) findViewById(R.id.username_thanhtoan);
        date = (EditText) findViewById(R.id.date_thanhtoan);
        phone = (EditText) findViewById(R.id.phone_thanhtoan);
        btnBuy = (Button) findViewById(R.id.buy_thanhtoan);
        btnGiamGia = (Button) findViewById(R.id.btnGiamGia);
    }

    boolean check() {
        if (address.getText().toString().equals("")) {
            Toast.makeText(this, "địa chỉ không để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    void muaHang() {


        service = APIHeroku.herokuService;



        List<CartItem> lstCart = Utils.cartItems;




        Cart cart = new Cart(email.getText().toString(), username.getText().toString(), address.getText().toString(), lstCart, date.getText().toString(), phone.getText().toString(),Utils.giamgia);


        Call<Cart> createCall = service.createCart(cart);
        createCall.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call2, Response<Cart> response2) {

            }

            @Override
            public void onFailure(Call<Cart> call2, Throwable t) {

            }
        });


        Utils.giamgia = "";
        Utils.cartItems.clear();


        finish();

        Intent intent = new Intent(ThanhToanActivity.this,HomeActivity.class);
        startActivity(intent);





    }


}