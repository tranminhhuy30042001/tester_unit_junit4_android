package com.quanao.hanghieu.service;

import com.quanao.hanghieu.data.Cart;
import com.quanao.hanghieu.data.CartItem;
import com.quanao.hanghieu.data.Product;
import com.quanao.hanghieu.data.User;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface HerokuService {
    @GET("api/product")
    Call<List<Product>> all();

    @GET("api/users")
    Call<List<User>> allUser();

    @POST("api/users")
    Call<User> createUser(@Body User user);

    @GET("api/product/{name}")
    Call<Product> get(@Path("name") String product);

    @POST("api/product")
    Call<Product> create(@Body Product user);
    @POST("api/product/{name}")
    Call<Product> update(@Path("id") String id,@Body Product productList);


    @GET("api/cart")
    Call<List<Cart>> allCart();

    @GET("api/cart/{name}")
    Call<Cart> getCart(@Path("name") String s);

    @GET("api/cart/")
    Call<List<Cart>> getCart();

    @POST("api/cart")
    Call<Cart> createCart(@Body Cart user);

    @POST("api/sold")
    Call<Cart> sold(@Body Cart user);
    @GET("api/sold")
    Call<List<Cart>> callSold();

    @DELETE("api/cart/{name}")
    Call<List<Cart>> deleteItem(@Path("name") String name);


    @POST("api/cart/{name}")
    Call<Cart> createCart2(@Path("name") String path);
}
