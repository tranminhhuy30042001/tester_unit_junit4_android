package com.quanao.hanghieu.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.adapter.ListItemAdapter;
import com.quanao.hanghieu.data.Product;
import com.quanao.hanghieu.data.ProductItem;
import com.quanao.hanghieu.data.Utils;
import com.quanao.hanghieu.service.APIHeroku;
import com.quanao.hanghieu.service.HerokuService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    List<ProductItem> arrayList;
    List<ProductItem> tmpList;

    ListView listView;
    ListItemAdapter adapter;

    Context context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        tmpList = new ArrayList<>();
        context = getContext();
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = getView().findViewById(R.id.search_listView);

        setView();

    }
    public void searchFurniture(String newText) {
        ArrayList<ProductItem> tmp = new ArrayList<>();


                for(ProductItem furniture : Utils.productItems){
                    if(furniture.getName().toLowerCase().contains(newText.toLowerCase())){
                        tmp.add(furniture);

                    }
                }


                if(tmp.size() > 0){

                    adapter = new ListItemAdapter(context, tmp);
                    listView.setAdapter(adapter);
                    listView.setVisibility(View.VISIBLE);
                }
                if(newText.isEmpty()){
                    getView();
                }
            }







    void setView(){
        HerokuService service = APIHeroku.herokuService;

        Call<List<Product>> createCall = service.all();
        createCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {


                arrayList.clear();
                arrayList = Utils.productItems;

                adapter = new ListItemAdapter(context, arrayList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}