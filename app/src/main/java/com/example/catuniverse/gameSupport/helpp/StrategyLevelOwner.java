package com.example.catuniverse.gameSupport.helpp;

public class StrategyLevelOwner implements Owner {
    @Override
    public boolean notifyTrue() {
        return true;
    }

    @Override
    public boolean notifyFalse() {
        return false;
    }
}
