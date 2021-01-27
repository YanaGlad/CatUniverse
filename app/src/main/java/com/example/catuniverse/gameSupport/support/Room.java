package com.example.catuniverse.gameSupport.support;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.catuniverse.R;
import com.example.catuniverse.gameSupport.BasicGameSupport;
import com.example.catuniverse.gameSupport.BitmapLoader;
import com.example.catuniverse.gameSupport.Buttons.BasicButton;
import com.example.catuniverse.gameSupport.Loopable;
import com.example.catuniverse.gameSupport.MainRunActivity;
import com.example.catuniverse.gameSupport.databaseHelpers.CatPet;
import com.example.catuniverse.gameSupport.graphics.PlayerManager;
import com.example.catuniverse.gameSupport.graphics.GamePaint;
import com.example.catuniverse.gameViews.general.MenuView;

import java.util.ArrayList;

import static com.example.catuniverse.MainActivity.listOfCats;
import static com.example.catuniverse.MainActivity.listOfPets;
import static com.example.catuniverse.gameSupport.BitmapLoader.addCatButton;
import static com.example.catuniverse.gameSupport.BitmapLoader.addCatButtonClicked;
import static com.example.catuniverse.gameSupport.BitmapLoader.questionCat;
import static com.example.catuniverse.gameSupport.BitmapLoader.shortBlueRect;
import static com.example.catuniverse.gameSupport.BitmapLoader.storageDown;
import static com.example.catuniverse.gameSupport.BitmapLoader.storageUp;

//Комната для котов
public class Room implements Loopable {
    private byte number; //номер комнаты
    private String name; //Название
    private Bitmap background; // Фон
    private CatPet catPet1, catPet2, catPet3; //3 кота, которые могут быть размещены в комнате
    private BasicButton up, down; //Посмотреть доступных котов
    private static BasicButton storage;
    private MainRunActivity mainRunActivity;
    private ArrayList<BasicButton> catStorage; //Кнопки с доступными персонажами ( видны когда нажата кнопка storage)
    private byte places; //занятые места в комнате

    public Room(MainRunActivity mainRunActivity, byte number, String name, Bitmap background) {
        this.mainRunActivity = mainRunActivity;
        this.number = number;
        this.name = name;
        this.background = background;
        places = 0;
        storage = new BasicButton(mainRunActivity, 20, 20, addCatButton, addCatButtonClicked, false);
        up = new BasicButton(mainRunActivity, 195, 20, storageUp, storageUp, false);
        down = new BasicButton(mainRunActivity, 195, 540, storageDown, storageDown, false);

        catPet1 = new CatPet(null, -1, number);
        catPet2 = new CatPet(null, -1, number);
        catPet3 = new CatPet(null, -1, number);
        catStorage = new ArrayList<>();
        int arrayX = 10, arrayY = 110;
        for (int i = 0; i < BasicGameSupport.catsCount; i++) {
            if (listOfCats.get(i).getUnlocked() == 1)
                catStorage.add(new BasicButton(mainRunActivity, arrayX, arrayY, MenuView.catIcons.get(i).getIcon(), MenuView.catIcons.get(i).getIcon(), false));
            else
                catStorage.add(new BasicButton(mainRunActivity, arrayX, arrayY, questionCat, questionCat, false));
            //arrayX += 120;
            //if ((i + 1) % 2 == 0) {
            //    arrayX = 20;
            arrayY += 110;
            // }
        }
    }


