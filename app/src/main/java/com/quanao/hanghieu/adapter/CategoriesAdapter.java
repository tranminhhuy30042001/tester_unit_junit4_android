package com.quanao.hanghieu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.data.Categories;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoriesAdapter extends BaseAdapter {
    private ArrayList<Categories> girdData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CategoriesAdapter(Context aContext,  ArrayList<Categories> listData) {
        this.context = aContext;
        this.girdData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }
    public CategoriesAdapter() {

    }

    @Override
    public int getCount() {
        return girdData.size();
    }

    @Override
    public Object getItem(int position) {
        return girdData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CategoriesAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.categories_layout, null);
            holder = new CategoriesAdapter.ViewHolder();
            holder.imgView = (ImageView) convertView.findViewById(R.id.imageCateView);
            holder.textView = (TextView) convertView.findViewById(R.id.textCateView);

            convertView.setTag(holder);
        } else {
            holder = (CategoriesAdapter.ViewHolder) convertView.getTag();
        }

        Categories categories = this.girdData.get(position);
        holder.textView.setText(categories.getName());

        //int imageId = this.getMipmapResIdByName(categories.getImage());

        //holder.imgView.setImageResource(imageId);


        Picasso.get().load(categories.getImage()).into(holder.imgView);


        return convertView;
    }
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);

        return resID;
    }

    static class ViewHolder {
        ImageView imgView;
        TextView textView;
    }
}
