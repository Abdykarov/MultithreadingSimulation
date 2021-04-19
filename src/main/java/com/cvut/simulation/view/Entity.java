package com.cvut.simulation.view;

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
    /**
     * Entity moves to next tile
     */
    public abstract void move(Tile tileToMove);

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

    /**
     * Drinks water
     * @param waterObject
     */
    public abstract void drinkWater(Water waterObject);
}
