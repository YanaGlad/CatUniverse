package com.example.catuniverse.gameSupport.support;

public enum  PLAN {
//PLAN FOR 08.05.2021 - 10.05.2021
//Fix timePlayer stand while falling DONE
//Complete 6 time level. Put keys on all of the disappearing platforms DONE
//Make sharps decoration ~ around 2000 pixels len. Player must lose suddenly after touching it. Just as decor DONE
//Fix empty parts it xml descr. Probably get rid of dp and make it wrap content DONE
//Try to understand why it doesnt work on other type of emulator NOT DONE!
//Optimizing tallObs will be fine DONE
//Add trigonometry levels. !!!it is important to be readable!!!!!!!! DONE
//Check why c' = 0 doesnt work and fix this stupid error DONE
//Optimizing levels additions including autoIncrement NOT DONE!
//Reduce asteroid count to 4 or 5 DONE

//PLAN FOR 11.05.2021 - 13.05.2021

//!!!! add abstract secretsLvl class!!!!! or abstract method in TimeLevel
//Class SecretItem
//db achievements _id, name, done
//db secrets _id, name, collected

//class Achievement CONSTRUCTOR: boolean achieve , int id, string name
//class Achievement methods: getters/setters
//ArrayList<Achievement>a; a.add(new Achievement(id, name, 1 or 0));
//star or cup button in the corner
//description for each . GridView? Or my graphics?
//Well it may be like a grid in strategy.  Also there should me method in basicgamesupport for creating button grid.
//When you click on the button, you go to simple xml view(in fragment) to read about achievement
//10 achievements = gold cat prize
//20 achievements = another cat prize and so on

//SecretItems.
// Secret items are required to get prize cats. For instance, 1 ruby + 2 coins + 3 diamond == money cat
// They are hidden in the levels and you need to complete the level to save them collected
// It is so similar to TimeInventoryItem, try to make it extend it or something
// Why it should be extra class. It is connected to db. Maybe db helper.
//IDEEA!!! It is singleton cause you can't add same items two times to the levels.  (All of em are created in the beginning to static arr list) (not sure)
//If you add item with id = 1 to lvl1 and to lvl2 or 2 times to lvl1, you must be banned
// Fix useless parent in xml description
}
