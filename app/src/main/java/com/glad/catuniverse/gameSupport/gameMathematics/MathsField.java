package com.glad.catuniverse.gameSupport.gameMathematics;

import static com.glad.catuniverse.gameSupport.BitmapLoader.menuMusic;
import static com.glad.catuniverse.gameSupport.BitmapLoader.movingMathsBackground;
import android.graphics.Color;
import androidx.annotation.Nullable;
import com.glad.catuniverse.R;
import com.glad.catuniverse.gameSupport.BitmapLoader;
import com.glad.catuniverse.gameSupport.CollisionDetectors;
import com.glad.catuniverse.gameSupport.Loopable;
import com.glad.catuniverse.gameSupport.MainRunActivity;
import com.glad.catuniverse.gameSupport.Media;
import com.glad.catuniverse.gameSupport.gameTime.MovingBackground;
import com.glad.catuniverse.gameSupport.graphics.GamePaint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

//Игровое поле математических уровней. Собирает все компоненты в одну сущность
public class MathsField implements Loopable {

    private final MathsPlayer mathsPlayer;
    private final MovingBackground movingBackground;
    private final MainRunActivity mainRunActivity;
    private final ArrayList<MathsAnswer> mathsAnswers;
    private final Theory theory;
    private final Random random;
    private int step;
    private int total;
    private int lives;
    private final int requestedLives;
    private boolean gameOver, won;
    private final int theoryStart;
    private final int theoryEnd;
    private final int answerStart;
    private final int answerEnd;
    private int a;
    private final int sizeAnswer;
    private final String key;
    private final String rewardId;
    private final Media.Music music;
    private final int achieveId;

    //Конструктор принимает MainRau, кол-во ответов, которые необходимо поймать,
    //кол-во жизней, базовый вопрос, промежуток вопросов, ключ темы уровня, размер шрифта в вопросе,
    //промежуток ответов
    public MathsField(MainRunActivity mainRunActivity, int total, int lives, int base,
                      int theoryStart, int theoryEnd, String key, int sizeAnswer,
                      int sizePlayer, int answerStart, int answerEnd, @Nullable String rewardId, Media.Music music, int achieveId) {
        theory = new Theory();
        a = base;
        this.mainRunActivity = mainRunActivity;
        this.total = total;
        this.lives = lives;
        this.requestedLives = lives;
        this.theoryStart = theoryStart;
        this.theoryEnd = theoryEnd;
        this.key = key;
        this.sizeAnswer = sizeAnswer;
        this.answerStart = answerStart;
        this.answerEnd = answerEnd;
        this.rewardId = rewardId;
        this.music = music;
        this.achieveId = achieveId;

       // exit = new BasicButton(mainRunActivity, 730, 30, BitmapLoader.exitButton, BitmapLoader.exitButtonClicked, false);
        movingBackground = new MovingBackground(movingMathsBackground, 1);
        mathsPlayer = new MathsPlayer(mainRunActivity, theory.getQuestions().get(a), sizePlayer);
        mathsAnswers = new ArrayList<>();

        random = new Random();

        step = 120;
        for (int i = theoryStart; i < theoryEnd; i++) {
            int a = random.nextInt(750);
            mathsAnswers.add(new MathsAnswer(a, step, key, sizeAnswer, answerStart, answerEnd));
            step += 120;
        }
        gameOver = false;
        won = false;
    }

    @Override
    public void run(GamePaint gamePaint) {
        repaint();
       if(menuMusic.isRunning()) menuMusic.stop();
        try {
            music.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Отображение фона, кнопки выход
        movingBackground.run(gamePaint);
       // exit.run(gamePaint);
        //Отображение игрока и ответов
        mathsPlayer.run(gamePaint);
        for (MathsAnswer answer : mathsAnswers) answer.run(gamePaint);

        //Отображение оставщийхся жизней и кол-ва ответов, которые необходимо поймать
        gamePaint.write(mainRunActivity.getString(R.string.left) + " " + total, 20, 80, Color.WHITE, 60);
        gamePaint.write(mainRunActivity.getString(R.string.lives) + " ", 400, 55, Color.WHITE, 30);
        int x = 500;
        for (int i = 0; i < lives; i++) {
            gamePaint.setVisibleBitmap(BitmapLoader.heart, x, 30);
            x += 20;
        }
    }

    @Override
    public void repaint() {
        //Проверка победы и поражения
        winOrLose(total, lives);

        for (int i = 0; i < mathsAnswers.size(); i++)
            //Проверка столкновения игрока и ответа
            if (CollisionDetectors.checkTwoItemCollision(mathsPlayer, mathsAnswers.get(i))) {
                //Если ответ верныей, выставить новый вопрос и уменьшить кол-во оставщихся ответов до победы
                //Иначе - уменьшить жинзни
                if (theory.checkMathAnswer(mathsPlayer.getExpression(), mathsAnswers.get(i).getExpression())) {
                    setQuestion();
                    total--;
                } else lives--;

                mathsAnswers.remove(i);
            }

        //Если ответ вылетел за пределы поля - удалить
        for (int i = 0; i < mathsAnswers.size(); i++)
            if (mathsAnswers.get(i).getY() > 650)
                mathsAnswers.remove(i);

        //Если количество ответов на поле меньше 5, добавить 100 новых
        if (mathsAnswers.size() < 5) {
            step = 120;
            for (int j = 0; j < 100; j++) {
                int a = random.nextInt(750);
                mathsAnswers.add(new MathsAnswer(a, step, key, sizeAnswer, answerStart, answerEnd));
                step += 120;
            }
        }
    }

    //Установить новый вопрос из заданного ( в конструкторе ) промежутка
    private void setQuestion() {
        int b;
        b = (int) (Math.random() * (theoryEnd - theoryStart) + theoryStart);
        while (b == a) //До тех пор, пока не выпадет новый вопрос
            b = (int) (Math.random() * (theoryEnd - theoryStart) + theoryStart);
        a = b;

        mathsPlayer.setExpression(theory.getQuestions().get(a));
    }

    //Проверка победы или поражения
    private void winOrLose(int total, int lives) {
        if (lives <= 0) gameOver = true;
        if (total <= 0) won = true;
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

    @Override
    public void repaint(double speed, double jumSpeed) { }

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
