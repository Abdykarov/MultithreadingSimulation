package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;

import javax.swing.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fox extends Entity {
    
    // A lock of this monitor
    public final Lock lock;
    public boolean available;
    public Fox(EntityManager em, Tile tilePos, int id, int aEnergy, int aHealth, int aSpeed, int aHunger, int aLifeLenght, int sexualDesire){
        this.aEnergy = aEnergy;
        this.em = em;
        this.available = true;
        this.lock = new ReentrantLock();
        this.aHealth = aHealth;
        this.aHunger = aHunger;
        this.sexualDesire = sexualDesire;
        this.aLifeLenght = aLifeLenght;
        this.aSpeed = aSpeed;
        this.aSpeedOriginal = aSpeed;
        this.aType = "Fox";
        this.image = "images/fox.png";
        this.id = id;
        this.width = 50;
        this.height = 45;
        this.currentPosition = tilePos;
        this.EntityImage = new ImageIcon(image).getImage();
        this.isAlive = true;

    }

    public Lock getLock(){
        return lock;
    }

    public Fox detectAnotherFox(){
        em.lock.lock();
        try {
            for(Entity entity: em.getEntities()){
                if((entity.aType == "Fox") && entity.id != id && (entity.currentPosition.x == currentPosition.x) && (entity.currentPosition.y == currentPosition.y)){
                    return (Fox) entity;
                }
            }
        }
        finally {
            em.lock.unlock();
        }
        return null;
    }

    public Rabbit detectAnotherRabbit(){
        em.lock.lock();
        try {
            for(Entity entity: em.getEntities()){
                if((entity.aType == "Rabbit") && (entity.currentPosition.x == currentPosition.x) && (entity.currentPosition.y == currentPosition.y)){
                    return (Rabbit) entity;
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
        return "Fox | " +
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
