package com.quanao.hanghieu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.quanao.hanghieu.R;
import com.quanao.hanghieu.fragment.GridItemFragment;
import com.quanao.hanghieu.fragment.SearchFragment;
import com.quanao.hanghieu.service.APIHeroku;


public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    GridItemFragment listItemFragment;
    SearchFragment searchFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchFragment = new SearchFragment();

        APIHeroku.getHerokuService();

        listItemFragment = new GridItemFragment();
        reloadMainFragment(listItemFragment);


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
                reloadMainFragment(listItemFragment );
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
                .replace(R.id.fragment_home, fragment)
                .commit();

    }
}