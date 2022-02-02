package com.example.catuniverse.gameViews.general;

import android.graphics.Color;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.MainRunActivity;

import java.io.IOException;

import static com.example.catuniverse.gameSupport.BitmapLoader.menuMusic;
import static com.example.catuniverse.gameSupport.BitmapLoader.technoBackground;
//Выдается, если уровень находится на стадии разработки
public class InDevelopmentView extends GameView {
    private final BasicButton exit;
    private final String key;

    public InDevelopmentView(MainRunActivity mainRunActivity, String key) {
        super(mainRunActivity);
        this.key = key;
        exit = new BasicButton(mainRunActivity, 730, 30, BitmapLoader.exitButton, BitmapLoader.exitButtonClicked, false);
    }

    @Override
    public void run() {
        repaint();
        super.getGamePaint().setVisibleBitmap(technoBackground,0,0);
        super.getGamePaint().write(super.getMainRunActivity().getString(R.string.in_dev),150,300, Color.BLACK,60);

        exit.run(super.getGamePaint());
    }

    @Override
    public void repaint() {
        exit.repaint();
        if (exit.isClicked()) {
            super.getMainRunActivity().setView(new ChooseView(super.getMainRunActivity() , key));
            try {
                menuMusic.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
            exit.notClicked();
        }
    }
}
