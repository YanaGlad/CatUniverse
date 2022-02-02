package com.example.catuniverse.gameViews.levels;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.gameMathematics.MathsField;
import com.example.catuniverse.gameViews.general.ChooseView;
import com.example.catuniverse.gameViews.general.GameOverView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static com.example.catuniverse.gameSupport.BitmapLoader.menuMusic;
import static com.example.catuniverse.gameSupport.BitmapLoader.stayInsideMusic;

public class MathsLevelsView extends GameView {
    private final int id;
    private final MathsField level;
    public static boolean levelRunning = false;
    private final BasicButton exit;

    public MathsLevelsView(MainRunActivity mainRunActivity, int id) {
        super(mainRunActivity);
        this.id = id;

        ArrayList<MathsField> mathsLevels = new ArrayList<>();

        Random random = new Random();
        int a;

        a = random.nextInt(9);
        mathsLevels.add(new MathsField(mainRunActivity, 5, 5, a, 0, 8, mainRunActivity.getString(R.string.square), 30, 32, 0, 12, null, stayInsideMusic, -1));

        a = (int) (Math.random() * (17 - 9) + 9);
        mathsLevels.add(new MathsField(mainRunActivity, 7, 5, a, 9, 17, mainRunActivity.getString(R.string.square), 30, 32, 13, 24, null, stayInsideMusic, -1));

        a = (int) (Math.random() * (37 - 30) + 30);
        mathsLevels.add(new MathsField(mainRunActivity, 5, 5, a, 30, 37, mainRunActivity.getString(R.string.derivative), 27, 30, 0, 14, "5", stayInsideMusic, -1));

        a = (int) (Math.random() * (45 - 38) + 38);
        mathsLevels.add(new MathsField(mainRunActivity, 5, 5, a, 38, 45, mainRunActivity.getString(R.string.derivative), 27, 28, 10, 20, null, stayInsideMusic, -1));

        a = (int) (Math.random() * (22 - 18) + 18);
        mathsLevels.add(new MathsField(mainRunActivity, 5, 8, a, 18, 22, mainRunActivity.getString(R.string.trigonometry), 20, 22, 0, 16, null, stayInsideMusic, -1));

        a = (int) (Math.random() * (29 - 23) + 23);
        mathsLevels.add(new MathsField(mainRunActivity, 5, 8, a, 23, 29, mainRunActivity.getString(R.string.trigonometry), 20, 20, 0, 16, null, stayInsideMusic, -1));

        level = mathsLevels.get(id - 1);
        exit = new BasicButton(mainRunActivity, 730, 30, BitmapLoader.exitButton, BitmapLoader.exitButtonClicked, false);
    }

    @Override
    public void run() {
        repaint();
        if (levelRunning) {
            level.run(super.getGamePaint());
            exit.run(super.getGamePaint());
        }
    }

    @Override
    public void repaint() {
        exit.repaint();
        if (exit.isClicked()) {
            super.getMainRunActivity().setView(new ChooseView(super.getMainRunActivity(), super.getMainRunActivity().getString(R.string.maths)));
            returnToMenu();
            exit.notClicked();
        }

        if (level.isWon()) {
            returnToMenu();
            BasicGameSupport.updateStrategyMathsStars(level.getLives(), level.getRequestedLives(), stars, id, level.getRewardId(), super.getMainRunActivity(), super.getMainRunActivity().getString(R.string.maths), level.getAchieveId());
        }

        if (level.isGameOver()) {
            returnToMenu();
            super.getMainRunActivity().setView(new GameOverView(super.getMainRunActivity(), new MathsLevelsView(super.getMainRunActivity(), id), super.getMainRunActivity().getString(R.string.maths)));
        }
        exit.repaint();
    }

    private void returnToMenu() {
        levelRunning = false;
        level.getMusic().stop();
        try {
            menuMusic.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
