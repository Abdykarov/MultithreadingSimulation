package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.Utils.Tile;

import javax.swing.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fox extends Entity {

    public int sexualDesire = 100;
    public final Lock lock = new ReentrantLock();
    private int killingRabbitCountdown = 0;
    private int creatingFoxCountdown = 0;

    public Fox(Tile tilePos, int id, int aEnergy, int aHealth, int aSpeed, int aHunger, int aLifeLenght, int sexualDesire){
        this.aEnergy = aEnergy;
        this.aHealth = aHealth;
        this.aHunger = aHunger;
        this.sexualDesire = sexualDesire;
        this.aLifeLenght = aLifeLenght;
        this.aSpeed = aSpeed;
        this.aType = "Fox";
        this.image = "images/fox.png";
        this.id = id;
        this.width = 50;
        this.height = 45;
        this.currentPosition = tilePos;
        this.EntityImage = new ImageIcon(image).getImage();
        this.isAlive = true;

    }


    public Entity detectAnotherFox(){
        for(Entity entity: EntityList){
            if((entity.aType == "Fox") && entity.id != id && (entity.currentPosition.x == currentPosition.x) && (entity.currentPosition.y == currentPosition.y)){
                System.out.println("another fox detected");
                return entity;
            }
        }
        return null;
    }

    /**
     * Entity moves to next tile
     */
    @Override
    public void move() {
        // create new fox
        if(detectAnotherFox() != null && aEnergy > 70 && detectAnotherFox().aEnergy > 70 && aHunger < 30 && detectAnotherFox().aHunger < 30 && sexualDesire > 90){
            sim.addFox(20,currentPosition.x + 50, currentPosition.y);
            sexualDesire = 50;
            aHunger += 20;
            aEnergy -= 20;
            System.out.println("fox borned");
            move();
        }

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
        sexualDesire += 1;

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
