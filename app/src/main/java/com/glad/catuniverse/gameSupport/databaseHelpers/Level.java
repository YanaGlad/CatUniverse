package com.glad.catuniverse.gameSupport.databaseHelpers;

public class Level {  // помогает получить уровень из базы данных и преобразовать его в объект Level
    private final int number;
    private final int stars;
    private final int achievement;

    public Level(int number, int stars, int achievement) {
        this.number = number;
        this.stars = stars;
        this.achievement = achievement;
    }

    public int getNumber() {
        return number;
    }

    public int getStars() {
        return stars;
    }

    public int getAchievement() {
        return achievement;
    }
}
