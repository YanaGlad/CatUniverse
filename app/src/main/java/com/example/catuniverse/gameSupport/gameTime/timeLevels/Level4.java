package com.example.catuniverse.gameSupport.gameTime.timeLevels;

import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.CollisionDetectors;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimeTallPlatform;
import com.example.catuniverse.gameSupport.gameTime.TimeLevel;
import com.example.catuniverse.gameSupport.graphics.PlayerManager;
import com.example.catuniverse.gameSupport.graphics.GamePaint;

import java.util.ArrayList;

import static com.example.catuniverse.gameSupport.BitmapLoader.blueGround;
import static com.example.catuniverse.gameSupport.BitmapLoader.movingSpaceBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.xStepMusic;

//Четвертый уровень на время.
public class Level4 extends TimeLevel {
    private ArrayList<TimePlatform> changingObstacles;
    private MainRunActivity mainRunActivity;
    private boolean done;

    public Level4(MainRunActivity mainRunActivity) {
        super(20, 15, 30, movingSpaceBackground, blueGround, 1, xStepMusic);
        this.mainRunActivity = mainRunActivity;
        done = false;
        changingObstacles = new ArrayList<>();

        gameOver = false;

        timeTallPlatformArrayList.add(new TimeTallPlatform(-100, 520));
        timeTallPlatformArrayList.add(new TimeTallPlatform(600, 520));
        timeTallPlatformArrayList.add(new TimeTallPlatform(-100, 900));
        timeTallPlatformArrayList.add(new TimeTallPlatform(-100, 1280));
        timeTallPlatformArrayList.add(new TimeTallPlatform(600, 900));
        timeTallPlatformArrayList.add(new TimeTallPlatform(600, 1280));
        timeTallPlatformArrayList.add(new TimeTallPlatform(1250, 520));
        timeTallPlatformArrayList.add(new TimeTallPlatform(1250, 900));
        timeTallPlatformArrayList.add(new TimeTallPlatform(1250, 1280));

        gameItems.add(new TimePlatform(190, 500));
        gameItems.add(new TimePlatform(350, 400));
        gameItems.add(new TimePlatform(480, 300));
        gameItems.add(new TimePlatform(350, 200));
        gameItems.add(new TimePlatform(190, 100));
        gameItems.add(new TimePlatform(350, 20));

        int posY = -100;
        for (int i = 0; i < 6; i++) {
            changingObstacles.add(new TimePlatform(300, posY));
            posY -= 100;
        }

        posY = -700;
        int posX = 350;
        for (int i = 0; i < 5; i++) {
            gameItems.add(new TimePlatform(posX, posY));
            posX += 150;
            posY -= 70;
        }

        gameItems.add(new TimePlatform(1150, GameView.screenHeight - 1240));

        passingDoor = new BasicButton(mainRunActivity, 1170, GameView.screenHeight - 1390, BitmapLoader.blueDoor, BitmapLoader.blueDoorOpened, true);
        gameItems.add(passingDoor);

    }

    @Override
    public void run(GamePaint gamePaint) {
        super.run(gamePaint);
        if (!done) {
            PlayerManager.timePlayer.setX(301);
            done = true;
        }
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
        return "4";
    }
}

