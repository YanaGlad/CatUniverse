package com.example.catuniverse.gameSupport.gameTime.timeLevels;

import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoor;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoorOpened;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueGround;
import static com.example.catuniverse.gameSupport.BitmapLoader.electrodynamixMusic;
import static com.example.catuniverse.gameSupport.BitmapLoader.movingBlueSpaceBackground;

import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.gameTime.TimeInventoryItem;
import com.example.catuniverse.gameSupport.gameTime.TimeLevel;
import com.example.catuniverse.gameSupport.graphics.GamePaint;

import java.util.ArrayList;

//В РАЗРАБОТКЕ
public class Level9 extends TimeLevel {

    private final MainRunActivity mainRunActivity;
    private final ArrayList<TimeInventoryItem> timeInventoryItems;

    public Level9(MainRunActivity mainRunActivity) {
        super(10, 15, 220, movingBlueSpaceBackground, blueGround, 1, electrodynamixMusic);

        timeInventoryItems = new ArrayList<>();

        int xX = 400;

        for (int i = 0; i < 10; i++) {
            timeInventoryItems.add(new TimeInventoryItem(xX, 550, BitmapLoader.keyBlue));
            xX += 100;
        }

        this.mainRunActivity = mainRunActivity;
        gameOver = false;

        passingDoor = new BasicButton(mainRunActivity, 110, 430, blueDoor, blueDoorOpened, true);

        gameItems.add(passingDoor);
    }

    @Override
    public void run(GamePaint gamePaint) {
        super.run(gamePaint);
        repaint();
        for (TimeInventoryItem timeInventoryItems : timeInventoryItems)
            timeInventoryItems.run(gamePaint);
        passingDoor.repaint();
        super.endingRun(gamePaint, mainRunActivity);
    }

    @Override
    public void repaint() {
        super.repaint();
        passingDoor.repaint();
        super.tallPlatformRepaint();
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
        for (int i = 0; i < timeInventoryItems.size(); i++) {
            if (!timeInventoryItems.get(i).isPicked())
                return false;
        }
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
