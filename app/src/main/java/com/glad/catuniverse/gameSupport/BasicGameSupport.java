package com.glad.catuniverse.gameSupport;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.Nullable;

import com.glad.catuniverse.R;
import com.glad.catuniverse.gameSupport.databaseHelpers.Achievement;
import com.glad.catuniverse.gameSupport.databaseHelpers.Cat;
import com.glad.catuniverse.gameSupport.databaseHelpers.CatPet;
import com.glad.catuniverse.gameSupport.databaseHelpers.Level;
import com.glad.catuniverse.gameSupport.gameTime.TimeLevel;
import com.glad.catuniverse.gameSupport.gameTime.TimePlayer;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;
import com.glad.catuniverse.gameSupport.graphics.ImageSet;
import com.glad.catuniverse.gameSupport.graphics.SpriteAnimation;
import com.glad.catuniverse.gameViews.general.CongratsView;
import com.glad.catuniverse.gameViews.general.GameOverView;
import com.glad.catuniverse.gameViews.levels.TimeLevelsView;
import com.glad.catuniverse.gameSupport.graphics.PlayerManager;

import java.io.IOException;

import static com.glad.catuniverse.MainActivity.achievementCursor;
import static com.glad.catuniverse.MainActivity.achievementDB;
import static com.glad.catuniverse.MainActivity.catCursor;
import static com.glad.catuniverse.MainActivity.catsDB;
import static com.glad.catuniverse.MainActivity.cursor;
import static com.glad.catuniverse.MainActivity.listOfAchievements;
import static com.glad.catuniverse.MainActivity.timeLevels;
import static com.glad.catuniverse.MainActivity.listOfCats;
import static com.glad.catuniverse.MainActivity.listOfPets;
import static com.glad.catuniverse.MainActivity.mathsCursor;
import static com.glad.catuniverse.MainActivity.mathsDB;
import static com.glad.catuniverse.MainActivity.mathsLevels;
import static com.glad.catuniverse.MainActivity.strategyCursor;
import static com.glad.catuniverse.MainActivity.strategyDB;
import static com.glad.catuniverse.MainActivity.strategyLevels;
import static com.glad.catuniverse.MainActivity.timeDB;

//В данном классе хранятся статические функции и переменные для поддержки работы игры
public class BasicGameSupport {
    //Статическая константа для преобразования наносекунд в секунды.
    public static final double SECOND = 1000000000;
    //Количество уровней
    public static int levelsCount = 9;
    //Количество котов
    public static int catsCount = 7;
    //Количесвто достижений
    public static int achievementCount = 3;

    public static final int maximumY = GameView.screenHeight - BitmapLoader.walkRightGray.get(0).getHeight();

    //Обновляет экран по оси ординат относительно текущего положения игрока
    public static int updateMovesY(GameItem gameItem, double jumSpeed, int y) {
        if (!PlayerManager.timePlayer.isRocketMode()) {
            if (PlayerManager.timePlayer.isJumpingLimit()) {
                if (PlayerManager.timePlayer.isJumping()) y += jumSpeed;
                if (PlayerManager.timePlayer.isFalling() && !PlayerManager.timePlayer.isJumping()) y -= jumSpeed;
            } else {
                if (y < gameItem.getControlY()) y += jumSpeed;
                if (y > gameItem.getControlY()) y -= jumSpeed;
            }
        }
        return y;
    }

    public static boolean movingRight(MainRunActivity mainRunActivity) {
        return mainRunActivity.getTouchListener().getTouchX() < GameView.screenWidth && mainRunActivity.getTouchListener().getTouchX() > GameView.screenWidth / 2;
    }

    public static boolean movingLeft(MainRunActivity mainRunActivity) {
        return mainRunActivity.getTouchListener().getTouchX() < GameView.screenWidth / 2;
    }

