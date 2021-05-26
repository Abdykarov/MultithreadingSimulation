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

    public Bullet(EntityManager entityManager, Tile tilePos, int id, int direction, int speed){
        this.direction = direction;
        if(direction == 1){
            this.image = "images/right.png";
        }else if(direction == 2){
            this.image = "images/down.png";
        }else if(direction == 3){
            this.image = "images/up.png";
        }else{
            this.image = "images/left.png";
        }
        this.aType = "Bullet";
        this.em = entityManager;
        this.aSpeed = speed;
        this.aSpeedOriginal = speed;
        this.id = id;
        this.width = 50;
        this.aLifeLenght = 1;
        this.height = 45;
        this.currentPosition = tilePos;
        this.EntityImage = new ImageIcon(image).getImage();
        this.isAlive = true;

    }


    public Fox detectFox(){
        em.lock.lock();
        try {
            for(Entity entity: em.getEntities()){
                if(entity.aType == "Fox" && entity.currentPosition.x == currentPosition.x && entity.currentPosition.y == currentPosition.y){
                    return (Fox) entity;
                }
            }
        }
        finally {
            em.lock.unlock();
        }
        return null;
    }


    public Wolf detectWolf(){
        em.lock.lock();
        try {
            for(Entity entity: em.getEntities()){
                if(entity.aType == "Wolf" && entity.currentPosition.x == currentPosition.x && entity.currentPosition.y == currentPosition.y){
                    return (Wolf) entity;
                }
            }
        }
        finally {
            em.lock.unlock();
        }
        return null;
    }


    public Sheep detectSheep(){
        em.lock.lock();
        try {
            for(Entity entity: em.getEntities()){
                if(entity.aType == "Sheep" && entity.currentPosition.x == currentPosition.x && entity.currentPosition.y == currentPosition.y){
                    return (Sheep) entity;
                }
            }
        }
        finally {
            em.lock.unlock();
        }
        return null;
    }


    public Rabbit detectRabbit(){
        em.lock.lock();
        try {
            for(Entity entity: em.getEntities()){
                if(entity.aType == "Rabbit" && entity.currentPosition.x == currentPosition.x && entity.currentPosition.y == currentPosition.y){
                    return (Rabbit) entity;
                }
            }
        }
        finally {
            em.lock.unlock();
        }
        return null;
    }


}
