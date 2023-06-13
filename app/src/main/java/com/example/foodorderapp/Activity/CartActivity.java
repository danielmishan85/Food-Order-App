package com.example.foodorderapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodorderapp.Adapter.CartListAdapter;
import com.example.foodorderapp.Helper.ChangeNumberItemsListener;
import com.example.foodorderapp.Helper.ManagementCart;
import com.example.foodorderapp.R;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView cartList;
    private ManagementCart managementCart;
    private TextView totalFee, taxTxt, delivery, total, empty;
    private double tax;
    private ScrollView scrollView;
    private ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        
        managementCart = new ManagementCart(this);
        
        initView();
        initList();
        calculateCart();
        setVariable();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.cart_homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cart_cartBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(CartActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(CartActivity.this, CartActivity.class)));
    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> finish());
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cartList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });

        cartList.setAdapter(adapter);

        if (managementCart.getListCart().isEmpty()) {
            empty.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart() {
        double percentTax = 0.02;
        double delivery = 10;
        tax = Math.round(managementCart.getTotalFee() * percentTax * 100.0) / 100.0;

        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100.0) / 100.0;

        totalFee.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        this.delivery.setText("$" + delivery);
        this.total.setText("$" + total);
    }

    private void initView() {
        totalFee = findViewById(R.id.cart_totalFreeTxt);
        taxTxt = findViewById(R.id.cart_taxTxt);
        delivery = findViewById(R.id.cart_deliveryTxt);
        total = findViewById(R.id.cart_totalTxt);
        cartList = findViewById(R.id.cart_cartList);
        scrollView = findViewById(R.id.cart_scrollView);
        backBtn = findViewById(R.id.cart_backBtn);
        empty = findViewById(R.id.cart_emptyTxt);
    }
}