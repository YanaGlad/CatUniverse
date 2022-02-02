package com.glad.catuniverse.gameSupport;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import com.example.catuniverse.R;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;
import com.glad.catuniverse.gameSupport.graphics.ImageSet;

import java.io.IOException;
import java.util.ArrayList;

//Загрузчик изображений и прочих ресурсов из assets
public class BitmapLoader {

    static ArrayList<ImageSet> imageSets = new ArrayList<>();

    //Музыка
    public static Media.Music electrodynamixMusic, phobosMusic, theoryMusic, xStepMusic, menuMusic, stayInsideMusic;

    //коты
    private static Bitmap grayCat, grayCatReversed, orangeCat, orangeCatReversed, shadowCat,
            shadowCatReversed, greenAlienCat, greenAlienCatReversed, mainCoonCat, mainCoonCatReversed,
            bobtailCat, bobtailCatReversed, redAlienCat, redAlienCatReversed;
    //Иконки котов
    public static Bitmap questionCat, grayIcon, orangeIcon, greenAlienCatIcon, shadowCatIcon, mainCoonCatIcon, bobtailCatIcon, redAlienCatIcon;

    //Иконки достижений
    public static Bitmap dexterityAch, starCollectorAch;

    //Анимации котов
    public static ArrayList<Bitmap> asteroidSprite;

    public static ArrayList<Bitmap> walkLeftGray, standRightGray, standLeftGray, rocketGray;
    public static ArrayList<Bitmap> jumpRightGray, walkRightGray, jumpLeftGray;
    public static ArrayList<Bitmap> walkLeftOrange, standRightOrange, standLeftOrange, rocketOrange;
    public static ArrayList<Bitmap> jumpRightOrange, walkRightOrange, jumpLeftOrange;
    public static ArrayList<Bitmap> walkLeftGreenAlien, standRightGreenAlien, standLeftGreenAlien, rocketGreenAlien;
    public static ArrayList<Bitmap> jumpRightGreenAlien, walkRightGreenAlien, jumpLeftGreenAlien;
    public static ArrayList<Bitmap> walkLeftShadow, standRightShadow, standLeftShadow, rocketShadow;
    public static ArrayList<Bitmap> jumpRightShadow, walkRightShadow, jumpLeftShadow;
    public static ArrayList<Bitmap> walkLeftMainCoon, standRightMainCoon, standLeftMainCoon, rocketMainCoon;
    public static ArrayList<Bitmap> jumpRightMainCoon, walkRightMainCoon, jumpLeftMainCoon;
    public static ArrayList<Bitmap> walkLeftBobtail, standRightBobtail, standLeftBobtail, rocketBobtail;
    public static ArrayList<Bitmap> jumpRightBobtail, walkRightBobtail, jumpLeftBobtail;
    public static ArrayList<Bitmap> walkLeftRedAlien, standRightRedAlien, standLeftRedAlien, rocketRedAlien;
    public static ArrayList<Bitmap> jumpRightRedAlien, walkRightRedAlien, jumpLeftRedAlien;

    //Кнопки
    public static Bitmap blueLevelButtonClicked, blueLevelButton, baseBlueButton, baseBlueButtonClicked, baseRedButton, baseRedButtonClicked, attackButton, nextButton, nextButtonReversed, redLevelButton, redLevelButtonClicked;
    public static Bitmap addCatButton, addCatButtonClicked, mainMenuButton, mainMenuButtonClicked, smallButton, smallButtonClicked, strategyButton, strategyButtonClicked;
    public static Bitmap exitButton, exitButtonClicked, appearButton, storageUp, storageDown, smallButtonRed, smallButtonRedClicked;

    //Фоны
    public static Bitmap spaceBackground, spaceBase, laboratoryBackground, technoBackground, redTechnoBackground, mathsBackground, mathsGameFinishBackground, strategyCatCount,
            movingSpaceBackground, movingBlueSpaceBackground, strategyBackground, blueGround, purpleGround, movingMathsBackground, moonBackground,
            spaceTestBackground;

