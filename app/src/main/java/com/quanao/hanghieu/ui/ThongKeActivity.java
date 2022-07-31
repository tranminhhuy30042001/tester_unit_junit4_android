package com.quanao.hanghieu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.adapter.ManagerListAdapter;
import com.quanao.hanghieu.data.Cart;
import com.quanao.hanghieu.service.APIHeroku;
import com.quanao.hanghieu.service.HerokuService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongKeActivity extends AppCompatActivity {

    ListView listView;
    List<Cart> carts;
    ManagerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        carts = new ArrayList<>();

        listView = findViewById(R.id.lstThongKe);

        HerokuService service = APIHeroku.herokuService;
        Call<List<Cart>> createCall = service.callSold();
        createCall.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                carts = response.body();
                adapter = new ManagerListAdapter(ThongKeActivity.this,carts);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(ThongKeActivity.this,ManagerDetailActivity.class);
                intent.putExtra("cart",carts.get(i));
                intent.putExtra("state","0");
                startActivity(intent);

            }
        });
    }
}