package upec.projetandroid20182019;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class Carte extends View {
    float constanteAccelBouleX;
    float constanteAccelBouleY;


    Coordonnee bouleCoord;
    Coordonnee trouCoord;
    Obstacle obstacle;

    float oldXVal;
    float oldYVal;

    float borderLeft;
    float borderRight;
    float borderTop;
    float borderBottom;


    public Carte(Context context , AttributeSet attrs){
        super(context,attrs);
        bouleCoord = new Coordonnee(200,1000);
        trouCoord = new Coordonnee(1300,2100);

        obstacle = new Obstacle();

        //for accelerometer
        constanteAccelBouleX = 0;
        constanteAccelBouleY = 0;

        //for border
        oldXVal = 0;
        oldYVal = 0;

        borderLeft = 0;
        borderRight = 0;
        borderTop = 0;
        borderBottom = 0;



    }

    public void onDraw(Canvas canvas){
            int h =getHeight();
            int w = getWidth();

            Paint paint = new Paint();


            paint.setColor(Color.RED);
            paint.setStrokeWidth(40);


        //BOULE ET ARRIVE
            //le x et y du cercle sont le centre du cercle
            canvas.drawCircle(bouleCoord.getX(),bouleCoord.getY(),60,paint); //x,y,radius,canva
            paint.setColor(Color.BLACK);
            canvas.drawCircle(trouCoord.getX(),trouCoord.getY(),60,paint); //x,y,radius,canva



        //OBSTACLE
            paint.setColor(Color.BLACK);
            Rect bordGauche = new Rect(0,0,40,h);
            Rect bordDroite = new Rect(w-40,0,w,h);
            Rect bordHaut = new Rect(0,0,w,40);
            Rect bordBas = new Rect(0,h-40,w,h);




        //Just for 4 borders
            borderLeft = 40;
            borderRight = w-40;
            borderTop = 40;
            borderBottom = h-40;

            obstacle.addToList(bordGauche);
            obstacle.addToList(bordDroite);
            obstacle.addToList(bordHaut);
            obstacle.addToList(bordBas);

            for(Rect r : obstacle.getListObstacle()){
                canvas.drawRect(r,paint);
            }




    }





    public void updateBoule(){

            oldXVal = bouleCoord.getX();
            oldYVal = bouleCoord.getY();

        //Problème de signe car Accelerometre et axe des x sont inversés
            if (constanteAccelBouleX > 0) {
                bouleCoord.setX(bouleCoord.getX() - (constanteAccelBouleX * (constanteAccelBouleX * 5)));

            } else {
                bouleCoord.setX(bouleCoord.getX() - (-(constanteAccelBouleX) * (constanteAccelBouleX * 5)));

            }

            if (constanteAccelBouleY > 0) {
                bouleCoord.setY(bouleCoord.getY() + (constanteAccelBouleY * (constanteAccelBouleY * 5)));

            } else {
                bouleCoord.setY(bouleCoord.getY() + (-(constanteAccelBouleY) * (constanteAccelBouleY * 5)));

            }


        checkBorder();

    }




//not cross the bord
    public void checkBorder(){


        if((bouleCoord.getX()-60) < borderLeft){
            bouleCoord.setX(oldXVal);
        }

        if((bouleCoord.getX()+60) > borderRight){
            bouleCoord.setX(oldXVal);
        }

        if((bouleCoord.getY()-60) < borderTop){
            bouleCoord.setY(oldYVal);
        }

        if((bouleCoord.getY()+60) > borderBottom){
            bouleCoord.setY(oldYVal);
        }


        reOnDraw();
    }





    public void reOnDraw(){
        invalidate();

    }



    public float getConstanteAccelBouleX(){
        return this.constanteAccelBouleX;
    }

    public void setConstanteAccelBouleX(float constanteAccelBouleX){
        this.constanteAccelBouleX = constanteAccelBouleX;
    }

    public float getConstanteAccelBouleY() {
        return constanteAccelBouleY;
    }

    public void setConstanteAccelBouleY(float constanteAccelBouleY){
        this.constanteAccelBouleY = constanteAccelBouleY;
    }

}

