package com.example.rosinov.cocos2dgamerealnofake;


import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.nodes.Sprite;

public class Avion {
    Sprite avion;

    public Avion() {
        avion = Sprite.sprite("avion.png");
        avion.setPosition(0 + avion.getWidth() / 4, 0 + avion.getHeight() / 2);
        avion.runAction(ScaleBy.action(0.01f, 0.25f, 0.25f));
    }

    public Sprite getAvion() {
        return avion;
    }
}
