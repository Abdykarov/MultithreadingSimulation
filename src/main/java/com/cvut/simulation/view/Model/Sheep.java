package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.View.Tile;

import javax.swing.*;

public class Sheep extends Entity {


    public Sheep(Tile tilePos, int id, int aEnergy, int aHealth, int aSpeed, int aHunger, int aLifeLenght){
        this.aEnergy = aEnergy;
        this.aHealth = aHealth;
        this.aHunger = aHunger;
        this.aLifeLenght = aLifeLenght;
        this.aSpeed = aSpeed;
        this.aType = "Sheap";
        this.image = "sheep.png";
        this.id = id;
        this.width = 55;
        this.height = 45;
        this.currentPosition = tilePos;
        this.EntityImage = new ImageIcon(image).getImage();
        this.isAlive = true;
    }



    /**
     * Entity moves to next tile
     */
    @Override
    public void move() {
        int xDelta = currentPosition.x;
        int yDelta = currentPosition.y;
        // TODO update ai logic in future
        int velocity = rand.nextInt(9-1) +1;
        // there is will be 9 ways to go,
        switch (velocity){
            case 1:
                xDelta+= 0;
                yDelta += 0;
                break;
            case 2:
                xDelta += 50;
                yDelta += 0;
                break;
            case 3:
                xDelta+= 50;
                yDelta += 50;
                break;
            case 4:
                xDelta += 0;
                yDelta += 50;
                break;
            case 5:
                xDelta += -50;
                yDelta += 50;
                break;
            case 6:
                xDelta += -50;
                yDelta += 0;
                break;
            case 7:
                xDelta += 50;
                yDelta += -50;
                break;
            case 8:
                xDelta += 0;
                yDelta += -50;
                break;
            case 9:
                xDelta += -50;
                yDelta += -50;
                break;
            default:
                break;
        }

        currentPosition.x = xDelta;
        currentPosition.y = yDelta;
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
