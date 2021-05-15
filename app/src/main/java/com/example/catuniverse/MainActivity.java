package com.example.catuniverse;//package com.example.gravity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.databaseHelpers.Achievement;
import com.example.catuniverse.gameSupport.databaseHelpers.Cat;
import com.example.catuniverse.gameSupport.databaseHelpers.CatPet;
import com.example.catuniverse.gameSupport.databaseHelpers.Level;
import com.example.catuniverse.gameViews.general.MenuView;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends MainRunActivity {
    public static SQLiteDatabase timeDB, catsDB, strategyDB, mathsDB, achievementDB;
    public static Cursor cursor, catCursor, strategyCursor, mathsCursor, achievementCursor;
    public static String DB_PATH_TIME, DB_PATH_CATS, DB_PATH_STRATEGY, DB_PATH_MATHS, DB_PATH_ACHIVE;
    public static ArrayList<Level> timeLevels, strategyLevels, mathsLevels;
    public static ArrayList<Cat> listOfCats;
    public static ArrayList<CatPet> listOfPets;
    public static ArrayList<Achievement> listOfAchievements;

    Context context;

    public MenuView getNewView() {
        context = this;
        try {
            BitmapLoader bitmapLoader = new BitmapLoader(this, this.getGamePaint());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MenuView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        timeLevels = new ArrayList<>();
        DB_PATH_TIME = this.getFilesDir().getPath() + "time.db";
        timeDB = getBaseContext().openOrCreateDatabase("time.db", MODE_PRIVATE, null);
        //     timeDB.execSQL("DROP TABLE IF EXISTS time");
        timeDB.execSQL("CREATE TABLE IF NOT EXISTS time (_id INTEGER, stars INTEGER)");
        timeDB.execSQL("INSERT into time (_id, stars) VALUES (1,0)");
        timeDB.execSQL("INSERT into time (_id, stars) VALUES (2,0)");
        timeDB.execSQL("INSERT into time (_id, stars) VALUES (3,0)");
        timeDB.execSQL("INSERT into time (_id, stars) VALUES (4,0)");
        timeDB.execSQL("INSERT into time (_id, stars) VALUES (5,0)");
        timeDB.execSQL("INSERT into time (_id, stars) VALUES (6,0)");
        timeDB.execSQL("INSERT into time (_id, stars) VALUES (7,0)");
        timeDB.execSQL("INSERT into time (_id, stars) VALUES (8,0)");
        timeDB.execSQL("INSERT into time (_id, stars) VALUES (9,0)");

        for (int i = 0; i < BasicGameSupport.levelsCount; i++) {
            cursor = timeDB.rawQuery("SELECT * from time WHERE _id = " + (i + 1), null);
            if (cursor != null && cursor.moveToFirst()) {
                timeLevels.add(new Level(cursor.getInt(0), cursor.getInt(1)));
            }
        }

        DB_PATH_CATS = this.getFilesDir().getPath() + "cats.db";
        catsDB = getBaseContext().openOrCreateDatabase("cats.db", MODE_PRIVATE, null);
        //  catsDB.execSQL("DROP TABLE IF EXISTS cats");
        catsDB.execSQL("CREATE TABLE IF NOT EXISTS cats (_id INTEGER, name TEXT, imageSet TEXT, power INTEGER, speed INTEGER, delay INTEGER,  chosen INTEGER, unlocked INTEGER, room INTEGER, price INTEGER, health INTEGER)"); ///PRICE
        catsDB.execSQL("INSERT into cats (_id, name, imageSet, power, speed , delay , chosen, unlocked, room, price, health) VALUES (1, 'Gray', 'gray', 25, 5, 3, 1, 1, 1, 25, 30)");
        catsDB.execSQL("INSERT into cats (_id, name, imageSet, power, speed , delay , chosen, unlocked, room, price, health) VALUES (2, 'Oragne', 'orange', 35 , 5, 3, 0, 0, -1, 30, 35)");
        catsDB.execSQL("INSERT into cats (_id, name, imageSet, power, speed , delay , chosen, unlocked, room, price, health) VALUES (3, 'Alien', 'greenAlien', 50 , 5, 4, 0, 0, -1, 50, 70)");
        catsDB.execSQL("INSERT into cats (_id, name, imageSet, power, speed , delay , chosen, unlocked, room, price, health) VALUES (4, 'Shadow', 'shadow', 40 , 5, 3, 0, 0, -1, 45, 40)");
        catsDB.execSQL("INSERT into cats (_id, name, imageSet, power, speed , delay , chosen, unlocked, room, price, health) VALUES (5, 'MainCoon', 'mainCoon', 60 , 5, 2, 0, 0, -1, 60, 100)");
        catsDB.execSQL("INSERT into cats (_id, name, imageSet, power, speed , delay , chosen, unlocked, room, price, health) VALUES (6, 'Bobtail', 'bobtail', 70 , 5, 4, 0, 0, -1, 60, 50)");


        listOfCats = new ArrayList<>();
        listOfPets = new ArrayList<>();
        listOfAchievements = new ArrayList<>();

        for (int i = 0; i < BasicGameSupport.catsCount; i++) {
            catCursor = catsDB.rawQuery("SELECT * from cats WHERE _id = " + (i + 1), null);
            if (catCursor != null && catCursor.moveToFirst()) {
                listOfCats.add(new Cat(catCursor.getInt(0), catCursor.getString(1), catCursor.getString(2),
                        catCursor.getInt(3), catCursor.getInt(4), catCursor.getInt(5), catCursor.getInt(6),
                        catCursor.getInt(7), catCursor.getInt(8), catCursor.getInt(9), catCursor.getInt(10)));

                listOfPets.add(new CatPet(listOfCats.get(i), catCursor.getInt(0), catCursor.getInt(8)));
            }
        }

        strategyLevels = new ArrayList<>();

        DB_PATH_STRATEGY = this.getFilesDir().getPath() + "strategy.db";
        strategyDB = getBaseContext().openOrCreateDatabase("strategy.db", MODE_PRIVATE, null);
        // strategyDB.execSQL("DROP TABLE IF EXISTS strategy");
        strategyDB.execSQL("CREATE TABLE IF NOT EXISTS strategy (_id INTEGER, stars INTEGER)");
        strategyDB.execSQL("INSERT into strategy (_id, stars) VALUES (1,0)");
        strategyDB.execSQL("INSERT into strategy (_id, stars) VALUES (2,0)");
        strategyDB.execSQL("INSERT into strategy (_id, stars) VALUES (3,0)");
        strategyDB.execSQL("INSERT into strategy (_id, stars) VALUES (4,0)");
        strategyDB.execSQL("INSERT into strategy (_id, stars) VALUES (5,0)");
        strategyDB.execSQL("INSERT into strategy (_id, stars) VALUES (6,0)");
        strategyDB.execSQL("INSERT into strategy (_id, stars) VALUES (7,0)");
        strategyDB.execSQL("INSERT into strategy (_id, stars) VALUES (8,0)");
        strategyDB.execSQL("INSERT into strategy (_id, stars) VALUES (9,0)");


        for (int i = 0; i < BasicGameSupport.levelsCount; i++) {
            strategyCursor = strategyDB.rawQuery("SELECT * from strategy WHERE _id = " + (i + 1), null);
            if (strategyCursor != null && strategyCursor.moveToFirst()) {
                strategyLevels.add(new Level(strategyCursor.getInt(0), strategyCursor.getInt(1)));
            }
        }

        mathsLevels = new ArrayList<>();

        DB_PATH_MATHS = this.getFilesDir().getPath() + "maths.db";
        mathsDB = getBaseContext().openOrCreateDatabase("maths.db", MODE_PRIVATE, null);
        // mathsDB.execSQL("DROP TABLE IF EXISTS maths");
        mathsDB.execSQL("CREATE TABLE IF NOT EXISTS maths (_id INTEGER, stars INTEGER)");
        mathsDB.execSQL("INSERT into maths (_id, stars) VALUES (1,0)");
        mathsDB.execSQL("INSERT into maths (_id, stars) VALUES (2,0)");
        mathsDB.execSQL("INSERT into maths (_id, stars) VALUES (3,0)");
        mathsDB.execSQL("INSERT into maths (_id, stars) VALUES (4,0)");
        mathsDB.execSQL("INSERT into maths (_id, stars) VALUES (5,0)");
        mathsDB.execSQL("INSERT into maths (_id, stars) VALUES (6,0)");
        mathsDB.execSQL("INSERT into maths (_id, stars) VALUES (7,0)");
        mathsDB.execSQL("INSERT into maths (_id, stars) VALUES (8,0)");
        mathsDB.execSQL("INSERT into maths (_id, stars) VALUES (9,0)");
        for (int i = 0; i < BasicGameSupport.levelsCount; i++) {
            mathsCursor = mathsDB.rawQuery("SELECT * from maths WHERE _id = " + (i + 1), null);
            if (mathsCursor != null && mathsCursor.moveToFirst()) {
                mathsLevels.add(new Level(mathsCursor.getInt(0), mathsCursor.getInt(1)));
            }
        }


        mathsLevels = new ArrayList<>();

        DB_PATH_ACHIVE = this.getFilesDir().getPath() + "achievement.db";
        achievementDB = getBaseContext().openOrCreateDatabase("achievement.db", MODE_PRIVATE, null);
        // mathsDB.execSQL("DROP TABLE IF EXISTS maths");
        achievementDB.execSQL("CREATE TABLE IF NOT EXISTS achievement (_id INTEGER, unlocked INTEGER, prize TEXT)");
        achievementDB.execSQL("INSERT into achievement (_id, unlocked,prize) VALUES (1 ,0, 'goldPart')");
        achievementDB.execSQL("INSERT into achievement (_id, unlocked,prize) VALUES (2 ,0, 'goldPart')");
        achievementDB.execSQL("INSERT into achievement (_id, unlocked,prize) VALUES (3 ,0, 'goldPart')");

        for (int i = 0; i < BasicGameSupport.achievementCount; i++) {
            achievementCursor = achievementDB.rawQuery("SELECT * from achievement WHERE _id = " + (i + 1), null);
            if (achievementCursor != null && achievementCursor.moveToFirst()) {
                listOfAchievements.add(new Achievement(achievementCursor.getInt(0), achievementCursor.getInt(1), achievementCursor.getString(2)));
            }
        }
    }
}


