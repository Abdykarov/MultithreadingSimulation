package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.Utils.Tile;

import javax.swing.*;

public class Meat extends Entity {


    public Meat(Tile tilePos, int id, int aEnergy, int aLifeLenght){
        this.aEnergy = aEnergy;
        this.aLifeLenght = aLifeLenght;
        this.aType = "Meat";
        this.image = "images/meat.png";
        this.id = id;
        this.width = 50;
        this.height = 35;
        this.currentPosition = tilePos;
        this.EntityImage = new ImageIcon(image).getImage();
        this.isAlive = true;
    }



    /**
     * Entity moves to next tile
     */
    @Override
    public void move() {

    }

    /**
     * Entity eats other entity, which is placed in the next tile
     *
     * @param entityToEat
     */
    @Override
    public void eat(Entity entityToEat) {

    }

    /**
     * Calculates nextPosition. Finds the best tile around
     *
     * @param currentPosition
     * @return
     */
    @Override
    public Tile calculateNextPosition(Tile currentPosition) {
        return null;
    }

    @Override
    public void die() {

    }

    @Override
    public int getXPosition() {
        return 0;
    }

    @Override
    public int getYPosition() {
        return 0;
    }

    @Override
    public void setXPosition(int x) {

    }

    @Override
    public void setYPosition(int y) {

    }

    /**
     * Drinks water
     *
     * @param waterObject
     */
    @Override
    public void drinkWater(Rabbit waterObject) {

    }

    @Override
    public Tile getTile() {
        return null;
    }
}
