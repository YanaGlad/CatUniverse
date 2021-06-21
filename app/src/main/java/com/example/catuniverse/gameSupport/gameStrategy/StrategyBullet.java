package com.example.catuniverse.gameSupport.gameStrategy;

import android.graphics.Bitmap;

import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.graphics.GamePaint;
import com.example.catuniverse.gameSupport.helpp.Owner;

import static com.example.catuniverse.gameSupport.Collisions.collisionDetectLengthViaWidth;
import static com.example.catuniverse.gameSupport.Collisions.createBaseSizeRect;

//"Пули", которыми стреляют игроки в стратегических уровнях.
//Главное назначение данного класса - отобразить перемещание пуль по экрану
// и сделать возможным обработку их столкновения с врагом
public class StrategyBullet extends GameItem {

    StrategyBullet(int x, int y, Bitmap bitmap) {
        this.x = x;
        this.y = (y + 20);
        this.bitmap = bitmap;
        collLength = collisionDetectLengthViaWidth(BitmapLoader.bullet, 3);
        collisionRect = createBaseSizeRect(x, y);
    }

    @Override
    public void run(GamePaint gamePaint) {
        repaint();
        gamePaint.setVisibleBitmap(bitmap, x, y);
    }

    @Override
    public void repaint() {
        x += 10;
        collisionRect = createBaseSizeRect(x, y);
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

    @Override
    public void setBitmap(Bitmap bitmap) {
        super.setBitmap(bitmap);
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
        return BasicGameSupport.strategyLevelOwner;
    }
}
