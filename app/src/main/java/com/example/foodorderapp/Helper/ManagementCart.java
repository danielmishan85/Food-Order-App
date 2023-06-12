package com.example.foodorderapp.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.foodorderapp.Domain.FoodDomain;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodDomain item) {
        ArrayList<FoodDomain> foodList = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < foodList.size(); i++) {
            if(foodList.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }
        if(existAlready) {
            foodList.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            foodList.add(item);
        }
        tinyDB.putListObject("CartList", foodList);
        Toast.makeText(context, "Added to your cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }
}
