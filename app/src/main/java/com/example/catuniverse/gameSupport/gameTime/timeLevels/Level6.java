package com.example.catuniverse.gameSupport.gameTime.timeLevels;

import com.example.catuniverse.R;
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
import static com.example.catuniverse.gameSupport.BitmapLoader.movingSpaceBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.purplePlatform;
import static com.example.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

//Шестой уровень на время. В РАЗРАБОТКЕ
public class Level6 extends TimeLevel {
    private ArrayList<TimeTallPlatform> timeTallPlatformArrayList;
    private ArrayList<TimeInventoryItem> asteroids;
    private SpriteAnimation asteroid;
    private EasyTimer easyTimer;
    private ArrayList<TimePlatform> changingPurplePlatforms;

    public Level6(MainRunActivity mainRunActivity) {
        super(70, 50, 200, movingSpaceBackground, blueGround, 5, electrodynamixMusic);

        rebase:
        gameOver = false;


        changingPurplePlatforms = new ArrayList<>();
        gameItems = new ArrayList<>();
        asteroids = new ArrayList<>();
        timeTallPlatformArrayList = new ArrayList<>();

        timeTallPlatformArrayList.add(new TimeTallPlatform(2050, GameView.screenHeight - 520));
        timeTallPlatformArrayList.add(new TimeTallPlatform(2050, GameView.screenHeight - 900));

        timeTallPlatformArrayList.add(new TimeTallPlatform(4050, GameView.screenHeight - 520));
        timeTallPlatformArrayList.add(new TimeTallPlatform(4050, GameView.screenHeight - 900));

        asteroid = new SpriteAnimation(asteroidSprite);

        asteroids.add(new TimeInventoryItem(900, 500, asteroid, true, false, mainRunActivity.getString(R.string.Asteroid), true));

        int xX = 500, yY = 550;
        for (int i = 0; i < 20; i++) {
            gameItems.add(new TimePlatform(xX, yY, bluePlatform));
            xX += 70;
            yY -= 70;
        }

        xX = 2100;
        yY += 600;
        for (int i = 0; i < 15; i++) {
            changingPurplePlatforms.add(new TimePlatform(xX, yY, purplePlatform));
            xX += 150;
        }

        passingDoor = new BasicButton(mainRunActivity, 4050, yY, blueDoor, blueDoorOpened, true);


        easyTimer = new EasyTimer();
        easyTimer.startTimer();
        gameItems.add(passingDoor);

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

        passingDoor.repaint();
        super.endingRun(gamePaint);
    }

    @Override
    public void repaint() {
        super.repaint();
        passingDoor.repaint();
        for (TimeTallPlatform tb : timeTallPlatformArrayList)
            tb.repaint(timePlayer.getMainPlayerSpeed(), timePlayer.getJumpSpeed());
        CollisionDetectors.tallPlatformCollision(205, 305, timeTallPlatformArrayList);

        for (int i = 0; i < asteroids.size(); i++) {
            if (asteroids.get(i).isPicked() && asteroids.size() > 0) {
                lives--;
                asteroids.remove(i);
            }
        }

        for (int i = 0; i < changingPurplePlatforms.size(); i++) {
            if ((i + 1) % 2 == 0) changingPurplePlatforms.get(i).changing(2);
        }

        Random random = new Random();

        if (asteroids.size() < 29) {
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

    @Override
    public boolean isRequirementsCollected() {
        return true;
    }

    @Override
    public String getRewardId() {
        return null;
    }

}