    //Двери
    public static Bitmap redDoor, blueDoor, blueDoorOpened, purpleDoor, purpleDoorOpened;
    //Объекты
    public static Bitmap asteroid, mathsPlayerSkeleton, bluePlatformSkeleton, star, redStar, mathsAnswerBig, closedLevel, pricePanel, bullet,
            bluePlatform, purplePlatform, tallWallSkeleton, tallWall, mathAnswerSkeleton, mathsAnswer, mathPlayer, heart, keyBlue, collTallRect,
            yellowKey, rocketStation, blueDecorStation, purpleDecorStation, longBlueRect, shortBlueRect, longRedRect, darkerDot,
            sharps, longPurpleRect, rocketDecor;

    public BitmapLoader(MainRunActivity mainRunActivity, GamePaint gamePaint) throws IOException {

        loadTexture(gamePaint);
        loadSprites(gamePaint);
        loadAudio(mainRunActivity);
    }

    private void loadAudio(MainRunActivity mainRunActivity) throws IOException {
        electrodynamixMusic = mainRunActivity.getMedia().setMusic("music.mp3");
        phobosMusic = mainRunActivity.getMedia().setMusic("phobos.mp3");
        menuMusic = mainRunActivity.getMedia().setMusic("Robot.mp3");
        theoryMusic = mainRunActivity.getMedia().setMusic("Theory.mp3");
        xStepMusic = mainRunActivity.getMedia().setMusic("xStep.mp3");
        stayInsideMusic = mainRunActivity.getMedia().setMusic("stayInside.mp3");

        //Инициализация наборов анимаций
        imageSets.add(new ImageSet(mainRunActivity.getString(R.string.cat_gray), BasicGameSupport.grayStandRight, BasicGameSupport.grayStandLeft, BasicGameSupport.grayWalkRight, BasicGameSupport.grayWalkLeft, BasicGameSupport.grayJumpRight, BasicGameSupport.grayJumpLeft, BasicGameSupport.grayRocket));
        imageSets.add(new ImageSet(mainRunActivity.getString(R.string.cat_orange), BasicGameSupport.orangeStandRight, BasicGameSupport.orangeStandLeft, BasicGameSupport.orangeWalkRight, BasicGameSupport.orangeWalkLeft, BasicGameSupport.orangeJumpRight, BasicGameSupport.orangeJumpLeft, BasicGameSupport.orangeRocket));
        imageSets.add(new ImageSet(mainRunActivity.getString(R.string.cat_green_alien), BasicGameSupport.greenAlienStandRight, BasicGameSupport.greenAlienStandLeft, BasicGameSupport.greenAlienWalkRight, BasicGameSupport.greenAlienWalkLeft, BasicGameSupport.greenAlienJumpRight, BasicGameSupport.greenAlienJumpLeft, BasicGameSupport.greenAlienRocket));
        imageSets.add(new ImageSet(mainRunActivity.getString(R.string.cat_shadow), BasicGameSupport.shadowStandRight, BasicGameSupport.shadowStandLeft, BasicGameSupport.shadowWalkRight, BasicGameSupport.shadowWalkLeft, BasicGameSupport.shadowJumpRight, BasicGameSupport.shadowJumpLeft, BasicGameSupport.shadowCatRocket));
        imageSets.add(new ImageSet(mainRunActivity.getString(R.string.cat_main_coon), BasicGameSupport.mainCoonStandRight, BasicGameSupport.mainCoonStandLeft, BasicGameSupport.mainCoonWalkRight, BasicGameSupport.mainCoonWalkLeft, BasicGameSupport.mainCoonJumpRight, BasicGameSupport.mainCoonJumpLeft, BasicGameSupport.mainCoonRocket));
        imageSets.add(new ImageSet(mainRunActivity.getString(R.string.cat_bob_tail), BasicGameSupport.bobtailStandRight, BasicGameSupport.bobtailStandLeft, BasicGameSupport.bobtailWalkRight, BasicGameSupport.bobtailWalkLeft, BasicGameSupport.bobtailJumpRight, BasicGameSupport.bobtailJumpLeft, BasicGameSupport.bobtailRocket));

    }

