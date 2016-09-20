package com.example.rosinov.cocos2dgamerealnofake;

import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.types.CCPoint;

import java.util.Random;

public class Disparo {
    Sprite disparo;
    CCPoint PosicionInicial;
    CCPoint PosicionFinal;

    public Disparo() {
        disparo = Sprite.sprite("Disparo.png");
        PosicionInicial = new CCPoint();
        PosicionFinal = new CCPoint();

        PosicionFinal.y = PosicionInicial.y;
        PosicionFinal.x=0;
        disparo.runAction(MoveTo.action(3, PosicionFinal.x, PosicionFinal.y));
    }

    public Sprite getDisparo() {
        return disparo;
    }

    public void setPosicionInicial(int y, int x) {
        PosicionInicial.x = x;
        PosicionInicial.y = y;

        disparo.setPosition(PosicionInicial.x, PosicionInicial.y);
    }
}
