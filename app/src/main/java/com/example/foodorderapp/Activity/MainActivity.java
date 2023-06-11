package com.example.foodorderapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodorderapp.Adapter.FoodListAdapter;
import com.example.foodorderapp.Domain.FoodDomain;
import com.example.foodorderapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerview();
    }

    private void initRecyclerview() {
        ArrayList<FoodDomain> items = new ArrayList<>();
        items.add(new FoodDomain("Cheese Burger", "Satisfy your cravings with our juicy Cheese Burger. \n" +
                "Made with 100% Angus beef patty and topped with\n" +
                " melted cheddar cheese, fresh lettuce, tomato, and\n" +
                " our  secret sauce, this classic burger will leave you\n" +
                " wanting more. Served with crispy fries and a drink,\n" +
                " it's the perfect meal for any occasion.", "fast_1", 15, 20, 120, 4));
        items.add(new FoodDomain("Pizza Peperoni", "Get a taste of Italy with our delicious Pepperoni Pizza. Made with freshly rolled dough, zesty tomato sauce, mozzarella cheese, and topped with spicy pepperoni slices, this is sure to be a crowd-pleaser. Perfectly baked in a wood-fire oven, it's the perfect choice for a quick lunch or a family dinner."
                , "fast_2", 10, 25, 200, 5));
        items.add(new FoodDomain("Vegetable Pizza", "Looking for a healthier option? Try our Vegetable Pizza, made with variety of fresh veggies such as bell peppers, onions, mushrooms, olives, and tomatoes. Topped with mozzarella cheese and a tangy tomato sauce, this pizza is full of flavor and goodness. Perfect for vegetarians and anyone who wants to add more their diet."
                , "fast_3", 13, 30, 100, 4.5));

        foodList = findViewById(R.id.main_foodList);
        foodList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapter = new FoodListAdapter(items);
        foodList.setAdapter(adapter);
    }
}