    private void loadSprites(GamePaint gamePaint) {
        createSpiteStandList(asteroidSprite, null, asteroid, null);

        createSpriteJumpingList(jumpLeftGray, jumpRightGray, grayCatReversed, grayCat);
        createSpiteStandList(standLeftGray, standRightGray, grayCatReversed, grayCat);
        createSpriteWalkList(walkLeftGray, walkRightGray, grayCatReversed, grayCat);
        createRocketList(rocketGray, grayCat);

        createSpriteJumpingList(jumpLeftOrange, jumpRightOrange, orangeCatReversed, orangeCat);
        createSpiteStandList(standLeftOrange, standRightOrange, orangeCatReversed, orangeCat);
        createSpriteWalkList(walkLeftOrange, walkRightOrange, orangeCatReversed, orangeCat);
        createRocketList(rocketOrange, orangeCat);

        createSpriteJumpingList(jumpLeftGreenAlien, jumpRightGreenAlien, greenAlienCatReversed, greenAlienCat);
        createSpiteStandList(standLeftGreenAlien, standRightGreenAlien, greenAlienCatReversed, greenAlienCat);
        createSpriteWalkList(walkLeftGreenAlien, walkRightGreenAlien, greenAlienCatReversed, greenAlienCat);
        createRocketList(rocketGreenAlien, greenAlienCat);

        createSpriteJumpingList(jumpLeftShadow, jumpRightShadow, shadowCatReversed, shadowCat);
        createSpiteStandList(standLeftShadow, standRightShadow, shadowCatReversed, shadowCat);
        createSpriteWalkList(walkLeftShadow, walkRightShadow, shadowCatReversed, shadowCat);
        createRocketList(rocketShadow, shadowCat);

        createSpriteJumpingList(jumpLeftMainCoon, jumpRightMainCoon, mainCoonCatReversed, mainCoonCat);
        createSpiteStandList(standLeftMainCoon, standRightMainCoon, mainCoonCatReversed, mainCoonCat);
        createSpriteWalkList(walkLeftMainCoon, walkRightMainCoon, mainCoonCatReversed, mainCoonCat);
        createRocketList(rocketMainCoon, mainCoonCat);

        createSpriteJumpingList(jumpLeftBobtail, jumpRightBobtail, bobtailCatReversed, bobtailCat);
        createSpiteStandList(standLeftBobtail, standRightBobtail, bobtailCatReversed, bobtailCat);
        createSpriteWalkList(walkLeftBobtail, walkRightBobtail, bobtailCatReversed, bobtailCat);
        createRocketList(rocketBobtail, bobtailCat);

        createSpriteJumpingList(jumpLeftRedAlien, jumpRightRedAlien, redAlienCatReversed, redAlienCat);
        createSpiteStandList(standLeftRedAlien, standRightRedAlien, redAlienCatReversed, redAlienCat);
        createSpriteWalkList(walkLeftRedAlien, walkRightRedAlien, redAlienCatReversed, redAlienCat);
        createRocketList(rocketRedAlien, redAlienCat);
        ///
    }


