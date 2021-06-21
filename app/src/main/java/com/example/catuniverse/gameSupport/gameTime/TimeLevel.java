package com.example.catuniverse.gameSupport.gameTime;

import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.CollisionDetectors;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.Loopable;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.Media;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimeTallPlatform;
import com.example.catuniverse.gameSupport.graphics.GamePaint;
import com.example.catuniverse.gameSupport.helpp.Checkable;
import com.example.catuniverse.gameSupport.helpp.Notify;
import com.example.catuniverse.gameViews.general.ChooseView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static com.example.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

//Класс, от которого наследуются все уровни на время. Он задает базовые характеристики уровня
public abstract class TimeLevel implements Loopable {
    private int twoStars, threeStars; //за сколько секунд можно пройти уровень на 2 или 3 звезды
    private double nowTime, endTime, totalTime; // Переменные для подсчёта прошедшего времени  и переменная с полным временем, данным на прохождение уровня
    private MovingBackground movingBackground; //Фон
    private TimeGround timeGround; //Земля
    protected int lives; //Число жизней
    protected boolean gameOver; //Проигран ли уровень
    protected BasicButton passingDoor; //Дверь, которую нужно открыть чтобы пройти уровень
    protected ArrayList<GameItem> gameItems; //Список любых игровых объектов
    private Media.Music music;
    protected ArrayList<TimeTallPlatform> timeTallPlatformArrayList;
    protected Notify notify;

    protected TimeLevel(int twoStars, int threeStars, double totalTime, Bitmap background, Bitmap ground, int lives, Media.Music music) {
        this.twoStars = twoStars;
        this.threeStars = threeStars;
        this.totalTime = totalTime;
        this.lives = lives;
        this.music = music;
        this.gameItems = new ArrayList<>();
        this.timeTallPlatformArrayList = new ArrayList<>();

        movingBackground = new MovingBackground(background, 3);
        timeGround = new TimeGround(ground);
        nowTime = System.nanoTime() / BasicGameSupport.SECOND;
    }

    private int[] possesGood = {530, 410, 290};
    private int[] possesBad = {510, 390, 270};

    @Override
    public void run(GamePaint gamePaint) {
        //Начальная отрисовка (под всеми игровыми элементами)
        movingBackground.run(gamePaint);
        timeGround.run(gamePaint);
        passingDoor.repaint();


    }

    protected void endingRun(GamePaint gamePaint, MainRunActivity mainRunActivity) {
        //Конечная отрисовка (над всеми игровыми элементами)
        if (lives <= 0) gameOver = true;
        endTime = System.nanoTime() / BasicGameSupport.SECOND - nowTime;
        gamePaint.write(String.valueOf((int) (totalTime - Math.ceil(endTime))), 345, 140, Color.WHITE, 55);
        writeTimeReqired(gamePaint, twoStars, threeStars);

        gamePaint.write(mainRunActivity.getString(R.string.lives), 350, 35, Color.WHITE, 30);

        int hX = 430;
        for (int i = 0; i < lives; i++) {
            gamePaint.setVisibleBitmap(BitmapLoader.heart, hX, 10);
            hX += 20;
        }

        passingDoor.repaint();
        ChooseView.playerManager.run(gamePaint);
    }

    protected void tallPlatformRepaint() {
        for (TimeTallPlatform tb : timeTallPlatformArrayList)
            tb.repaint(timePlayer.getMainPlayerSpeed(), timePlayer.getJumpSpeed());
        CollisionDetectors.tallPlatformCollision(timeTallPlatformArrayList);
    }

