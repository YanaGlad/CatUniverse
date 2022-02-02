package com.glad.catuniverse.gameSupport.gameTime;

import android.graphics.Bitmap;
import com.glad.catuniverse.gameSupport.BasicGameSupport;
import com.glad.catuniverse.gameSupport.EasyTimer;
import com.glad.catuniverse.gameSupport.GameItem;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;

import static com.glad.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

public class TimeDecoration extends GameItem {
    //Сделать bitmap у GameItem
    private final boolean levitate;
    private boolean down = false;
    private final EasyTimer easyTimer;
    private final EasyTimer easyTimer2;
    private final EasyTimer easyTimerLen;

    public TimeDecoration(int x, int y, Bitmap bitmap, boolean levitate) {
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
        this.controlY = y;
        this.levitate = levitate;

        easyTimer = new EasyTimer();
        easyTimer2 = new EasyTimer();
        easyTimerLen = new EasyTimer();
        easyTimer.startTimer();
        easyTimerLen.startTimer();
    }

    @Override
    public void run(GamePaint gamePaint) {
        repaint(timePlayer.getSpeed(), timePlayer.getJumpSpeed());
        gamePaint.setVisibleBitmap(bitmap, x, y);
    }

    @Override
    public void repaint(double speed, double jumSpeed) {

        if (levitate) {
            if (easyTimer.timerDelay(10.0) && !down) {
                down = true;
                easyTimer2.startTimer();
                easyTimer.stopTimer();
            }
            if (easyTimer2.timerDelay(10.0) && down) {
                down = false;
                easyTimer.startTimer();
                easyTimer2.stopTimer();
            }
            if (down) {
                if (easyTimerLen.timerDelay(0.08)) {
                    y--;
                    controlY--;
                    easyTimerLen.startTimer();
                }
            } else {
                if (easyTimerLen.timerDelay(0.08)) {
                    y++;
                    controlY++;
                    easyTimerLen.startTimer();
                }
            }
        }
        x = BasicGameSupport.updateMovesX(speed, x);
        y = BasicGameSupport.updateMovesY(this, jumSpeed, y);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        super.setBitmap(bitmap);
    }

    @Override
    public Bitmap getBitmap() {
        return super.getBitmap();
    }
}
