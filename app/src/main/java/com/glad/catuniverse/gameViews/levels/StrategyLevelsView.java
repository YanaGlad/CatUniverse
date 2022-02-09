package com.glad.catuniverse.gameViews.levels;

import static com.glad.catuniverse.gameSupport.BasicGameSupport.updateStrategyMathsStars;
import static com.glad.catuniverse.gameSupport.BitmapLoader.exitButton;
import static com.glad.catuniverse.gameSupport.BitmapLoader.exitButtonClicked;
import static com.glad.catuniverse.gameSupport.BitmapLoader.menuMusic;
import static com.glad.catuniverse.gameSupport.BitmapLoader.theoryMusic;
import com.glad.catuniverse.R;
import com.glad.catuniverse.gameSupport.Buttons.BasicButton;
import com.glad.catuniverse.gameSupport.GameView;
import com.glad.catuniverse.gameSupport.MainRunActivity;
import com.glad.catuniverse.gameSupport.gameStrategy.StrategyField;
import com.glad.catuniverse.gameViews.general.ChooseView;
import com.glad.catuniverse.gameViews.general.GameOverView;

import java.io.IOException;
import java.util.ArrayList;

public class StrategyLevelsView extends GameView {

    public static boolean levelRunning = false;

    private final int id;
    private final StrategyField level;
    private final BasicButton exit;

    public StrategyLevelsView(MainRunActivity mainRunActivity, int id) {
        super(mainRunActivity);
        this.id = id;
        ArrayList<StrategyField> strategyLevels = new ArrayList<>();

        strategyLevels.add(new StrategyField(mainRunActivity, 20, 70, 0.5, 10, 5, 3, 7, new int[]{3}, 1, null, theoryMusic, 3));
        strategyLevels.add(new StrategyField(mainRunActivity, 30, 100, 0.5, 7, 10, 4, 7, new int[]{3}, 1, null, theoryMusic, -1));
        strategyLevels.add(new StrategyField(mainRunActivity, 30, 70, 0.5, 7, 7, 4, 10, new int[]{3}, 1, "3", theoryMusic, -1));
        strategyLevels.add(new StrategyField(mainRunActivity, 30, 100, 0.5, 7, 10, 3, 8, new int[]{5, 4, 3, 2, 1}, 1, null, theoryMusic, -1));
        strategyLevels.add(new StrategyField(mainRunActivity, 30, 100, 0.5, 5, 15, 4, 12, new int[]{7}, 1, null, theoryMusic, -1));
        strategyLevels.add(new StrategyField(mainRunActivity, 30, 100, 0.5, 7, 15, 3, 12, new int[]{7, 3}, 1, "7", theoryMusic, -1));

        level = strategyLevels.get(id - 1);
        exit = new BasicButton(mainRunActivity, 730, 10, exitButton, exitButtonClicked, false);
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
        if (exit.isClicked()) { //Выйти из уровня в основное меню при нажатии на кнопку
            super.getMainRunActivity().setView(new ChooseView(super.getMainRunActivity(), super.getMainRunActivity().getString(R.string.strategy)));
            returnToMenu();
            exit.notClicked();
        }

        if (level.isWon()) {
            returnToMenu();
            updateStrategyMathsStars(level.getLives(), level.getRequestedLives(), stars, id, level.getRewardId(), super.getMainRunActivity(), super.getMainRunActivity().getString(R.string.strategy), level.getAchieveId());
        }

        if (level.isGameOver()) {
            returnToMenu();
            super.getMainRunActivity().setView(new GameOverView(super.getMainRunActivity(), new StrategyLevelsView(super.getMainRunActivity(), id), super.getMainRunActivity().getString(R.string.strategy)));
        }
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