    private void loadTexture(GamePaint gamePaint) {

        asteroidSprite = new ArrayList<>();
        //Коты
        jumpLeftGray = new ArrayList<>();
        jumpRightGray = new ArrayList<>();
        walkRightGray = new ArrayList<>();
        walkLeftGray = new ArrayList<>();
        standRightGray = new ArrayList<>();
        standLeftGray = new ArrayList<>();
        rocketGray = new ArrayList<>();

        jumpLeftOrange = new ArrayList<>();
        jumpRightOrange = new ArrayList<>();
        walkRightOrange = new ArrayList<>();
        walkLeftOrange = new ArrayList<>();
        standRightOrange = new ArrayList<>();
        standLeftOrange = new ArrayList<>();
        rocketOrange = new ArrayList<>();

        walkLeftGreenAlien = new ArrayList<>();
        walkRightGreenAlien = new ArrayList<>();
        jumpLeftGreenAlien = new ArrayList<>();
        jumpRightGreenAlien = new ArrayList<>();
        standRightGreenAlien = new ArrayList<>();
        standLeftGreenAlien = new ArrayList<>();
        rocketGreenAlien = new ArrayList<>();

        walkLeftShadow = new ArrayList<>();
        walkRightShadow = new ArrayList<>();
        jumpLeftShadow = new ArrayList<>();
        jumpRightShadow = new ArrayList<>();
        standRightShadow = new ArrayList<>();
        standLeftShadow = new ArrayList<>();
        rocketShadow = new ArrayList<>();

        walkLeftMainCoon = new ArrayList<>();
        walkRightMainCoon = new ArrayList<>();
        jumpLeftMainCoon = new ArrayList<>();
        jumpRightMainCoon = new ArrayList<>();
        standRightMainCoon = new ArrayList<>();
        standLeftMainCoon = new ArrayList<>();
        rocketMainCoon = new ArrayList<>();

        walkLeftBobtail = new ArrayList<>();
        walkRightBobtail = new ArrayList<>();
        jumpLeftBobtail = new ArrayList<>();
        jumpRightBobtail = new ArrayList<>();
        standRightBobtail = new ArrayList<>();
        standLeftBobtail = new ArrayList<>();
        rocketBobtail = new ArrayList<>();

        walkLeftRedAlien= new ArrayList<>();
        walkRightRedAlien = new ArrayList<>();
        jumpLeftRedAlien = new ArrayList<>();
        jumpRightRedAlien = new ArrayList<>();
        standRightRedAlien = new ArrayList<>();
        standLeftRedAlien = new ArrayList<>();
        rocketRedAlien = new ArrayList<>();

        //Загрузка спрайтов котов
        grayCatReversed = gamePaint.createNewGraphicsBitmap("grayCatReversed.png");
        grayCat = gamePaint.createNewGraphicsBitmap("grayCat.png");
        orangeCat = gamePaint.createNewGraphicsBitmap("orangeCat.png");
        orangeCatReversed = gamePaint.createNewGraphicsBitmap("orangeCatReversed.png");
        greenAlienCat = gamePaint.createNewGraphicsBitmap("greenAlienCat.png");
        greenAlienCatReversed = gamePaint.createNewGraphicsBitmap("greenAlienCatReversed.png");
        shadowCat = gamePaint.createNewGraphicsBitmap("shadowCat.png");
        shadowCatReversed = gamePaint.createNewGraphicsBitmap("shadowCatReversed.png");
        mainCoonCat = gamePaint.createNewGraphicsBitmap("mainCoonCat.png");
        mainCoonCatReversed = gamePaint.createNewGraphicsBitmap("mainCoonCatReversed.png");
        bobtailCat = gamePaint.createNewGraphicsBitmap("bobtailCat.png");
        bobtailCatReversed = gamePaint.createNewGraphicsBitmap("bobtailCatReversed.png");
        redAlienCat = gamePaint.createNewGraphicsBitmap("redAlien.png");
        redAlienCatReversed = gamePaint.createNewGraphicsBitmap("redAlienReversed.png");

        //Загрузка иконок котов
        questionCat = gamePaint.createNewGraphicsBitmap("questionCat.png");
        grayIcon = gamePaint.createNewGraphicsBitmap("grayIcon.png");
        orangeIcon = gamePaint.createNewGraphicsBitmap("OrangeIcon.png");
        greenAlienCatIcon = gamePaint.createNewGraphicsBitmap("greenAlienIcon.png");
        shadowCatIcon = gamePaint.createNewGraphicsBitmap("shadowIcon.png");
        mainCoonCatIcon = gamePaint.createNewGraphicsBitmap("mainCoonIcon.png");
        bobtailCatIcon = gamePaint.createNewGraphicsBitmap("bobtailIcon.png");
        redAlienCatIcon = gamePaint.createNewGraphicsBitmap("redAlienIcon.png");

        //Загрузка иконок достижений
        dexterityAch = gamePaint.createNewGraphicsBitmap("strengthAch.png");
        starCollectorAch = gamePaint.createNewGraphicsBitmap("starCollector.png");

        //Загрузка изображений кнопок
        blueLevelButton = gamePaint.createNewGraphicsBitmap("levelButton.png");
        blueLevelButtonClicked = gamePaint.createNewGraphicsBitmap("levelButtonClicked.png");
        baseBlueButton = gamePaint.createNewGraphicsBitmap("baseButton.png");
        baseBlueButtonClicked = gamePaint.createNewGraphicsBitmap("baseButtonClicked.png");
        baseRedButton = gamePaint.createNewGraphicsBitmap("baseRedButton.png");
        baseRedButtonClicked = gamePaint.createNewGraphicsBitmap("baseRedButtonClicked.png");
        attackButton = gamePaint.createNewGraphicsBitmap("attack3.png");
        addCatButton = gamePaint.createNewGraphicsBitmap("addCatButton.png");
        addCatButtonClicked = gamePaint.createNewGraphicsBitmap("addCatButtonClicked.png");
        mainMenuButton = gamePaint.createNewGraphicsBitmap("mainMenuButton.png");
        mainMenuButtonClicked = gamePaint.createNewGraphicsBitmap("mainMenuButtonClicked.png");
        smallButton = gamePaint.createNewGraphicsBitmap("smallButton.png");
        smallButtonClicked = gamePaint.createNewGraphicsBitmap("smallButtonClicked.png");
        redLevelButton = gamePaint.createNewGraphicsBitmap("redLevelButton.png");
        redLevelButtonClicked = gamePaint.createNewGraphicsBitmap("redButtonClicked.png");
        strategyButton = gamePaint.createNewGraphicsBitmap("strategyButton.png");
        strategyButtonClicked = gamePaint.createNewGraphicsBitmap("strategyButtonClicked.png");
        exitButton = gamePaint.createNewGraphicsBitmap("exitButton.png");
        exitButtonClicked = gamePaint.createNewGraphicsBitmap("exitButtonClicked.png");
        strategyCatCount = gamePaint.createNewGraphicsBitmap("strategyCatCount.png");
        storageUp = gamePaint.createNewGraphicsBitmap("up.png");
        storageDown = gamePaint.createNewGraphicsBitmap("down.png");
        smallButtonRed = gamePaint.createNewGraphicsBitmap("smallButtonRed.png");
        smallButtonRedClicked = gamePaint.createNewGraphicsBitmap("smallButtonRedClicked.png");

        //Загрузка фонов
        spaceBase = gamePaint.createNewGraphicsBitmap("spaceBase.png");
        spaceBackground = gamePaint.createNewGraphicsBitmap("spaceBackground.png");
        technoBackground = gamePaint.createNewGraphicsBitmap("technoBackground.png");
        redTechnoBackground = gamePaint.createNewGraphicsBitmap("redTechnoBackground.png");
        movingSpaceBackground = gamePaint.createNewGraphicsBitmap("movingSpaceBackground.png");
        movingBlueSpaceBackground = gamePaint.createNewGraphicsBitmap("movingBlueSpaceBackground.png");
        strategyBackground = gamePaint.createNewGraphicsBitmap("strategyBackground.png");
        pricePanel = gamePaint.createNewGraphicsBitmap("pricePanel.png");
        mathsBackground = gamePaint.createNewGraphicsBitmap("maths.png");
        blueGround = gamePaint.createNewGraphicsBitmap("groundBackground.png");
        mathsGameFinishBackground = gamePaint.createNewGraphicsBitmap("mathsGameOverBackground.png");
        movingMathsBackground = gamePaint.createNewGraphicsBitmap("movingMathsBackground.png");
        laboratoryBackground = gamePaint.createNewGraphicsBitmap("LaboratoryBackground.png");
        purpleGround = gamePaint.createNewGraphicsBitmap("purpleGround.png");
        moonBackground = gamePaint.createNewGraphicsBitmap("Moon.png");
        spaceTestBackground = gamePaint.createNewGraphicsBitmap("spaceTest.png");

        //Загрузка изображений дверей
        redDoor = gamePaint.createNewGraphicsBitmap("redDoor.png");
        blueDoor = gamePaint.createNewGraphicsBitmap("blueDoor.png");
        blueDoorOpened = gamePaint.createNewGraphicsBitmap("blueDoorOpened.png");
        purpleDoor = gamePaint.createNewGraphicsBitmap("purpleDoor.png");
        purpleDoorOpened = gamePaint.createNewGraphicsBitmap("purpleDoorOpened.png");

        //Объекты
        bluePlatform = gamePaint.createNewGraphicsBitmap("blueObstacle.png");
        purplePlatform = gamePaint.createNewGraphicsBitmap("purpleObstacle.png");
        bluePlatformSkeleton = gamePaint.createNewGraphicsBitmap("blueObstacleSkeleton.png");
        heart = gamePaint.createNewGraphicsBitmap("heart.png");
        mathsPlayerSkeleton = gamePaint.createNewGraphicsBitmap("mathPlayerSkeleton.png");
        tallWallSkeleton = gamePaint.createNewGraphicsBitmap("tallWallSkeleton.png");
        tallWall = gamePaint.createNewGraphicsBitmap("tallWall.png");
        mathAnswerSkeleton = gamePaint.createNewGraphicsBitmap("mathAnswerSkeleton.png");
        mathsAnswer = gamePaint.createNewGraphicsBitmap("mathsAnswer.png");
        mathPlayer = gamePaint.createNewGraphicsBitmap("mathPlayer.png");
        star = gamePaint.createNewGraphicsBitmap("star.png");
        redStar = gamePaint.createNewGraphicsBitmap("redStar.png");
        mathsAnswerBig = gamePaint.createNewGraphicsBitmap("mathsAnswerBig.png");
        closedLevel = gamePaint.createNewGraphicsBitmap("closedLevel.png");
        keyBlue = gamePaint.createNewGraphicsBitmap("key.png");
        appearButton = gamePaint.createNewGraphicsBitmap("buttonPr.png");
        collTallRect = gamePaint.createNewGraphicsBitmap("collTallRect.png");
        bullet = gamePaint.createNewGraphicsBitmap("bullet.png");
        asteroid = gamePaint.createNewGraphicsBitmap("asteroid.png");
        yellowKey = gamePaint.createNewGraphicsBitmap("yellowKey.png");
        rocketStation = gamePaint.createNewGraphicsBitmap("rocketStation.png");
        blueDecorStation = gamePaint.createNewGraphicsBitmap("decorStation.png");
        purpleDecorStation = gamePaint.createNewGraphicsBitmap("decorStationPurple.png");
        longBlueRect = gamePaint.createNewGraphicsBitmap("shortBlueRect.png");
        shortBlueRect = gamePaint.createNewGraphicsBitmap("longBlueRect.png");
        longRedRect = gamePaint.createNewGraphicsBitmap("longRedRect.png");
        longPurpleRect = gamePaint.createNewGraphicsBitmap("longPurpleRect.png");
        darkerDot = gamePaint.createNewGraphicsBitmap("darkerDot.png");
        sharps = gamePaint.createNewGraphicsBitmap("shipi.png");
        rocketDecor = gamePaint.createNewGraphicsBitmap("rocket.png");
    }

