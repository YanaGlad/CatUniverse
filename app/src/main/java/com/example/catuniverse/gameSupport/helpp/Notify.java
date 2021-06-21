package com.example.catuniverse.gameSupport.helpp;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public abstract class Notify {

    private ArrayList<ArrayList<Checkable>> list;


    public Notify(@NonNull ArrayList<ArrayList<Checkable>> list) {
        this.list = list;
    }

    public void checkParam() {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                if (list.get(i).get(j).checkAcceptParam()) {
                    list.get(i).get(i).getOwner().notifyTrue();
                }

                if (list.get(i).get(j).checkDeclineParam()) {
                    list.get(i).get(i).getOwner().notifyFalse();
                }

            }
        }
    }
}
