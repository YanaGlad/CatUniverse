package com.example.catuniverse.gameSupport.gameStrategy;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.CollisionDetectors;
import com.example.catuniverse.gameSupport.Loopable;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.Media;
import com.example.catuniverse.gameSupport.graphics.GamePaint;
import com.example.catuniverse.gameViews.general.ChooseView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static com.example.catuniverse.MainActivity.listOfCats;

//Игровое поле стратегических уровней. Собирает вс
// е компоненты в одну сущность
public class StrategyField implements Loopable {
    private final Media.Music music;
    private final ArrayList<StrategyEnemy> strategyEnemies;
    private final ArrayList<BasicButton> playerButtons;
    private final ArrayList<StrategyPlayer> players;
    private boolean showPrice = false;
    private int money;
    private final double lowerDelay;
    private final double powerIncrease;
    private int leftToDefeat;
    private int lives;
    private final int requestedLives;
    private boolean gameOver, won;
    private final MainRunActivity mainRunActivity;
    private final StrategyCatChoice strategyCatChoice;
    private boolean oneTime;
    private final int[] enemyIds;
    private final int speed;
    private final int enemyReward;
    private final int enemiesCount;
    private final int achieveId;
    private final String rewardId;

    //Конструктор принимает MainRun, кол-во врагов, кол-во денег, на сколько можно увелить урон и уменьшить задержку,
    //сколько врагов необзоходимо победить, кол-во жизней, награда(деньги за побежденного врага, id врагов, которые могут встретиться в уровне, их скорость
    public StrategyField(MainRunActivity mainRunActivity, int enemiesCount,
                         int money, double lowerDelay, double powerIncrease,
                         int leftToDefeat, int lives, int enemyReward, int[] enemyIds,
                         int speed, @Nullable String rewardId, Media.Music music, int achieveId) {
        this.mainRunActivity = mainRunActivity;
        this.money = money;
        this.lowerDelay = lowerDelay;
        this.leftToDefeat = leftToDefeat;
        this.lives = lives;
        this.requestedLives = lives;
        this.powerIncrease = powerIncrease;
        this.enemyReward = enemyReward;
        this.enemyIds = enemyIds;
        this.speed = speed;
        this.enemiesCount = enemiesCount;
        this.rewardId = rewardId;
        this.music = music;
        this.achieveId = achieveId;

        strategyCatChoice = new StrategyCatChoice(mainRunActivity);
        oneTime = false;
        gameOver = false;
        won = false;

        strategyEnemies = new ArrayList<>();
        players = new ArrayList<>();
        playerButtons = new ArrayList<>();

        int x = 0, y = 0;

        //Создание поля 3*5, где можно размещать персонажей
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                players.add(new StrategyPlayer(-1, mainRunActivity, null, -1, -1, x, y, 5, 7, BitmapLoader.bullet));
                x += 100;
            }
            x = 0;
            y += 100;
        }
    }

    @Override
    public void run(GamePaint gamePaint) {
        strategyCatChoice.run(gamePaint); //Выбрать персонажей для игры
        if (!strategyCatChoice.isRunning()) { //Если персонажи выбраны, можно начать игру
            if (!oneTime) { // Выполнить один раз
                randomEnemies(strategyEnemies, enemiesCount, enemyReward, speed, enemyIds); //сгенерировать врагов

                int xx = 200;
                for (int i = 0; i < strategyCatChoice.getChosenStrategyCats().size(); i++) { //Кнопки с выбранными персонажами. Нажав на них, необходимо разместить выбранного персонажа на поле
                    playerButtons.add(new BasicButton(mainRunActivity, xx, 530,
                            strategyCatChoice.getChosenStrategyCats().get(i).getImageSet().getStandRight().getSprite1(),
                            strategyCatChoice.getChosenStrategyCats().get(i).getImageSet().getStandRight().getSprite1(), false));
                    xx += 100;
                }
                BitmapLoader.menuMusic.stop();
                try {
                    music.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                oneTime = true;
            }
            repaint();

            //Задать фон, начертить линии, отделяющие клетки
            BasicGameSupport.drawGrid(gamePaint, BitmapLoader.strategyBackground, Color.BLACK);

            //Отобразить остаток денег, жизней, кол-ва врагов, необходимых для победы
            gamePaint.setVisibleBitmap(BitmapLoader.pricePanel, -20, 500);
            gamePaint.write(mainRunActivity.getString(R.string.money), 30, 550, Color.BLACK, 40);
            gamePaint.write("" + money, 50, 595, Color.BLACK, 40);

            gamePaint.write(mainRunActivity.getString(R.string.lives), 540, 35, Color.BLACK, 30);
            gamePaint.write(mainRunActivity.getString(R.string.left_to_defeat) + " " + leftToDefeat, 300, 35, Color.BLACK, 30);

            int x = 620;
            for (int i = 0; i < lives; i++) {
                gamePaint.setVisibleBitmap(BitmapLoader.heart, x, 10);
                x += 20;
            }

            //Запустить игроков и врагов
            for (StrategyPlayer strategyPlayer : players) strategyPlayer.run(gamePaint);
            for (StrategyEnemy enemy : strategyEnemies) enemy.run(gamePaint);

            if (!showPrice) { //Если ни один игрок не нажат для просмотра характеристик, запустить кнопки для покупки игроков, отобразить цену каждого
                for (BasicButton character : playerButtons) character.run(gamePaint);

                x = 205;
                for (int i = 0; i < strategyCatChoice.getChosenStrategyCats().size(); i++) {
                    gamePaint.write(mainRunActivity.getString(R.string.price) + " " + strategyCatChoice.getChosenStrategyCats().get(i).getPrice(), x, 540, Color.BLACK, 25);
                    x += 100;
                }
            }

//Цикл по купленным игрокам
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getImage() != null) //Если изображение игрока не null т.е. если в данной клетке размещен игрок
                    if (players.get(i).isClicked()) { //Если игрок нажат

                        //Запустить меню с отображением характеристик игрока
                        players.get(i).getChangeDelay().run(gamePaint); //Кнопка для возможности уменьшения задержки выстрелов
                        players.get(i).getChangePower().run(gamePaint);//Кнопка для возможности увеличения урона

                        if (players.get(i).getChangeDelay().isClicked()) { //Уменьшить задержку, если она не минимальна
                            if (players.get(i).getDelay() > 0.5 && money >= players.get(i).getPriceDelay()) {
                                players.get(i).setDelay(players.get(i).getDelay() - lowerDelay);
                                money -= players.get(i).getPriceDelay();
                            }
                            players.get(i).getChangeDelay().notClicked();
                        }

                        if (players.get(i).getChangePower().isClicked()) { // Увеличить урон,если он не максимален
                            if (players.get(i).getPower() < strategyCatChoice.getChosenStrategyCats().get(strategyCatChoice.findCat(players.get(i).getId())).getPower() * 2 && money >= players.get(i).getPricePower()) {
                                players.get(i).setPower(players.get(i).getPower() + powerIncrease);
                                money -= players.get(i).getPricePower();
                            }
                            players.get(i).getChangePower().notClicked();
                        }
                    }
            }
        }
    }

    @Override
    public void repaint() {

        winOrLose(leftToDefeat, lives); //Проверить победу и поражение

        for (int i = 0; i < players.size(); i++) //цикл по игрокам
            for (int j = 0; j < players.get(i).getStrategyBullets().size(); j++) //цикл по пулям данного игрока
                for (int k = 0; k < strategyEnemies.size(); k++) //Проверка столкновения пули и врага.
                    if (strategyEnemies.get(k).getX() < 750 && players.get(i).getStrategyBullets().get(j).getX() < 750 &&
                            CollisionDetectors.checkTwoItemCollision(players.get(i).getStrategyBullets().get(j), strategyEnemies.get(k))) {
                        strategyEnemies.get(k).setHealth(strategyEnemies.get(k).getHealth(), players.get(i).getPower());
                        players.get(i).getStrategyBullets().get(j).setX(900);
                    }


        for (int i = 0; i < strategyEnemies.size(); i++) {
            if (strategyEnemies.get(i).isDefeated()) { //Если враг повержен, получить деньги в награду и уменьшить число врагов оставшихся до победы
                money += strategyEnemies.get(i).getReward();

                leftToDefeat--;
                strategyEnemies.remove(i);
            }

            if (strategyEnemies.get(i).getX() < -100) { //Если враг прошел через оборону, уменьшить число жизней
                lives--;
                strategyEnemies.remove(i);
            }
        }

        boolean darker = false;
        for (BasicButton btn : playerButtons)
            if (btn.isClicked()) {
                darker = true;
                break;
            }

        if (darker) {
            for (int i = 0; i < players.size(); i++)
                if (players.get(i).getImage() == null)
                    players.get(i).setAvailableForChoice(true);
        } else {
            for (int i = 0; i < players.size(); i++)
                players.get(i).setAvailableForChoice(false);
        }

        //Цикл по кнопкам для покупки игрока
        for (int i = 0; i < playerButtons.size(); i++)
            if (playerButtons.get(i).isClicked()) //Если выбран персонаж для покупки
                if (money >= strategyCatChoice.getChosenStrategyCats().get(i).getPrice()) { //Если хватает денег для его покупки
                    for (int j = 0; j < players.size(); j++)
                        if (players.get(j).isClicked()) { //Если выбрана клетка для размещения персонажа, заменить все null и -1 значения на его характеристики
                            if (players.get(j).getImage() == null) {
                                players.get(j).setId(strategyCatChoice.getChosenStrategyCats().get(i).getId());
                                players.get(j).setImage(players.get(j).getImageSets().get(strategyCatChoice.getChosenStrategyCats().get(i).getId() - 1));
                                players.get(j).setPower(strategyCatChoice.getChosenStrategyCats().get(i).getPower());
                                players.get(j).setDelay(strategyCatChoice.getChosenStrategyCats().get(i).getDelay());
                                money -= strategyCatChoice.getChosenStrategyCats().get(i).getPrice();
                                players.get(j).notClicked();
                                playerButtons.get(i).notClicked();
                            } else {
                                players.get(j).notClicked();
                            }
                        }
                } else
                    playerButtons.get(i).notClicked();//Если денег не хватает, кнопка не может быть нажата

        int count = 0;
        for (int i = 0; i < players.size(); i++)
            if (players.get(i).isClicked() && players.get(i).getImage() != null) count++;
        showPrice = count > 0;

        if (count > 1)
            for (int i = 0; i < players.size(); i++)
                players.get(i).notClicked();

        boolean checkClicks = false;

        for (int j = 0; j < players.size(); j++) {
            if (players.get(j).isClicked() && players.get(j).getImage() == null) {
                for (int i = 0; i < playerButtons.size(); i++) {
                    if (playerButtons.get(i).isClicked()) checkClicks = true;
                }
                if (!checkClicks) players.get(j).notClicked();
                checkClicks = false;
            }
        }
    }

    @Override
    public void repaint(double speed, double jumSpeed) {
    }

    //Генерация рандомных врагов. Принимает ArrayList, в который нужно добавить врага,
    // кол-во добавляемых врагов, награду за врагов, скорость врагов, массив возможных id врагов
    private void randomEnemies(ArrayList<StrategyEnemy> strategyEnemies, int size, int reward, int speed, int[] catId) {
        int enemyX = 850;
        int enemyY = 300;

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int result = random.nextInt(5);
            //System.out.println("Random line = " + result);
            switch (result) { // Рандомное расположение врага
                case 0:
                    enemyY = 100;
                    break;
                case 1:
                    enemyY = 200;
                    break;
                case 2:
                    enemyY = 300;
                    break;
                case 3:
                    enemyY = 400;
                    break;
                case 4:
                    enemyY = 0;
                    break;
            }

            int a = random.nextInt(catId.length);

            strategyEnemies.add(new StrategyEnemy(listOfCats.get(catId[a] - 1).getImageSet().getMoveLeft(),
                    enemyX, enemyY, listOfCats.get(catId[a] - 1).getHealth(), reward, speed));
            enemyX += 300;
        }
    }

    //Проверка игры на победу и поражение
    private void winOrLose(int leftToDefeat, int lives) {
        if (lives <= 0) gameOver = true;
        if (leftToDefeat <= 0) won = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWon() {
        return won;
    }

    public int getLives() {
        return lives;
    }

    public int getRequestedLives() {
        return requestedLives;
    }

    public String getRewardId() {
        return rewardId;
    }

    public Media.Music getMusic() {
        return music;
    }

    public int getAchieveId() {
        return achieveId;
    }
}
