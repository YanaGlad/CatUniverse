package com.glad.catuniverse.gameSupport.support;

import android.graphics.Bitmap;

import com.glad.catuniverse.MainActivity;
import com.glad.catuniverse.gameSupport.BasicGameSupport;
import com.glad.catuniverse.gameSupport.BitmapLoader;
import com.glad.catuniverse.gameSupport.Buttons.LevelButton;
import com.glad.catuniverse.gameSupport.GameView;
import com.glad.catuniverse.gameSupport.Loopable;
import com.glad.catuniverse.gameSupport.MainRunActivity;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;
import com.glad.catuniverse.gameViews.levels.MathsLevelsView;
import com.glad.catuniverse.gameViews.levels.StrategyLevelsView;
import com.glad.catuniverse.gameViews.levels.TimeLevelsView;

import java.util.ArrayList;

//Меню для выбора уровней. Определит к какому типу относятся уровни по полученному ключу.
public class LevelChoice implements Loopable {
    private ArrayList<LevelButton> levelButtons; //Кнопки для выбора уровней
    private ArrayList<GameView> timeLevels, strategyLevels, mathsLevels; //Список уровняй того или иного типа
    private GameView gameView; //текущий кадр
    private String key;

    public LevelChoice(GameView gameView, MainRunActivity mainRunActivity, Bitmap notClicked, Bitmap clicked, String key) {
        this.gameView = gameView;
        levelButtons = new ArrayList<>();
        timeLevels = new ArrayList<>();
        strategyLevels = new ArrayList<>();
        mathsLevels = new ArrayList<>();
        this.key = key;

        int x = 70;
        int y = 50;

        for (int i = 0; i < BasicGameSupport.levelsCount; i++) {
            levelButtons.add(new LevelButton(mainRunActivity, x, y, notClicked, clicked, key));
            x += 220;
            if ((i + 1) % 3 == 0) {
                y += 170;
                x = 70;
            }
        }
    }

    @Override
    public void run(GamePaint gamePaint) {
        repaint();
        for (LevelButton lb : levelButtons) lb.run(gamePaint);
        checkClosedLevel(gamePaint);
    }

    @Override
    public void repaint() {
        BitmapLoader.electrodynamixMusic.stop();
        int starsCount = 0;
        for (int i = 0; i < BasicGameSupport.levelsCount; i++) {
            switch (key) {
                case "time":
                    levelButtons.get(i).repaint(MainActivity.timeLevels.get(i).getStars(), String.valueOf(MainActivity.timeLevels.get(i).getNumber()));
                    starsCount += MainActivity.timeLevels.get(i).getStars();
                    break;
                case "strategy":
                    levelButtons.get(i).repaint(MainActivity.strategyLevels.get(i).getStars(), String.valueOf(MainActivity.strategyLevels.get(i).getNumber()));
                    starsCount += MainActivity.strategyLevels.get(i).getStars();

                    break;
                case "maths":
                    levelButtons.get(i).repaint(MainActivity.mathsLevels.get(i).getStars(), String.valueOf(MainActivity.mathsLevels.get(i).getNumber()));
                    starsCount += MainActivity.mathsLevels.get(i).getStars();

                    break;
            }
        }
        for (int i = 0; i < BasicGameSupport.levelsCount; i++) {
            if (levelButtons.get(i).isClicked()) {
                boolean start = false;
                if (i == 0 || levelButtons.get(i - 1).getStars() > 0) start = true;
                if (start) {
                    BitmapLoader.menuMusic.stop();
                    switch (key) {
                        case "time":
                            gameView.getMainRunActivity().setView(new TimeLevelsView(gameView.getMainRunActivity(), i + 1));
                            TimeLevelsView.levelRunning = true;
                            break;
                        case "strategy":
                            gameView.getMainRunActivity().setView(new StrategyLevelsView(gameView.getMainRunActivity(), i + 1));
                            StrategyLevelsView.levelRunning = true;
                            break;
                        case "maths":
                            gameView.getMainRunActivity().setView(new MathsLevelsView(gameView.getMainRunActivity(), i + 1));
                            MathsLevelsView.levelRunning = true;
                            break;
                    }
                }
                levelButtons.get(i).notClicked();
            }

        }
        if (starsCount >= 18 && MainActivity.listOfAchievements.get(1).isUnlocked() != 1) {
            BasicGameSupport.unlockAchievement(2);
        }
    }

    private void checkClosedLevel(GamePaint gamePaint) {
        for (int i = 1; i < levelButtons.size(); i++)
            if (levelButtons.get(i - 1).getStars() <= 0)
                gamePaint.setVisibleBitmap(BitmapLoader.closedLevel, levelButtons.get(i).getX(), levelButtons.get(i).getY());
    }

    @Override
    public void repaint(double speed, double jumSpeed) {

    }

    public ArrayList<LevelButton> getLevelButtons() {
        return levelButtons;
    }
}
