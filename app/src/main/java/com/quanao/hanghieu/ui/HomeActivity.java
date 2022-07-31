package com.quanao.hanghieu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.quanao.hanghieu.data.Utils;
import com.quanao.hanghieu.fragment.CartViewFragment;
import com.quanao.hanghieu.R;
import com.quanao.hanghieu.databinding.ActivityHomeBinding;
import com.quanao.hanghieu.fragment.CategoriesFragment;
import com.quanao.hanghieu.fragment.GridItemFragment;
import com.quanao.hanghieu.fragment.SearchFragment;
import com.quanao.hanghieu.service.APIHeroku;

public class HomeActivity extends AppCompatActivity {

    SearchView searchView;
    SearchFragment searchFragment;
    CategoriesFragment categoriesFragment;
    CartViewFragment cartFragment;
    GridItemFragment listItemFragment;
    ActivityHomeBinding binding;

    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        APIHeroku.getHerokuService();




        searchFragment = new SearchFragment();
        categoriesFragment = new CategoriesFragment();
        listItemFragment = new GridItemFragment();
        cartFragment = new CartViewFragment();

        reloadMainFragment(categoriesFragment);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId())
            {
                case R.id.homeFragment:

                    reloadMainFragment(categoriesFragment);
                    break;
                case R.id.cartViewFragment:
                    reloadMainFragment(cartFragment);
                    break;
                case R.id.qrActivity:
                    Intent intent = new Intent(HomeActivity.this,Barcode.class);
                    startActivity(intent);
                    break;

            }
            return true;
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        searchView = findViewById(R.id.search_view);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search,menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reloadMainFragment(searchFragment);
            }
        });
       searchView.setOnCloseListener(new SearchView.OnCloseListener() {
           @Override
           public boolean onClose() {
               reloadMainFragment(categoriesFragment );
               return false;
           }
       });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchFragment.searchFurniture(s);
                if(s.isEmpty())
                {

                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    public void reloadMainFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit();

    }
}