    //Обновляет экран по оси абсцисс относительно текущего положения игрока
    public static int updateMovesX(double speedPlayer, int x) {
        if (!PlayerManager.timePlayer.isCollisionDetectedRight())
            if (PlayerManager.timePlayer.isMovingLeft() && TimePlayer.start >= -550) {
                x += speedPlayer;
            }

        if (!PlayerManager.timePlayer.isCollisionDetectedLeft() && TimePlayer.start >= -550)
            if (PlayerManager.timePlayer.isMovingRight()) {
                x -= speedPlayer;
            }

        // if()

        if (PlayerManager.timePlayer.isRocketMode()) {
            x -= 2000;
        }
        return x;
    }

    //Обновляет базу данных уровней на время в соответствии с заданными параметрами.
    private static void updateTimeStars(double time, int maxThree, int maxTwo, int stars, int whereClause, @Nullable String catId, MainRunActivity mainRunActivity) {
        ContentValues cv = new ContentValues();
        if (time < maxThree) stars = 3;
        if (time > maxThree && time < maxTwo) stars = 2;
        if (time > maxTwo) stars = 1;

        cv.put("stars", stars);

        if (timeLevels.get(whereClause - 1).getStars() < stars)
            timeDB.update("time", cv, "_id = " + whereClause, null);

        updateTimeDBHelpers();
        if ((catId != null) && (listOfCats.get(Integer.parseInt(catId) - 1).getUnlocked() != 1))
            mainRunActivity.setView(new CongratsView(mainRunActivity, timeLevels.get(whereClause - 1).getNumber(), stars, listOfCats.get((Integer.parseInt(catId) - 1)), mainRunActivity.getString(R.string.time)));

        else
            mainRunActivity.setView(new CongratsView(mainRunActivity, timeLevels.get(whereClause - 1).getNumber(), stars, null, mainRunActivity.getString(R.string.time)));
    }

    //Обнавляет базу данных с информацией о стратегических или математических уровнях (в зависимости от переданного ключа)
    public static void updateStrategyMathsStars(int lives, int requestedLives, int stars, int whereClause,
                                                @Nullable String catId, MainRunActivity mainRunActivity, String key, int achieveId) {
        ContentValues cv = new ContentValues();
        if (lives == requestedLives) stars = 3;
        if (lives < requestedLives && lives > 1) stars = 2;
        if (lives == 1) stars = 1;
        cv.put("stars", stars);

        Log.d("UPDATESTARS", "Ach id " + achieveId);
        if (achieveId != -1) unlockAchievement(achieveId);

        switch (key) {
            case "strategy":
                if (strategyLevels.get(whereClause - 1).getStars() < stars)
                    strategyDB.update("strategy", cv, "_id = " + whereClause, null);
                updateStrategyDBHelpers();
                if ((catId != null) && (listOfCats.get(Integer.parseInt(catId) - 1).getUnlocked() != 1))
                    mainRunActivity.setView(new CongratsView(mainRunActivity, strategyLevels.get(whereClause - 1).getNumber(), stars, listOfCats.get(Integer.parseInt(catId) - 1), "strategy"));
                else
                    mainRunActivity.setView(new CongratsView(mainRunActivity, strategyLevels.get(whereClause - 1).getNumber(), stars, null, "strategy"));
                break;

            case "maths":
                if (mathsLevels.get(whereClause - 1).getStars() < stars)
                    mathsDB.update("maths", cv, "_id = " + whereClause, null);
                updateMathsDBHelpers();
                if ((catId != null) && (listOfCats.get(Integer.parseInt(catId) - 1).getUnlocked() != 1))
                    mainRunActivity.setView(new CongratsView(mainRunActivity, mathsLevels.get(whereClause - 1).getNumber(), stars, listOfCats.get(Integer.parseInt(catId) - 1), "maths"));
                else
                    mainRunActivity.setView(new CongratsView(mainRunActivity, mathsLevels.get(whereClause - 1).getNumber(), stars, null, "maths"));
                break;
        }
    }

