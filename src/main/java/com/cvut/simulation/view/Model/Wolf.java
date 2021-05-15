package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.View.Tile;

public class Wolf extends Entity {


    Wolf(Tile tilePos, int id, int aEnergy, int aHealth, int aSpeed, int aHunger, int aLifeLenght){
        this.aEnergy = aEnergy;
        this.aHealth = aHealth;
        this.aHunger = aHunger;
        this.aLifeLenght = aLifeLenght;
        this.aSpeed = aSpeed;
        this.aType = "Wolf";
        this.id = id;
        this.currentPosition = tilePos;
        this.nextPosition = calculateNextPosition(tilePos);
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
    public void drinkWater(Water waterObject) {

    }

    @Override
    public Tile getTile() {
        return null;
    }
}
