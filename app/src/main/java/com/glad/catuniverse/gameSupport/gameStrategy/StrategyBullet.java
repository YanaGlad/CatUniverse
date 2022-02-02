package com.glad.catuniverse.gameSupport.gameStrategy;

import android.graphics.Bitmap;
import com.glad.catuniverse.gameSupport.BitmapLoader;
import com.glad.catuniverse.gameSupport.GameItem;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;
import com.glad.catuniverse.gameSupport.Collisions;

import static com.glad.catuniverse.gameSupport.Collisions.createBaseSizeRect;

//"Пули", которыми стреляют игроки в стратегических уровнях.
//Главное назначение данного класса - отобразить перемещание пуль по экрану
// и сделать возможным обработку их столкновения с врагом
public class StrategyBullet extends GameItem {

    StrategyBullet(int x, int y, Bitmap bitmap) {
        this.x = x;
        this.y = (y + 20);
        this.bitmap = bitmap;
        collLength = Collisions.collisionDetectLengthViaWidth(BitmapLoader.bullet, 3);
        collisionRect = Collisions.createBaseSizeRect(x, y);
    }

    @Override
    public void run(GamePaint gamePaint) {
        repaint();
        gamePaint.setVisibleBitmap(bitmap, x, y);
    }

    @Override
    public void repaint() {
        x += 10;
        collisionRect = Collisions.createBaseSizeRect(x, y);
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
}
