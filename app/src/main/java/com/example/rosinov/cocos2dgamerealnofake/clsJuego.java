package com.example.rosinov.cocos2dgamerealnofake;

import android.util.Log;

import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCSize;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class clsJuego {
    CCGLSurfaceView VistaDelJuego;
    CCSize TamañoPantalla;
    Avion avion;
    AvionEnemigo avenemigo;
    Disparo disparo;
    Sprite fondo;
    int vida;

    ArrayList<AvionEnemigo> arrEnemigos;

    public clsJuego(CCGLSurfaceView vp){
        VistaDelJuego = vp;
        avion = new Avion();
        disparo = new Disparo();
        arrEnemigos = new ArrayList<>();
        arrEnemigos.add(new AvionEnemigo(1, 1));

        vida = 3;
    }

    public void ComenzarJuego(){
        Director.sharedDirector().attachInView(VistaDelJuego);

        TamañoPantalla = Director.sharedDirector().displaySize();

        Director.sharedDirector().runWithScene(Escena());
    }

    private Scene Escena(){
        Scene escenajuego;
        escenajuego = Scene.node();

        CapaDeFondo capfon;
        capfon = new CapaDeFondo();

        CapaDeFrente capfren;
        capfren = new CapaDeFrente();

        escenajuego.addChild(capfon, -10);
        escenajuego.addChild(capfren, 10);

        return  escenajuego;
    }

    class CapaDeFondo extends Layer{
        public CapaDeFondo() {
            fondo = Sprite.sprite("fondo.png");
            fondo.setPosition(TamañoPantalla.width / 2, TamañoPantalla.height / 2);
            fondo.runAction(ScaleBy.action(0.01f, 14.0f, 10.0f));
            super.addChild(fondo);
        }
    }

    class CapaDeFrente extends Layer{
        public CapaDeFrente() {
            TimerTask TareaPonerEnemigos = new TimerTask() {
                @Override
                public void run() {
                    //Preguntar por pantalla tocada
                    disparo = new Disparo();
                    disparo.setPosicionInicial(Math.round(avion.getPosicionInicial().x), Math.round(avion.getPosicionInicial().y));
                    arrEnemigos.add(new AvionEnemigo(Math.round(TamañoPantalla.width), Math.round(TamañoPantalla.height)));
                    Log.d("enemigos", String.valueOf(arrEnemigos.size()));
                    for(int i = 0; i < arrEnemigos.size(); i++){
                        AvionEnemigo aven = arrEnemigos.get(i);
                        addChild(aven.getAvionenemigo());
                        if(aven.getAvionenemigo().getPositionX() == 0){
                            arrEnemigos.remove(i);
                            vida--;
                        }
                    }
                    DetectarColision();

                }
            };

            Timer RelojEnemigos = new Timer();
            RelojEnemigos.schedule(TareaPonerEnemigos, 0, 1000);

            super.addChild(avion.getAvion());
        }
    }

    void DetectarColision(){
        for(int i = 0; i <= arrEnemigos.size()-1; i++){
            AvionEnemigo aven = arrEnemigos.get(i);
            if(Colision(aven.getAvionenemigo(), disparo.getDisparo())){
                arrEnemigos.remove(i);
            }
        }
    }

    boolean Colision(Sprite sprite1, Sprite sprite2){
        boolean Devolver;
        Devolver = false;

        int Sprite1I, Sprite1D, Sprite1Ab, Sprite1Ar;
        int Sprite2I, Sprite2D, Sprite2Ab, Sprite2Ar;

        Sprite1I = (int) (sprite1.getPositionX() - sprite1.getWidth()/2);
        Sprite1D = (int) (sprite1.getPositionX() + sprite1.getWidth()/2);
        Sprite1Ab = (int) (sprite1.getPositionY() - sprite1.getHeight()/2);
        Sprite1Ar = (int) (sprite1.getPositionY() - sprite1.getHeight()/2);

        Sprite2I = (int) (sprite2.getPositionX() - sprite2.getWidth()/2);
        Sprite2D = (int) (sprite2.getPositionX() - sprite2.getWidth()/2);
        Sprite2Ab = (int) (sprite2.getPositionY() - sprite2.getHeight()/2);
        Sprite2Ar = (int) (sprite2.getPositionY() - sprite2.getHeight()/2);

        if(EstaEntre(Sprite1I, Sprite2I, Sprite2D) &&
                EstaEntre(Sprite1Ab, Sprite2Ab, Sprite2Ar)){
            Devolver = true;
        }

        if(EstaEntre(Sprite1I, Sprite2I, Sprite2D) &&
                EstaEntre(Sprite1Ar, Sprite2Ab, Sprite2Ar)){
            Devolver = true;
        }

        if(EstaEntre(Sprite1D, Sprite2I, Sprite2D) &&
                EstaEntre(Sprite1Ar, Sprite2Ab, Sprite2Ar)){
            Devolver = true;
        }

        if(EstaEntre(Sprite1D, Sprite2I, Sprite2D) &&
                EstaEntre(Sprite1Ab, Sprite2Ab, Sprite2Ar)){
            Devolver = true;
        }

        //asasdasdasd

        if(EstaEntre(Sprite2I, Sprite1I, Sprite1D) &&
                EstaEntre(Sprite2Ab, Sprite1Ab, Sprite1Ar)){
            Devolver = true;
        }

        if(EstaEntre(Sprite2I, Sprite1I, Sprite1D) &&
                EstaEntre(Sprite2Ar, Sprite1Ab, Sprite1Ar)){
            Devolver = true;
        }

        if(EstaEntre(Sprite2D, Sprite1I, Sprite1D) &&
                EstaEntre(Sprite2Ar, Sprite1Ab, Sprite1Ar)){
            Devolver = true;
        }

        if(EstaEntre(Sprite2D, Sprite1I, Sprite1D) &&
                EstaEntre(Sprite2Ab, Sprite1Ab, Sprite1Ar)){
            Devolver = true;
        }

        return Devolver;
    }

    boolean EstaEntre(int NumeroAComparar, int Menor, int Mayor){
        boolean Devolver;

        if(Menor > Mayor){
            int Auxiliar;
            Auxiliar=Mayor;
            Mayor = Menor;
            Menor = Auxiliar;
        }

        if(NumeroAComparar >= Menor && NumeroAComparar <= Mayor){
            Devolver = true;
        } else {
            Devolver = false;
        }

        return Devolver;
    }
}
