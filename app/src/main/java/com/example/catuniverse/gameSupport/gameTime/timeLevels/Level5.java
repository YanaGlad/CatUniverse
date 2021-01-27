package com.example.catuniverse.gameSupport.gameTime.timeLevels;

import android.graphics.Color;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.CollisionDetectors;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimeTallPlatform;
import com.example.catuniverse.gameSupport.gameTime.TimeDecoration;
import com.example.catuniverse.gameSupport.gameTime.TimeInventoryItem;
import com.example.catuniverse.gameSupport.gameTime.TimeLevel;
import com.example.catuniverse.gameSupport.graphics.GamePaint;
import com.example.catuniverse.gameSupport.graphics.SpriteAnimation;

import java.util.ArrayList;

import static com.example.catuniverse.gameSupport.BitmapLoader.*;
import static com.example.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

//Пятый уровень на время.
public class Level5 extends TimeLevel {
    private ArrayList<TimeTallPlatform> timeTallPlatformArrayList;
    private ArrayList<TimePlatform> smartObstacles;
    private ArrayList<TimeInventoryItem> timeInventoryItemsG, timeInventoryItemsB;
    private ArrayList<TimeInventoryItem> goodItems, badItems;

    private int[] requestedCount = {20};
    private int[] collectedCount = {0};
    private String[] keyRequested = {"yellowkey"};

    private boolean oneTime = false, oneTime2 = false;

    public Level5(MainRunActivity mainRunActivity) {
        super(50, 70, 100, movingBlueSpaceBackground, purpleGround, 3, phobosMusic);
        gameOver = false;
        gameItems = new ArrayList<>();
        smartObstacles = new ArrayList<>();
        timeInventoryItemsG = new ArrayList<>();
        timeInventoryItemsB = new ArrayList<>();
        timeTallPlatformArrayList = new ArrayList<>();
        goodItems = new ArrayList<>();
        badItems = new ArrayList<>();

        SpriteAnimation asteroid = new SpriteAnimation(asteroidSprite);

        goodItems.add(new TimeInventoryItem(1000, -919, yellowKey, true, true, mainRunActivity.getString(R.string.YellowKey)));
        badItems.add(new TimeInventoryItem(1000, -919, asteroid, true, false, mainRunActivity.getString(R.string.Asteroid), false));


        gameItems.add(new TimePlatform(400, 490, purplePlatform));
        gameItems.add(new TimePlatform(600, 420, purplePlatform));
        smartObstacles.add(new TimePlatform(800, 360, purplePlatform));
        gameItems.add(new TimePlatform(1000, 420, purplePlatform));
        gameItems.add(new TimePlatform(1200, 490, purplePlatform));

        gameItems.add(new TimePlatform(1300, 400, purplePlatform));
        gameItems.add(new TimePlatform(1500, 320, purplePlatform));
        gameItems.add(new TimePlatform(1350, 240, purplePlatform));
        smartObstacles.add(new TimePlatform(1230, 160, rocketStation));

        gameItems.add(new TimeDecoration(2100, -130, purpleDecorStation,true)); //сделать фиолетовым + добавить синюю в один из уровней

        gameItems.add(new TimePlatform(3350, 240, purplePlatform));
        gameItems.add(new TimePlatform(3500, 360, purplePlatform));
        gameItems.add(new TimePlatform(3650, 480, purplePlatform));

        gameItems.add(new TimePlatform(3050, 490, purplePlatform));
        gameItems.add(new TimePlatform(2900, 430, purplePlatform));
        gameItems.add(new TimePlatform(2750, 370, purplePlatform));

        passingDoor = new BasicButton(mainRunActivity, 808, 218, purpleDoor, purpleDoorOpened, true);
        gameItems.add(passingDoor);
    }

    @Override
    public void run(GamePaint gamePaint) {
        super.run(gamePaint);
        repaint();

        if (!timePlayer.isRocketMode()) {
            passingDoor.repaint();
            for (GameItem b : gameItems) b.run(gamePaint);
            for (TimeTallPlatform tb : timeTallPlatformArrayList) tb.run(gamePaint);
            for (TimePlatform sm : smartObstacles) sm.run(gamePaint);
            passingDoor.repaint();
        } else {
            for (TimeInventoryItem tm : timeInventoryItemsG) tm.run(gamePaint);
            for (TimeInventoryItem tm : timeInventoryItemsB) tm.run(gamePaint);
        }

        if (smartObstacles.get(1).isPlayerOn() && !isRequirementsCollected()) {
            timePlayer.setRocketMode(true);
            super.drawThreeRoadLines(gamePaint);
            if (!oneTime) {
                timePlayer.setX(30);
                timePlayer.setY(620);

                oneTime = true;
            }

        } else {
            if (isRequirementsCollected() && !oneTime2) {
                for (GameItem b : gameItems) b.run(gamePaint);
                for (TimeTallPlatform tb : timeTallPlatformArrayList) tb.run(gamePaint);
                for (TimePlatform sm : smartObstacles) sm.run(gamePaint);

                oneTime2 = true;
            }

            timePlayer.setRocketMode(false);
        }


        gamePaint.write(collectedCount[0] + "/" + requestedCount[0], 550, 50, Color.WHITE, 35);
        gamePaint.setVisibleBitmap(yellowKey, 645, 15);

        super.endingRun(gamePaint);
    }

    @Override
    public void repaint() {
        super.repaint();
        if (!timePlayer.isRocketMode()) {
            passingDoor.repaint();
            for (TimeTallPlatform tb : timeTallPlatformArrayList)
                tb.repaint(timePlayer.getMainPlayerSpeed(), timePlayer.getJumpSpeed());

            CollisionDetectors.tallPlatformCollision(205, 305, timeTallPlatformArrayList);
        } else
            super.generateRocketItems(timeInventoryItemsG, timeInventoryItemsB, goodItems, badItems, requestedCount, keyRequested, collectedCount);


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
        return requestedCount[0] == collectedCount[0];
    }

    @Override
    public String getRewardId() {
        return null;
    }
}