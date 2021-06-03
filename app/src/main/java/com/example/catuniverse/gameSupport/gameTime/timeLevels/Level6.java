package com.example.catuniverse.gameSupport.gameTime.timeLevels;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.CollisionDetectors;
import com.example.catuniverse.gameSupport.EasyTimer;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.gameTime.TimeDecoration;
import com.example.catuniverse.gameSupport.gameTime.TimeInventoryItem;
import com.example.catuniverse.gameSupport.gameTime.TimeLevel;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimeTallPlatform;
import com.example.catuniverse.gameSupport.graphics.GamePaint;
import com.example.catuniverse.gameSupport.graphics.SpriteAnimation;

import java.util.ArrayList;
import java.util.Random;

import static com.example.catuniverse.gameSupport.BitmapLoader.asteroidSprite;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDecorStation;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoor;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoorOpened;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueGround;
import static com.example.catuniverse.gameSupport.BitmapLoader.bluePlatform;
import static com.example.catuniverse.gameSupport.BitmapLoader.electrodynamixMusic;
import static com.example.catuniverse.gameSupport.BitmapLoader.movingBlueSpaceBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.movingSpaceBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.purplePlatform;
import static com.example.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

//Шестой уровень на время.
public class Level6 extends TimeLevel {
    private ArrayList<TimeInventoryItem> asteroids;
    private SpriteAnimation asteroid;
    private EasyTimer easyTimer;
    private ArrayList<TimePlatform> changingPurplePlatforms;
    private MainRunActivity mainRunActivity;
    private ArrayList<TimeInventoryItem> timeInventoryItems;


    public Level6(MainRunActivity mainRunActivity) {
        super(35, 45, 60, movingBlueSpaceBackground, blueGround, 5, electrodynamixMusic);
        this.mainRunActivity = mainRunActivity;

        gameOver = false;

        changingPurplePlatforms = new ArrayList<>();
        asteroids = new ArrayList<>();
        timeInventoryItems = new ArrayList<>();

        timeTallPlatformArrayList.add(new TimeTallPlatform(2050, 520));
        timeTallPlatformArrayList.add(new TimeTallPlatform(2050, 900));
        timeTallPlatformArrayList.add(new TimeTallPlatform(4050, 520));
        timeTallPlatformArrayList.add(new TimeTallPlatform(4050, 900));

        asteroid = new SpriteAnimation(asteroidSprite);

        asteroids.add(new TimeInventoryItem(900, 500, asteroid, true, false, mainRunActivity.getString(R.string.Asteroid), true));


        int yY = 550;
        int xX = 700;
        for (int i = 0; i < 20; i++) {
            gameItems.add(new TimePlatform(xX, yY, bluePlatform));
            xX += 100;
            yY -= 70;
        }

        xX = 2100;
        yY += 600;
        for (int i = 0; i < 14; i++) {
            changingPurplePlatforms.add(new TimePlatform(xX, yY, purplePlatform));
            xX += 135;

            if (i % 2 == 0)
                timeInventoryItems.add(new TimeInventoryItem(xX + 70, yY - 20, BitmapLoader.keyBlue));

        }

        passingDoor = new BasicButton(mainRunActivity, 4070, yY - 200, blueDoor, blueDoorOpened, true);


        easyTimer = new EasyTimer();
        easyTimer.startTimer();
        gameItems.add(passingDoor);
        gameItems.add(new TimeDecoration(2120, 500, BitmapLoader.sharps, false));

    }

    @Override
    public void run(GamePaint gamePaint) {
        super.run(gamePaint);
        repaint();
        for (GameItem b : gameItems) b.run(gamePaint);
        for (TimeInventoryItem as : asteroids) as.run(gamePaint);
        passingDoor.repaint();
        for (TimeTallPlatform tb : timeTallPlatformArrayList) tb.run(gamePaint);
        for (TimePlatform tp : changingPurplePlatforms) tp.run(gamePaint);
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

        for (int i = 0; i < asteroids.size(); i++) {
            if (asteroids.get(i).isPicked() && asteroids.size() > 0) {
                lives--;
                asteroids.remove(i);
                continue;
            }
            if (asteroids.get(i).getX() < 0) asteroids.remove(i);
        }

        for (int i = 0; i < changingPurplePlatforms.size(); i++) {
            if ((i + 1) % 2 == 0) changingPurplePlatforms.get(i).changing(2);
        }

        Random random = new Random();

        if (asteroids.size() < 5 && !(timeTallPlatformArrayList.get(0).getX() <= 305)) {
            if (easyTimer.timerDelay(2)) {
                asteroids.add(new TimeInventoryItem(900, random.nextInt(500), asteroid, true, false, "asteroid", true));
                easyTimer.startTimer();
            }
        }

        if (timeTallPlatformArrayList.get(0).getX() <= 305 && timePlayer.getFakeY() >= 310)
            super.setGameOver();

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

    private ArrayList<TimeInventoryItem> getTimeInventoryItems() {
        return timeInventoryItems;
    }

    @Override
    public boolean isRequirementsCollected() {
        return super.inventoryItemsLeft(getTimeInventoryItems());
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