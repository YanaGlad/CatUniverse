package com.example.catuniverse.gameSupport.databaseHelpers;

import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.Loopable;
import com.example.catuniverse.gameSupport.graphics.GamePaint;

import java.util.Random;

//Кот, ходящий из стороны в сторону для отображения в комнате
public class CatPet implements Loopable {
    private Cat cat; //Вся информация о персонаже
    private boolean right, left;
    private int room;
    private final int id;
    private int x, y;

    public CatPet(Cat cat, int id, int room) {
        this.cat = cat;
        this.id = id;
        this.room = room;
        right = true;
        left = false;

        //Рандомные координаты расположения кота. При перезаходе в комнату, коты будут ходить в разных местах
        Random rnd = new Random();
        int chs = rnd.nextInt(3);
        x = (int) (Math.random() * (300 - 71)) + 71;

        if (chs == 0) y = 360;
        if (chs == 1) y = 510;
        if (chs == 2) y = 440;

    }

    @Override
    public void run(GamePaint gamePaint) {
        repaint();
        //Запуск анимаций в зависимости от направления движения
        if (left) if (cat != null) cat.getImageSet().getMoveLeft().run(gamePaint, x, y, 3);
        if (right) if (cat != null) cat.getImageSet().getMoveRight().run(gamePaint, x, y, 3);
    }

    @Override
    public void repaint() {
        //Перемещение влево/вправо
        if (left) x--;

        if (right) x++;

        if (x == 600) {
            right = false;
            left = true;
        }
        if (x == 70) {
            left = false;
            right = true;
        }
    }

    @Override
    public void repaint(double speed, double jumSpeed) {

    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }
}
