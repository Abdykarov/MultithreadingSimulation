package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;

import javax.swing.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bullet extends Entity {

    public final int direction;
    public int steps = 0;
    public Entity entityToDestroy;
    public final Lock lock = new ReentrantLock();
    public EntityManager em;

    public Bullet(EntityManager entityManager, Tile tilePos, int id, int direction){
        this.direction = direction;
        this.aType = "Bullet";
        this.em = entityManager;
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


    }

    public Entity detectCollision(){
        for(Entity entity: em.getEntities()){
            if((entity.aType == "Wolf" || entity.aType == "Fox" || entity.aType == "Rabbit" || entity.aType=="Sheep") && entity.currentPosition.x == currentPosition.x && entity.currentPosition.y == currentPosition.y){
                entityToDestroy = entity;
                System.out.println("bullet shots");
                if(entity.aType == "Wolf"){
                    return (Wolf) entity;
                }
                else if(entity.aType == "Fox"){
                    return (Fox) entity;
                }
                else if(entity.aType == "Rabbit"){
                    return (Rabbit) entity;
                }
                else if(entity.aType == "Sheep"){
                    return (Sheep) entity;
                }
            }
        }
        return null;
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
