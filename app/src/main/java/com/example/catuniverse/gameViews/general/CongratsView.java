package com.example.catuniverse.gameViews.general;

import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.annotation.Nullable;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.databaseHelpers.Cat;

import static com.example.catuniverse.gameSupport.BitmapLoader.baseRedButton;
import static com.example.catuniverse.gameSupport.BitmapLoader.baseRedButtonClicked;
import static com.example.catuniverse.gameSupport.BitmapLoader.longBlueRect;

//Выдается при прохождении уровня, обновляет информацию бд о звездах в уровне и доступных персонажах, если необходимо
public class CongratsView extends GameView {

    private final int level;
    private final BasicButton returnBack;
    private final int stars;
    private final Cat reward;
    private final String key;
    private Bitmap starBitmap, backgBitmap, longBackg;
    private int color;

    public CongratsView(MainRunActivity mainRunActivity, int level, int stars, @Nullable Cat reward, String key) {
        super(mainRunActivity);
        this.level = level;
        this.stars = stars;
        this.reward = reward;
        this.key = key;
        returnBack = new BasicButton(mainRunActivity, 300, 150, mainRunActivity.getString(R.string.continue_game), Color.BLACK, 30, BitmapLoader.baseBlueButton, BitmapLoader.baseBlueButtonClicked, 40, 35);

        switch (key) {
            case "time":
                starBitmap = BitmapLoader.star;
                backgBitmap = BitmapLoader.technoBackground;
                longBackg = longBlueRect;
                color = Color.BLACK;
                break;
            case "strategy":
                starBitmap = BitmapLoader.redStar;
                backgBitmap = BitmapLoader.redTechnoBackground;
                longBackg = BitmapLoader.longRedRect;
                returnBack.setBitmap(baseRedButton);
                returnBack.setBitmapClicked(baseRedButtonClicked);
                color = Color.BLACK;
                break;
            case "maths":
                starBitmap = BitmapLoader.redStar;
                backgBitmap = BitmapLoader.mathsGameFinishBackground;
                longBackg = null;
                color = Color.WHITE;
                break;
        }
    }

    @Override
    public void run() {
        repaint();
        int posX = 280;
        super.getGamePaint().setVisibleBitmap(backgBitmap, 0, 0);
        if (longBackg != null)
            super.getGamePaint().setVisibleBitmap(longBackg, -20, -165); // сделать разные цвета для уровне!!!!!
        for (int i = 0; i < stars; i++) {
            super.getGamePaint().setVisibleBitmap(starBitmap, posX, 300);
            posX += 80;
        }

        super.getGamePaint().write(super.getMainRunActivity().getString(R.string.congratulations), 230, 50, color, 45);
        super.getGamePaint().write(super.getMainRunActivity().getString(R.string.you_completed_level) + " " + level, 200, 100, color, 45);

        if (reward != null) { //Если за уровень дается награда
            super.getGamePaint().write(super.getMainRunActivity().getString(R.string.reward), 200, 450, color, 55);
            super.getGamePaint().setVisibleBitmap(MenuView.catIcons.get((reward.getId() - 1)).getIcon(), 400, 380); // отобразить выигранного кота
            if (reward.getUnlocked() == 0)
                BasicGameSupport.unlockCat(reward.getId()); //сделать кота доступным
        }
        returnBack.run(super.getGamePaint());
    }

    @Override
    public void repaint() {
        if (returnBack.isClicked()) {
            super.getMainRunActivity().setView(new ChooseView(super.getMainRunActivity(), key));
        }
    }
}