    @Override
    public void run(GamePaint gamePaint) {
        repaint();
        gamePaint.setVisibleBitmap(background, -100, 0);
        storage.run(gamePaint);

        for (int i = 0; i < listOfPets.size(); i++)
            if (listOfCats.get(i).getUnlocked() == 1) checkCatPet(listOfPets.get(i));


        if (catPet1 != null) catPet1.run(gamePaint);
        if (catPet2 != null) catPet2.run(gamePaint);
        if (catPet3 != null) catPet3.run(gamePaint);

        //Если нажата кнопка добавления кота
        if (storage.isClicked()) {
            //Запускаем склад доступных котов
            gamePaint.setVisibleBitmap(BitmapLoader.technoBackground, -750, 0);

            if (up.isClicked() && catStorage.get(catStorage.size() - 1).getY() > 10) {
                for (int i = 0; i < catStorage.size(); i++)
                    catStorage.get(i).setY(catStorage.get(i).getY() - 50);
                up.notClicked();
            }
            if (down.isClicked() && catStorage.get(0).getY() < 110) {
                for (int i = 0; i < catStorage.size(); i++)
                    catStorage.get(i).setY(catStorage.get(i).getY() + 50);
                down.notClicked();
            }

            for (int i = 0; i < catStorage.size(); i++)
                if (catStorage.get(i) != null)
                    if (catStorage.get(i).getY() > -150 && catStorage.get(i).getY() < 600) {
                        catStorage.get(i).run(gamePaint);
                        if (listOfCats.get(i).getUnlocked() == 1 && catStorage.get(i).getY() > 70)
                            gamePaint.write(listOfCats.get(i).getName(), 115, catStorage.get(i).getY() + 28, Color.BLACK, 30);
                    }

            gamePaint.setVisibleBitmap(shortBlueRect, -49, -25);
            gamePaint.write(mainRunActivity.getString(R.string.available), 10, 40, Color.BLACK, 40);
            gamePaint.write(mainRunActivity.getString(R.string.cats), 50, 90, Color.BLACK, 40);

            up.run(gamePaint);
            down.run(gamePaint);

            for (int i = 0; i < catStorage.size(); i++) {
                if (catStorage.get(i).isClicked() && listOfCats.get(i).getUnlocked() == 1 && catStorage.get(i).getY() > 10) {
                    //System.out.println(listOfCats.get(i).getName() + " is chosen??? - " + listOfCats.get(i).getChosen() );

                    BasicButton add = new BasicButton(mainRunActivity, 115, catStorage.get(i).getY() + 35, mainRunActivity.getString(R.string.add), Color.BLACK, 25, BitmapLoader.smallButton, BitmapLoader.smallButtonClicked, 20, 20);
                    if (listOfCats.get(i).getRoom() == -1) add.run(gamePaint);

                    BasicButton choose = new BasicButton(mainRunActivity, 115, catStorage.get(i).getY() + 70, mainRunActivity.getString(R.string.choose_cat), Color.BLACK, 25, BitmapLoader.smallButton, BitmapLoader.smallButtonClicked, 20, 20);
                    choose.run(gamePaint);

                    if (add.isClicked() && places < 3) { // && listOfCats.get(i).getRoom() == -1
                        //Добавить кота в данную комнату
                        BasicGameSupport.putCatIntoRoom(number, listOfPets.get(i).getId());
                        if (listOfCats.get(i).getUnlocked() == 1) checkCatPet(listOfPets.get(i));
                        catStorage.get(i).notClicked();
                        add.notClicked();
                    } else add.notClicked();

                    if (choose.isClicked() && listOfCats.get(i).getRoom() != -1) {
                        BasicGameSupport.choosePlayer(listOfCats.get(i).getId());
                        PlayerManager.setChosenCat(listOfCats.get(i));
                        catStorage.get(i).notClicked();
                        choose.notClicked();
                    } else choose.notClicked();
                } else catStorage.get(i).notClicked();
            }

        }
    }

    @Override
    public void repaint() {
    }

    @Override
    public void repaint(double speed, double jumSpeed) {
    }

    //Проверить, есть ли уже кот в комнате. Если нет, то переданный кот будет занесен в свободный слот
    private void checkCatPet(CatPet catPet) {
        //  System.out.println("ID " + catPet.getId() + " Room " + catPet.getRoom());
        if (catPet.getRoom() == number) {
            if (catPet.getId() != catPet1.getId() && catPet.getId() != catPet2.getId() && catPet.getId() != catPet3.getId())
                if (catPet1.getCat() == null) {
                    catPet1 = catPet;
                    places++;
                } else {
                    if (catPet2.getCat() == null) {
                        catPet2 = catPet;
                        places++;
                    } else {
                        if (catPet3.getCat() == null) {
                            catPet3 = catPet;
                            places++;
                        }
                    }
                }
        }
    }

    public String getName() {
        return name;
    }

    public BasicButton getStorage() {
        return storage;
    }
}
