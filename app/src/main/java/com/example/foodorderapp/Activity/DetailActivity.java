package com.example.foodorderapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodorderapp.Domain.FoodDomain;
import com.example.foodorderapp.Helper.ManagementCart;
import com.example.foodorderapp.R;



public class DetailActivity extends AppCompatActivity {
    private Button addToCartBtn;
    private TextView title, price, description, numberOrder, time, score, energy, plusBtn, minusBtn;
    private ImageView pic;
    private FoodDomain object;
    private int amount = 1;
    private ManagementCart managementCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        managementCart = new ManagementCart(DetailActivity.this);

        initView();
        getBundle();
    }

    private void getBundle() {
        object = (FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(pic);

        title.setText(object.getTitle());
        price.setText("$" + object.getPrice());
        description.setText(object.getDescription());
        numberOrder.setText(""+ amount);
        time.setText(object.getTime() + " min");
        score.setText(object.getScore() + "");
        energy.setText(object.getEnergy() + " Cal");
        addToCartBtn.setText("Add to cart - $" + Math.round(amount * object.getPrice()));

        plusBtn.setOnClickListener(v -> {
            amount += 1;
            numberOrder.setText("" + amount);
            addToCartBtn.setText("Add to cart - $" + Math.round(amount * object.getPrice()));
        });
        minusBtn.setOnClickListener(v -> {
            amount -= 1;
            numberOrder.setText("" + amount);
            addToCartBtn.setText("Add to cart - $" + Math.round(amount * object.getPrice()));
        });
        addToCartBtn.setOnClickListener(v -> {
            Log.d("tag", object.getTitle());
            object.setNumberInCart(amount);
            managementCart.insertFood(object);
        });
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.detail_addToCart_btn);
        title = findViewById(R.id.detail_titleTxt);
        price = findViewById(R.id.detail_priceTxt);
        description = findViewById(R.id.detail_description);
        numberOrder = findViewById(R.id.detail_numberItemTxt);
        time = findViewById(R.id.detail_timeTxt);
        score = findViewById(R.id.detail_scoreTxt);
        energy = findViewById(R.id.detail_energyTxt);
        plusBtn = findViewById(R.id.detail_plusCartBtn);
        minusBtn = findViewById(R.id.detail_minusCartBtn);
        pic = findViewById(R.id.detail_foodPic);
    }
}