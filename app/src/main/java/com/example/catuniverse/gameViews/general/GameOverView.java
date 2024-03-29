package com.example.catuniverse.gameViews.general;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameViews.levels.MathsLevelsView;
import com.example.catuniverse.gameViews.levels.StrategyLevelsView;
import com.example.catuniverse.gameViews.levels.TimeLevelsView;

import static com.example.catuniverse.gameSupport.BitmapLoader.baseBlueButton;
import static com.example.catuniverse.gameSupport.BitmapLoader.baseBlueButtonClicked;
import static com.example.catuniverse.gameSupport.BitmapLoader.baseRedButton;
import static com.example.catuniverse.gameSupport.BitmapLoader.baseRedButtonClicked;
import static com.example.catuniverse.gameSupport.BitmapLoader.keyBlue;
import static com.example.catuniverse.gameSupport.BitmapLoader.longBlueRect;
import static com.example.catuniverse.gameSupport.BitmapLoader.longRedRect;
import static com.example.catuniverse.gameSupport.BitmapLoader.mathsGameFinishBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.redTechnoBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.technoBackground;

//Выдается при поражении, дает возможность попытаться пройти уровень снова или выйти в главное меню
public class GameOverView extends GameView {
    private BasicButton tryAgaing, goBack;
    private GameView level;
    private String key;

    public GameOverView(MainRunActivity mainRunActivity, GameView level, String key) {
        super(mainRunActivity);
        this.level = level;
        this.key = key;

        Bitmap tryAgainBmp = null, tryAgaingBmpClicked = null;
        switch (key) {
            case "time":
                tryAgainBmp = baseBlueButton;
                tryAgaingBmpClicked = baseBlueButtonClicked;
                break;
            case "strategy":
                tryAgainBmp = baseRedButton;
                tryAgaingBmpClicked = baseRedButtonClicked;
                break;
            case "maths":
                tryAgainBmp = baseRedButton;
                tryAgaingBmpClicked = baseRedButtonClicked;
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
                super.getGamePaint().setVisibleBitmap(technoBackground, 0, 0);
                super.getGamePaint().setVisibleBitmap(longBlueRect, -20, -155);
                super.getGamePaint().write(super.getMainRunActivity().getString(R.string.you_lost), 200, 100, Color.BLACK, 100);
                break;
            case "strategy":
                super.getGamePaint().setVisibleBitmap(redTechnoBackground, 0, 0);
                super.getGamePaint().setVisibleBitmap(longRedRect, -20, -155);
                super.getGamePaint().write(super.getMainRunActivity().getString(R.string.you_lost), 200, 100, Color.BLACK, 100);

                break;
            case "maths":
                super.getGamePaint().setVisibleBitmap(mathsGameFinishBackground, 0, 0);
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
