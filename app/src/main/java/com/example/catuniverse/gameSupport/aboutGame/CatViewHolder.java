package com.example.catuniverse.gameSupport.aboutGame;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catuniverse.R;

 class CatViewHolder extends RecyclerView.ViewHolder {
    AppCompatImageView catIcon;
    private AppCompatTextView catName, power, delay, health, price;

    CatViewHolder(@NonNull View itemView) {
        super(itemView);
        catIcon = itemView.findViewById(R.id.cat_icon);
        catName = itemView.findViewById(R.id.cat_name);
        power = itemView.findViewById(R.id.power);
        delay = itemView.findViewById(R.id.delay);
        health = itemView.findViewById(R.id.health);
        price = itemView.findViewById(R.id.price);

    }

    void setCatNameText(String text) {
        this.catName.setText(text);
    }

    void setCatDescription(Context context, String power, String delay, String health, String price) {
        this.power.setText(context.getString(R.string.power) + power);
        this.delay.setText(context.getString(R.string.delay) + delay);
        this.health.setText(context.getString(R.string.health) + health);
        this.price.setText(context.getString(R.string.price) + price);
    }
}

