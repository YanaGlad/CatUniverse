package com.example.catuniverse.gameSupport.gameMathematics;

import android.graphics.Color;
import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.graphics.GamePaint;
import com.example.catuniverse.gameSupport.helpp.Owner;

import static com.example.catuniverse.gameSupport.Collisions.collisionDetectLengthViaHeight;
import static com.example.catuniverse.gameSupport.Collisions.createCollisionsRect;

//"Игрок" в математических уровнях, который ловит выпадающие ответы
public class MathsPlayer extends GameItem {
    private MainRunActivity mainRunActivity;
    private boolean movingRight, movingLeft;
    private String expr;
    private int size;

    //Конструктор принимает MainRun,  текст вопроса и размер шрифта
    MathsPlayer(MainRunActivity mainRunActivity, String expr, int size) {
        this.mainRunActivity = mainRunActivity;
        x = 320;
        y = 530;
        this.expr = expr;
        this.size = size;
        movingLeft = false;
        movingRight = false;
        collLength = collisionDetectLengthViaHeight(BitmapLoader.mathsPlayerSkeleton, 2.7);
        collisionRect = createCollisionsRect(x, y, BitmapLoader.mathsPlayerSkeleton);

    }

    @Override
    public void run(GamePaint gamePaint) {
        repaint();
        gamePaint.setVisibleBitmap(BitmapLoader.mathPlayer, x + 100, y);
        gamePaint.write(expr, x + 109, y + 40, Color.BLACK, size);
    }

    @Override
    public void repaint() {
        //Если удержание пальцем слева/справа -  игрок движется в необходимую сторону, иначе - останавливается
        if (mainRunActivity.getTouchListener().down(0, GameView.screenHeight, GameView.screenWidth, GameView.screenHeight)) {
            if (BasicGameSupport.movingRight(mainRunActivity)) {
                movingRight = true;
                movingLeft = false;
            }
            if (BasicGameSupport.movingLeft(mainRunActivity)) {
                movingLeft = true;
                movingRight = false;
            }
        } else if (mainRunActivity.getTouchListener().up(0, GameView.screenHeight, GameView.screenWidth, GameView.screenHeight)) {
            movingRight = false;
            movingLeft = false;
        }

        //Если игрок не выходит за пределы экрана - перемещаться влево/вправо
        if (movingRight && x < 740 - BitmapLoader.mathsPlayerSkeleton.getWidth()) x += 10;
        if (movingLeft && x > -90) x -= 10;

        collisionRect = createCollisionsRect(x, y, BitmapLoader.mathsPlayerSkeleton);

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
        return expr;
    }

    void setExpression(String expr) {
        this.expr = expr;
    }

    @Override
    public boolean checkAcceptParam() {
        return false;
    }

    @Override
    public boolean checkDeclineParam() {
        return false;
    }

    @Override
    public Owner getOwner() {
        return BasicGameSupport.mathsLevelOwner;
    }
}

