package com.glad.catuniverse.gameSupport.gameTime;

import android.graphics.Bitmap;

import com.glad.catuniverse.gameSupport.GameItem;
import com.glad.catuniverse.gameSupport.MainRunActivity;

public abstract class Clickable extends GameItem {

    private boolean clicked = false;
    private final MainRunActivity mainRunActivity;
    private final Bitmap bitmap;
    private final int objX;
    private final int objY;

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
