package com.example.catuniverse.gameSupport.databaseHelpers;

public class Level {  // помогает получить уровень из базы данных и преобразовать его в объект Level
    private int number, stars;

    public Level(int number, int stars) {
        this.number = number;
        this.stars = stars;
    }

    public int getNumber() {
        return number;
    }

    public int getStars() {
        return stars;
    }

}
