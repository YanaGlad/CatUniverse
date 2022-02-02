package com.glad.catuniverse.gameViews.general;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.catuniverse.R;
import com.glad.catuniverse.gameSupport.Buttons.BasicButton;
import com.glad.catuniverse.gameSupport.GameView;
import com.glad.catuniverse.gameSupport.MainRunActivity;
import com.glad.catuniverse.gameViews.levels.MathsLevelsView;
import com.glad.catuniverse.gameViews.levels.StrategyLevelsView;
import com.glad.catuniverse.gameViews.levels.TimeLevelsView;
import com.glad.catuniverse.gameSupport.BitmapLoader;

//Выдается при поражении, дает возможность попытаться пройти уровень снова или выйти в главное меню
public class GameOverView extends GameView {
    private final BasicButton tryAgaing;
    private final BasicButton goBack;
    private final GameView level;
    private final String key;

    public GameOverView(MainRunActivity mainRunActivity, GameView level, String key) {
        super(mainRunActivity);
        this.level = level;
        this.key = key;

        Bitmap tryAgainBmp = null, tryAgaingBmpClicked = null;
        switch (key) {
            case "time":
                tryAgainBmp = BitmapLoader.baseBlueButton;
                tryAgaingBmpClicked = BitmapLoader.baseBlueButtonClicked;
                break;
            case "strategy":
            case "maths":
                tryAgainBmp = BitmapLoader.baseRedButton;
                tryAgaingBmpClicked = BitmapLoader.baseRedButtonClicked;
                break;
        }
        tryAgaing = new BasicButton(mainRunActivity, 295, 200, mainRunActivity.getString(R.string.play_again), Color.BLACK, 30, tryAgainBmp, tryAgaingBmpClicked, 30, 35);
        goBack = new BasicButton(mainRunActivity, 295, 400, mainRunActivity.getString(R.string.back), Color.BLACK, 30, tryAgainBmp, tryAgaingBmpClicked, 50, 40);

    }

    @Override
    public void run() {
        repaint();

        switch (key) { //Упростить
            case "time":
                super.getGamePaint().setVisibleBitmap(BitmapLoader.technoBackground, 0, 0);
                super.getGamePaint().setVisibleBitmap(BitmapLoader.longBlueRect, -20, -155);
                super.getGamePaint().write(super.getMainRunActivity().getString(R.string.you_lost), 200, 100, Color.BLACK, 100);
                break;
            case "strategy":
                super.getGamePaint().setVisibleBitmap(BitmapLoader.redTechnoBackground, 0, 0);
                super.getGamePaint().setVisibleBitmap(BitmapLoader.longRedRect, -20, -155);
                super.getGamePaint().write(super.getMainRunActivity().getString(R.string.you_lost), 200, 100, Color.BLACK, 100);

                break;
            case "maths":
                super.getGamePaint().setVisibleBitmap(BitmapLoader.mathsGameFinishBackground, 0, 0);
                super.getGamePaint().write(super.getMainRunActivity().getString(R.string.you_lost), 200, 100, Color.RED, 100);
                break;
        }
        tryAgaing.run(super.getGamePaint());
        goBack.run(super.getGamePaint());
    }

    @Override
    public void repaint() {
        if (tryAgaing.isClicked()) {
            if (key.equals(super.getMainRunActivity().getString(R.string.time)))
                TimeLevelsView.levelRunning = true;
            if (key.equals(super.getMainRunActivity().getString(R.string.strategy)))
                StrategyLevelsView.levelRunning = true;
            if (key.equals(super.getMainRunActivity().getString(R.string.maths)))
                MathsLevelsView.levelRunning = true;

            super.getMainRunActivity().setView(level);
            tryAgaing.notClicked();
        }
        if (goBack.isClicked()) {
            super.getMainRunActivity().setView(new ChooseView(super.getMainRunActivity(), key));
            goBack.notClicked();
        }
    }
}
