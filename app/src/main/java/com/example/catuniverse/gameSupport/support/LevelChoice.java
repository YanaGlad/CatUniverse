package com.example.catuniverse.gameSupport.support;

import android.graphics.Bitmap;

import com.example.catuniverse.MainActivity;
import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.LevelButton;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.Loopable;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.gameStrategy.StrategyField;
import com.example.catuniverse.gameSupport.graphics.GamePaint;
import com.example.catuniverse.gameViews.general.InDevelopmentView;
import com.example.catuniverse.gameViews.levels.MathsLevelsView;
import com.example.catuniverse.gameViews.levels.StrategyLevelsView;
import com.example.catuniverse.gameViews.levels.TimeLevelsView;

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

        switch (key) {
            case "time":
                timeLevels.add(new TimeLevelsView(mainRunActivity, 1));
                timeLevels.add(new TimeLevelsView(mainRunActivity, 2));
                timeLevels.add(new TimeLevelsView(mainRunActivity, 3));
                timeLevels.add(new TimeLevelsView(mainRunActivity, 4));
                timeLevels.add(new TimeLevelsView(mainRunActivity, 5));
                timeLevels.add(new TimeLevelsView(mainRunActivity, 6));
                break;
            case "strategy":
                strategyLevels.add(new StrategyLevelsView(mainRunActivity, 1));
                strategyLevels.add(new StrategyLevelsView(mainRunActivity, 2));
                strategyLevels.add(new StrategyLevelsView(mainRunActivity, 3));
                strategyLevels.add(new StrategyLevelsView(mainRunActivity, 4));
                strategyLevels.add(new InDevelopmentView(mainRunActivity, key));
                break;
            case "maths":
                mathsLevels.add(new MathsLevelsView(mainRunActivity, 1));
                mathsLevels.add(new MathsLevelsView(mainRunActivity, 2));
                mathsLevels.add(new MathsLevelsView(mainRunActivity, 3));
                mathsLevels.add(new MathsLevelsView(mainRunActivity, 4));
                mathsLevels.add(new InDevelopmentView(mainRunActivity, key));
                break;
        }

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
        for (int i = 0; i < BasicGameSupport.levelsCount; i++) {
            switch (key) {
                case "time":
                    levelButtons.get(i).repaint(MainActivity.timeLevels.get(i).getStars(), String.valueOf(MainActivity.timeLevels.get(i).getNumber()));
                    break;
                case "strategy":
                    levelButtons.get(i).repaint(MainActivity.strategyLevels.get(i).getStars(), String.valueOf(MainActivity.strategyLevels.get(i).getNumber()));
                    break;
                case "maths":
                    levelButtons.get(i).repaint(MainActivity.mathsLevels.get(i).getStars(), String.valueOf(MainActivity.mathsLevels.get(i).getNumber()));
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
                            gameView.getMainRunActivity().setView(timeLevels.get(i));
                            TimeLevelsView.levelRunning = true;
                            break;
                        case "strategy":
                            gameView.getMainRunActivity().setView(strategyLevels.get(i));
                            StrategyLevelsView.levelRunning = true;
                            break;
                        case "maths":
                            gameView.getMainRunActivity().setView(mathsLevels.get(i));
                            MathsLevelsView.levelRunning = true;
                            break;
                    }
                }
                levelButtons.get(i).notClicked();
            }

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
