package com.glad.catuniverse.gameSupport;

import com.glad.catuniverse.gameSupport.gameTime.platforms.TimePlatform;
import com.glad.catuniverse.gameSupport.gameTime.platforms.CollisionSupportElement;
import com.glad.catuniverse.gameSupport.gameTime.platforms.TimeTallPlatform;
import com.glad.catuniverse.gameSupport.graphics.PlayerManager;

import java.util.ArrayList;

import static com.glad.catuniverse.gameSupport.Collisions.checkGameItemCollision;
import static com.glad.catuniverse.gameSupport.Collisions.collisionDetectEasy;

public class CollisionDetectors {

    //Коллизия 2х GameItem'ов
    public static boolean checkTwoItemCollision(GameItem gameItem, GameItem gameItem2) {
        return checkGameItemCollision(gameItem, gameItem2);
    }

    //Коллизия какого-либо GameItem и игрока
    public static boolean checkPlayerItemCollision(GameItem gameItem) {
        return checkGameItemCollision(PlayerManager.timePlayer, gameItem);
    }

    //Коллизия высокой платформы. Для неё сделан омобенный метод т.к. необходимо обрабатывать соприкосновение и сбоку, и сверзху
    public static void checkObsCollision(TimePlatform obs) {
        if (checkGameItemCollision(PlayerManager.timePlayer, obs))
            PlayerManager.timePlayer.obstacleCollision(obs);
        else
            PlayerManager.timePlayer.notStanding();
    }

    //Коллизия для CollisionSupportElement ( часть высокой платформы)
    public static void checkTallCollision(CollisionSupportElement collisionSupportElement) {
        if (checkGameItemCollision(PlayerManager.timePlayer, collisionSupportElement))
            PlayerManager.timePlayer.hitCallTall(collisionSupportElement);
        else
            PlayerManager.timePlayer.notStanding();
    }

    //Коллизия высокой платформы. (Возможность работы коллизии для сразу нескольких платформ.  Это необходимо т.к. для них создан особенный способ обработки коллизий
    public static void tallPlatformCollision(  ArrayList<TimeTallPlatform> gameItems) {
        int maxX = 190, minX = 315;
        ///Попробовать рекурсией
        int count = 0;
        for (TimeTallPlatform tb : gameItems) {
            if (collisionDetectEasy(PlayerManager.timePlayer, tb, BitmapLoader.tallWallSkeleton, maxX, minX, true)) {
                count++;
                if (tb.getX() <= maxX) {
                    PlayerManager.timePlayer.setCollisionDetectedRight(true);
                    PlayerManager.timePlayer.setCollisionDetectedLeft(false);
                }
                if (tb.getX() >= minX) {
                    PlayerManager.timePlayer.setCollisionDetectedLeft(true);
                    PlayerManager.timePlayer.setCollisionDetectedRight(false);
                }
            }

            if (count == 0) {
                PlayerManager.timePlayer.setCollisionDetectedRight(false);
                PlayerManager.timePlayer.setCollisionDetectedLeft(false);
            }
        }
    }
}
