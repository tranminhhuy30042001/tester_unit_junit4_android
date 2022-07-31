package com.quanao.hanghieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.quanao.hanghieu.adapter.ListHorizontalAdapter;
import com.quanao.hanghieu.adapter.ListItemAdapter;
import com.quanao.hanghieu.data.Cart;
import com.quanao.hanghieu.data.CartItem;
import com.quanao.hanghieu.data.Product;
import com.quanao.hanghieu.data.Utils;
import com.quanao.hanghieu.service.APIHeroku;
import com.quanao.hanghieu.service.HerokuService;
import com.quanao.hanghieu.ui.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        RelativeLayout relativeLayout = findViewById(R.id.testLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable)relativeLayout.getBackground();

        animationDrawable.start();


        APIHeroku.getHerokuService();
        HerokuService service = APIHeroku.herokuService;
        Call<List<Product>> createCall = service.all();

        createCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                for(Product p : response.body())
                {
                    Utils.addToProduct(p);
                }

                Intent i = new Intent(TestActivity.this, LoginActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }

        });




    }
}