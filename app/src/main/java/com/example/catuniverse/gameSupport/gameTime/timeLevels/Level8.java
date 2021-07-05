package com.example.catuniverse.gameSupport.gameTime.timeLevels;

import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.gameTime.TimeDecoration;
import com.example.catuniverse.gameSupport.gameTime.TimeLevel;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimeTallPlatform;
import com.example.catuniverse.gameSupport.graphics.GamePaint;

import java.util.ArrayList;

import static com.example.catuniverse.gameSupport.BitmapLoader.blueDecorStation;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoor;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoorOpened;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueGround;
import static com.example.catuniverse.gameSupport.BitmapLoader.bluePlatform;
import static com.example.catuniverse.gameSupport.BitmapLoader.electrodynamixMusic;
import static com.example.catuniverse.gameSupport.BitmapLoader.movingSpaceBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.rocketDecor;
import static com.example.catuniverse.gameSupport.BitmapLoader.rocketStation;
import static com.example.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

//В РАЗРАБОТКЕ
public class Level8 extends TimeLevel {
    private MainRunActivity mainRunActivity;
    private TimePlatform station;
    private int[] requestedCount = {20, 10};
    private int[] collectedCount = {0, 0};
    private boolean oneTime = false;

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

        xX += 40;
        yY += 70;
        station = new TimePlatform(xX, yY-50, rocketStation);

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
        station.run(gamePaint);
        for (TimeTallPlatform tb : timeTallPlatformArrayList) tb.run(gamePaint);

        if (station.isPlayerOn() && !isRequirementsCollected()) {
            timePlayer.setRocketMode(true);
            super.drawThreeRoadLines(gamePaint);
            if (!oneTime) {
                timePlayer.setX(30);
                timePlayer.setY(620);
                oneTime = true;
            }
        }

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
        return (requestedCount[0] == collectedCount[0]) && (requestedCount[1] == collectedCount[1]);
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