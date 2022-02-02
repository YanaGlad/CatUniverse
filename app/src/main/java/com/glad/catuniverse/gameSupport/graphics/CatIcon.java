package com.glad.catuniverse.gameSupport.graphics;

import android.graphics.Bitmap;

//Иконка с изображением кота
public class CatIcon {
    private String key;
    private Bitmap icon;

    public CatIcon(String key, Bitmap icon) {
        this.key = key;
        this.icon = icon;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
}
