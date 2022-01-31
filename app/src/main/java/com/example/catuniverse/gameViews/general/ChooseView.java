package com.example.catuniverse.gameViews.general;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.graphics.PlayerManager;
import com.example.catuniverse.gameSupport.support.LevelChoice;

import static com.example.catuniverse.MainActivity.listOfCats;
import static com.example.catuniverse.gameSupport.BitmapLoader.baseBlueButton;
import static com.example.catuniverse.gameSupport.BitmapLoader.baseBlueButtonClicked;
import static com.example.catuniverse.gameSupport.BitmapLoader.baseRedButton;
import static com.example.catuniverse.gameSupport.BitmapLoader.baseRedButtonClicked;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueLevelButton;
import static com.example.catuniverse.gameSupport.BitmapLoader.blueLevelButtonClicked;
import static com.example.catuniverse.gameSupport.BitmapLoader.mathsBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.redLevelButton;
import static com.example.catuniverse.gameSupport.BitmapLoader.redLevelButtonClicked;
import static com.example.catuniverse.gameSupport.BitmapLoader.redTechnoBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.technoBackground;

//ChooseView - отображение меню для выбора уровней определенного типа. Класс создан 19.05.20,
//он является улучшением кода, т.к. до него его работу выполняло сразу 3 класса - MathsChooseView, TimeChooseView
// и StrategyChooseView (их можно посотреть, если зайти в историю проекта), которые работали по-разному.
//Данный класс же просто принимает ключ, говорящий какого типа данный уровень и определяет ему соответствующую логику и дизайн.
public class ChooseView extends GameView {

    public static PlayerManager playerManager;
    private final LevelChoice levelChoice;
    private final BasicButton goBack;
    private String key;
    private Bitmap background;

    public ChooseView(MainRunActivity mainRunActivity, String key) {
        super(mainRunActivity);
        this.key = key;

        Bitmap button = null, buttonClicked = null, goBackButton = null, goBackButtonClicked = null;
        background = null;

        //Определение элементов дизайна по ключу
        switch (key) {
            case "time":
                background = technoBackground;
                button = blueLevelButton;
                buttonClicked = blueLevelButtonClicked;
                goBackButton = baseBlueButton;
                goBackButtonClicked = baseBlueButtonClicked;
                break;
            case "strategy":
                background = redTechnoBackground;
                button = redLevelButton;
                buttonClicked = redLevelButtonClicked;
                goBackButton = baseRedButton;
                goBackButtonClicked = baseRedButtonClicked;
                break;
            case "maths":
                background = mathsBackground;

                button = redLevelButton;
                buttonClicked = redLevelButtonClicked;
                goBackButton = baseRedButton;
                goBackButtonClicked = baseRedButtonClicked;
                break;
        }
        levelChoice = new LevelChoice(this, mainRunActivity, button, buttonClicked, key);
        goBack = new BasicButton(mainRunActivity, 20, 540, mainRunActivity.getString(R.string.back), Color.BLACK, 30, goBackButton, goBackButtonClicked, 10, 40);
    }

    @Override
    public void run() {
        repaint();
        super.getGamePaint().setVisibleBitmap(background, 0, 0);
        goBack.run(super.getGamePaint());
        levelChoice.run(super.getGamePaint());
    }

    @Override
    public void repaint() {
        if (goBack.isClicked()) {
            super.getMainRunActivity().setView(new MenuView(super.getMainRunActivity()));
            goBack.notClicked();
        }

        if (key.equals("time")) { //Обновлять ифнормацию о выбранном игроке нужно только для уровней на время
            for (int i = 0; i < listOfCats.size(); i++)
                if (listOfCats.get(i).getChosen() == 1) {
                    PlayerManager.setChosenCat(listOfCats.get(i));
                }
            playerManager = new PlayerManager(super.getMainRunActivity());
        }
    }

    public void setKey(String key) {
        this.key = key;
    }
}
