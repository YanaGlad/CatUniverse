package com.glad.catuniverse.gameSupport.gameTime;

import android.graphics.Bitmap;
import com.glad.catuniverse.gameSupport.Loopable;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;
//Двигающийся фон
//Поочередно перемещает 2 картинки, визуально переходящие друг в друга, создавая иллюзию движения
public class MovingBackground implements Loopable  {

    private int startX;
    private final int end;
    private int secondStartX;
    private final int speed;
    private final Bitmap background;

    public MovingBackground(Bitmap background, int speed) {
        startX = 0;
        this.speed = speed;
        this.background = background;
        secondStartX = background.getWidth();
        end = background.getWidth();
    }

    @Override
    public void run(GamePaint gamePaint)  {
        repaint();
        gamePaint.setVisibleBitmap(background, startX, 0);
        gamePaint.setVisibleBitmap(background, secondStartX, 0);
      }

    @Override
    public void repaint() {
        startX -= speed;
        secondStartX -= speed;
        if (startX < (-end)) {
            startX = 0;
        }
        if (secondStartX < 0) {
            secondStartX = end;
        }
    }

    @Override
    public void repaint(double speed, double jumSpeed) {}
}
