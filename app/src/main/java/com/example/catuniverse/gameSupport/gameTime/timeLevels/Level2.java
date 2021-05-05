package com.example.catuniverse.gameSupport.gameTime.timeLevels;


import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.CollisionDetectors;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimeTallPlatform;
import com.example.catuniverse.gameSupport.gameTime.TimeDecoration;
import com.example.catuniverse.gameSupport.gameTime.TimeLevel;
import com.example.catuniverse.gameSupport.graphics.GamePaint;

import java.util.ArrayList;

import static com.example.catuniverse.gameSupport.BitmapLoader.blueDecorStation;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoor;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueDoorOpened;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueGround;
import static com.example.catuniverse.gameSupport.BitmapLoader.electrodynamixMusic;
import static com.example.catuniverse.gameSupport.BitmapLoader.movingSpaceBackground;
import static com.example.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

//Второй уровень на время.
public class Level2 extends TimeLevel {
    private ArrayList<TimeTallPlatform> timeTallPlatformArrayList;

    public Level2(MainRunActivity mainRunActivity) {
        super(10, 15, 20, movingSpaceBackground, blueGround, 1, electrodynamixMusic);
        gameOver = false;

        gameItems = new ArrayList<>();
        timeTallPlatformArrayList = new ArrayList<>();

        timeTallPlatformArrayList.add(new TimeTallPlatform(2050, GameView.screenHeight - 520));
        timeTallPlatformArrayList.add(new TimeTallPlatform(2050, GameView.screenHeight - 900));

        passingDoor = new BasicButton(mainRunActivity, 2070, -440, blueDoor, blueDoorOpened, true);

        gameItems.add(new TimeDecoration(2150, -800, blueDecorStation, true));

        gameItems.add(passingDoor);
        int xO = 300;
        int yO = 500;
        for (int i = 0; i < 10; i++) {
            gameItems.add(new TimePlatform(xO, yO));
            xO += 170;
            yO -= 90;
        }
    }

    @Override
    public void run(GamePaint gamePaint) {
        super.run(gamePaint);
        repaint();
        for (GameItem b : gameItems) b.run(gamePaint);
        passingDoor.repaint();
        for (TimeTallPlatform tb : timeTallPlatformArrayList) tb.run(gamePaint);
        passingDoor.repaint();
        super.endingRun(gamePaint);
    }

    @Override
    public void repaint() {
        super.repaint();
        passingDoor.repaint();
        for (TimeTallPlatform tb : timeTallPlatformArrayList)
            tb.repaint(timePlayer.getMainPlayerSpeed(), timePlayer.getJumpSpeed());
        CollisionDetectors.tallPlatformCollision(timeTallPlatformArrayList);
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