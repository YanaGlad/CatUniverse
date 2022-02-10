package com.glad.catuniverse.gameSupport.support;


import android.view.MotionEvent;
import android.view.View;

public class TouchListener implements View.OnTouchListener {

    private float touchX, touchY;
    private boolean swipe, swipeUp, swipeDown;
    private float downX, downY;
    private int swipeDistance;

    TouchListener(View view) {
        view.setOnTouchListener(this);
        swipeDistance = 50;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        view.performClick();

        synchronized (this) {
            switch (event.getAction()) { //Получим действие, которое произвел пользователь над экраном
                case MotionEvent.ACTION_DOWN: //Если удержание пальца на экране
                    touchX = event.getX();
                    touchY = event.getY();

                    swipe = false;

                    downX = event.getX();
                    downY = event.getY();
                    break;

                case MotionEvent.ACTION_UP://Если отрыв пальца от экрана
                    touchX = event.getX();
                    touchY = event.getY();

                    swipe = false;

                    float upX = event.getX();
                    float upY = event.getY();

                    float deltaX = downX - upX; //рассчёт свайпа по горизонтали
                    float deltaYUp = downY - upY; // рассчёт свайпа по вертикали
                    float deltaYDown = upY - downY;

                    if (Math.abs(deltaYUp) > swipeDistance) { //если свайп достигает заданное значение
                        if (deltaYUp > 0) swipe = true;
                    }

                    if (deltaYUp > swipeDistance) {
                        swipeUp = true;
                        swipeDown = false;
                    }
                    if (deltaYDown > swipeDistance) {
                        swipeDown = true;
                        swipeUp = false;
                    }

                    break;
            }
        }
        return true;
    }
}
