package com.fucshiateam.fishboy;

import com.fucshiateam.framework.Screen;
import com.fucshiateam.framework.impl.AndroidGame;

public class Fishboy extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this); 
    }
}