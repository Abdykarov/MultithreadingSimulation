package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.View.Tile;

import javax.swing.*;
import java.awt.*;

public abstract class Entity{

    public static final int SIZE = 50; // size of entity
    public static final Color COLOR = Color.BLUE.darker().darker();
    public Integer id;
    public Integer aSpeed; //animalSpeed
    public Integer aHunger;
    public Integer aHealth;
    public Integer aEnergy;
    public Integer aLifeLenght;
    public Integer width;
    public Integer height;
    public String aType;
    public Tile currentPosition;
    public Tile nextPosition;
    public String image;
    public Image EntityImage = new ImageIcon(image).getImage();

//    public abstract Tile getTile();


    /**
     * Entity moves to next tile
     */
    public abstract void move(int x,int y);

    /**
     * Entity eats other entity, which is placed in the next tile
     */
    public abstract void eat(Entity entityToEat);

    /**
     * Calculates nextPosition. Finds the best tile around
     * @return
     */
    public abstract Tile calculateNextPosition(Tile currentPosition);

    /**
     * Entity dies and thread goes down
     */
    public abstract void die();

    public abstract int getXPosition();

    public abstract int getYPosition();

    public abstract void setXPosition(int x);

    public abstract void setYPosition(int y);
    /**
     * Drinks water
     * @param waterObject
     */
    public abstract void drinkWater(Water waterObject);

    public abstract Tile getTile();
}
