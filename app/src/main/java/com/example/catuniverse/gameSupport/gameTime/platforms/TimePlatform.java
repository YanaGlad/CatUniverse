package com.example.catuniverse.gameSupport.gameTime.platforms;

import android.graphics.Bitmap;

import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.CollisionDetectors;
import com.example.catuniverse.gameSupport.EasyTimer;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.graphics.GamePaint;

import static com.example.catuniverse.gameSupport.BitmapLoader.bluePlatform;
import static com.example.catuniverse.gameSupport.BitmapLoader.bluePlatformSkeleton;
import static com.example.catuniverse.gameSupport.Collisions.collisionDetectLengthViaHeight;
import static com.example.catuniverse.gameSupport.Collisions.createCollisionsRect;
import static com.example.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

//Платформы, на которые игрок может забираться
public class TimePlatform extends GameItem {
    private boolean visibility;
    private EasyTimer easyTimer;
    public boolean playerOn;

    //По умолчанию цвет платформ - синий
    public TimePlatform(int x, int y) {
        this.x = x;
        this.y = y;
        this.controlY = y;
        visibility = true;
        speed = 3;
        collLength = collisionDetectLengthViaHeight(bluePlatformSkeleton, 1.5);
        easyTimer = new EasyTimer();
        easyTimer.startTimer();
        playerOn = false;
    }

    //Конструктор для создания универсальной платформы ( обработка соприкосновений для всех одинакова, поэтому они должны быть примерно одного размера)
    public TimePlatform(int x, int y, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.controlY = y;
        this.bitmap = bitmap;
        visibility = true;
        speed = 3;
        collLength = collisionDetectLengthViaHeight(bluePlatformSkeleton, 1.5);
        easyTimer = new EasyTimer();
        easyTimer.startTimer();
        playerOn = false;
    }

    //Конструктор для исчезающих платформ
    public TimePlatform(int x, int y, boolean visibility, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.controlY = y;
        this.visibility = visibility;
        this.bitmap = bitmap;
        speed = 3;
        collLength = collisionDetectLengthViaHeight(bluePlatformSkeleton, 1.5);
        easyTimer = new EasyTimer();
        easyTimer.startTimer();
        playerOn = false;
    }

    @Override
    public void run(GamePaint gamePaint) {
        repaint(timePlayer.getMainPlayerSpeed(), timePlayer.getJumpSpeed());
        if (isOnScreen()) {
            if (isVisibile()) { //Отображать платформу, если она видима
                if (bitmap == null)
                    gamePaint.setVisibleBitmap(bluePlatform, x, y);
                else gamePaint.setVisibleBitmap(bitmap, x, y);
            }
        }
    }

    @Override
    public void repaint(double speed, double jumSpeed) {
        x = BasicGameSupport.updateMovesX(speed, x);
        y = BasicGameSupport.updateMovesY(this, jumSpeed, y);
        collisionRect = createCollisionsRect(x, y, bluePlatformSkeleton);

        if (isOnScreen()) {
            if (isVisibile()) CollisionDetectors.checkObsCollision(this);
            //Стоит ли игрок на данной платформе
            playerOn = timePlayer.getX() > x - 30 && timePlayer.getX() < x + 120 && timePlayer.getY() < y - 31;

        }
    }

    //Исчезать на delay секунд
    public void changing(double delay) {
        if (easyTimer.timerDelay(delay)) {
            if (isVisibile()) setVisibility(false);
            else setVisibility(true);
            easyTimer.startTimer();
        }
    }

    private boolean isVisibile() {
        return visibility;
    }

    private void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean isPlayerOn() {
        return playerOn;
    }

    public void setPlayerOn(boolean playerOn) {
        this.playerOn = playerOn;
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        super.setBitmap(bitmap);
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }
}
