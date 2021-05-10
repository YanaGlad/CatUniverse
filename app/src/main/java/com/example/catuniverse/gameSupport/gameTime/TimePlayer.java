package com.example.catuniverse.gameSupport.gameTime;

import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.EasyTimer;
import com.example.catuniverse.gameSupport.GameItem;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.databaseHelpers.Cat;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.example.catuniverse.gameSupport.gameTime.platforms.CollisionSupportElement;
import com.example.catuniverse.gameSupport.graphics.GamePaint;

import static com.example.catuniverse.gameSupport.BitmapLoader.walkRightGray;
import static com.example.catuniverse.gameSupport.Collisions.createBaseSizeRect;

//Игрок в уровнях на время
public class TimePlayer extends GameItem {

    private MainRunActivity mainRunActivity;
    private boolean rocketMode = false;
    private boolean oneTimeRocket = true;
    private Cat cat;
    private static final int maximumY = GameView.screenHeight - walkRightGray.get(0).getHeight();
    private static final int STANDARD_X = 50;
    private final int jumpHeight;
    private int maxX, ground;
    private boolean collisionDetectedRight, collisionDetectedLeft;
    private boolean jumAnim, jumpingLimit, walkingLimit, readyToJum;
    private boolean standing, jumping, movingRight, movingLeft;
    private boolean falling, oneTimeJump;
    private EasyTimer jumpingTimer, jumpingChecker;
    public static int start;
    private final int GROUND = GameView.screenHeight - walkRightGray.get(0).getHeight();
    private int fakeY;

    public TimePlayer(MainRunActivity mainRunActivity, Cat cat) {
        this.cat = cat;

        x = STANDARD_X;
        start = 50;
        speed = 5;
        readyToJum = false;
        jumpHeight = 140;
        jumpingSpeed = 6;
        jumAnim = false;
        jumpingTimer = new EasyTimer();
        jumpingChecker = new EasyTimer();
        jumpingLimit = false;
        walkingLimit = false;
        movingLeft = false;
        jumping = false;
        falling = false;
        movingRight = false;
        oneTimeJump = false;

        this.mainRunActivity = mainRunActivity;
        this.maxX = GameView.screenWidth;
        ground = GameView.screenHeight - walkRightGray.get(0).getHeight();
        collLength = 32;
        this.y = ground;
        fakeY = y;
    }


