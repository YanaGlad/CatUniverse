package com.example.catuniverse.gameSupport.gameTime.platforms;

import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.graphics.GamePaint;
import com.example.catuniverse.gameSupport.helpp.Owner;

import static com.example.catuniverse.gameSupport.BitmapLoader.collTallRect;
import static com.example.catuniverse.gameSupport.CollisionDetectors.checkTallCollision;
import static com.example.catuniverse.gameSupport.Collisions.collisionDetectLengthViaHeight;
import static com.example.catuniverse.gameSupport.Collisions.createCollisionsRect;


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

    @Override
    public boolean checkAcceptParam() {
        return false;
    }

    @Override
    public Owner getOwner() {
        return BasicGameSupport.timeLevelOwner;
    }
}

