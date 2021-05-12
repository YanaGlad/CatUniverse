package com.example.catuniverse.gameSupport.databaseHelpers;

import androidx.annotation.NonNull;

import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.graphics.CatIcon;
import com.example.catuniverse.gameSupport.graphics.ImageSet;

import java.util.ArrayList;

import static com.example.catuniverse.gameViews.general.MenuView.checkIconKey;

public class Cat { // помогает получить кота из базы данных и преобразовать его в объект Cat
    private int id, speed, chosen, unlocked; //Значения полей таблицы cats - id, скоростьЮ выбран  и доступен ли кот
    private String name, key; //имя кота, ключ
    private ImageSet imageSet; //Набор спрайтовых анимаций персонажа
    private int room, price, health; // комната, цена и здоровье(для стратегии)
    private double power, delay; //сила и задержка ( для стратегии )

    private double[] characteristics = new double[3];

    public Cat(int id, String name, String key, double power, int speed, double delay, int chosen, int unlocked, int room, int price, int health) {
        this.imageSet = BasicGameSupport.checkKey(key);
        this.id = id;
        this.power = power;
        this.speed = speed;
        this.delay = delay;
        this.chosen = chosen;
        this.unlocked = unlocked;
        this.name = name;
        this.key = key;
        this.room = room;
        this.price = price;
        this.health = health;

        characteristics[0] = price;
        characteristics[1] = power;
        characteristics[2] = delay;
    }

    public double getCharacteristics(int id) {
        return characteristics[id - 1];
    }
    public void setCharacteristics(int data, int id) {
        characteristics[id - 1] = data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setChosen(int chosen) {
        this.chosen = chosen;
    }

    public void setUnlocked(int unlocked) {
        this.unlocked = unlocked;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setImageSet(ImageSet imageSet) {
        this.imageSet = imageSet;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @NonNull
    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", power=" + power +
                ", speed=" + speed +
                ", chosen=" + chosen +
                ", unlocked=" + unlocked +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", imageSet=" + imageSet.getKey() +
                ", room=" + room +
                '}';
    }



    public int getId() {
        return id;
    }

    public double getPower() {
        return power;
    }

    public int getSpeed() {
        return speed;
    }

    public int getChosen() {
        return chosen;
    }

    public int getUnlocked() {
        return unlocked;
    }

    public ImageSet getImageSet() {
        return imageSet;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public double getDelay() {
        return delay;
    }

    public int getRoom() {
        return room;
    }

    public int getPrice() {
        return price;
    }

    public int getHealth() {
        return health;
    }
}
