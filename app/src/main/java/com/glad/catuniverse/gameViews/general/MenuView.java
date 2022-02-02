package com.glad.catuniverse.gameViews.general;

import android.graphics.Color;

import com.glad.catuniverse.R;
import com.glad.catuniverse.gameSupport.BitmapLoader;
import com.glad.catuniverse.gameSupport.Buttons.BasicButton;
import com.glad.catuniverse.gameSupport.GameView;
import com.glad.catuniverse.gameSupport.MainRunActivity;
import com.glad.catuniverse.gameSupport.graphics.CatIcon;
import java.io.IOException;
import java.util.ArrayList;

//Меню игры, которое предоставляет возможность перейти в комнаты или выбрать тип уровней для игры
public class MenuView extends GameView {

    public static ArrayList<CatIcon> catIcons = new ArrayList<>();

    private final BasicButton time, room, maths, strategy, about, achiveBtn;

    public MenuView(MainRunActivity mainRunActivity) {
        super(mainRunActivity);

        //Инициализация иконок котов
        catIcons.add(new CatIcon(mainRunActivity.getString(R.string.cat_gray), BitmapLoader.grayIcon));
        catIcons.add(new CatIcon(mainRunActivity.getString(R.string.cat_orange), BitmapLoader.orangeIcon));
        catIcons.add(new CatIcon(mainRunActivity.getString(R.string.cat_green_alien), BitmapLoader.greenAlienCatIcon));
        catIcons.add(new CatIcon(mainRunActivity.getString(R.string.cat_shadow), BitmapLoader.shadowCatIcon));
        catIcons.add(new CatIcon(mainRunActivity.getString(R.string.cat_main_coon), BitmapLoader.mainCoonCatIcon));
        catIcons.add(new CatIcon(mainRunActivity.getString(R.string.cat_bob_tail), BitmapLoader.bobtailCatIcon));
        catIcons.add(new CatIcon(mainRunActivity.getString(R.string.cat_red_alien), BitmapLoader.redAlienCatIcon));

        room = new BasicButton(mainRunActivity, 200, 120, mainRunActivity.getString(R.string.go_to_the_room), Color.BLACK, 40, BitmapLoader.mainMenuButton, BitmapLoader.mainMenuButtonClicked, 50, 50);
        time = new BasicButton(mainRunActivity, 200, 220, mainRunActivity.getString(R.string.play_time_game), Color.BLACK, 40, BitmapLoader.mainMenuButton, BitmapLoader.mainMenuButtonClicked, 50, 50);
        maths = new BasicButton(mainRunActivity, 200, 320, mainRunActivity.getString(R.string.play_maths), Color.BLACK, 40, BitmapLoader.mainMenuButton, BitmapLoader.mainMenuButtonClicked, 50, 50);
        strategy = new BasicButton(mainRunActivity, 200, 420, mainRunActivity.getString(R.string.play_strategy), Color.BLACK, 40, BitmapLoader.mainMenuButton, BitmapLoader.mainMenuButtonClicked, 50, 50);

        about = new BasicButton(mainRunActivity, 200, 520, mainRunActivity.getString(R.string.about), Color.BLACK, 30, BitmapLoader.baseBlueButton, BitmapLoader.baseBlueButtonClicked, 40, 35);
        achiveBtn = new BasicButton(mainRunActivity, 20, 20, BitmapLoader.addCatButton, BitmapLoader.addCatButtonClicked, false);
    }

    @Override
    public void run() {
        repaint();
        super.getGamePaint().setVisibleBitmap(BitmapLoader.spaceBackground, 0, 0);
        super.getGamePaint().write(super.getMainRunActivity().getString(R.string.cat_universe), 210, 90, Color.WHITE, 70);
        room.run(super.getGamePaint());
        time.run(super.getGamePaint());
        maths.run(super.getGamePaint());
        strategy.run(super.getGamePaint());
        about.run(super.getGamePaint());
        achiveBtn.run(super.getGamePaint());

        // gamePaint.write("(c)MonsterGlad ", 570, 590, Color.WHITE, 15);
    }

    @Override
    public void repaint() {
        try {
            BitmapLoader.menuMusic.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (room.isClicked())
            super.getMainRunActivity().setView(new RoomView(super.getMainRunActivity()));


        ChooseView chooseView;
        if (time.isClicked()) {
            chooseView = new ChooseView(super.getMainRunActivity(), super.getMainRunActivity().getString(R.string.time));
            super.getMainRunActivity().setView(chooseView);
            time.notClicked();
        }

        if (strategy.isClicked()) {
            chooseView = new ChooseView(super.getMainRunActivity(), super.getMainRunActivity().getString(R.string.strategy));
            super.getMainRunActivity().setView(chooseView);
            strategy.notClicked();
        }

        if (maths.isClicked()) {
            chooseView = new ChooseView(super.getMainRunActivity(), super.getMainRunActivity().getString(R.string.maths));
            super.getMainRunActivity().setView(chooseView);
            maths.notClicked();
        }

        if (about.isClicked()) {
            super.getMainRunActivity().aboutGame();
            about.notClicked();
        }

        AchieveMenuView achieveMenuView;

        if (achiveBtn.isClicked()) {
            achieveMenuView = new AchieveMenuView(super.getMainRunActivity());
            super.getMainRunActivity().setView(achieveMenuView);
            achiveBtn.notClicked();
        }
    }

    public static CatIcon checkIconKey(String key) {
        for (CatIcon c : catIcons) if (key.equals(c.getKey())) return c;
        return null;
    }
}
