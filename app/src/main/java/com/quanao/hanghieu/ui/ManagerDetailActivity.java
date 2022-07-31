package com.quanao.hanghieu.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.adapter.ListHorizontalAdapter;
import com.quanao.hanghieu.adapter.ListItemAdapterManager;
import com.quanao.hanghieu.data.Cart;
import com.quanao.hanghieu.data.CartItem;
import com.quanao.hanghieu.data.ProductItem;
import com.quanao.hanghieu.data.Utils;
import com.quanao.hanghieu.service.APIHeroku;
import com.quanao.hanghieu.service.HerokuService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerDetailActivity extends AppCompatActivity {

    ListView lstView;
    Cart list;
    List<CartItem> lstCartItem;
    ListItemAdapterManager adapter;
    HerokuService service;
    Button btnAccept;
    TextView txtTotal,txtSale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_detail);

        btnAccept = findViewById(R.id.btnAccept);

        if(getIntent().getExtras().get("state").equals("1"))
        {
            btnAccept.setVisibility(View.VISIBLE);
        }
        else {
            btnAccept.setVisibility(View.INVISIBLE);
        }



        list = new Cart();
        lstView = findViewById(R.id.listDetailManager);
        txtTotal = findViewById(R.id.txtTotalManager);
        txtSale = findViewById(R.id.txtSaleManager);



        list =(Cart) getIntent().getSerializableExtra("cart");
        lstCartItem = list.getListorder();

        adapter = new ListItemAdapterManager(ManagerDetailActivity.this,lstCartItem);
        lstView.setAdapter(adapter);
        float total = 0;
        for(CartItem item:lstCartItem)
        {
            total+= Float.parseFloat(item.getPrice()) * Float.parseFloat(item.getQuantity());
        }
        Float giam = 0f;
        try {
             giam = Float.parseFloat(list.getGiamgia());
        }catch (Exception e)
        {

        }

        txtTotal.setText("Total: " + (total-giam) +" $");
        txtSale.setText("Sale:" + list.getGiamgia() +" $");

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                muaHang();
                HerokuService service = APIHeroku.herokuService;
                Call<List<Cart>> createCall = service.deleteItem(list.getAddress());
                createCall.enqueue(new Callback<List<Cart>>() {
                    @Override
                    public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {

                    }

                    @Override
                    public void onFailure(Call<List<Cart>> call, Throwable t) {

                    }
                });

                Intent intent = new Intent(ManagerDetailActivity.this,ManagerActivity.class);
                startActivity(intent);

                finish();


            }
        });





    }
    void muaHang() {


        service = APIHeroku.herokuService;

        Cart cart = new Cart();
        cart = list;

        Call<Cart> createCall = service.sold(cart);
        createCall.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call2, Response<Cart> response2) {

            }

            @Override
            public void onFailure(Call<Cart> call2, Throwable t) {

            }
        });





        finish();






    }


}