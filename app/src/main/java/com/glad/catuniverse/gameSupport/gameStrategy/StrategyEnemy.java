package com.glad.catuniverse.gameSupport.gameStrategy;

import android.graphics.Color;

import com.glad.catuniverse.gameSupport.GameItem;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;
import com.glad.catuniverse.gameSupport.graphics.SpriteAnimation;
import com.glad.catuniverse.gameSupport.Collisions;

import static com.glad.catuniverse.gameSupport.Collisions.createBaseSizeRect;

//Враг в стратегических уровнях. Беэит из правого конца в левый. Если он проходит мимо обороны - игрок теряет жизнь.

public class StrategyEnemy extends GameItem {
    private final int reward; //Награда за победу
    private final SpriteAnimation walk; //Анимация ходьбы
    private double health; //здоровье
    private double k; //Размер прямоугольника с жизнями без учёта процентного соотношения к количеству жизней
    private boolean defeated = false; //Побежден ли
    private final int speed; // скорость

    StrategyEnemy(SpriteAnimation walk, int x, int y, double health, int reward, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.walk = walk;
        this.health = health;
        this.reward = reward;
        k = 50;
        collLength = Collisions.collisionDetectLengthViaWidth(walk.getSprite(), 3.5);
        collisionRect = Collisions.createBaseSizeRect(x, y);
    }

    @Override
    public void run(GamePaint gamePaint) {
        if (health > 0) { //Если здоровье больше 0 , иначе - враг повержен
            repaint();
            //Запуск анимации
            walk.run(gamePaint, x, y, 100);
            //Прямоугольник жизней
            gamePaint.createGreenRect(x, y - 10, (int) (x + k), y);
            gamePaint.createLine(x, y - 10, x + 50, y - 10, Color.RED);
            gamePaint.createLine(x, y, x + 50, y, Color.RED);
            gamePaint.createLine(x, y - 10, x, y, Color.RED);
            gamePaint.createLine(x + 50, y - 10, x + 50, y, Color.RED);
        } else defeated = true;
    }

    // отображает остаток жизней
    void setHealth(double health, double power) {
        double healthCompare = k * 100 / health; //Найти сколько от k составляет количество жизней игрока
        this.k = (health - power) * healthCompare / 100; //Задать новый размер зеленого прямоугольника жизней
        this.health = health - power; //уменьшить жизни
    }

    @Override
    public void repaint() {
        x -= speed; //Перемещение влево
        collisionRect = Collisions.createBaseSizeRect(x, y);
    }

    boolean isDefeated() {
        return defeated;
    }

    int getReward() {
        return reward;
    }

    double getHealth() {
        return health;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public double getSpeed() {
        return super.getSpeed();
    }

    @Override
    public void setSpeed(double speed) {
        super.setSpeed(speed);
    }
}
