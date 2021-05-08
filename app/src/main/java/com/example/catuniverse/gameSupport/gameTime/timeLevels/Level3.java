package com.example.catuniverse.gameSupport.gameTime.timeLevels;

import android.graphics.Color;

import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.CollisionDetectors;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimeTallPlatform;
import com.example.catuniverse.gameSupport.gameTime.TimeInventoryItem;
import com.example.catuniverse.gameSupport.gameTime.TimeLevel;
import com.example.catuniverse.gameSupport.graphics.PlayerManager;
import com.example.catuniverse.gameSupport.graphics.GamePaint;

import java.util.ArrayList;

import static com.example.catuniverse.gameSupport.BitmapLoader.blueGround;
import static com.example.catuniverse.gameSupport.BitmapLoader.bluePlatform;
import static com.example.catuniverse.gameSupport.BitmapLoader.keyBlue;
import static com.example.catuniverse.gameSupport.BitmapLoader.electrodynamixMusic;
import static com.example.catuniverse.gameSupport.BitmapLoader.movingSpaceBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.xStepMusic;

//Третий уровень на время.
public class Level3 extends TimeLevel {
    private ArrayList<TimeTallPlatform> timeTallPlatformArrayList;
    private ArrayList<TimePlatform> changingObstacles;
    private ArrayList<TimeInventoryItem> timeInventoryItems;
    private int collectedCount = 0;
    private boolean firstCount = false, secondCount = false;
    private MainRunActivity mainRunActivity;

    public Level3(MainRunActivity mainRunActivity) {
        super(18, 23, 30, movingSpaceBackground, blueGround, 1, xStepMusic);
        this.mainRunActivity = mainRunActivity;
        timeInventoryItems = new ArrayList<>();
        gameItems = new ArrayList<>();
        changingObstacles = new ArrayList<>();
        gameOver = false;


        timeInventoryItems.add(new TimeInventoryItem(1120, -85, BitmapLoader.keyBlue));
        timeInventoryItems.add(new TimeInventoryItem(1950, -35, BitmapLoader.keyBlue));

        timeTallPlatformArrayList = new ArrayList<>();

        timeTallPlatformArrayList.add(new TimeTallPlatform(1700, GameView.screenHeight - 520));
        timeTallPlatformArrayList.add(new TimeTallPlatform(2000, GameView.screenHeight - 520));
        timeTallPlatformArrayList.add(new TimeTallPlatform(2300, GameView.screenHeight - 520));


        int oY = 490;
        int oX = 300;
        for (int i = 0; i < 5; i++) {
            if ((i + 1) % 2 == 0)
                changingObstacles.add(new TimePlatform(oX, oY, false, bluePlatform));
            else
                changingObstacles.add(new TimePlatform(oX, oY, true, bluePlatform));

            oX += 140;
            oY -= 110;
        }

        oX = 1200;
        oY = 490;
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                gameItems.add(new TimePlatform(oX, oY));
                oX += 100;
                oY -= 100;
            } else {
                if (oX < 2350) {
                    oY += 100;
                    oX = 2350;
                }
                gameItems.add(new TimePlatform(oX, oY));
                oY += 100;
                oX += 100;
            }

        }
        gameItems.add(new TimePlatform(1870, -5));
        gameItems.add(new TimePlatform(2170, -5));

        passingDoor = new BasicButton(mainRunActivity, 3200, 430, BitmapLoader.blueDoor, BitmapLoader.blueDoorOpened, true);
    }

    @Override
    public void run(GamePaint gamePaint) {
        super.run(gamePaint);
        repaint();
        for (GameItem b : gameItems) b.run(gamePaint);
        passingDoor.repaint();
        for (int i = 0; i < changingObstacles.size(); i++) {
            changingObstacles.get(i).run(gamePaint);
            double delay = (Math.random() * (3 - 2)) + 2;
            if ((i + 1) % 2 == 0)
                changingObstacles.get(i).changing(delay);
        }
        for (TimeTallPlatform tb : timeTallPlatformArrayList) tb.run(gamePaint);
        for (TimeInventoryItem timeInventoryItems : timeInventoryItems)
            timeInventoryItems.run(gamePaint);
        passingDoor.run(gamePaint);
        gamePaint.write(collectedCount + "/2", 550, 50, Color.WHITE, 30);
        gamePaint.setVisibleBitmap(keyBlue, 610, 25);
        passingDoor.repaint();
        super.endingRun(gamePaint, mainRunActivity);
    }

    @Override
    public void repaint() {
        super.repaint();
        passingDoor.repaint();
        for (TimeTallPlatform tb : timeTallPlatformArrayList)
            tb.repaint(PlayerManager.timePlayer.getMainPlayerSpeed(), PlayerManager.timePlayer.getJumpSpeed());
        CollisionDetectors.tallPlatformCollision(timeTallPlatformArrayList);

        if (timeInventoryItems.get(0).isPicked() && !firstCount) {
            collectedCount++;
            firstCount = true;
        }
        if (timeInventoryItems.get(1).isPicked() && !secondCount) {
            collectedCount++;
            secondCount = true;
        }

    }

    @Override
    public void repaint(double speed, double jumSpeed) {

    }

    public ArrayList<TimeInventoryItem> getTimeInventoryItems() {
        return timeInventoryItems;
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
    public double getEndTime() {
        return super.getEndTime();
    }

    @Override
    public boolean isRequirementsCollected() {
        int n = 0;
        for (int i = 0; i < getTimeInventoryItems().size(); i++)
            if (!getTimeInventoryItems().get(i).isPicked()) n = 1;

        return n != 1;

    }

    @Override
    public String getRewardId() {
        return null;
    }
}