    @Override //Задает анимацию движения при ходьбе, прыжках и т.д.
    public void run(GamePaint gamePaint) {

        if (!rocketMode) { //Если не в режиме мини-игры "Ракета"
            if ((movingRight || movingLeft) && y == ground && !((fakeY < ground && ground == GROUND && !jumping && !readyToJum))) {
                oneTimeJump = false;
                if (movingRight && !jumAnim)
                    cat.getImageSet().getMoveRight().run(gamePaint, x, y, 3);

                if (movingLeft && !jumAnim)
                    cat.getImageSet().getMoveLeft().run(gamePaint, x, y, 3);

            } else {
                if (mainRunActivity.getTouchListener().getTouchX() < maxX && mainRunActivity.getTouchListener().getTouchX() > maxX / 2) {
                    if (jumping || readyToJum || (fakeY < ground && ground == GROUND) || y < ground) {
                        if (y < (ground) && !falling && !readyToJum)
                            gamePaint.setVisibleBitmap(cat.getImageSet().getJumpRight().getSprite1(), x, y);
                        else {
                            if (readyToJum)
                                gamePaint.setVisibleBitmap(cat.getImageSet().getJumpRight().getSprite2(), x, y);
                            else if (y < ground) {
                                gamePaint.setVisibleBitmap(cat.getImageSet().getJumpRight().getSprite3(), x, y);
                            } else {
                                gamePaint.setVisibleBitmap(cat.getImageSet().getJumpRight().getSprite3(), x, y);

                            }
                        }
                    } else {
                        System.out.println("Doing it. ");
                        cat.getImageSet().getStandRight().run(gamePaint, x, y, 2.5);
                        oneTimeJump = false;
                    }

                }
                if (mainRunActivity.getTouchListener().getTouchX() < maxX / 2) {
                    if (jumping || readyToJum || (fakeY < ground && ground == GROUND) || y < ground) {
                        if (y < (ground) && !falling && !readyToJum)
                            gamePaint.setVisibleBitmap(cat.getImageSet().getJumpLeft().getSprite3(), x, y);
                        else {
                            if (readyToJum)
                                gamePaint.setVisibleBitmap(cat.getImageSet().getJumpLeft().getSprite2(), x, y);
                            else if (y < ground) {
                                gamePaint.setVisibleBitmap(cat.getImageSet().getJumpLeft().getSprite1(), x, y);
                            } else {
                                gamePaint.setVisibleBitmap(cat.getImageSet().getJumpLeft().getSprite1(), x, y);

                            }
                        }
                    } else {
                        System.out.println("Doing it (left)");
                        oneTimeJump = false;
                        cat.getImageSet().getStandLeft().run(gamePaint, x, y, 2.5);
                    }
                }
            }

            if (movingRight && y < ground) {
                jumAnim = true;
                if (!falling && !readyToJum) {
                    gamePaint.setVisibleBitmap(cat.getImageSet().getJumpRight().getSprite1(), x, y);
                } else {
                    if (readyToJum)
                        gamePaint.setVisibleBitmap(cat.getImageSet().getJumpRight().getSprite2(), x, y);
                    else
                        gamePaint.setVisibleBitmap(cat.getImageSet().getJumpRight().getSprite3(), x, y);

                }
            }
            if (movingLeft && y < ground) {
                jumAnim = true;
                if (!falling && !readyToJum) {
                    gamePaint.setVisibleBitmap(cat.getImageSet().getJumpLeft().getSprite3(), x, y);
                } else {
                    if (readyToJum)
                        gamePaint.setVisibleBitmap(cat.getImageSet().getJumpLeft().getSprite2(), x, y);
                    else
                        gamePaint.setVisibleBitmap(cat.getImageSet().getJumpLeft().getSprite1(), x, y);
                }
            }

            if (y >= ground) jumAnim = false;
        } else  //В режиме минии-игры "Ракета" запускается анимация кота на ракете
            cat.getImageSet().getRocketRight().run(gamePaint, x, y, 3);

    }


    @Override // Изменяет состояние игрока.
    public void repaint() {
        if (!rocketMode) {
            this.speed = 5;
            if (mainRunActivity.getTouchListener().down(0, GameView.screenHeight, GameView.screenWidth, GameView.screenHeight)) { // При удержании , игрок идёт влево или вправо
                movingRight = BasicGameSupport.movingRight(mainRunActivity);
                movingLeft = BasicGameSupport.movingLeft(mainRunActivity);
            } else if (mainRunActivity.getTouchListener().up(0, maximumY, maxX, maximumY)) { //При отпускании игрок останавливается
                stopMoving();
                if (mainRunActivity.getTouchListener().isSwipe() && !oneTimeJump) { // При свайпе вверх, игрок прыгает
                    if (!jumping && !falling && !(y < ground)) jumping = true;
                    oneTimeJump = true;
                    jumpingChecker.startTimer();
                } else jumping = false;
            }
            if (movingLeft && x >= 50) if (!collisionDetectedRight) if (!walkingLimit) x -= speed;
            if (movingRight) if (!walkingLimit) if (!collisionDetectedLeft) x += speed;

            if (x > 300)
                walkingLimit = true; //После достижении лимита ходьбы, игрок перестается перемещаться. Начинает перемещаться фон относительно направления его движения.

            if (walkingLimit && x < 300) x = 300;

            jumpingLimit = y <= 350;

            if (jumping && !jumpingLimit) { //Прыгает, пока не достигнет лимит в высоте прыжка
                if (y > ground - jumpHeight) {
                    falling = false;
                    oneTimeJump = true;
                    y -= jumpingSpeed;
                    fakeY -= jumpingSpeed;
                }
            } else if (jumping) {
                fakeY -= jumpingSpeed;
            }

            if (jumping) //Прыгает и зависает в воздухе на 0.3 секунды
                if (jumpingChecker.timerDelay(0.3)) {
                    jumping = false;
                    jumpingTimer.startTimer();
                    readyToJum = true;
                }

            if (readyToJum) if (jumpingTimer.timerDelay(0.3)) readyToJum = false;

            if (y > ground) y = ground;



            if (y < ground && !jumping && !readyToJum) { //Падает, пока не коснется земли
                y += jumpingSpeed;
                fakeY += jumpingSpeed;
                falling = true;
            } else falling = false;

            if (y >= ground) jumAnim = false;

            if (!standing && !jumping) {
                standing = false;
                ground = maximumY;
            }

            if (falling || jumping || readyToJum) oneTimeJump = true;

            if (fakeY < ground && ground == GROUND && !( y < ground) && !jumping && !readyToJum) {
                fakeY += jumpingSpeed;
            }

            collisionRect = createBaseSizeRect(x, y);
        } else {
            rocketModeRepaint();
        }
    }


