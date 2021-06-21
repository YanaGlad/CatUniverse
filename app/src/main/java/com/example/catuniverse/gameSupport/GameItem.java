package com.example.catuniverse.gameSupport;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.catuniverse.gameSupport.graphics.GamePaint;
import com.example.catuniverse.gameSupport.helpp.Checkable;

//Абстрактный класс, описывающий все объекты для уровней на время
public abstract class GameItem implements Loopable, Checkable {
    protected int x, y, controlY;
    protected double speed, jumpingSpeed, collLength;
    protected Rect collisionRect;
    protected Bitmap bitmap, bitmapClicked;

    protected boolean isOnScreen() {
        return x > -200 && x < GameView.screenWidth + 200 && y > -200 && y < 750; //Проверить что объект в пределах экрана
    }

    @Override
    public void run(GamePaint gamePaint) {
    }

    @Override
    public void repaint() {

    }

    @Override
    public void repaint(double speed, double jumSpeed) {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    protected Rect getCollisionRect() {
        return collisionRect;
    }

    protected double getCollLength() {
        return collLength;
    }

    protected int getControlY() {
        return controlY;
    }

    public double getJumpingSpeed() {
        return jumpingSpeed;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Bitmap getBitmapClicked() {
        return bitmapClicked;
    }

    public void setControlY(int controlY) {
        this.controlY = controlY;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setBitmapClicked(Bitmap bitmapClicked) {
        this.bitmapClicked = bitmapClicked;
    }
}
