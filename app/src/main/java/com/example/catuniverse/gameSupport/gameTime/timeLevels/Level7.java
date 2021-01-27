package com.example.catuniverse.gameSupport.gameTime.timeLevels;

import android.graphics.Bitmap;

import com.example.catuniverse.gameSupport.Media;
import com.example.catuniverse.gameSupport.gameTime.TimeLevel;

//В РАЗРАБОТКЕ
public class Level7 extends TimeLevel {
    protected Level7(int twoStars, int threeStars, double totalTime, Bitmap background, Bitmap ground, int lives, Media.Music music) {
        super(twoStars, threeStars, totalTime, background, ground, lives, music);
    }

    @Override
    public boolean isRequirementsCollected() {
        return false;
    }

    @Override
    public String getRewardId() {
        return null;
    }
}
