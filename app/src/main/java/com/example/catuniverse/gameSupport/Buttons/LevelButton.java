package com.example.catuniverse.gameSupport.Buttons;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Loopable;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.graphics.GamePaint;

//Кнопки с информацией об уровнях (кол-во звезд, номер уровня)
public class LevelButton implements Loopable {
    private int x, y;
    private MainRunActivity mainRunActivity;
    private boolean clicked;
    private String text;
    private int stars = -1;
    private Bitmap btnClicked, notClicked;
    private String key; //Ключ для определения типа уровня

    public LevelButton(MainRunActivity mainRunActivity, int x, int y, Bitmap notClicked, Bitmap btnClicked, String key) {
        this.x = x;
        this.y = y;
        this.mainRunActivity = mainRunActivity;
        this.notClicked = notClicked;
        this.btnClicked = btnClicked;
        this.key = key;
    }

    @Override
    public void run(GamePaint gamePaint) {
         repaint();

        if (clicked) gamePaint.setVisibleBitmap(btnClicked, x, y);
        else gamePaint.setVisibleBitmap(notClicked, x, y);

        gamePaint.write(mainRunActivity.getString(R.string.level) + text, x + 15, y + 55, Color.BLACK, 55);
        int step = 10;
        switch (key) {
            case "time":
                for (int i = 0; i < stars; i++) {
                    gamePaint.setVisibleBitmap(BitmapLoader.star, x + step, y + 70);
                    step += 60;
                }
                break;
            case "strategy":
                for (int i = 0; i < stars; i++) {
                    gamePaint.setVisibleBitmap(BitmapLoader.redStar, x + step, y + 70);
                    step += 60;
                }
                break;

            case "maths":
                for (int i = 0; i < stars; i++) {
                    gamePaint.setVisibleBitmap(BitmapLoader.redStar, x + step, y + 70);
                    step += 60;
                }
                break;
        }
    }

    //Обновление информации о звездах и тексте (т.к. она получается из бд)
    public void repaint(int stars, String text) {
        this.stars = stars;
        this.text = text;
    }

    @Override
    public void repaint() {
        //Проверка нажатия кнопки
        if (mainRunActivity.getTouchListener().up(x, y + 200, 200, 133)) clicked = true;
    }

    @Override
    public void repaint(double speed, double jumSpeed) {
    }

    public boolean isClicked() {
        return clicked;
    }

    public void notClicked() {
        this.clicked = false;
    }

    public int getStars() {
        return stars;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}


