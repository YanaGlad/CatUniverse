package com.example.catuniverse.gameSupport.graphics;

//Набор различных анимаций для персонажа
public class ImageSet {
    private String key;
    private SpriteAnimation standRight, standLeft, moveRight, moveLeft, jumpRight, jumpLeft, rocketRight;

    public ImageSet(String key, SpriteAnimation standRight,
                    SpriteAnimation standLeft,
                    SpriteAnimation moveRight,
                    SpriteAnimation moveLeft,
                    SpriteAnimation jumpRight,
                    SpriteAnimation jumpLeft,
                    SpriteAnimation rocketRight)
    {
        this.key = key;
        this.standRight = standRight;
        this.standLeft = standLeft;
        this.moveRight = moveRight;
        this.moveLeft = moveLeft;
        this.jumpRight = jumpRight;
        this.jumpLeft = jumpLeft;
        this.rocketRight = rocketRight;
    }

    //Конструктор для котов в стратегии
    public ImageSet( SpriteAnimation standRight){
        this.standRight = standRight;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SpriteAnimation getStandRight() {
        return standRight;
    }

    public void setStandRight(SpriteAnimation standRight) {
        this.standRight = standRight;
    }

    public SpriteAnimation getStandLeft() {
        return standLeft;
    }

    public void setStandLeft(SpriteAnimation standLeft) {
        this.standLeft = standLeft;
    }

    public SpriteAnimation getMoveRight() {
        return moveRight;
    }

    public void setMoveRight(SpriteAnimation moveRight) {
        this.moveRight = moveRight;
    }

    public SpriteAnimation getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(SpriteAnimation moveLeft) {
        this.moveLeft = moveLeft;
    }

    public SpriteAnimation getJumpRight() {
        return jumpRight;
    }

    public void setJumpRight(SpriteAnimation jumpRight) {
        this.jumpRight = jumpRight;
    }

    public SpriteAnimation getJumpLeft() {
        return jumpLeft;
    }

    public void setJumpLeft(SpriteAnimation jumpLeft) {
        this.jumpLeft = jumpLeft;
    }

    public SpriteAnimation getRocketRight() {
        return rocketRight;
    }
}
