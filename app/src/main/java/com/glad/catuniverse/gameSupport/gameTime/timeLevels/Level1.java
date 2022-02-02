package com.glad.catuniverse.gameSupport.gameTime.timeLevels;

import android.graphics.Color;

import com.glad.catuniverse.gameSupport.BitmapLoader;
import com.glad.catuniverse.gameSupport.Buttons.BasicButton;
import com.glad.catuniverse.gameSupport.GameItem;
import com.glad.catuniverse.gameSupport.MainRunActivity;
import com.glad.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.glad.catuniverse.gameSupport.gameTime.platforms.TimeTallPlatform;
import com.glad.catuniverse.gameSupport.gameTime.TimeInventoryItem;
import com.glad.catuniverse.gameSupport.gameTime.TimeLevel;
import com.glad.catuniverse.gameSupport.gameTime.TimePlayer;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;
import com.glad.catuniverse.gameSupport.graphics.PlayerManager;

import java.util.ArrayList;

import static com.glad.catuniverse.gameSupport.BitmapLoader.blueDoor;
import static com.glad.catuniverse.gameSupport.BitmapLoader.blueDoorOpened;
import static com.glad.catuniverse.gameSupport.BitmapLoader.blueGround;
import static com.glad.catuniverse.gameSupport.BitmapLoader.keyBlue;
import static com.glad.catuniverse.gameSupport.BitmapLoader.electrodynamixMusic;
import static com.glad.catuniverse.gameSupport.BitmapLoader.movingSpaceBackground;

//Первый уровень на время.
public class Level1 extends TimeLevel {
    private boolean ladder2Disappear;
    private final TimeInventoryItem timeInventoryItem;
    private final ArrayList<TimePlatform> ladderDisapearLvl0;
    private final ArrayList<TimePlatform> bigObstaclesDisappearLvl0;
    private final BasicButton appearButton;
    private final MainRunActivity mainRunActivity;
    private int collectedCount = 0;

    private static Level1 level1 = null;

    public Level1 getInstance() {
        if (level1 == null) {
            level1 = new Level1(mainRunActivity);
        }
        return level1;
    }

    public Level1 getInstance(boolean restart) {
        if (level1 == null || restart) {
            level1 = new Level1(mainRunActivity);
        }
        return level1;
    }

    public Level1(MainRunActivity mainRunActivity) {
        super(20, 25, 30, movingSpaceBackground, blueGround, 1, electrodynamixMusic);
        this.mainRunActivity = mainRunActivity;
        gameOver = false;

        timeInventoryItem = new TimeInventoryItem(700, 240, keyBlue);

        appearButton = new BasicButton(mainRunActivity, 2700, 490, BitmapLoader.appearButton, BitmapLoader.appearButton, true);
        passingDoor = new BasicButton(mainRunActivity, 4000, 430, blueDoor, blueDoorOpened, true);

        gameItems.add(appearButton);
        gameItems.add(passingDoor);

        ladderDisapearLvl0 = new ArrayList<>();
        bigObstaclesDisappearLvl0 = new ArrayList<>();
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

        timeTallPlatformArrayList.add(new TimeTallPlatform(2000, 520));
        timeTallPlatformArrayList.add(new TimeTallPlatform(3200, 520));

    }


    @Override
    public void run(GamePaint gamePaint) {
        super.run(gamePaint);
        repaint();

        for (GameItem item : gameItems) item.run(gamePaint);
        for (TimePlatform obs2 : ladderDisapearLvl0)
            if (!ladder2Disappear) obs2.run(gamePaint);
            else obs2.repaint(PlayerManager.timePlayer.getMainPlayerSpeed(), PlayerManager.timePlayer.getJumpSpeed());
        for (TimePlatform obs2 : bigObstaclesDisappearLvl0) obs2.run(gamePaint);
        for (TimeTallPlatform tb : timeTallPlatformArrayList) tb.run(gamePaint);
        timeInventoryItem.run(gamePaint);
        passingDoor.repaint();

        gamePaint.write(collectedCount + "/1", 550, 50, Color.WHITE, 30);
        gamePaint.setVisibleBitmap(keyBlue, 610, 25);
        super.endingRun(gamePaint, mainRunActivity);
    }

    @Override
    public void repaint() {
        super.repaint();
        passingDoor.repaint();

        super.tallPlatformRepaint();

        if (appearButton.isClicked()) ladder2Disappear = false;
        if (timeTallPlatformArrayList.get(0).getX() < PlayerManager.timePlayer.getX() && PlayerManager.timePlayer.getY() >= TimePlayer.getMaximumY() && !appearButton.isClicked())
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
    public boolean unlockAchievement() {
        return false;
    }

    @Override
    public int getAchievementId() {
        return -1;
    }

    @Override
    public String getRewardId() {
        return "2";
    }

}
