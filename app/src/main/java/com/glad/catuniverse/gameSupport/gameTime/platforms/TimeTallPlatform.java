package com.glad.catuniverse.gameSupport.gameTime.platforms;

import android.graphics.Bitmap;

import com.glad.catuniverse.gameSupport.BasicGameSupport;
import com.glad.catuniverse.gameSupport.BitmapLoader;
import com.glad.catuniverse.gameSupport.GameItem;
import com.glad.catuniverse.gameSupport.GameView;
import com.glad.catuniverse.gameSupport.graphics.PlayerManager;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;

//Высокая платформа, на которую игрок может забираться
public class TimeTallPlatform extends GameItem {

    private final CollisionSupportElement collisionSupportElement; //элемент для помощи в обработке коллизий. Чтобы можно было как упираться сбоку, так и стоять на платформе.

    public TimeTallPlatform(int x, int y) {
        this.bitmap = BitmapLoader.tallWall; //временно. Позже будет возможность добавлять универсальное изображение.
        this.x = x;
        this.y = GameView.screenHeight - y;
        controlY = GameView.screenHeight - y;
        speed = 3;
        collisionSupportElement = new CollisionSupportElement(x - 50 + BitmapLoader.tallWallSkeleton.getWidth() + 20, GameView.screenHeight - y);
    }

    @Override
    public void run(GamePaint gamePaint) {
        gamePaint.setVisibleBitmap(bitmap, x + 50, y);
    }

    @Override
    public void repaint(double speed, double jumSpeed) {
        x = BasicGameSupport.updateMovesX(speed, x);
        y = BasicGameSupport.updateMovesY(this, jumSpeed, y);
        collisionSupportElement.repaint(PlayerManager.timePlayer.getMainPlayerSpeed(), PlayerManager.timePlayer.getJumpSpeed());
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
    public Bitmap getBitmap() {
        return super.getBitmap();
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        super.setBitmap(bitmap);
    }
}
