package com.glad.catuniverse.gameSupport.gameMathematics;

import android.graphics.Color;
import com.glad.catuniverse.gameSupport.BasicGameSupport;
import com.glad.catuniverse.gameSupport.BitmapLoader;
import com.glad.catuniverse.gameSupport.GameItem;
import com.glad.catuniverse.gameSupport.GameView;
import com.glad.catuniverse.gameSupport.MainRunActivity;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;
import com.glad.catuniverse.gameSupport.Collisions;
import static com.glad.catuniverse.gameSupport.BitmapLoader.mathsPlayerSkeleton;
import static com.glad.catuniverse.gameSupport.GameView.screenHeight;

//"Игрок" в математических уровнях, который ловит выпадающие ответы
public class MathsPlayer extends GameItem {
    private final MainRunActivity mainRunActivity;
    private boolean movingRight, movingLeft;
    private String expr;
    private final int size;

    //Конструктор принимает MainRun,  текст вопроса и размер шрифта
    MathsPlayer(MainRunActivity mainRunActivity, String expr, int size) {
        this.mainRunActivity = mainRunActivity;
        x = 320;
        y = 530;
        this.expr = expr;
        this.size = size;
        movingLeft = false;
        movingRight = false;
        collLength = Collisions.collisionDetectLengthViaHeight(mathsPlayerSkeleton, 2.7);
        collisionRect = Collisions.createCollisionsRect(x, y, mathsPlayerSkeleton);
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
        if (mainRunActivity.getTouchListener().down(0, screenHeight, GameView.screenWidth, screenHeight)) {
            if (BasicGameSupport.movingRight(mainRunActivity)) {
                movingRight = true;
                movingLeft = false;
            }
            if (BasicGameSupport.movingLeft(mainRunActivity)) {
                movingLeft = true;
                movingRight = false;
            }
        } else if (mainRunActivity.getTouchListener().up(0, screenHeight, GameView.screenWidth, screenHeight)) {
            movingRight = false;
            movingLeft = false;
        }

        //Если игрок не выходит за пределы экрана - перемещаться влево/вправо
        if (movingRight && x < 740 - mathsPlayerSkeleton.getWidth()) x += 10;
        if (movingLeft && x > -90) x -= 10;

        collisionRect = Collisions.createCollisionsRect(x, y, mathsPlayerSkeleton);
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
}
