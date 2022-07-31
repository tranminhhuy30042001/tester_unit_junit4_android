package com.quanao.hanghieu.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.adapter.ListHorizontalAdapter;
import com.quanao.hanghieu.data.CartItem;
import com.quanao.hanghieu.data.Utils;
import com.quanao.hanghieu.ui.ThanhToanActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartViewFragment extends Fragment {

    List<CartItem> arrayList;


    ListView listView;
    ListHorizontalAdapter adapter;
    Context context;

    Button btnThanhToan;
    TextView txtTotal;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartViewFragment newInstance(String param1, String param2) {
        CartViewFragment fragment = new CartViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        arrayList = new ArrayList<>();


        context = getContext();
        return inflater.inflate(R.layout.fragment_cart_view, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = getView().findViewById(R.id.cart_listView);
        txtTotal = getView().findViewById(R.id.txtTotal);
        btnThanhToan = getView().findViewById(R.id.btnThanhToan);
        setView();

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ThanhToanActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });


    }

    void setView() {


        float total = 0;
        arrayList.clear();

        arrayList = Utils.cartItems;
        for (CartItem cartItem : arrayList) {

            total += Float.parseFloat(cartItem.getPrice()) * Integer.parseInt(cartItem.getQuantity());
        }

        adapter = new ListHorizontalAdapter(context, arrayList);
        txtTotal.setText(total + "");
        listView.setAdapter(adapter);


    }


}