    //Делает кота доступным (обновляет поле unlocked в бд cats)
    public static void unlockCat(int whereClause) {
        System.out.println("WHERE = " + whereClause);
        ContentValues cv = new ContentValues();
        cv.put("unlocked", 1);
        catsDB.update("cats", cv, "_id = " + whereClause, null);
        updateCatDBHelpers();
    }

    //Добавляет кота в комнату (обновляет поле room в бд cats)
    public static void putCatIntoRoom(int room, int whereClause) {
        ContentValues cv = new ContentValues();
        cv.put("room", room);
        catsDB.update("cats", cv, "_id = " + whereClause, null);
        updateCatDBHelpers();
    }

    //Находит набор анимаций персонажа по полученному ключу
    public static ImageSet checkKey(String key) {
        for (ImageSet im : BitmapLoader.imageSets) if (key.equals(im.getKey())) return im;
        return null;
    }

    //Обновить списки котов
    public static void updateCatDBHelpers() {
        System.out.println("CAT UPDATE");
        listOfCats.clear();
        listOfPets.clear();
        for (int i = 0; i < catsCount; i++) {
            catCursor = catsDB.rawQuery("SELECT * from cats WHERE _id = " + (i + 1), null);
            if (catCursor != null && catCursor.moveToFirst()) {
                listOfCats.add(new Cat(catCursor.getInt(0), catCursor.getString(1), catCursor.getString(2), catCursor.getInt(3), catCursor.getInt(4), catCursor.getInt(5), catCursor.getInt(6), catCursor.getInt(7), catCursor.getInt(8), catCursor.getInt(9), catCursor.getInt(10)));
                listOfPets.add(new CatPet(listOfCats.get(i), catCursor.getInt(0), catCursor.getInt(8)));
            }
        }
    }

