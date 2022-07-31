package com.quanao.hanghieu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.adapter.ListItemAdapter;
import com.quanao.hanghieu.data.ProductItem;
import com.quanao.hanghieu.data.Utils;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {

    GridView gistView;
    List<ProductItem> arrayList;
    ListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        arrayList = new ArrayList<>();
        gistView = findViewById(R.id.gridItemView);
        gistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ItemActivity.this, DetailActivity.class);

                intent.putExtra("products", arrayList.get(i));

                startActivity(intent);

            }
        });
        setView();

    }
    void setView() {
        int i = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            i = extras.getInt("category") + 1;

        }



        List<ProductItem> tmp = Utils.productItems;
        arrayList.clear();

        for (ProductItem p : tmp) {

            if (Integer.parseInt(p.getType()) == i)
                arrayList.add(p);
        }
        adapter = new ListItemAdapter(ItemActivity.this, arrayList);
        gistView.setAdapter(adapter);
    }
}