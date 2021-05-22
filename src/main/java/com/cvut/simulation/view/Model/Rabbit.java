package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.Utils.Tile;

import javax.swing.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Rabbit extends Entity {

    public int sexualDesire = 100;

    // A lock of this monitor
    public final Lock lock = new ReentrantLock();
    public Rabbit(Tile tilePos, int id, int aEnergy, int aHealth, int aSpeed, int aHunger, int aLifeLenght, int sexualDesire){
        this.aEnergy = aEnergy;
        this.aHealth = aHealth;
        this.aHunger = aHunger;
        this.sexualDesire = sexualDesire;
        this.aLifeLenght = aLifeLenght;
        this.aSpeed = aSpeed;
        this.aType = "Rabbit";
        this.image = "images/rabbit.png";
        this.id = id;
        this.width = 50;
        this.height = 45;
        this.currentPosition = tilePos;
        this.EntityImage = new ImageIcon(image).getImage();
        this.isAlive = true;

    }


    public Rabbit detectAnotherRabbit(){
        for(Entity entity: EntityList){
            if((entity.aType == "Rabbit") && (entity.currentPosition.x == currentPosition.x) && (entity.currentPosition.y == currentPosition.y)){
                return (Rabbit) entity;
            }
        }
        return null;
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

    @Override
    public String toString() {
        return "Rabbit | " +
                "id=" + id +
                " | sexualDesire=" + sexualDesire +
                " | aSpeed=" + aSpeed +
                " | aHunger=" + aHunger +
                " | aHealth=" + aHealth +
                " | aEnergy=" + aEnergy +
                " | aLifeLenght=" + aLifeLenght +
                " | currentPosition=" + currentPosition +
                "\n";
    }
}
