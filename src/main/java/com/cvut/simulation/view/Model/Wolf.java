package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;

import javax.swing.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Wolf extends Entity {

    public final Lock lock;
    public EntityManager em;
    public boolean available;

    public Wolf(EntityManager em, Tile tilePos, int id, int aEnergy, int aHealth, int aSpeed, int aHunger, int aLifeLenght, int sexualDesire){
        this.aEnergy = aEnergy;
        this.em = em;
        this.lock = new ReentrantLock();
        this.sexualDesire = sexualDesire;
        this.aHealth = aHealth;
        this.available = true;
        this.aHunger = aHunger;
        this.aLifeLenght = aLifeLenght;
        this.aSpeed = aSpeed;
        this.aType = "Wolf";
        this.image = "images/wolf.png";
        this.id = id;
        this.width = 50;
        this.height = 35;
        this.aSpeedOriginal = aSpeed;
        this.currentPosition = tilePos;
        this.EntityImage = new ImageIcon(image).getImage();
        this.isAlive = true;

    }


    public Wolf detectAnotherWolf(){
        em.lock.lock();
        try {
            for(Entity entity: em.getEntities()){
                if((entity.aType == "Wolf") && entity.id != id && (entity.currentPosition.x == currentPosition.x) && (entity.currentPosition.y == currentPosition.y)){
                    return (Wolf) entity;
                }
            }
        }
        finally {
            em.lock.unlock();
        }
        return null;
    }

    public Sheep detectAnotherSheep(){
        em.lock.lock();
        try {
            for(Entity entity: em.getEntities()){
                if((entity.aType == "Sheep") && (entity.currentPosition.x == currentPosition.x) && (entity.currentPosition.y == currentPosition.y)){
                    return (Sheep) entity;
                }
            }
        }
        finally {
            em.lock.unlock();
        }
        return null;
    }


    @Override
    public String toString() {
        return "Wolf | " +
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