    //Режим мини-игры "Ракета"
    private void rocketModeRepaint() {
        if (oneTimeRocket) {
            rocketMode = true;
            x = 30;
            y = 620;
            movingLeft = false;
            movingRight = true;
            oneTimeRocket = false;
        }
        //В мини-игре "Ракета" игрок может передвигаться вверх и вниз при помощи свайпов
        if (mainRunActivity.getTouchListener().isSwipeUp() && y - 130 > 200) {
            y -= 120;
            mainRunActivity.getTouchListener().setSwipeUp(false);
        }
        if (mainRunActivity.getTouchListener().isSwipeDown() && y + 120 <= 600) {
            y += 120;
            mainRunActivity.getTouchListener().setSwipeDown(false);
        }
        collisionRect = createBaseSizeRect(x, y);
    }

    //Прекратить движение
    private void stopMoving() {
        movingLeft = false;
        movingRight = false;
        speed = 1;
    }


    public double getMainPlayerSpeed() {
        return speed;
    }

    public double getJumpSpeed() {
        return jumpingSpeed;
    }

    public boolean isRocketMode() {
        return rocketMode;
    }

    public void hitCallTall(CollisionSupportElement collisionSupportElement) {
        if (x > collisionSupportElement.getX() - 50 && x < collisionSupportElement.getX() + 40 && y < collisionSupportElement.getY() - 31) {
            ground = collisionSupportElement.getY() - 80;
            standing = true;
        }
    }

    public void obstacleCollision(TimePlatform obs) {
        if (x > obs.getX() - 30 && x < obs.getX() + 120 && y < obs.getY() - 31) {
            ground = obs.getY() - 80;
            standing = true;
        }
    }

    public boolean isJumping() {
        return jumping;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public boolean isFalling() {
        return falling;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public boolean isStanding() {
        return standing;
    }

    public void notStanding() {
        this.standing = false;
    }

    public boolean isJumpingLimit() {
        return jumpingLimit;
    }

    public boolean isCollisionDetectedRight() {
        return collisionDetectedRight;
    }

    public boolean isCollisionDetectedLeft() {
        return collisionDetectedLeft;
    }

    public void setCollisionDetectedRight(boolean collisionDetectedRight) {
        this.collisionDetectedRight = collisionDetectedRight;
    }

    public void setCollisionDetectedLeft(boolean collisionDetectedLeft) {
        this.collisionDetectedLeft = collisionDetectedLeft;
    }

    public Cat getCat() {
        return cat;
    }

    public static int getMaximumY() {
        return maximumY;
    }

    public void setRocketMode(boolean rocketMode) {
        this.rocketMode = rocketMode;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public double getSpeed() {
        return super.getSpeed();
    }

    @Override
    public void setSpeed(double speed) {
        super.setSpeed(speed);
    }
}
