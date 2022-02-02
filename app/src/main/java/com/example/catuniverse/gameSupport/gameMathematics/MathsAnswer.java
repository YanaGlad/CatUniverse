package com.example.catuniverse.gameSupport.gameMathematics;

import android.graphics.Color;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Collisions;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.graphics.GamePaint;

//Падающие ответы в математических уровнях.
//Т.к. у них есть координаты x,y, они должны сталкиваться с игроком (обработка коллизий) ,
// MathsAnswer наследует GameItem
public class MathsAnswer extends GameItem {

    private String expression;
    private final int plus;
    private final int size;
    private int step;
    private final int stepHeight;

    // Конструктор принимает координаты х, отступ по y, ключ для проверки типа уровны,
    // промежуток выпадения ответов (т.к. одна большая тема может делиться на несколько уровней)
    MathsAnswer(int x, int stepY, String key, int size, int questionStart, int questionEnd) {
        this.x = x;
        this.y = -100 - stepY;
        this.size = size;
        collLength = Collisions.collisionDetectLengthViaHeight(BitmapLoader.bullet, 2.5);
        collisionRect = Collisions.createCollisionsRect(x, y, BitmapLoader.mathAnswerSkeleton);

        //Массивы возможны ответов на различные темы.
        String[] square = {"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "121", "144", "169", "196", "194", "225", "256", "289", "324", "364", "361", "121"};
        String[] trig = {"sin²α", "cos²α", "1", "2cos²α", "2sin²α", "tgα", "tgα", "ctgα", "tg²α", "ctg²α", "2sinα", "2cosα", "sinα", "cosα", "2", "3", "cos2α"};
        String[] derivative = {"sin²x", "cos²x", "sinx", "-sinx", "cosx", "-cosx", "2x", "0", "1", "2√x", "1/x", "e²", "-e²", "x", "c", "√x", "lnx", "sinx", "cosx", "x²", "-cosx" };


        // В зависимости от ключа, выпадают ответы, соответствующие теме
        stepHeight = 50;
        switch (key) {
            case "square":
                expression = square[(int) (Math.random() * (questionEnd - questionStart) + questionStart)];
                step = 5;
                break;
            case "trigonometry":
                expression = trig[(int) (Math.random() * (questionEnd - questionStart) + questionStart)];
                step = 12;
                break;
            case "derivative":
                expression = derivative[(int) (Math.random() * (questionEnd - questionStart) + questionStart)];
                step = 7;
                break;
            case "antiderivative":
                break;
        }
        plus = (int) (Math.random() * (10 - 5) + 5);

    }


    @Override
    public void run(GamePaint gamePaint) {
        repaint();
        gamePaint.setVisibleBitmap(BitmapLoader.mathsAnswer, x, y); // Кружок для отображения ответа
        int more = 1;
        int sizing = 1;
        if (expression.length() == 1 || expression.length() == 2) {
            more = 10;//*expression.length()
            sizing = 3;
        }
        gamePaint.write(expression, x + step + more, y + stepHeight, Color.BLACK, size + sizing); //Отображение ответа с необходимыми отступами в зависимости от его рамера
    }

    @Override
    public void repaint() {
        y += plus;
        collisionRect = Collisions.createCollisionsRect(x, y, BitmapLoader.mathAnswerSkeleton);
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

    String getExpression() {
        return expression;
    }
}