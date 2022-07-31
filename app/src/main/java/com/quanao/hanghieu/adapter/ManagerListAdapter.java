package com.quanao.hanghieu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.data.Cart;
import com.quanao.hanghieu.data.CartItem;
import com.quanao.hanghieu.data.ProductItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ManagerListAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Cart> listData;
    private Context context;

    public ManagerListAdapter(Context aContext, List<Cart> listData) {
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
        ManagerListAdapter.ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_manager, null);
            holder = new ManagerListAdapter.ViewHolder();

            holder.textName = (TextView) view.findViewById(R.id.managerName);
            holder.textPhone = (TextView) view.findViewById(R.id.managerPhone);
            holder.textDate = (TextView) view.findViewById(R.id.managerDate);
            holder.textAddress = (TextView) view.findViewById(R.id.managerAddress);


            view.setTag(holder);
        } else {
            holder = (ManagerListAdapter.ViewHolder) view.getTag();
        }

        Cart product = this.listData.get(i);
        holder.textName.setText(product.getName());
        holder.textPhone.setText(product.getPhone());
        holder.textDate.setText(product.getOrderdate());
        holder.textAddress.setText(product.getAddress());






        return view;
    }

    static class ViewHolder {

        TextView textName;
        TextView textPhone;
TextView textAddress;
        TextView textDate;


    }
    public void clear() {
        listData.clear();
    }
    public void addAll(ArrayList arrayList){
        listData = arrayList;
    }
}
