package com.example.catuniverse.gameSupport.gameTime.timeLevels;

import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.gameTime.TimeDecoration;
import com.example.catuniverse.gameSupport.gameTime.TimeLevel;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimeTallPlatform;
import com.example.catuniverse.gameSupport.graphics.GamePaint;

import static com.example.catuniverse.gameSupport.BitmapLoader.blueDecorStation;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoor;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoorOpened;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueGround;
import static com.example.catuniverse.gameSupport.BitmapLoader.electrodynamixMusic;
import static com.example.catuniverse.gameSupport.BitmapLoader.movingSpaceBackground;

//В РАЗРАБОТКЕ
public class Level8 extends TimeLevel {
    private MainRunActivity mainRunActivity;

    public Level8(MainRunActivity mainRunActivity) {
        super(10, 15, 20, movingSpaceBackground, blueGround, 1, electrodynamixMusic);
        this.mainRunActivity = mainRunActivity;
        gameOver = false;


        passingDoor = new BasicButton(mainRunActivity, 110, 430, blueDoor, blueDoorOpened, true);

    }

    @Override
    public void run(GamePaint gamePaint) {
        super.run(gamePaint);
        repaint();
        passingDoor.repaint();
        super.endingRun(gamePaint, mainRunActivity);
    }

    @Override
    public void repaint() {
        super.repaint();
        passingDoor.repaint();
     }

    @Override
    public void repaint(double speed, double jumSpeed) {
    }

    @Override
    public double getEndTime() {
        return super.getEndTime();
    }

    @Override
    public BasicButton getPassingDoor() {
        return super.getPassingDoor();
    }

    @Override
    public boolean isGameOver() {
        return super.isGameOver();
    }

    @Override
    public boolean isRequirementsCollected() {
        return true;
    }

    @Override
    public boolean unlockAchievement() {
        return false;
    }

    @Override
    public int getAchievementId() {
        return -1;
    }

    @Override
    public String getRewardId() {
        return null;
    }

}