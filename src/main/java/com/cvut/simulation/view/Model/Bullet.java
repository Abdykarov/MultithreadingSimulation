package com.cvut.simulation.view.Model;

import javax.swing.*;

public class Bullet extends Entity {

    public final int direction;
    public int steps = 0;
    public Entity entityToDestroy;

    public Bullet(Tile tilePos, int id, int direction){
        this.direction = direction;
        this.aType = "Bullet";
        this.image = "images/bullet.png";
        this.id = id;
        this.width = 50;
        this.aLifeLenght = 1;
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
        if(steps == 5){
            isAlive = false;
            sim.removeEntity(this);
        }else if(detectCollision()){
            entityToDestroy.isAlive = false;
            sim.removeEntity(entityToDestroy);
        }
        int xDelta = currentPosition.x;
        int yDelta = currentPosition.y;
        xDelta = xDelta + 50;
        yDelta = yDelta;
        currentPosition.x = xDelta;
        currentPosition.y = yDelta;
        steps += 1;

    }

    public boolean detectCollision(){

        for(Entity entity: EntityList){
            if((entity.aType == "Wolf" || entity.aType == "Fox") && entity.currentPosition.x == currentPosition.x && entity.currentPosition.y == currentPosition.y){
                entityToDestroy = entity;
                System.out.println("bullet shots");
                return true;
            }
        }
        return false;
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
