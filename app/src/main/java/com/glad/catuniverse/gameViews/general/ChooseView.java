package com.glad.catuniverse.gameViews.general;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.glad.catuniverse.R;
import com.glad.catuniverse.gameSupport.Buttons.BasicButton;
import com.glad.catuniverse.gameSupport.GameView;
import com.glad.catuniverse.gameSupport.MainRunActivity;
import com.glad.catuniverse.gameSupport.graphics.PlayerManager;
import com.glad.catuniverse.gameSupport.support.LevelChoice;
import com.glad.catuniverse.gameSupport.BitmapLoader;

import static com.glad.catuniverse.MainActivity.listOfCats;

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
                background = BitmapLoader.technoBackground;
                button = BitmapLoader.blueLevelButton;
                buttonClicked = BitmapLoader.blueLevelButtonClicked;
                goBackButton = BitmapLoader.baseBlueButton;
                goBackButtonClicked = BitmapLoader.baseBlueButtonClicked;
                break;
            case "strategy":
                background = BitmapLoader.redTechnoBackground;
                button = BitmapLoader.redLevelButton;
                buttonClicked = BitmapLoader.redLevelButtonClicked;
                goBackButton = BitmapLoader.baseRedButton;
                goBackButtonClicked = BitmapLoader.baseRedButtonClicked;
                break;
            case "maths":
                background = BitmapLoader.mathsBackground;

                button = BitmapLoader.redLevelButton;
                buttonClicked = BitmapLoader.redLevelButtonClicked;
                goBackButton = BitmapLoader.baseRedButton;
                goBackButtonClicked = BitmapLoader.baseRedButtonClicked;
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
