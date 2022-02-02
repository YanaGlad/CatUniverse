package com.glad.catuniverse.gameSupport.gameStrategy;

import android.graphics.Color;

import com.example.catuniverse.R;
import com.glad.catuniverse.gameSupport.BasicGameSupport;
import com.glad.catuniverse.gameSupport.BitmapLoader;
import com.glad.catuniverse.gameSupport.Buttons.BasicButton;
import com.glad.catuniverse.gameSupport.Loopable;
import com.glad.catuniverse.gameSupport.MainRunActivity;
import com.glad.catuniverse.gameSupport.databaseHelpers.Cat;
import com.glad.catuniverse.gameSupport.graphics.CatIcon;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;
import com.glad.catuniverse.MainActivity;

import java.util.ArrayList;

//Выбор персонажей в стратегических уровнях. Просит выбрать от 1 до 5 персонажей для игры
public class StrategyCatChoice implements Loopable {
    private ArrayList<BasicButton> catStorage;
    private final BasicButton finish;
    private int countPlaces;
    private final ArrayList<Cat> chosenStrategyCats; //Список выбранных персонажей
    private final MainRunActivity mainRunActivity;
    private boolean running;
    private final ArrayList<CatIcon> catIcons;
    private boolean oneTime;

    StrategyCatChoice(MainRunActivity mainRunActivity) {
        oneTime = false;
        catIcons = new ArrayList<>();
        catIcons.add(new CatIcon(mainRunActivity.getString(R.string.cat_gray), BitmapLoader.grayIcon));
        catIcons.add(new CatIcon(mainRunActivity.getString(R.string.cat_orange), BitmapLoader.orangeIcon));
        catIcons.add(new CatIcon(mainRunActivity.getString(R.string.cat_green_alien), BitmapLoader.greenAlienCatIcon));
        catIcons.add(new CatIcon(mainRunActivity.getString(R.string.cat_shadow), BitmapLoader.shadowCatIcon));
        catIcons.add(new CatIcon(mainRunActivity.getString(R.string.cat_main_coon), BitmapLoader.mainCoonCatIcon));
        catIcons.add(new CatIcon(mainRunActivity.getString(R.string.cat_bob_tail), BitmapLoader.bobtailCatIcon));
        catIcons.add(new CatIcon(mainRunActivity.getString(R.string.cat_red_alien), BitmapLoader.redAlienCatIcon));

        chosenStrategyCats = new ArrayList<>();

        running = true;
        countPlaces = 0;

        this.mainRunActivity = mainRunActivity;

        finish = new BasicButton(mainRunActivity, 20, 540, mainRunActivity.getString(R.string.finish), Color.BLACK, 30, BitmapLoader.baseRedButton, BitmapLoader.baseRedButtonClicked, 10, 40);
    }

    @Override
    public void run(GamePaint gamePaint) {
        repaint();
        if (!oneTime) {
            int arrayX = 100, arrayY = 50;

            catStorage = new ArrayList<>();
            for (int i = 0; i < BasicGameSupport.catsCount; i++) {
                catStorage.add(new BasicButton(mainRunActivity, arrayX, arrayY, catIcons.get(i).getIcon(), catIcons.get(i).getIcon(), false));
                arrayX += 120;
                if ((i + 1) % 4 == 0) {
                    arrayX = 100;
                    arrayY += 140;
                }
            }
            oneTime = true;
        }
        gamePaint.setVisibleBitmap(BitmapLoader.redTechnoBackground, 0, 0);
        gamePaint.setVisibleBitmap(BitmapLoader.strategyCatCount, 590, 25);
        gamePaint.write(countPlaces + "/5", 600, 115, Color.BLACK, 60);

        for (int i = 0; i < catStorage.size(); i++) {
            if (MainActivity.listOfCats.get(i).getUnlocked() == 1)
                catStorage.get(i).run(gamePaint);
        }

        for (int i = 0; i < catStorage.size(); i++) { //Цикл по доступным персонажам
            if (catStorage.get(i).isClicked()) { // Если нажат, то его можно выбрать

                BasicButton choose = new BasicButton(mainRunActivity, catStorage.get(i).getX(), catStorage.get(i).getY() + 110,
                        mainRunActivity.getString(R.string.choose_cat), Color.BLACK, 25, BitmapLoader.smallButtonRed, BitmapLoader.smallButtonRedClicked, 20, 20);
                choose.run(gamePaint);

                if (choose.isClicked()) {
                    boolean check = false;
                    if (chosenStrategyCats.size() > 0)
                        for (int j = 0; j < chosenStrategyCats.size(); j++) {
                            if (chosenStrategyCats.get(j) == MainActivity.listOfCats.get(i)) check = true;
                        }

                    if (!check) {
                        countPlaces++;
                        chosenStrategyCats.add(MainActivity.listOfCats.get(i));
                        choose.notClicked();
                    }
                    catStorage.get(i).notClicked();
                }
            }
        }
        finish.run(gamePaint); //Завершить выбор персонажей
        if (finish.isClicked() && countPlaces > 0)
            running = false;
        else finish.notClicked();
    }

    @Override
    public void repaint() {
    }

    @Override
    public void repaint(double speed, double jumSpeed) {
    }

    boolean isRunning() {
        return running;
    }

    //найти кота по его id
    int findCat(int id) {
        for (int i = 0; i < chosenStrategyCats.size(); i++)
            if (id == chosenStrategyCats.get(i).getId()) return i;
        return -1;
    }

    ArrayList<Cat> getChosenStrategyCats() {
        return chosenStrategyCats;
    }
}
