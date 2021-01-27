package com.example.catuniverse.gameSupport;

import com.example.catuniverse.gameSupport.graphics.GamePaint;

public interface Loopable {

    void run(GamePaint gamePaint);

    void repaint();

    void repaint(double speed, double jumSpeed);
}
