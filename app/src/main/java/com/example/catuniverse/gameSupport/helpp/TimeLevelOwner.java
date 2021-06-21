package com.example.catuniverse.gameSupport.helpp;

import java.util.ArrayList;

public class TimeLevelOwner implements Owner {

    private boolean isTrue = false;

    @Override
    public boolean notifyTrue() {
        this.isTrue = true;
        return true;
    }

    @Override
    public boolean notifyFalse() {
        this.isTrue = false;
        return false;
    }
}
