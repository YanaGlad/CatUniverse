package com.example.catuniverse.gameSupport.gameStrategy;

import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.annotation.Nullable;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.EasyTimer;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.graphics.GamePaint;
import com.example.catuniverse.gameSupport.graphics.ImageSet;
import com.example.catuniverse.gameSupport.graphics.SpriteAnimation;

import java.util.ArrayList;

import static com.example.catuniverse.gameSupport.BitmapLoader.darkerDot;

public class StrategyPlayer extends GameItem {
    private ArrayList<ImageSet> imageSets;
    private ImageSet image;
    private ArrayList<StrategyBullet> strategyBullets;
    private BasicButton changeDelay, changePower;
    private boolean clicked = false;
    private MainRunActivity mainRunActivity;
    private double priceDelay, pricePower, power, delay;
    private EasyTimer easyTimer;
    private int id;
    private boolean availableForChoice;

    //Конструктор принимает id персонажа,  MainRun, набор его анимаций, силу, задержку, координаты, цену увеличения урона и уменьшения задержки, внешний вид его патронов (будет использоваться позже)
    StrategyPlayer(int id, MainRunActivity mainRunActivity, @Nullable ImageSet image, int power, double delay, int x, int y, int priceDelay, int pricePower, Bitmap bullet) {
        this.mainRunActivity = mainRunActivity;
        this.id = id;
        this.image = image;
        this.power = power;
        this.delay = delay;
        this.x = x;
        this.y = y;
        this.priceDelay = priceDelay;
        this.pricePower = pricePower;
        strategyBullets = new ArrayList<>();

        imageSets = new ArrayList<>();

        SpriteAnimation grayStandRight = new SpriteAnimation(BitmapLoader.standRightGray),
                orangeStandRight = new SpriteAnimation(BitmapLoader.standRightOrange),
                greenAlienStandRight = new SpriteAnimation(BitmapLoader.standRightGreenAlien),
                shadowStandRight = new SpriteAnimation(BitmapLoader.standRightShadow),
                mainCoonStandRight = new SpriteAnimation(BitmapLoader.standRightMainCoon);

        imageSets.add(new ImageSet(grayStandRight));
        imageSets.add(new ImageSet(orangeStandRight));
        imageSets.add(new ImageSet(greenAlienStandRight));
        imageSets.add(new ImageSet(shadowStandRight));
        imageSets.add(new ImageSet(mainCoonStandRight));

        changeDelay = new BasicButton(mainRunActivity, 288, 525, mainRunActivity.getString(R.string.delay) + " " + delay, Color.WHITE, 25, BitmapLoader.strategyButton, BitmapLoader.strategyButtonClicked, 20, 45);
        changePower = new BasicButton(mainRunActivity, 550, 525, mainRunActivity.getString(R.string.power) + " " + power, Color.WHITE, 25, BitmapLoader.strategyButton, BitmapLoader.strategyButtonClicked, 20, 45);

        easyTimer = new EasyTimer(); //Таймер для подсчёта времени задержек
        easyTimer.startTimer(); //

        availableForChoice = false;

    }

    @Override
    public void run(GamePaint gamePaint) {
        repaint();
        if (availableForChoice) gamePaint.setVisibleBitmap(darkerDot, x, y);
        if (image != null) { //Игрок начинает работать только после покупки, до этого StrategyPlayer - просто пустая клетка, куда можно разместить персонажа
            image.getStandRight().run(gamePaint, x, y, 2); //Запуск анимации игрока

            if (easyTimer.timerDelay(delay)) { //Каждое delay секунд игрок стреляет
                strategyBullets.add(new StrategyBullet(x, y, BitmapLoader.bullet));
                easyTimer.startTimer();
            }

            for (int i = 0; i < strategyBullets.size(); i++) {
                strategyBullets.get(i).run(gamePaint); //Если пуля за пределами экрана- удалить её
                if (strategyBullets.get(i).getX() > 1000) strategyBullets.remove(i);
            }
        }

        if (clicked && image != null) { //Если в клетке размещен игрок и на неё нажали, можно посмотреть характеристики игрока и их возможные улучшения
            gamePaint.write(mainRunActivity.getString(R.string.price) + " " + (int) priceDelay, 445, 540, Color.BLACK, 25);
            gamePaint.write(mainRunActivity.getString(R.string.price) + " " + (int) pricePower, 700, 540, Color.BLACK, 25);
        }

    }

    @Override
    public void repaint() {
        //Нажатие на клетку
        if (mainRunActivity.getTouchListener().up(x, y + BasicGameSupport.grayStandRight.getSprite1().getHeight(),
                BasicGameSupport.grayStandRight.getSprite1().getWidth(),
                BasicGameSupport.grayStandRight.getSprite1().getHeight()))
            clicked = !clicked;

        changeDelay.setText(mainRunActivity.getString(R.string.delay) + " " + delay);
        changePower.setText(mainRunActivity.getString(R.string.power) + " " + (int) power);

    }

    public boolean isClicked() {
        return clicked;
    }

    public void notClicked() {
        this.clicked = false;
    }

    void setPower(double power) {
        this.power = power;
    }

    void setDelay(double delay) {
        this.delay = delay;
    }

    void setImage(ImageSet image) {
        this.image = image;
    }

    ImageSet getImage() {
        return image;
    }

    ArrayList<ImageSet> getImageSets() {
        return imageSets;
    }

    BasicButton getChangeDelay() {
        return changeDelay;
    }

    BasicButton getChangePower() {
        return changePower;
    }

    double getPower() {
        return power;
    }

    double getDelay() {
        return delay;
    }

    double getPriceDelay() {
        return priceDelay;
    }

    double getPricePower() {
        return pricePower;
    }

    ArrayList<StrategyBullet> getStrategyBullets() {
        return strategyBullets;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    void setAvailableForChoice(boolean availableForChoice) {
        this.availableForChoice = availableForChoice;
    }
}
