package com.glad.catuniverse.gameSupport.gameTime.timeLevels;


import com.glad.catuniverse.gameSupport.Buttons.BasicButton;
import com.glad.catuniverse.gameSupport.GameItem;
import com.glad.catuniverse.gameSupport.MainRunActivity;
import com.glad.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.glad.catuniverse.gameSupport.gameTime.platforms.TimeTallPlatform;
import com.glad.catuniverse.gameSupport.gameTime.TimeDecoration;
import com.glad.catuniverse.gameSupport.gameTime.TimeLevel;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;

import static com.glad.catuniverse.gameSupport.BitmapLoader.blueDecorStation;
import static com.glad.catuniverse.gameSupport.BitmapLoader.blueDoor;
import static com.glad.catuniverse.gameSupport.BitmapLoader.blueDoorOpened;
import static com.glad.catuniverse.gameSupport.BitmapLoader.blueGround;
import static com.glad.catuniverse.gameSupport.BitmapLoader.electrodynamixMusic;
import static com.glad.catuniverse.gameSupport.BitmapLoader.movingSpaceBackground;

//Второй уровень на время.
public class Level2 extends TimeLevel {

    private final MainRunActivity mainRunActivity;

    public Level2(MainRunActivity mainRunActivity) {
        super(10, 15, 20, movingSpaceBackground, blueGround, 1, electrodynamixMusic);
        this.mainRunActivity = mainRunActivity;
        gameOver = false;

        timeTallPlatformArrayList.add(new TimeTallPlatform(2050, 520));
        timeTallPlatformArrayList.add(new TimeTallPlatform(2050, 900));

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
//        System.out.println("DOO");
        for (GameItem b : gameItems) b.run(gamePaint);
        passingDoor.repaint();
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
        return null;
    }
}
