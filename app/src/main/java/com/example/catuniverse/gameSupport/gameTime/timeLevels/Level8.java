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
import static com.example.catuniverse.gameSupport.BitmapLoader.bluePlatform;
import static com.example.catuniverse.gameSupport.BitmapLoader.electrodynamixMusic;
import static com.example.catuniverse.gameSupport.BitmapLoader.movingSpaceBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.rocketDecor;

//В РАЗРАБОТКЕ
public class Level8 extends TimeLevel {
    private MainRunActivity mainRunActivity;

    public Level8(MainRunActivity mainRunActivity) {
        super(10, 15, 220, movingSpaceBackground, blueGround, 1, electrodynamixMusic);
        this.mainRunActivity = mainRunActivity;
        gameOver = false;


        int yY = 550;
        int xX = 700;
        for (int i = 0; i < 10; i++) {
            gameItems.add(new TimePlatform(xX, yY, bluePlatform));
            xX += 100;
            yY -= 70;
        }

        xX+=40;
        yY+=70;
        for (int i = 0; i < 10; i++) {
            gameItems.add(new TimePlatform(xX, yY, bluePlatform));
            xX += 100;
            yY += 70;
        }
        gameItems.add(new TimeDecoration(500, -600, rocketDecor, true));

        timeTallPlatformArrayList.add(new TimeTallPlatform(1655, 520));
        timeTallPlatformArrayList.add(new TimeTallPlatform(1655, 685));

        passingDoor = new BasicButton(mainRunActivity, 110, 430, blueDoor, blueDoorOpened, true);

    }

    @Override
    public void run(GamePaint gamePaint) {
        super.run(gamePaint);
        repaint();
        passingDoor.repaint();
        for (GameItem b : gameItems) b.run(gamePaint);
        for (TimeTallPlatform tb : timeTallPlatformArrayList) tb.run(gamePaint);
        super.endingRun(gamePaint, mainRunActivity);
    }

    @Override
    public void repaint() {
        super.repaint();
        passingDoor.repaint();
        super.tallPlatformRepaint();

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