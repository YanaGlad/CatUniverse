package com.example.catuniverse.gameSupport.aboutGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.databaseHelpers.Cat;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter {

    private List<Cat> catList;
    private List<Bitmap> icons;
    private Context context;

    CatAdapter(List<Cat> catList, Context context) {
        this.catList = catList;

        icons = new ArrayList<>();
        icons.add(BitmapLoader.grayIcon);
        icons.add(BitmapLoader.orangeIcon);
        icons.add(BitmapLoader.greenAlienCatIcon);
        icons.add(BitmapLoader.shadowCatIcon);
        icons.add(BitmapLoader.mainCoonCatIcon);
        icons.add(BitmapLoader.grayIcon);

        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cat, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CatViewHolder holder = (CatViewHolder) viewHolder;
        holder.setCatNameText(catList.get(position).getName());
        holder.setCatDescription(
                context,
                String.valueOf(catList.get(position).getPower()),
                String.valueOf(catList.get(position).getDelay()),
                String.valueOf(catList.get(position).getHealth()),
                String.valueOf(catList.get(position).getPrice())
        );

        Glide.with(holder.catIcon)
                .load(icons.get(position))
                .into(holder.catIcon);
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }
}
