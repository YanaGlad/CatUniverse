package com.example.catuniverse.gameSupport.Buttons;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;
import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.graphics.GamePaint;
import static com.example.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

//Кнопка, а также игровые предметы, поддерживающие обработку касаний (Например, двери)
public class BasicButton extends GameItem {
    private MainRunActivity mainRunActivity;
    private boolean clicked;
    private String text = null;
    private int size = 0;
    private int stepX = 0, stepY = 0;
    private boolean item;
    private int color;

    //Конструктор кнопки с текстом
    public BasicButton(MainRunActivity mainRunActivity, int x, int y, @Nullable String text, int color, int size, Bitmap button, Bitmap clickedButton, int stepX, int stepY) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
        this.size = size;
        this.mainRunActivity = mainRunActivity;
        this.bitmap = button;
        this.bitmapClicked = clickedButton;
        this.stepX = stepX;
        this.stepY = stepY;
        clicked = false;
        item = false;
    }

    //Конструктор кнопки без текста
    public BasicButton(MainRunActivity mainRunActivity, int x, int y, Bitmap button, Bitmap clickedButton, boolean item) {
        this.x = x;
        this.y = y;
        this.controlY = y;
        this.mainRunActivity = mainRunActivity;
        this.bitmap = button;
        this.bitmapClicked = clickedButton;
        clicked = false;
        this.item = item;

    }

    @Override
    public void run(GamePaint gamePaint) {
        //Если игровой объект - перемещать относительно игрока, иначе - просто проверять нажатие
        if (item) repaint(timePlayer.getMainPlayerSpeed(), timePlayer.getJumpSpeed());
        else repaint();

        if (clicked) gamePaint.setVisibleBitmap(bitmapClicked, x, y);//Нажатое состояние
        else gamePaint.setVisibleBitmap(bitmap, x, y); //Обычное состояние

        if (text != null)
            gamePaint.write(text, x + stepX, y + stepY, color, size); //Если кнопка с текстом, отображать на ней текст
    }

    @Override
    public void repaint() {
        //Проверка нажатия кнопки
        if (mainRunActivity.getTouchListener().up(x, y + bitmap.getHeight(), bitmap.getWidth(), bitmap.getHeight()))
            clicked = !clicked;

    }

    @Override
    public void repaint(double speed, double jumSpeed) {
        repaint();
        //Перемещение относительно игрока
        x = BasicGameSupport.updateMovesX(speed, x);
        y = BasicGameSupport.updateMovesY(this, jumSpeed, y);

        repaint();
    }

    public boolean isClicked() {
        return clicked;
    }

    public void notClicked() {
        this.clicked = false;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Bitmap getBitmap() {
        return super.getBitmap();
    }

    @Override
    public Bitmap getBitmapClicked() {
        return super.getBitmapClicked();
    }


    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        super.setBitmap(bitmap);
    }

    @Override
    public void setBitmapClicked(Bitmap bitmapClicked) {
        super.setBitmapClicked(bitmapClicked);
    }
}
