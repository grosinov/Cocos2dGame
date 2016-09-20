package com.example.rosinov.cocos2dgamerealnofake;

import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.types.CCPoint;

import java.util.Random;

public class AvionEnemigo {
    Sprite avionenemigo;
    CCPoint PosicionInicial;
    CCPoint PosicionFinal;
    Random rand;

    public AvionEnemigo() {
        avionenemigo = Sprite.sprite("AvionEnemigo.png");
        PosicionInicial = new CCPoint();
        PosicionFinal = new CCPoint();
        rand = new Random();
    }

    public Sprite getAvionenemigo() {
        return avionenemigo;
    }

    public void setPosicionInicial(int x, int y) {
        PosicionInicial.x = x;
        PosicionInicial.y = rand.nextInt(y);

        avionenemigo.setPosition(PosicionInicial.x, PosicionInicial.y);
    }

    public void moverse(){
        PosicionFinal.y = PosicionInicial.y;
        PosicionFinal.x=0;
        avionenemigo.runAction(MoveTo.action(3, PosicionFinal.x, PosicionFinal.y));
    }

    public CCPoint getPosicionInicial() {
        return PosicionInicial;
    }
}
