package com.example.catuniverse.gameSupport;

import com.example.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.example.catuniverse.gameSupport.gameTime.platforms.CollisionSupportElement;
import com.example.catuniverse.gameSupport.gameTime.platforms.TimeTallPlatform;

import java.util.ArrayList;

import static com.example.catuniverse.gameSupport.BitmapLoader.tallWallSkeleton;
import static com.example.catuniverse.gameSupport.Collisions.checkGameItemCollision;
import static com.example.catuniverse.gameSupport.Collisions.collisionDetectEasy;
import static com.example.catuniverse.gameSupport.graphics.PlayerManager.timePlayer;

public class CollisionDetectors {

    //Коллизия 2х GameItem'ов
    public static boolean checkTwoItemCollision(GameItem gameItem, GameItem gameItem2) {
        return checkGameItemCollision(gameItem, gameItem2);
    }

    //Коллизия какого-либо GameItem и игрока
    public static boolean checkPlayerItemCollision(GameItem gameItem) {
        return checkGameItemCollision(timePlayer, gameItem);
    }

    //Коллизия высокой платформы. Для неё сделан омобенный метод т.к. необходимо обрабатывать соприкосновение и сбоку, и сверзху
    public static void checkObsCollision(TimePlatform obs) {
        if (checkGameItemCollision(timePlayer, obs))
            timePlayer.obstacleCollision(obs);
        else
            timePlayer.notStanding();
    }

    //Коллизия для CollisionSupportElement ( часть высокой платформы)
    public static void checkTallCollision(CollisionSupportElement collisionSupportElement) {
        if (checkGameItemCollision(timePlayer, collisionSupportElement))
            timePlayer.hitCallTall(collisionSupportElement);
        else
            timePlayer.notStanding();
    }

    //Коллизия высокой платформы. (Возможность работы коллизии для сразу нескольких платформ.  Это необходимо т.к. для них создан особенный способ обработки коллизий
    public static void tallPlatformCollision(  ArrayList<TimeTallPlatform> gameItems) {
        int maxX = 190, minX = 315;
        ///Попробовать рекурсией
        int count = 0;
        for (TimeTallPlatform tb : gameItems) {
            if (collisionDetectEasy(timePlayer, tb, tallWallSkeleton, maxX, minX, true)) {
                count++;
                if (tb.getX() <= maxX) {
                    timePlayer.setCollisionDetectedRight(true);
                    timePlayer.setCollisionDetectedLeft(false);
                }
                if (tb.getX() >= minX) {
                    timePlayer.setCollisionDetectedLeft(true);
                    timePlayer.setCollisionDetectedRight(false);
                }
            }

            if (count == 0) {
                timePlayer.setCollisionDetectedRight(false);
                timePlayer.setCollisionDetectedLeft(false);
            }
        }
    }
}
