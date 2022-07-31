package com.quanao.hanghieu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.quanao.hanghieu.R;
import com.quanao.hanghieu.adapter.ListHorizontalAdapter;
import com.quanao.hanghieu.data.Cart;
import com.quanao.hanghieu.data.CartItem;
import com.quanao.hanghieu.data.Product;
import com.quanao.hanghieu.data.ProductItem;
import com.quanao.hanghieu.data.Utils;
import com.quanao.hanghieu.service.APIHeroku;
import com.quanao.hanghieu.service.HerokuService;
import com.squareup.picasso.Picasso;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    ImageView imgView;

    TextView addToCart;
    TextView txtName;
    ProductItem product;
    TextView txtDescription,txtPrice,txtQuantity;
    Button btnDiv,btnPlus;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        APIHeroku.getHerokuService();
        anhXa();


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        date = sdf.format(new Date());

        product =(ProductItem) getIntent().getSerializableExtra("products");




        Picasso.get().load(product.getImageView()).into(imgView);
        txtName.setText(product.getName());
        int k = Integer.parseInt(product.getPrice()) * 25;
        txtPrice.setText(k+" VND");
        txtDescription.setText(product.getDescription());

        xuliBtn();
       addToCart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(Integer.parseInt(product.getQuantity()) - Integer.parseInt(txtQuantity.getText().toString()) >= 0)
               {
                   CartItem cartItem = new CartItem(product.getId(), product.getName(), product.getDescription(), product.getImageView(),txtQuantity.getText().toString(), product.getPrice());
                   Utils.addToCart(cartItem);
                   Toast.makeText(DetailActivity.this, "thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();

               }
               else {
                   Toast.makeText(DetailActivity.this, "đã hết hàng", Toast.LENGTH_SHORT).show();
               }
           }
       });



    }
    void xuliBtn()
    {

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(txtQuantity.getText().toString());
                if(num > 1)
                {
                    num = num -1;
                    txtQuantity.setText(num+"");
                }
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(txtQuantity.getText().toString());

                num = num +1;
                txtQuantity.setText(num+"");

            }
        });
    }

    void anhXa(){
        imgView = (ImageView) findViewById(R.id.imgDetail);
        addToCart = (TextView) findViewById(R.id.add_to_cart);
        txtName = (TextView) findViewById(R.id.detailName);
        txtPrice = (TextView) findViewById(R.id.detailPrice);
        txtQuantity = (TextView) findViewById(R.id.txtQuantity);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        txtDescription = (TextView) findViewById(R.id.description_detail);

    }

}