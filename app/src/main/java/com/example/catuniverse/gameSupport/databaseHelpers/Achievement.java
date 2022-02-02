package com.example.catuniverse.gameSupport.databaseHelpers;

public class Achievement {
    private int _id, unlocked;
    private String prize;
    private final String name;

    public Achievement(int _id, String name, int unlocked, String prize) {
        this._id = _id;
        this.name = name;
        this.unlocked = unlocked;
        this.prize = prize;
    }

    public int isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(int unlocked) {
        this.unlocked = unlocked;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPrize() {
        return prize;
    }

    public String getName() {
        return name;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}
