package com.example.catuniverse.gameSupport.gameTime;

import android.graphics.Bitmap;

import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.MainRunActivity;

public abstract class Clickable extends GameItem {
    private boolean clicked = false;
    private MainRunActivity mainRunActivity;
    private Bitmap bitmap;
    private int objX, objY;

    public Clickable(MainRunActivity mainRunActivity, Bitmap bitmap, int objX, int objY) {
        this.mainRunActivity = mainRunActivity;
        this.bitmap = bitmap;
        this.objX = objX;
        this.objY = objY;
    }

    protected boolean clicked() {
        if (mainRunActivity.getTouchListener().up(objX, objY + bitmap.getHeight(), bitmap.getWidth(), bitmap.getHeight()) && requirements())
            clicked = !clicked;
        return clicked;
    }

    protected boolean isClicked() {
        return clicked;
    }

    protected void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public abstract boolean requirements();

}
