package com.example.catuniverse.gameSupport.helpp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public abstract class Notify<T> {

    private ArrayList<ArrayList<Checkable>> list;
    private boolean param;
    private boolean value;

    public boolean getValue() {
        return value;
    }


    public Notify(@NonNull ArrayList<ArrayList<Checkable>> list) {
        this.list = list;
    }

    public void checkParam() {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(0).size(); j++) {

            }
        }
    }
}
