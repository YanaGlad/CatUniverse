package com.glad.catuniverse.gameViews.levels;

import com.example.catuniverse.R;
import com.glad.catuniverse.gameSupport.BitmapLoader;
import com.glad.catuniverse.gameSupport.Buttons.BasicButton;
import com.glad.catuniverse.gameSupport.GameView;
import com.glad.catuniverse.gameSupport.MainRunActivity;
import com.glad.catuniverse.gameSupport.gameTime.TimeLevel;
import com.glad.catuniverse.gameSupport.gameTime.timeLevels.Level1;
import com.glad.catuniverse.gameSupport.gameTime.timeLevels.Level2;
import com.glad.catuniverse.gameSupport.gameTime.timeLevels.Level3;
import com.glad.catuniverse.gameSupport.gameTime.timeLevels.Level4;
import com.glad.catuniverse.gameSupport.gameTime.timeLevels.Level5;
import com.glad.catuniverse.gameSupport.gameTime.timeLevels.Level6;
import com.glad.catuniverse.gameSupport.gameTime.timeLevels.Level7;
import com.glad.catuniverse.gameSupport.gameTime.timeLevels.Level8;
import com.glad.catuniverse.gameSupport.gameTime.timeLevels.Level9;
import com.glad.catuniverse.gameViews.general.ChooseView;

import java.io.IOException;
import java.util.ArrayList;

import static com.glad.catuniverse.gameSupport.BasicGameSupport.timeLevelFinish;

public class TimeLevelsView extends GameView {
    private final int id;
    private final BasicButton exit;
    private final TimeLevel level;
    public static boolean levelRunning = false;

    public TimeLevelsView(MainRunActivity mainRunActivity, int id) {
        super(mainRunActivity);
        this.id = id;
        ArrayList<TimeLevel> timeLevels = new ArrayList<>();
        exit = new BasicButton(mainRunActivity, 730, 30, BitmapLoader.exitButton, BitmapLoader.exitButtonClicked, false);

        timeLevels.add(new Level1(mainRunActivity));
        timeLevels.add(new Level2(mainRunActivity));
        timeLevels.add(new Level3(mainRunActivity));
        timeLevels.add(new Level4(mainRunActivity));
        timeLevels.add(new Level5(mainRunActivity));
        timeLevels.add(new Level6(mainRunActivity));
        timeLevels.add(new Level7(mainRunActivity));
        timeLevels.add(new Level8(mainRunActivity));
        timeLevels.add(new Level9(mainRunActivity));

        level = timeLevels.get(id - 1);
    }

    @Override
    public void run() {
        repaint();

        if (levelRunning) level.run(super.getGamePaint());
        exit.run(super.getGamePaint());
    }

    @Override
    public void repaint() {
        exit.repaint();
        if (exit.isClicked()) {
            super.getMainRunActivity().setView(new ChooseView(super.getMainRunActivity(), super.getMainRunActivity().getString(R.string.time)));
            levelRunning = false;
            level.getMusic().stop();
            try {
                BitmapLoader.menuMusic.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
            exit.notClicked();
        }
        timeLevelFinish(level, this, id, level.getRewardId(), new TimeLevelsView(super.getMainRunActivity(), id), level.getMusic());
        exit.repaint();
    }
}
