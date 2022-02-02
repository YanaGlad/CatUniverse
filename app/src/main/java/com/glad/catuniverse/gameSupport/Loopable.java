package com.glad.catuniverse.gameSupport;

import com.glad.catuniverse.gameSupport.graphics.GamePaint;

public interface Loopable {

    void run(GamePaint gamePaint);

    void repaint();

    void repaint(double speed, double jumSpeed);
}
