package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;

import javax.swing.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sheep extends Entity {

    public final Lock lock = new ReentrantLock();
    public boolean available;
    public boolean lockAcquired = false;

    public Sheep(EntityManager em, Tile tilePos, int id, int aEnergy, int aHealth, int aSpeed, int aHunger, int aLifeLenght, int sexualDesire){
        this.aEnergy = aEnergy;
        this.em = em;
        this.available = true;
        this.sexualDesire = sexualDesire;
        this.aHealth = aHealth;
        this.aHunger = aHunger;
        this.aLifeLenght = aLifeLenght;
        this.aSpeed = aSpeed;
        this.aType = "Sheep";
        this.image = "images/sheep.png";
        this.id = id;
        this.width = 55;
        this.height = 45;
        this.aSpeedOriginal = aSpeed;
        this.currentPosition = tilePos;
        this.EntityImage = new ImageIcon(image).getImage();
        this.isAlive = true;
    }

    public Sheep detectAnotherSheep(){
        em.lock.lock();
        try {
            for(Entity entity: em.getEntities()){
                if((entity.aType == "Sheep") && entity.id != id && (entity.currentPosition.x == currentPosition.x) && (entity.currentPosition.y == currentPosition.y)){
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
        return "Sheep | " +
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
