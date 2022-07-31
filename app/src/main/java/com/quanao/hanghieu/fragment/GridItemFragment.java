package com.quanao.hanghieu.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.adapter.ListItemAdapter;
import com.quanao.hanghieu.data.Product;
import com.quanao.hanghieu.data.ProductItem;
import com.quanao.hanghieu.data.Utils;
import com.quanao.hanghieu.service.APIHeroku;
import com.quanao.hanghieu.service.HerokuService;
import com.quanao.hanghieu.ui.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GridItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridItemFragment extends Fragment {


    GridView gistView;
    List<ProductItem> arrayList;
    ListItemAdapter adapter;
    Context context;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static GridItemFragment newInstance(int pos) {
        Bundle bundle = new Bundle();
        bundle.putInt("category", pos);

        GridItemFragment categoriesFragment = new GridItemFragment(Utils.productItems);
        categoriesFragment.setArguments(bundle);
        return categoriesFragment;
    }

    public GridItemFragment() {
        // Required empty public constructor
    }
    public GridItemFragment(List<ProductItem> productItems) {
        this.arrayList = productItems;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GridItemFragment newInstance(String param1, String param2) {
        GridItemFragment fragment = new GridItemFragment();
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
        context = getContext();
        return inflater.inflate(R.layout.fragment_list_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gistView = view.findViewById(R.id.listItemView);
        gistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), DetailActivity.class);

                intent.putExtra("products", arrayList.get(i));

                startActivity(intent);

            }
        });
        setView();
    }

    void setView() {


        Bundle bundle = getArguments();
        Log.e("TAG", "onCreate: "+Utils.productItems.size() );
        Log.e("TAG", "onCreate: "+arrayList.size() );
        int i = 0;
        if (bundle != null)
            i = bundle.getInt("category") + 1;

        List<ProductItem> tmp = arrayList;
        arrayList.clear();

        for (ProductItem p : tmp) {

            if (Integer.parseInt(p.getType()) == i)
                arrayList.add(p);
        }
        adapter = new ListItemAdapter(context, arrayList);
        gistView.setAdapter(adapter);
    }




}