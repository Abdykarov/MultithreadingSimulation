package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;

import javax.swing.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sheep extends Entity {

    public final Lock lock;

    public Sheep(EntityManager em, Tile tilePos, int id, int aEnergy, int aHealth, int aSpeed, int aHunger, int aLifeLenght, int sexualDesire){
        this.lock = new ReentrantLock();
        this.aEnergy = aEnergy;
        this.em = em;
        this.sexualDesire = sexualDesire;
        this.aHealth = aHealth;
        this.aHunger = aHunger;
        this.aLifeLenght = aLifeLenght;
        this.aSpeed = aSpeed;
        this.aType = "Sheap";
        this.image = "images/sheep.png";
        this.id = id;
        this.width = 55;
        this.height = 45;
        this.aSpeedOriginal = aSpeed;
        this.currentPosition = tilePos;
        this.EntityImage = new ImageIcon(image).getImage();
        this.isAlive = true;
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
