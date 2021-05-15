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
    private ArrayList<Bitmap> achievementIcons;

    public AchiveMenuView(MainRunActivity mainRunActivity) {
        super(mainRunActivity);

        basicButtons = new ArrayList<>();
        achievementIcons = new ArrayList<>();
        achievementIcons.add(BitmapLoader.strengthAch);

        int x = 0, y = 100;
        for (int i = 0; i < achievementIcons.size(); i++) {
            //for (int j = 0; j < achievementIcons.size(); j++) {
            basicButtons.add(new BasicButton(getMainRunActivity(), x, y, achievementIcons.get(i), achievementIcons.get(i), false));
            x += 100;
            //  }
            //  x = 0;
            //   y += 100;
        }
    }

    @Override
    public void run() {
        repaint();
        BasicGameSupport.drawGrid(getGamePaint());
        getGamePaint().setVisibleBitmap(BitmapLoader.longBlueRect, -20, -198);
        super.getGamePaint().write(super.getMainRunActivity().getString(R.string.achievements), 230, 50, Color.WHITE, 45);

        for (BasicButton btn : basicButtons) {
            btn.run(getGamePaint());
        }

    }

    @Override
    public void repaint() {

    }
}
