package com.example.foodorderapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.foodorderapp.Domain.FoodDomain;
import com.example.foodorderapp.Helper.ChangeNumberItemsListener;
import com.example.foodorderapp.Helper.ManagementCart;
import com.example.foodorderapp.R;

import java.util.ArrayList;


public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    ArrayList<FoodDomain> foodListSelected;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<FoodDomain> foodListSelected, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.foodListSelected = foodListSelected;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(foodListSelected.get(position).getTitle());
        holder.price.setText("$" + foodListSelected.get(position).getPrice());
        holder.totalFee.setText("$" + Math.round(foodListSelected.get(position).getNumberInCart() * foodListSelected.get(position).getPrice()));
        holder.num.setText(String.valueOf(foodListSelected.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodListSelected.get(position).getPicUrl(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30, 30, 30, 30))
                .into(holder.pic);

        holder.plusBtn.setOnClickListener(v -> managementCart.plusNumberFood(foodListSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
                }));
        holder.minusBtn.setOnClickListener(v -> managementCart.minusNumberFood(foodListSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));
    }

    @Override
    public int getItemCount() {
        return foodListSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, price, totalFee, plusBtn, minusBtn, num;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cart_row_titleTxt);
            price = itemView.findViewById(R.id.cart_row_priceTxt);
            totalFee = itemView.findViewById(R.id.cart_row_feeTxt);
            plusBtn = itemView.findViewById(R.id.cart_row_plusBtn);
            minusBtn = itemView.findViewById(R.id.cart_row_minusBtn);
            num = itemView.findViewById(R.id.cart_row_numberItemTxt);
            pic = itemView.findViewById(R.id.cart_row_pic);
        }
    }
}
