package com.glad.catuniverse.gameSupport.gameStrategy;

import static com.glad.catuniverse.gameSupport.BasicGameSupport.grayStandRight;
        import static com.glad.catuniverse.gameSupport.BitmapLoader.bullet;
        import static com.glad.catuniverse.gameSupport.BitmapLoader.darkerDot;
        import static com.glad.catuniverse.gameSupport.BitmapLoader.standRightGray;
        import static com.glad.catuniverse.gameSupport.BitmapLoader.standRightGreenAlien;
        import static com.glad.catuniverse.gameSupport.BitmapLoader.standRightMainCoon;
        import static com.glad.catuniverse.gameSupport.BitmapLoader.standRightOrange;
        import static com.glad.catuniverse.gameSupport.BitmapLoader.standRightShadow;
        import static com.glad.catuniverse.gameSupport.BitmapLoader.strategyButton;
        import static com.glad.catuniverse.gameSupport.BitmapLoader.strategyButtonClicked;
        import android.graphics.Bitmap;
        import android.graphics.Color;
        import androidx.annotation.Nullable;
        import com.glad.catuniverse.R;
        import com.glad.catuniverse.gameSupport.Buttons.BasicButton;
        import com.glad.catuniverse.gameSupport.EasyTimer;
        import com.glad.catuniverse.gameSupport.GameItem;
        import com.glad.catuniverse.gameSupport.MainRunActivity;
        import com.glad.catuniverse.gameSupport.graphics.GamePaint;
        import com.glad.catuniverse.gameSupport.graphics.ImageSet;
        import com.glad.catuniverse.gameSupport.graphics.SpriteAnimation;
        import java.util.ArrayList;

public class StrategyPlayer extends GameItem {

    private final ArrayList<ImageSet> imageSets;
    private ImageSet image;
    private final ArrayList<StrategyBullet> strategyBullets;
    private final BasicButton changeDelay;
    private final BasicButton changePower;
    private boolean clicked = false;
    private final MainRunActivity mainRunActivity;
    private final double priceDelay;
    private final double pricePower;
    private double power;
    private double delay;
    private final EasyTimer easyTimer;
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

        SpriteAnimation grayStandRight = new SpriteAnimation(standRightGray),
                orangeStandRight = new SpriteAnimation(standRightOrange),
                greenAlienStandRight = new SpriteAnimation(standRightGreenAlien),
                shadowStandRight = new SpriteAnimation(standRightShadow),
                mainCoonStandRight = new SpriteAnimation(standRightMainCoon);

        imageSets.add(new ImageSet(grayStandRight));
        imageSets.add(new ImageSet(orangeStandRight));
        imageSets.add(new ImageSet(greenAlienStandRight));
        imageSets.add(new ImageSet(shadowStandRight));
        imageSets.add(new ImageSet(mainCoonStandRight));

        changeDelay = new BasicButton(mainRunActivity, 288, 525, mainRunActivity.getString(R.string.delay) + " " + delay, Color.WHITE, 25, strategyButton, strategyButtonClicked, 20, 45);
        changePower = new BasicButton(mainRunActivity, 550, 525, mainRunActivity.getString(R.string.power) + " " + power, Color.WHITE, 25, strategyButton, strategyButtonClicked, 20, 45);

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
                strategyBullets.add(new StrategyBullet(x, y, bullet));
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
        if (mainRunActivity.getTouchListener().up(x, y + grayStandRight.getSprite1().getHeight(),
                grayStandRight.getSprite1().getWidth(),
                grayStandRight.getSprite1().getHeight()))
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
