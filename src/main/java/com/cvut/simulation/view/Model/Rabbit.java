package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;

import javax.swing.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Rabbit extends Entity {

    public int sexualDesire = 100;

    // A lock of this monitor
    public final Lock lock;
    public boolean available;

    public Rabbit(EntityManager em, Tile tilePos, int id, int aEnergy, int aHealth, int aSpeed, int aHunger, int aLifeLenght, int sexualDesire){
        this.aEnergy = aEnergy;
        this.available = true;
        this.em = em;
        this.lock = new ReentrantLock();
        this.aHealth = aHealth;
        this.aHunger = aHunger;
        this.sexualDesire = sexualDesire;
        this.aLifeLenght = aLifeLenght;
        this.aSpeed = aSpeed;
        this.aType = "Rabbit";
        this.aSpeedOriginal = aSpeed;
        this.image = "images/rabbit.png";
        this.id = id;
        this.width = 50;
        this.height = 45;
        this.currentPosition = tilePos;
        this.EntityImage = new ImageIcon(image).getImage();
        this.isAlive = true;

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
