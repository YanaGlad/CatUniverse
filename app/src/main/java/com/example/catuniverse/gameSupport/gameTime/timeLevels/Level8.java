package com.example.catuniverse.gameSupport.gameTime.timeLevels;

import android.graphics.Color;
import android.util.Log;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.gameTime.TimeDecoration;
import com.example.catuniverse.gameSupport.gameTime.TimeInventoryItem;
import com.example.catuniverse.gameSupport.gameTime.TimeLevel;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimeTallPlatform;
import com.example.catuniverse.gameSupport.graphics.GamePaint;
import com.example.catuniverse.gameSupport.graphics.SpriteAnimation;

import java.util.ArrayList;

import static com.example.catuniverse.gameSupport.BitmapLoader.asteroidSprite;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoor;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoorOpened;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueGround;
import static com.example.catuniverse.gameSupport.BitmapLoader.bluePlatform;
import static com.example.catuniverse.gameSupport.BitmapLoader.electrodynamixMusic;
import static com.example.catuniverse.gameSupport.BitmapLoader.keyBlue;
import static com.example.catuniverse.gameSupport.BitmapLoader.movingSpaceBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.rocketDecor;
import static com.example.catuniverse.gameSupport.BitmapLoader.rocketStation;
import static com.example.catuniverse.gameSupport.BitmapLoader.yellowKey;
import static com.example.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

//В РАЗРАБОТКЕ
public class Level8 extends TimeLevel {
    private final MainRunActivity mainRunActivity;
    private final TimePlatform station;
    private final int[] requestedCount = {20, 20};
    private final int[] collectedCount = {0, 0};
    private final ArrayList<TimeInventoryItem> timeInventoryItemsG;
    private final ArrayList<TimeInventoryItem> timeInventoryItemsB;
    private final ArrayList<TimeInventoryItem> goodItems;
    private final ArrayList<TimeInventoryItem> badItems;
    private final String[] keyRequested = {"yellowkey", "bluekey"};

    public Level8(MainRunActivity mainRunActivity) {
        super(100, 60, 150, movingSpaceBackground, blueGround, 1, electrodynamixMusic);
        this.mainRunActivity = mainRunActivity;
        gameOver = false;

        timeInventoryItemsG = new ArrayList<>();
        timeInventoryItemsB = new ArrayList<>();
        goodItems = new ArrayList<>();
        badItems = new ArrayList<>();

        goodItems.add(new TimeInventoryItem(1000, -919, yellowKey, true, true, mainRunActivity.getString(R.string.YellowKey)));
        goodItems.add(new TimeInventoryItem(1000, -919, keyBlue, true, true, mainRunActivity.getString(R.string.BlueKey)));

        SpriteAnimation asteroid = new SpriteAnimation(asteroidSprite);
        badItems.add(new TimeInventoryItem(1000, -919, asteroid, true, false, mainRunActivity.getString(R.string.Asteroid), false));


        int yY = 550;
        int xX = 700;
        for (int i = 0; i < 10; i++) {
            gameItems.add(new TimePlatform(xX, yY, bluePlatform));
            xX += 100;
            yY -= 70;
        }

        xX += 40;
        yY += 70;
        station = new TimePlatform(xX, yY - 100, rocketStation);

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
        if (!timePlayer.isRocketMode()) {
            passingDoor.repaint();
            for (GameItem b : gameItems) b.run(gamePaint);
            station.run(gamePaint);
            for (TimeTallPlatform tb : timeTallPlatformArrayList) tb.run(gamePaint);
        } else {
            super.drawThreeRoadLines(gamePaint);
            for (TimeInventoryItem tm : timeInventoryItemsG) tm.run(gamePaint);
            for (TimeInventoryItem tm : timeInventoryItemsB) tm.run(gamePaint);
        }
        if (station.isPlayerOn() && !isRequirementsCollected()) {
            timePlayer.setRocketMode(true);
            super.drawThreeRoadLines(gamePaint);
        } else if (isRequirementsCollected()) {
            passingDoor.setClicked(true);
        }
        gamePaint.write(collectedCount[0] + "/" + requestedCount[0], 590, 50, Color.WHITE, 35);
        gamePaint.setVisibleBitmap(yellowKey, 675, 15);
        gamePaint.write(collectedCount[1] + "/" + requestedCount[1], 470, 50, Color.WHITE, 35);
        gamePaint.setVisibleBitmap(keyBlue, 550, 23);

        super.endingRun(gamePaint, mainRunActivity);
    }

    @Override
    public void repaint() {
        super.repaint();
        if (!timePlayer.isRocketMode()) {
            passingDoor.repaint();
            super.tallPlatformRepaint();
        } else {
            super.generateRocketItems(timeInventoryItemsG, timeInventoryItemsB, goodItems, badItems, requestedCount, keyRequested, collectedCount);
        }
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
