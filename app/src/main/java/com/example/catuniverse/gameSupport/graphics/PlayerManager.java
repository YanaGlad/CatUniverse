package com.example.catuniverse.gameSupport.graphics;

import com.example.catuniverse.gameSupport.Loopable;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.databaseHelpers.Cat;
import com.example.catuniverse.gameSupport.gameTime.TimePlayer;

//Класс для упрощения работы с персонажем.
public class PlayerManager implements Loopable {
    public static TimePlayer timePlayer;
    private static Cat chosenCat; // - Характеристики выбранного кота


    public PlayerManager(MainRunActivity mainRunActivity) {
        timePlayer = new TimePlayer(mainRunActivity, chosenCat);
    }

    @Override
    public void run(GamePaint gamePaint) {
        timePlayer.run(gamePaint);
    }

    @Override
    public void repaint() {
        timePlayer.repaint();
    }

    @Override
    public void repaint(double speed, double jumSpeed) {
    }

    public static Cat getChosenCat() {
        return chosenCat;
    }
    public static void setChosenCat(Cat chosenCat) {
        PlayerManager.chosenCat = chosenCat;
    }
}