    @Override
    public void repaint() {
        //Подсчёт прошедшего с начала игры времени
        try {
            music.run();
            if (BitmapLoader.menuMusic.isRunning()) BitmapLoader.menuMusic.stop();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (totalTime - Math.ceil(endTime) <= 0) gameOver = true;
        endTime = System.nanoTime() / BasicGameSupport.SECOND - nowTime;
        ChooseView.playerManager.repaint();

    }

    public abstract boolean isRequirementsCollected();

    public abstract boolean unlockAchievement();

    public abstract int getAchievementId();

    protected void drawThreeRoadLines(GamePaint gamePaint) {
        gamePaint.createLine(-1, 490, 840, 490, Color.CYAN);
        gamePaint.createLine(-1, 370, 840, 370, Color.CYAN);
        gamePaint.createLine(-1, 250, 840, 250, Color.CYAN);

    }

    //Сгененрировать элементы в мини-игре "Ракета"
    //Добавляет в списки "плохие" и "хорошие" объекты. Хорошие - те, которые  необходимо собрать, усилители и т.д.
    //Плохие - препятствия, которые нужно обходить.
    //Также принимает списки с доступными на данном уровне объектами, кол-во собранных объектов, необходимых для победы, ключи к необходымым объетам, собранные объекты

    protected void generateRocketItems(ArrayList<TimeInventoryItem> itemsG, ArrayList<TimeInventoryItem> itemsB, @NonNull ArrayList<TimeInventoryItem> goodItems,
                                       @NonNull ArrayList<TimeInventoryItem> badItems, int[] requested, String[] keyRequested, int[] collected) {

        Random random = new Random();
        int rndA = random.nextInt(goodItems.size());

        TimeInventoryItem good;
        TimeInventoryItem bad;

        good = new TimeInventoryItem(960,
                possesGood[random.nextInt(3)],
                goodItems.get(rndA).getBitmap(),
                goodItems.get(rndA).isRocket(),
                goodItems.get(rndA).isGood(),
                goodItems.get(rndA).getKey());

        rndA = random.nextInt(badItems.size());

        bad = new TimeInventoryItem(1000,
                possesBad[random.nextInt(3)],
                badItems.get(rndA).getSpriteAnimation(),
                badItems.get(rndA).isRocket(),
                badItems.get(rndA).isGood(),
                badItems.get(rndA).getKey(),
                false);


        ///System.out.println("Bad Items size is... " + itemsB.size() + " Good Items = " + itemsG.size());

        if (itemsB.size() >= 1 && itemsB.size() < 20 && itemsB.get(itemsB.size() - 1).getX() <= 750)
            itemsB.add(bad);
        else if (itemsB.size() < 1)
            itemsB.add(bad);

        if (itemsG.size() >= 1 && itemsG.size() < 10 && itemsB.get(itemsB.size() - 1).getX() == 760)//
            itemsG.add(good);
        else if (itemsG.size() < 1)
            itemsG.add(good);


        for (int i = 0; i < itemsB.size(); i++)
            if (itemsB.get(i).isPicked() && itemsB.size() > 3) {
                lives--;
                itemsB.remove(i);
            }
        for (int i = 0; i < itemsG.size(); i++)
            if (itemsG.get(i).isPicked()) {
                for (int j = 0; j < keyRequested.length; j++)
                    if (itemsG.get(i).getKey().equals(keyRequested[j]) && collected[j] < requested[j]) {
                        collected[j]++;
                        break;
                    }
                itemsG.remove(i);
            }


        for (int i = 0; i < itemsG.size(); i++)
            if (itemsG.size() > 3)
                if (itemsG.get(i).getX() < timePlayer.getX() - 100) itemsG.remove(i);


        for (int i = 0; i < itemsB.size(); i++)
            if (itemsB.size() > 3)
                if (itemsB.get(i).getX() < timePlayer.getX() - 100) itemsB.remove(i);

    }


    private static void writeTimeReqired(GamePaint gamePaint, int twoStars, int threeStars) {
        gamePaint.write(twoStars + " seconds = 2 stars", 20, 40, Color.WHITE, 25);
        gamePaint.write(threeStars + " seconds = 3 stars", 20, 70, Color.WHITE, 25);
    }

    protected boolean inventoryItemsLeft(ArrayList<TimeInventoryItem> timeInventoryItems) {
        for (int i = 0; i < timeInventoryItems.size(); i++)
            if (!timeInventoryItems.get(i).isPicked()) return false;
        return true;
    }

    public abstract String getRewardId();

    @Override
    public void repaint(double speed, double jumSpeed) {

    }


    public BasicButton getPassingDoor() {
        return passingDoor;
    }

    protected double getTotalTime() {
        return totalTime;
    }

    protected void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver() {
        this.gameOver = true;
    }

    protected double getNowTime() {
        return nowTime;
    }

    protected void setNowTime(double nowTime) {
        this.nowTime = nowTime;
    }

    public double getEndTime() {
        return endTime;
    }

    protected void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public int getTwoStars() {
        return twoStars;
    }

    protected void setTwoStars(int twoStars) {
        this.twoStars = twoStars;
    }

    public int getThreeStars() {
        return threeStars;
    }

    protected void setThreeStars(int threeStars) {
        this.threeStars = threeStars;
    }

    public Media.Music getMusic() {
        return music;
    }
}
