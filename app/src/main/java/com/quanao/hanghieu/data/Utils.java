package com.quanao.hanghieu.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {

    Context context;
    public static String username;
    public static String giamgia = "";
    public static List<CartItem> cartItems = new ArrayList<>();
    public static List<ProductItem> productItems = new ArrayList<>();
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public static void addToProduct(Product product){
        ProductItem tmp = new ProductItem();
        tmp.id = product.id;
        tmp.description = product.description;;
        tmp.imageView = product.imageView;;
        tmp.name = product.name;
        tmp.price = product.price;
        tmp.quantity = product.quantity;
        tmp.type = product.type;
        productItems.add(tmp);
    }
    public static List<Product> convertItemToList(List<ProductItem> product){
        List<Product> nList = new ArrayList<>();
        for(ProductItem p: product)
        {
            Product tmp = new Product();
            tmp.id = p.id;
            tmp.description = p.description;;
            tmp.imageView = p.imageView;;
            tmp.name = p.name;
            tmp.price = p.price;
            tmp.quantity = p.quantity;
            tmp.type = p.type;
            nList.add(tmp);
        }
        return  nList;


    }



    public static void addToCart(CartItem cartItem){
        cartItems.add(cartItem);
    }

    public static void removeItemCart(int pos){
        cartItems.remove(pos);
    }



    public Utils(Context context) {
        this.context = context;



    }

    public Utils() {

    }


    public ArrayList<Product> getMockData() {
        ArrayList<Product> tmp = new ArrayList<>();


        tmp.add(new Product("1"));
        tmp.add(new Product("2"));
        tmp.add(new Product("3"));
        tmp.add(new Product("4"));
        tmp.add(new Product("1"));
        tmp.add(new Product("2"));
        tmp.add(new Product("3"));
        tmp.add(new Product("4"));


        return tmp;
    }

    public ArrayList<Categories> getMockDataCategories() {
        ArrayList<Product> arrayListAo = new ArrayList<>();
        ArrayList<Product> arrayListQuan = new ArrayList<>();
        ArrayList<Product> arrayListVay = new ArrayList<>();
        ArrayList<Product> arrayListDam = new ArrayList<>();
        ArrayList<Categories> tmp = new ArrayList<>();

        tmp.add(new Categories("Cà phê", arrayListAo, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_rilawqeYHweQAGUl9ohdEYkCoVoSgR4Yvg&usqp=CAU"));

        tmp.add(new Categories("Trà", arrayListQuan, "https://photo-cms-tpo.zadn.vn/w890/Uploaded/2022/rwbvhvobvvimsb/2021_11_15/tra-xanh-la-gi-11-3162.jpg"));

        tmp.add(new Categories("Bánh ngọt", arrayListVay, "https://kenh14cdn.com/thumb_w/660/203336854389633024/2021/12/26/photo-1-16405079289291083121287.jpg"));

        tmp.add(new Categories("Nước ngọt", arrayListDam, "https://sangphatwater.com/upload/images/n%C6%B0%E1%BB%9Bc%20ng%E1%BB%8Dt%20c%C3%B3%20ga.jpg"));


        return tmp;
    }


    public static boolean checkEmailForValidity(String email) {

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();

    }
    public static boolean isEmpty(String s) {
        return s.equals("");
    }



    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