    private void createSpiteStandList(ArrayList<Bitmap> catLeft, @Nullable ArrayList<Bitmap> catRight, Bitmap bmpReversed, @Nullable Bitmap bmpRegular) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            catLeft.add(Bitmap.createBitmap(bmpReversed, count, 0, 80, 94));
            if (catRight != null)
                catRight.add(Bitmap.createBitmap(bmpRegular, count, 0, 80, 94));
            count += 80;
        }
    }

    private void createSpriteWalkList(ArrayList<Bitmap> walkLeft, ArrayList<Bitmap> walkRight, Bitmap bmpReversed, Bitmap bmpRegular) {
        int count = 0;
        int count2 = 0;
        for (int i = 0; i < 6; i++) {
            walkLeft.add(Bitmap.createBitmap(bmpReversed, 164 + count, 94, 79, 94));
            walkRight.add(Bitmap.createBitmap(bmpRegular, count2, 94, 78, 94));
            count += 79;
            count2 += 80;
        }
    }

    private void createSpriteJumpingList(ArrayList<Bitmap> jumpLeft, ArrayList<Bitmap> jumpRight, Bitmap bmpReversed, Bitmap bmpRegular) {
        int count = 0;
        int count2 = 0;

        for (int i = 0; i < 3; i++) {
            jumpRight.add(Bitmap.createBitmap(bmpRegular, count2, 282, 90, 94));
            jumpLeft.add(Bitmap.createBitmap(bmpReversed, 380 + count, 282, 85, 94));

            count += 85;
            count2 += 90;
        }
    }

    private void createRocketList(ArrayList<Bitmap> rocketRight, Bitmap bmpRegular) {
        int count = 277;

        for (int i = 0; i < 3; i++) {
            rocketRight.add(Bitmap.createBitmap(bmpRegular, count, 282, 110, 94));
            count += 110;
        }
    }
}
