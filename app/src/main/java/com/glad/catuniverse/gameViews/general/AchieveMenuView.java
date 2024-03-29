package com.glad.catuniverse.gameViews.general;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.glad.catuniverse.MainActivity;
import com.glad.catuniverse.R;
import com.glad.catuniverse.gameSupport.BasicGameSupport;
import com.glad.catuniverse.gameSupport.BitmapLoader;
import com.glad.catuniverse.gameSupport.Buttons.BasicButton;
import com.glad.catuniverse.gameSupport.GameView;
import com.glad.catuniverse.gameSupport.MainRunActivity;
import java.util.ArrayList;

public class AchieveMenuView extends GameView {

    private final ArrayList<BasicButton> basicButtons;
    private final BasicButton exit;
    private final ArrayList<Bitmap> achievementIcons;

    public AchieveMenuView(MainRunActivity mainRunActivity) {
        super(mainRunActivity);

        basicButtons = new ArrayList<>();
        achievementIcons = new ArrayList<>();
        achievementIcons.add(BitmapLoader.dexterityAch);
        achievementIcons.add(BitmapLoader.starCollectorAch);

        int x = 0, y = 100;
        for (int i = 0; i < achievementIcons.size(); i++) {
            basicButtons.add(new BasicButton(getMainRunActivity(), x, y, achievementIcons.get(i), achievementIcons.get(i), false));
            x += 100;
        }
        exit = new BasicButton(mainRunActivity, 730, 17, BitmapLoader.exitButton, BitmapLoader.exitButtonClicked, false);
    }

    @Override
    public void run() {
        repaint();
        BasicGameSupport.drawGrid(getGamePaint(), BitmapLoader.spaceTestBackground, Color.WHITE);
        getGamePaint().setVisibleBitmap(BitmapLoader.longPurpleRect, -20, -198);
        super.getGamePaint().write(super.getMainRunActivity().getString(R.string.achievements), 233, 70, Color.WHITE, 55);

        for (int i = 0; i < basicButtons.size(); i++) {
            if (MainActivity.listOfAchievements.get(i).isUnlocked() == 1)
                basicButtons.get(i).run(getGamePaint());
        }
        exit.run(super.getGamePaint());
    }

    @Override
    public void repaint() {
        if (exit.isClicked()) {
            super.getMainRunActivity().setView(new MenuView(super.getMainRunActivity()));
            exit.notClicked();
        }
        for (int i = 0; i < basicButtons.size(); i++) {
            if (basicButtons.get(i).isClicked())
                getMainRunActivity().aboutAchievement(i, achievementIcons.get(i));
        }
    }
}
