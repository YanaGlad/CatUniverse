package com.example.catuniverse.gameSupport;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.catuniverse.MainActivity;
import com.example.catuniverse.gameSupport.aboutGame.AboutAchievementActivity;
import com.example.catuniverse.gameSupport.aboutGame.GameDescriptionActivity;
import com.example.catuniverse.gameSupport.graphics.GamePaint;


public class MainRunActivity extends AppCompatActivity {

    private GamePaint gamePaint;
    private TouchListener touchListener;
    private GameView gameView;
    private Media media;
    private GameLoop gameLoop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        float configFPS = 90;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        Bitmap bmp = Bitmap.createBitmap(800, 600, Bitmap.Config.ARGB_8888);

        float w = (float) 800 / point.x;
        float h = (float) 600 / point.y;

        media = new Media(this);

        gameLoop = new GameLoop(this, bmp);
        gamePaint = new GamePaint(getAssets(), bmp);

        if (w == h) touchListener = new TouchListener(gameLoop, w);
        else touchListener = new TouchListener(gameLoop, w, h);

        gameView = getNewView();

        setContentView(gameLoop);
        gameLoop.startGame();
    }

    public GamePaint getGamePaint() {
        return gamePaint;
    }

    public TouchListener getTouchListener() {
        return touchListener;
    }

    public void setView(GameView gameView) {
        if (gameView == null) {
            throw new IllegalArgumentException("Something went wrong, gameView is null :(");
        }
        this.gameView = gameView;
    }

    public void aboutGame() {
        Intent intent = new Intent(this, GameDescriptionActivity.class);
        startActivity(intent);
        gameLoop.stopGame();
    }

    private String[] descriptions =  {
        "This is cool achievement", "This is amazing achievement"
    };

    public void aboutAchievement(int id, Bitmap bitmap) {
        Intent intent = new Intent(this, AboutAchievementActivity.class);
        intent.putExtra("name", MainActivity.listOfAchievements.get(id).getName());
        intent.putExtra("description", descriptions[id]);
        intent.putExtra("bitmap", bitmap);

        startActivity(intent);
        gameLoop.stopGame();
    }

    public GameView getCurrentView() {
        return gameView;
    }

    public GameView getNewView() {
        return gameView;
    }

    public Media getMedia() {
        return media;
    }
}
