package com.glad.catuniverse.gameSupport.gameTime.platforms;

import com.glad.catuniverse.gameSupport.BasicGameSupport;
import com.glad.catuniverse.gameSupport.GameItem;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;

import static com.glad.catuniverse.gameSupport.BitmapLoader.collTallRect;
import static com.glad.catuniverse.gameSupport.CollisionDetectors.checkTallCollision;
import static com.glad.catuniverse.gameSupport.Collisions.collisionDetectLengthViaHeight;
import static com.glad.catuniverse.gameSupport.Collisions.createCollisionsRect;


public class CollisionSupportElement extends GameItem {

    CollisionSupportElement(int startX, int startY) {
        x = startX;
        y = startY;
        controlY = startY;
        speed = 3;
        collLength = collisionDetectLengthViaHeight(collTallRect, 1.5);
    }

    @Override
    public void run(GamePaint gamePaint) {
        gamePaint.setVisibleBitmap(collTallRect, x, y);
    }

    @Override
    public void repaint(double speed, double jumSpeed) {

        x = BasicGameSupport.updateMovesX(speed, x);
        y = BasicGameSupport.updateMovesY(this, jumSpeed, y);
        collisionRect = createCollisionsRect(x, y, collTallRect);
        checkTallCollision(this);
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
