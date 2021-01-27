package com.example.catuniverse.gameViews.general;

import android.graphics.Color;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.GameView;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.support.Room;

import java.util.ArrayList;

import static com.example.catuniverse.gameSupport.BitmapLoader.baseBlueButton;
import static com.example.catuniverse.gameSupport.BitmapLoader.baseBlueButtonClicked;
import static com.example.catuniverse.gameSupport.BitmapLoader.laboratoryBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.moonBackground;
import static com.example.catuniverse.gameSupport.BitmapLoader.spaceBase;

//Данный класс отображает выбранную комнату
public class RoomView extends GameView {

    private BasicButton goBack, changeRoom;
    private ArrayList<Room> rooms; //Список комнат
    private ArrayList<BasicButton> roomsChoice; //Кнопки для выбора комнаты
    private Room currentRoom; //текущая комната

    RoomView(MainRunActivity mainRunActivity) {
        super(mainRunActivity);
        rooms = new ArrayList<>();
        roomsChoice = new ArrayList<>();
        goBack = new BasicButton(mainRunActivity, 20, 540, mainRunActivity.getString(R.string.back), Color.BLACK, 30, baseBlueButton, baseBlueButtonClicked, 10, 35);
        changeRoom = new BasicButton(mainRunActivity, 610, 25, mainRunActivity.getString(R.string.rooms), Color.BLACK, 30, baseBlueButton, baseBlueButtonClicked, 10, 35);
        rooms.add(new Room(mainRunActivity, (byte) 1, mainRunActivity.getString(R.string.space_base), spaceBase));
        rooms.add(new Room(mainRunActivity, (byte) 2, mainRunActivity.getString(R.string.laboratory), laboratoryBackground));
        rooms.add(new Room(mainRunActivity, (byte) 3, mainRunActivity.getString(R.string.moon), moonBackground)); //В разработке!

        int chY = 100;
        for (int i = 0; i < rooms.size(); i++) {
            roomsChoice.add(new BasicButton(mainRunActivity, 610, chY, rooms.get(i).getName(), Color.BLACK, 30, baseBlueButton, baseBlueButtonClicked, 10, 35));
            chY += 75;
        }
        currentRoom = rooms.get(0);
    }

    @Override
    public void run() {
        repaint();
        currentRoom.run(super.getGamePaint());
        changeRoom.run(super.getGamePaint());

        if (changeRoom.isClicked())
            for (int i = 0; i < roomsChoice.size(); i++)
                roomsChoice.get(i).run(super.getGamePaint());

        if(!currentRoom.getStorage().isClicked()) goBack.run(super.getGamePaint());
        super.getGamePaint().write(super.getMainRunActivity().getString(R.string.current_room) + " " + currentRoom.getName(), 260, 50, Color.WHITE, 30);
    }

    @Override
    public void repaint() {
        if (goBack.isClicked())
            super.getMainRunActivity().setView(new MenuView(super.getMainRunActivity()));

        for (int i = 0; i < roomsChoice.size(); i++)
            if (roomsChoice.get(i).isClicked()) {
                switchRooms(i);
                roomsChoice.get(i).notClicked();
            }
    }

    //Сменить текушую комнату
    private void switchRooms(int choice) {
        this.currentRoom = rooms.get(choice);
    }
}
