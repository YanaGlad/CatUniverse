package com.glad.catuniverse.gameSupport.gameTime.timeLevels;

import com.example.catuniverse.R;
import com.glad.catuniverse.gameSupport.BitmapLoader;
import com.glad.catuniverse.gameSupport.Buttons.BasicButton;
import com.glad.catuniverse.gameSupport.EasyTimer;
import com.glad.catuniverse.gameSupport.GameItem;
import com.glad.catuniverse.gameSupport.MainRunActivity;
import com.glad.catuniverse.gameSupport.gameTime.TimeDecoration;
import com.glad.catuniverse.gameSupport.gameTime.TimeInventoryItem;
import com.glad.catuniverse.gameSupport.gameTime.TimeLevel;
import com.glad.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;
import com.glad.catuniverse.gameSupport.graphics.SpriteAnimation;

import java.util.ArrayList;
import java.util.Random;

import static com.glad.catuniverse.gameSupport.BitmapLoader.asteroidSprite;
import static com.glad.catuniverse.gameSupport.BitmapLoader.blueGround;
import static com.glad.catuniverse.gameSupport.BitmapLoader.bluePlatform;
import static com.glad.catuniverse.gameSupport.BitmapLoader.electrodynamixMusic;
import static com.glad.catuniverse.gameSupport.BitmapLoader.movingBlueSpaceBackground;
import static com.glad.catuniverse.gameSupport.BitmapLoader.purpleDoor;
import static com.glad.catuniverse.gameSupport.BitmapLoader.purpleDoorOpened;

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
