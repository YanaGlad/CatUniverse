package com.example.catuniverse.gameSupport.gameTime.timeLevels;

import android.graphics.Bitmap;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.CollisionDetectors;
import com.example.catuniverse.gameSupport.EasyTimer;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.Media;
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
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoor;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoorOpened;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueGround;
import static com.example.catuniverse.gameSupport.BitmapLoader.bluePlatform;
import static com.example.catuniverse.gameSupport.BitmapLoader.electrodynamixMusic;
import static com.example.catuniverse.gameSupport.BitmapLoader.movingBlueSpaceBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.purpleDoor;
import static com.example.catuniverse.gameSupport.BitmapLoader.purpleDoorOpened;
import static com.example.catuniverse.gameSupport.BitmapLoader.purplePlatform;
import static com.example.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

//В РАЗРАБОТКЕ
public class Level7 extends TimeLevel {

    private final ArrayList<TimeInventoryItem> asteroids;
    private final SpriteAnimation asteroid;
    private final EasyTimer easyTimer;
    private final MainRunActivity mainRunActivity;
    private final ArrayList<TimeInventoryItem> timeInventoryItems;

    public Level7(MainRunActivity mainRunActivity) {
        super(35, 45, 260, movingBlueSpaceBackground, blueGround, 5, electrodynamixMusic);
        this.mainRunActivity = mainRunActivity;
        gameOver = false;
        asteroids = new ArrayList<>();
        timeInventoryItems = new ArrayList<>();
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();

        asteroid = new SpriteAnimation(asteroidSprite);
        asteroids.add(new TimeInventoryItem(900, 500, asteroid, true, false, mainRunActivity.getString(R.string.Asteroid), true));

        int n;
        for (int i = -20; i < 7; i++) {
            if (i % 2 != 0) {
                n = 200;
            } else {
                n = 100;
            }

            for (int j = -5; j < 11; j++) {
                gameItems.add(new TimePlatform(n + 250 * j, 90 * i, bluePlatform));
                x.add(n + 250 * j);
                y.add(90 * i);
            }
        }

        easyTimer = new EasyTimer();
        easyTimer.startTimer();

        int pos = new Random().nextInt(x.size());
        passingDoor = new BasicButton(mainRunActivity, x.get(pos) + 10, y.get(pos) + 30, purpleDoor, purpleDoorOpened, true);

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
        for (TimeInventoryItem timeInventoryItems : timeInventoryItems)
            timeInventoryItems.run(gamePaint);
        super.endingRun(gamePaint, mainRunActivity);
    }

    @Override
    public void repaint() {
        super.repaint();
        passingDoor.repaint();

        for (int i = 0; i < asteroids.size(); i++) {
            if (asteroids.get(i).isPicked() && asteroids.size() > 0) {
                lives--;
                asteroids.remove(i);
                continue;
            }
            if (asteroids.get(i).getX() < 0) asteroids.remove(i);
        }

        Random random = new Random();

        if (asteroids.size() < 5) {
            if (easyTimer.timerDelay(2)) {
                asteroids.add(new TimeInventoryItem(900, random.nextInt(500), asteroid, true, false, "asteroid", true));
                easyTimer.startTimer();
            }
        }

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
