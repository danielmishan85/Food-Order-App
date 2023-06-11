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
import com.example.foodorderapp.R;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {
    ArrayList<FoodDomain> items;
    Context context;

    public FoodListAdapter(ArrayList<FoodDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_food_list, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.time.setText(items.get(position).getTime() + " min");
        holder.score.setText("" + items.get(position).getScore());
        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, time, score;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.viewholder_titleTxt);
            time = itemView.findViewById(R.id.viewholder_timeTxt);
            score = itemView.findViewById(R.id.viewholder_scoreTxt);
            pic = itemView.findViewById(R.id.viewholder_pic);
        }
    }
}
