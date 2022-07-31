package com.quanao.hanghieu.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
        @SerializedName("email")
        private String email;
        @SerializedName("name")
        private String name;
        @SerializedName("address")
        private String address;
        @SerializedName("listorder")
        private List<CartItem> listorder;
        @SerializedName("orderdate")
        private String orderdate;
        @SerializedName("phone")
        private String phone;
        @SerializedName("offer")
        private String giamgia;

        public Cart(String email, String name, String address, List<CartItem> listorder, String orderdate, String phone) {
            this.email = email;
            this.name = name;
            this.address = address;
            this.listorder = listorder;
            this.orderdate = orderdate;
            this.phone = phone;
        }
        public  Cart(String email, String name, String address, List<CartItem> listorder, String orderdate, String phone,String giamgia) {
            this.email = email;
            this.name = name;
            this.address = address;
            this.listorder = listorder;
            this.orderdate = orderdate;
            this.phone = phone;
            this.giamgia = giamgia;
        }

        public Cart() {
        }

        public void setListorder(List<CartItem> listorder) {
            this.listorder = listorder;
        }

        public String getGiamgia() {
            return giamgia;
        }

        public void setGiamgia(String giamgia) {
            this.giamgia = giamgia;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<CartItem> getListorder() {
            return listorder;
        }

        public void setListorder(ArrayList<CartItem> listorder) {
            this.listorder = listorder;
        }

        public String getOrderdate() {
            return orderdate;
        }

        public void setOrderdate(String orderdate) {
            this.orderdate = orderdate;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
}