    //Обновить списки с информацией об уровнях
    private static void updateTimeDBHelpers() {
        System.out.println("TIME UPDATE");

        timeLevels.clear();
        for (int i = 0; i < levelsCount; i++) {
            cursor = timeDB.rawQuery("SELECT * from time WHERE _id = " + (i + 1), null);
            if (cursor != null && cursor.moveToFirst())
                timeLevels.add(new Level(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2)));
        }
    }

    //Обновить списки с информацией о стратегических уровнях
    private static void updateStrategyDBHelpers() {
        System.out.println("STRATEGY UPDATE");

        strategyLevels.clear();
        for (int i = 0; i < levelsCount; i++) {
            strategyCursor = strategyDB.rawQuery("SELECT * from strategy WHERE _id = " + (i + 1), null);
            if (strategyCursor != null && strategyCursor.moveToFirst())
                strategyLevels.add(new Level(strategyCursor.getInt(0), strategyCursor.getInt(1), cursor.getInt(2)));
        }
    }

    //Обновить списки с информацией о математических уровнях
    private static void updateMathsDBHelpers() {
        System.out.println("MATHS UPDATE");

        mathsLevels.clear();
        for (int i = 0; i < levelsCount; i++) {
            mathsCursor = mathsDB.rawQuery("SELECT * from maths WHERE _id = " + (i + 1), null);
            if (mathsCursor != null && mathsCursor.moveToFirst())
                mathsLevels.add(new Level(mathsCursor.getInt(0), mathsCursor.getInt(1), cursor.getInt(2)));
        }
    }

    //Обновить списки с информацией о достижениях
    public static void updateAchieveDBHelpers() {
        System.out.println("ACHIEVE UPDATE");

        listOfAchievements.clear();
        for (int i = 0; i < BasicGameSupport.achievementCount; i++) {
            achievementCursor = achievementDB.rawQuery("SELECT * from achievement WHERE _id = " + (i + 1), null);
            if (achievementCursor != null && achievementCursor.moveToFirst()) {
                listOfAchievements.add(new Achievement(achievementCursor.getInt(0), achievementCursor.getString(1), achievementCursor.getInt(2), achievementCursor.getString(3)));
            }
        }
    }

    //открыть достижение
    public static void unlockAchievement(int whereClause) {
        ContentValues cv = new ContentValues();
        cv.put("unlocked", 1);
        achievementDB.update("achievement", cv, "_id = " + whereClause, null);
        updateAchieveDBHelpers();
    }

    //Выбрать игрока
    public static void choosePlayer(int whereClause) {
        ContentValues cv = new ContentValues();
        cv.put("chosen", 1);

        boolean unlocked = false;

        catCursor = catsDB.rawQuery("SELECT * from cats WHERE _id = " + whereClause, null);
        if (catCursor != null && catCursor.moveToFirst())
            if (catCursor.getInt(7) == 1) unlocked = true;

        if (unlocked) {
            for (int j = 0; j < catsCount; j++) {
                catCursor = catsDB.rawQuery("SELECT * from cats WHERE _id = " + (j + 1), null);
                if (catCursor != null && catCursor.moveToFirst())
                    if (catCursor.getInt(6) == 1) {
                        ContentValues cvNot = new ContentValues();
                        cvNot.put("chosen", 0);
                        catsDB.update("cats", cvNot, "_id = " + catCursor.getInt(0), null);
                        updateCatDBHelpers();
                    }
            }

            catsDB.update("cats", cv, "_id = " + whereClause, null);
        }
        updateCatDBHelpers();
    }

    //Проверяет пройден или проигран уровень (уровни на время), принимает уровень, текущий GameView,
    //булевую переменну, с помощью которой проверяет выполнены ли требования к прохождению уровня, номер уровня,
    //id персонжа, GsmeView уровеня, который нужно перезапустить при поражении, GsmeView, музыку текущего уровня
    public static void timeLevelFinish(TimeLevel timeLevel, GameView gameView,
                                       int levelNum, @Nullable String rewardId, GameView restart, Media.Music music) {


        if (timeLevel.getPassingDoor().isClicked() && timeLevel.isRequirementsCollected()) {
            Log.d("TimeLevelFinish", "in 1st");
            if (timeLevel.unlockAchievement() && timeLevel.getAchievementId() != -1)
                unlockAchievement(timeLevel.getAchievementId());

            BasicGameSupport.updateTimeStars(timeLevel.getEndTime(), timeLevel.getThreeStars(), timeLevel.getTwoStars(), gameView.getStars(), levelNum, rewardId, gameView.getMainRunActivity());
            TimeLevelsView.levelRunning = false;
            music.stop();
            try {
                BitmapLoader.menuMusic.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
            timeLevel.getPassingDoor().notClicked();
        }

        if (timeLevel.isGameOver()) {
            Log.d("TimeLevelFinish", "in 2nd");
            TimeLevelsView.levelRunning = false;
            music.stop();
            try {
                BitmapLoader.menuMusic.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
            PlayerManager.timePlayer.setRocketMode(false);

            gameView.getMainRunActivity().setView(new GameOverView(gameView.getMainRunActivity(), restart, gameView.getMainRunActivity().getString(R.string.time)));

        }
    }


    public static void quickSort(int[] array, int low, int high) {

        int i = low;
        int j = high;
        int pivot = array[(i + j) / 2];
        int temp;

        while (i <= j) {
            while (array[i] < pivot)
                i++;
            while (array[j] > pivot)
                j--;
            if (i <= j) {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (j > low)
            quickSort(array, low, j);
        if (i < high)
            quickSort(array, i, high);
    }

    public static void drawGrid(GamePaint gamePaint, Bitmap background, int color) {
        gamePaint.setVisibleBitmap(background, 0, 0);

        int y = 100;
        for (int i = 0; i < 10; i++) {
            gamePaint.createLine(0, y, 950, y, color);
            y += 100;
        }
        int x = 100;
        for (int i = 0; i < 10; i++) {
            gamePaint.createLine(x, 0, x, 650, color);
            x += 100;
        }
    }

    //Инициализация анимаций всех котов в качестве статических переменных
    public static SpriteAnimation
            asteroidItem = new SpriteAnimation(BitmapLoader.asteroidSprite),

    grayWalkLeft = new SpriteAnimation(BitmapLoader.walkLeftGray),
            grayStandRight = new SpriteAnimation(BitmapLoader.standRightGray),
            grayStandLeft = new SpriteAnimation(BitmapLoader.standLeftGray),
            grayWalkRight = new SpriteAnimation(BitmapLoader.walkRightGray),
            grayJumpRight = new SpriteAnimation(BitmapLoader.jumpRightGray),
            grayJumpLeft = new SpriteAnimation(BitmapLoader.jumpLeftGray),
            grayRocket = new SpriteAnimation(BitmapLoader.rocketGray),

    orangeStandRight = new SpriteAnimation(BitmapLoader.standRightOrange),
            orangeStandLeft = new SpriteAnimation(BitmapLoader.standLeftOrange),
            orangeWalkRight = new SpriteAnimation(BitmapLoader.walkRightOrange),
            orangeWalkLeft = new SpriteAnimation(BitmapLoader.walkLeftOrange),
            orangeJumpRight = new SpriteAnimation(BitmapLoader.jumpRightOrange),
            orangeJumpLeft = new SpriteAnimation(BitmapLoader.jumpLeftOrange),
            orangeRocket = new SpriteAnimation(BitmapLoader.rocketOrange),

    greenAlienStandRight = new SpriteAnimation(BitmapLoader.standRightGreenAlien),
            greenAlienStandLeft = new SpriteAnimation(BitmapLoader.standLeftGreenAlien),
            greenAlienWalkRight = new SpriteAnimation(BitmapLoader.walkRightGreenAlien),
            greenAlienWalkLeft = new SpriteAnimation(BitmapLoader.walkLeftGreenAlien),
            greenAlienJumpRight = new SpriteAnimation(BitmapLoader.jumpRightGreenAlien),
            greenAlienJumpLeft = new SpriteAnimation(BitmapLoader.jumpLeftGreenAlien),
            greenAlienRocket = new SpriteAnimation(BitmapLoader.rocketGreenAlien),

    shadowStandRight = new SpriteAnimation(BitmapLoader.standRightShadow),
            shadowStandLeft = new SpriteAnimation(BitmapLoader.standLeftShadow),
            shadowWalkRight = new SpriteAnimation(BitmapLoader.walkRightShadow),
            shadowWalkLeft = new SpriteAnimation(BitmapLoader.walkLeftShadow),
            shadowJumpRight = new SpriteAnimation(BitmapLoader.jumpRightShadow),
            shadowJumpLeft = new SpriteAnimation(BitmapLoader.jumpLeftShadow),
            shadowCatRocket = new SpriteAnimation(BitmapLoader.rocketShadow),

    mainCoonStandRight = new SpriteAnimation(BitmapLoader.standRightMainCoon),
            mainCoonStandLeft = new SpriteAnimation(BitmapLoader.standLeftMainCoon),
            mainCoonWalkRight = new SpriteAnimation(BitmapLoader.walkRightMainCoon),
            mainCoonWalkLeft = new SpriteAnimation(BitmapLoader.walkLeftMainCoon),
            mainCoonJumpRight = new SpriteAnimation(BitmapLoader.jumpRightMainCoon),
            mainCoonJumpLeft = new SpriteAnimation(BitmapLoader.jumpLeftMainCoon),
            mainCoonRocket = new SpriteAnimation(BitmapLoader.rocketMainCoon),

    bobtailStandRight = new SpriteAnimation(BitmapLoader.standRightBobtail),
            bobtailStandLeft = new SpriteAnimation(BitmapLoader.standLeftBobtail),
            bobtailWalkRight = new SpriteAnimation(BitmapLoader.walkRightBobtail),
            bobtailWalkLeft = new SpriteAnimation(BitmapLoader.walkLeftBobtail),
            bobtailJumpRight = new SpriteAnimation(BitmapLoader.jumpRightBobtail),
            bobtailJumpLeft = new SpriteAnimation(BitmapLoader.jumpLeftBobtail),
            bobtailRocket = new SpriteAnimation(BitmapLoader.rocketBobtail);
}
