package com.quanao.hanghieu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.data.Cart;
import com.quanao.hanghieu.data.CartItem;
import com.quanao.hanghieu.data.Product;
import com.quanao.hanghieu.data.Utils;
import com.quanao.hanghieu.fragment.CartViewFragment;
import com.quanao.hanghieu.fragment.GridItemFragment;
import com.quanao.hanghieu.service.APIHeroku;
import com.quanao.hanghieu.service.HerokuService;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListHorizontalAdapter extends BaseAdapter {
    private List<CartItem> listData;
    private LayoutInflater layoutInflater;

    String date;
    private Context context;


    public ListHorizontalAdapter(Context aContext,  List<CartItem> listData) {
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
        ListHorizontalAdapter.ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_h_layout, null);
            holder = new ListHorizontalAdapter.ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.imageView_h);
            holder.textName = (TextView) view.findViewById(R.id.itemTextName_h);
            holder.textPrice = (TextView) view.findViewById(R.id.itemTextPrice_h);
            holder.textQuantity = (TextView) view.findViewById(R.id.itemQuantity_h);
            holder.txtHuy  = (TextView) view.findViewById(R.id.btnHuy);
            view.setTag(holder);
        } else {
            holder = (ListHorizontalAdapter.ViewHolder) view.getTag();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        date = sdf.format(new Date());

        CartItem product = this.listData.get(i);
        holder.textName.setText(product.getName());
        holder.textPrice.setText("$"+product.getPrice());
        holder.textQuantity.setText("quantity: "+product.getQuantity());
        Picasso.get().load(product.getImageView()).into(holder.imageView);
        holder.txtHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utils.removeItemCart(i);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                CartViewFragment myFragment = new CartViewFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, myFragment).addToBackStack(null).commit();

            }
        });



        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textName;
        TextView textPrice;
        TextView textQuantity;
        TextView txtHuy;

    }
    public void clear() {
        listData.clear();
    }
    public void addAll(ArrayList arrayList){
        listData = arrayList;
    }
}
