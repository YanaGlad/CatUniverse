package com.example.catuniverse.gameSupport.gameTime.timeLevels;

import android.graphics.Color;

import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.CollisionDetectors;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimeTallPlatform;
import com.example.catuniverse.gameSupport.gameTime.TimeInventoryItem;
import com.example.catuniverse.gameSupport.gameTime.TimeLevel;
import com.example.catuniverse.gameSupport.gameTime.TimePlayer;
import com.example.catuniverse.gameSupport.graphics.GamePaint;

import java.util.ArrayList;

import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoor;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoorOpened;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueGround;
import static com.example.catuniverse.gameSupport.BitmapLoader.keyBlue;
import static com.example.catuniverse.gameSupport.BitmapLoader.electrodynamixMusic;
import static com.example.catuniverse.gameSupport.BitmapLoader.movingSpaceBackground;
import static com.example.catuniverse.gameSupport.GameView.screenHeight;
import static com.example.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

//Первый уровень на время.
public class Level1 extends TimeLevel {
    private boolean ladder2Disappear;
    private TimeInventoryItem timeInventoryItem;
    private ArrayList<TimeTallPlatform> timeTallPlatformArrayList;
    private ArrayList<TimePlatform> ladderDisapearLvl0, bigObstaclesDisappearLvl0;
    private BasicButton appearButton;

    private int collectedCount = 0;

    public Level1(MainRunActivity mainRunActivity) {
        super(20, 25, 30, movingSpaceBackground, blueGround, 1, electrodynamixMusic);

        gameOver = false;

        timeInventoryItem = new TimeInventoryItem(700, 240, keyBlue);

        appearButton = new BasicButton(mainRunActivity, 2700, 490, BitmapLoader.appearButton, BitmapLoader.appearButton, true);
        passingDoor = new BasicButton(mainRunActivity, 4000, 430, blueDoor, blueDoorOpened, true);

        gameItems = new ArrayList<>();
        gameItems.add(appearButton);
        gameItems.add(passingDoor);

        ladderDisapearLvl0 = new ArrayList<>();
        bigObstaclesDisappearLvl0 = new ArrayList<>();
        timeTallPlatformArrayList = new ArrayList<>();
        ladder2Disappear = false;

        gameItems.add(new TimePlatform(470, 380));
        gameItems.add(new TimePlatform(650, 270));
        gameItems.add(new TimePlatform(650, 470));
        gameItems.add(new TimePlatform(900, 470));
        gameItems.add(new TimePlatform(1070, 360));

        gameItems.add(new TimePlatform(1500, 470));
        gameItems.add(new TimePlatform(1600, 370));
        gameItems.add(new TimePlatform(1700, 270));
        gameItems.add(new TimePlatform(1800, 170));
        gameItems.add(new TimePlatform(1950, 100));
        gameItems.add(new TimePlatform(3250, 180));
        gameItems.add(new TimePlatform(3400, 260));
        gameItems.add(new TimePlatform(3550, 380));
        gameItems.add(new TimePlatform(3700, 470));
        bigObstaclesDisappearLvl0.add(new TimePlatform(2680, 220));
        bigObstaclesDisappearLvl0.add(new TimePlatform(2820, 120));
        bigObstaclesDisappearLvl0.add(new TimePlatform(2950, 50));
        bigObstaclesDisappearLvl0.add(new TimePlatform(3100, 120));

        int ladderX = 2050, ladderY = 100;
        for (int i = 0; i < 6; i++) {
            ladderDisapearLvl0.add(new TimePlatform(ladderX, ladderY));
            ladderX += 150;
            ladderY += 70;
        }

        timeTallPlatformArrayList.add(new TimeTallPlatform(2000, screenHeight - 520));
        timeTallPlatformArrayList.add(new TimeTallPlatform(3200, screenHeight - 520));

    }


    @Override
    public void run(GamePaint gamePaint) {
        super.run(gamePaint);
        repaint();

        for (GameItem item : gameItems) item.run(gamePaint);
        for (TimePlatform obs2 : ladderDisapearLvl0)
            if (!ladder2Disappear) obs2.run(gamePaint);
            else obs2.repaint(timePlayer.getMainPlayerSpeed(), timePlayer.getJumpSpeed());
        for (TimePlatform obs2 : bigObstaclesDisappearLvl0) obs2.run(gamePaint);
        for (TimeTallPlatform tb : timeTallPlatformArrayList) tb.run(gamePaint);
        timeInventoryItem.run(gamePaint);
        passingDoor.repaint();

        gamePaint.write(collectedCount + "/1", 550, 50, Color.WHITE, 30);
        gamePaint.setVisibleBitmap(keyBlue, 610, 25);
        super.endingRun(gamePaint);
    }

    @Override
    public void repaint() {
        super.repaint();
        passingDoor.repaint();

        for (TimeTallPlatform tb : timeTallPlatformArrayList)
            tb.repaint(timePlayer.getMainPlayerSpeed(), timePlayer.getJumpSpeed());
        CollisionDetectors.tallPlatformCollision(timeTallPlatformArrayList);

        if (appearButton.isClicked()) ladder2Disappear = false;
        if (timeTallPlatformArrayList.get(0).getX() < timePlayer.getX() && timePlayer.getY() >= TimePlayer.getMaximumY() && !appearButton.isClicked())
            ladder2Disappear = true;
        if (appearButton.isClicked()) ladder2Disappear = false;

        if (timeInventoryItem.isPicked()) collectedCount = 1;
    }

    @Override
    public void repaint(double speed, double jumSpeed) {
    }


    @Override
    public BasicButton getPassingDoor() {
        return passingDoor;
    }

    public TimeInventoryItem getTimeInventoryItem() {
        return timeInventoryItem;
    }

    @Override
    public double getEndTime() {
        return super.getEndTime();
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public int getThreeStars() {
        return super.getThreeStars();
    }

    @Override
    public int getTwoStars() {
        return super.getTwoStars();
    }

    @Override
    public boolean isRequirementsCollected() {
        return timeInventoryItem.isPicked();
    }

    @Override
    public String getRewardId() {
        return "2";
    }

}
