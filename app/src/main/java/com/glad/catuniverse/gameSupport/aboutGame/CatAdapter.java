package com.glad.catuniverse.gameSupport.aboutGame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.catuniverse.R;
import com.glad.catuniverse.gameSupport.BitmapLoader;
import com.glad.catuniverse.gameSupport.databaseHelpers.Cat;
import com.glad.catuniverse.gameSupport.graphics.CatIcon;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter {

    private final List<Cat> catList;
    private final List<CatIcon> icons;
    private final Context context;

    public CatIcon connect(Cat cat, List<CatIcon> icons) {
        for (int i = 0; i < icons.size(); i++)
            for (int j = 0; j < icons.size(); j++)
                if (cat.getKey().equals(icons.get(j).getKey()))
                    return icons.get(j);
        return null;
    }

    CatAdapter(List<Cat> catList, Context context) {
        this.catList = catList;

        icons = new ArrayList<>();

        icons.add(new CatIcon(context.getString(R.string.cat_gray), BitmapLoader.grayIcon));
        icons.add(new CatIcon(context.getString(R.string.cat_orange), BitmapLoader.orangeIcon));
        icons.add(new CatIcon(context.getString(R.string.cat_green_alien), BitmapLoader.greenAlienCatIcon));
        icons.add(new CatIcon(context.getString(R.string.cat_shadow), BitmapLoader.shadowCatIcon));
        icons.add(new CatIcon(context.getString(R.string.cat_main_coon), BitmapLoader.mainCoonCatIcon));
        icons.add(new CatIcon(context.getString(R.string.cat_bob_tail), BitmapLoader.bobtailCatIcon));
        icons.add(new CatIcon(context.getString(R.string.cat_red_alien), BitmapLoader.redAlienCatIcon));

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
