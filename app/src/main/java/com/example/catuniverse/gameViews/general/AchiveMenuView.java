package com.example.catuniverse.gameViews.general;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.MainRunActivity;

import java.util.ArrayList;

public class AchiveMenuView extends GameView {

    private ArrayList<BasicButton> basicButtons;
    private ArrayList<Bitmap>achievementIcons;

    public AchiveMenuView(MainRunActivity mainRunActivity) {
        super(mainRunActivity);

        basicButtons = new ArrayList<>();
        achievementIcons = new ArrayList<>();

        int x = 0, y = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                basicButtons.add(new BasicButton(getMainRunActivity(), x, y, BitmapLoader.grayIcon, BitmapLoader.grayIcon, false));
                x += 100;
            }
            x = 0;
            y += 100;
        }
    }

    @Override
    public void run() {
        repaint();
        BasicGameSupport.drawGrid(getGamePaint());
        for (BasicButton btn : basicButtons) {
            btn.run(getGamePaint());
        }

    }

    @Override
    public void repaint() {

    }
}
