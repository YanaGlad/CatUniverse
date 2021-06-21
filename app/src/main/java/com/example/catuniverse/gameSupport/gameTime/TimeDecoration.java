package com.example.catuniverse.gameSupport.gameTime;

import android.graphics.Bitmap;
import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.EasyTimer;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.graphics.GamePaint;
import com.example.catuniverse.gameSupport.helpp.Owner;

import static com.example.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

public class TimeDecoration extends GameItem {
    //Сделать bitmap у GameItem
    private boolean levitate, down = false;
    private EasyTimer easyTimer, easyTimer2, easyTimerLen;

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

    @Override
    public boolean checkAcceptParam() {
        return false;
    }

    @Override
    public boolean checkDeclineParam() {
        return false;
    }

    @Override
    public Owner getOwner() {
        return BasicGameSupport.timeLevelOwner;
    }
}
