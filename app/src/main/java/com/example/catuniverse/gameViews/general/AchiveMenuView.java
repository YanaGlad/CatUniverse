package com.example.catuniverse.gameViews.general;

import android.graphics.Color;

import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.MainRunActivity;

public class AchiveMenuView extends GameView {

    public AchiveMenuView(MainRunActivity mainRunActivity) {
        super(mainRunActivity);
    }

    @Override
    public void run() {
        repaint();

        //Change it to support methods
        //Задать фон, начертить линии, отделяющие клетки
        super.getGamePaint().setVisibleBitmap(BitmapLoader.strategyBackground, 0, 0);
        int y = 100;
        for (int i = 0; i < 10; i++) {
            super.getGamePaint().createLine(0, y, 950, y, Color.BLACK);
            y += 100;
        }
        int x = 100;
        for (int i = 0; i < 10; i++) {
            super.getGamePaint().createLine(x, 0, x, 650, Color.BLACK);
            x += 100;
        }

    }

    @Override
    public void repaint() {

    }
}
