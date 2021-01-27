package com.example.catuniverse.gameSupport.gameTime;

import android.graphics.Bitmap;
import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.graphics.GamePaint;
import java.util.ArrayList;
import static com.example.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

//Генерирует землю в уровнях
public class TimeGround extends GameItem {
    private ArrayList<Ground> ground;

     TimeGround(Bitmap appearance) {
        TimePlayer.start = 50;
        x = -845 * 5;
        ground = new ArrayList<>();
        for (int i = 0; i < 50; i++) { //генерирует 50 участков земли.
            ground.add(new Ground(x, appearance));
            x += 845;
        }
    }

    @Override
    public void run(GamePaint gamePaint) {
        repaint(timePlayer.getMainPlayerSpeed(), timePlayer.getJumpSpeed());
        for (int i = 0; i < ground.size(); i++) {
            ground.get(i).run(gamePaint);
        }
    }

    @Override
    public void repaint(double speed, double jumSpeed) {
        if (timePlayer.isMovingLeft() && !timePlayer.isCollisionDetectedRight() && TimePlayer.start >= -550)
            TimePlayer.start -= speed;
        if (timePlayer.isMovingRight() && !timePlayer.isCollisionDetectedLeft())
            TimePlayer.start += speed;
    }

    class Ground extends GameItem {

        private Ground(int x, Bitmap bitmap) {
            this.x = x;
            this.bitmap = bitmap;
            controlY = 520;
            y = 520;
        }

        public void repaint(double speed, double jumSpeed) {
            x = BasicGameSupport.updateMovesX(speed, x);
            y = BasicGameSupport.updateMovesY(this, jumSpeed, y);
        }

        public void run(GamePaint gamePaint) {
            if (!timePlayer.isRocketMode()) {
                repaint(timePlayer.getMainPlayerSpeed(), timePlayer.getJumpSpeed());
                gamePaint.setVisibleBitmap(bitmap, x, y);
            }
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

