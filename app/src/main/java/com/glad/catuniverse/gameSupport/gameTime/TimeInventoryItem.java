package com.glad.catuniverse.gameSupport.gameTime;

import android.graphics.Bitmap;

import com.glad.catuniverse.gameSupport.BasicGameSupport;
import com.glad.catuniverse.gameSupport.BitmapLoader;
import com.glad.catuniverse.gameSupport.CollisionDetectors;
import com.glad.catuniverse.gameSupport.Collisions;
import com.glad.catuniverse.gameSupport.GameItem;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;
import com.glad.catuniverse.gameSupport.graphics.SpriteAnimation;

import static com.glad.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

//Предметы, которые нужно собирать в ходе прохождения уровней. Они необходимы чтобы открывать двери, получать звезды и т.д.
public class TimeInventoryItem extends GameItem {

    private boolean picked;
    private final int speed = 8;
    private boolean rocket = false, good = true;
    private String key;
    private SpriteAnimation spriteAnimation;
    private boolean animateRocketMode = false;

    //Конструктор, если предмет испольщуется просто в уровне
    //Принимает x y координаты, изображение предмета
    public TimeInventoryItem(int x, int y, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
        picked = false;
        controlY = y;
        collLength = Collisions.collisionDetectLengthViaHeight(BitmapLoader.keyBlue, 5.4); //Т.к. размер подобных предметов примерно одинаков, для них задана общая длина соприкосновения
    }

    //Конструктор для объектов в мини-игре стратегических уровней "Ракета"
    public TimeInventoryItem(int x, int y, Bitmap bitmap, boolean rocket, boolean good, String key) {
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
        picked = false;
        controlY = y;
        collLength = Collisions.collisionDetectLengthViaHeight(BitmapLoader.keyBlue, 5.4);
        this.rocket = rocket;
        this.good = good;
        this.key = key;
    }

    //Конструктор для анимированных объектов в мини-игре "Ракета"
    public TimeInventoryItem(int x, int y, SpriteAnimation spriteAnimation, boolean rocket, boolean good, String key, boolean animateRocketMode) {
        this.x = x;
        this.y = y;
        this.spriteAnimation = spriteAnimation;
        picked = false;
        controlY = y;
        collLength = Collisions.collisionDetectLengthViaHeight(BitmapLoader.keyBlue, 5.4)/2;
        this.rocket = rocket;
        this.good = good;
        this.key = key;
        this.animateRocketMode = animateRocketMode;
        bitmap = null;
    }

    @Override
    public void run(GamePaint gamePaint) {

        if (!isPicked()) { //Отображать предмет, если игрок его не взял
            repaint(timePlayer.getSpeed(), timePlayer.getJumpSpeed());
            if (bitmap != null)
                gamePaint.setVisibleBitmap(bitmap, x, y);
            else
                spriteAnimation.run(gamePaint, x, y, 9);

        }
    }

    @Override
    public void repaint(double speed, double jumSpeed) {
        if ((!rocket && !timePlayer.isRocketMode()) || animateRocketMode) {
            x = BasicGameSupport.updateMovesX(speed, x);
            y = BasicGameSupport.updateMovesY(this, jumSpeed, y);
            if (animateRocketMode){
                x-=this.speed;
            }
        } else {
            x -= this.speed;
        }
        collisionRect = Collisions.createCollisionsRect(x, y, BitmapLoader.keyBlue);
        if (CollisionDetectors.checkPlayerItemCollision(this)) {
            picked = true;
        }
    }

    public String getKey() {
        return key;
    }

    public boolean isPicked() {
        return picked;
    }

    public void notPicked() {
        picked = false;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }


    public int getSpeedTM() {
        return speed;
    }

    boolean isRocket() {
        return rocket;
    }

    boolean isGood() {
        return good;
    }

    public void setSpriteAnimation(SpriteAnimation spriteAnimation) {
        this.spriteAnimation = spriteAnimation;
    }

    public void setAnimateRocketMode(boolean animateRocketMode) {
        this.animateRocketMode = animateRocketMode;
    }

    SpriteAnimation getSpriteAnimation() {
        return spriteAnimation;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }
}
