package com.example.catuniverse.gameSupport.aboutGame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.databaseHelpers.Cat;
import com.example.catuniverse.gameSupport.graphics.CatIcon;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter {

    private List<Cat> catList;
    private List<CatIcon> icons;
    private Context context;

    public CatIcon connect(Cat cat, List<CatIcon> icons) {
        for (int i = 0; i < icons.size(); i++) {
            for (int j = 0; j < icons.size(); j++) {
                if (cat.getKey().equals(icons.get(j).getKey()))
                    return icons.get(j);
            }
        }
        return null;
    }

    CatAdapter(List<Cat> catList, Context context) {
        this.catList = catList;

        icons = new ArrayList<>();

        icons.add(new CatIcon("gray", BitmapLoader.grayIcon));
        icons.add(new CatIcon("orange", BitmapLoader.orangeIcon));
        icons.add(new CatIcon("greenAlien", BitmapLoader.greenAlienCatIcon));
        icons.add(new CatIcon("shadow", BitmapLoader.shadowCatIcon));
        icons.add(new CatIcon("mainCoon", BitmapLoader.mainCoonCatIcon));
        icons.add(new CatIcon("bobtail", BitmapLoader.bobtailCatIcon));

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
        if (position < icons.size()) {
            holder.setCatNameText(catList.get(position).getName());
            holder.setCatDescription(
                    context,
                    String.valueOf(catList.get(position).getPower()),
                    String.valueOf(catList.get(position).getDelay()),
                    String.valueOf(catList.get(position).getHealth()),
                    String.valueOf(catList.get(position).getPrice())
            );

            Glide.with(holder.catIcon)
                    .load(connect(catList.get(position), icons).getIcon())
                    .into(holder.catIcon);
        }
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }
}
