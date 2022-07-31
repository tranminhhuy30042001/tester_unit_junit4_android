package com.quanao.hanghieu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.data.Product;

import com.quanao.hanghieu.data.ProductItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListItemAdapter extends BaseAdapter {

    private List<ProductItem> listData;
    private LayoutInflater layoutInflater;
    private Context context;


    public ListItemAdapter(Context aContext,  List<ProductItem> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_layout, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.imageView);
            holder.textName = (TextView) view.findViewById(R.id.itemTextName);
            holder.textPrice = (TextView) view.findViewById(R.id.itemTextPrice);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ProductItem product = this.listData.get(i);
        holder.textName.setText(product.getName());
        holder.textPrice.setText("$"+product.getPrice());

        Picasso.get().load(product.getImageView()).into(holder.imageView);



        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textName;
        TextView textPrice;

        TextView textDescription;

    }
    public void clear() {
        listData.clear();
    }
    public void addAll(ArrayList arrayList){
        listData = arrayList;
    }
}
