package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.Simulation;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;


/**
 * Each entity has own lock, which could be locked and unlocked only by one of the threads
 * This way prevents from possible deadlocks and race conditions, makes the class thread-safety to use
 */
public abstract class Entity{
    public Random rand = new Random();
    public static final int SIZE = 50; // size of entity
    public static final Color COLOR = Color.BLUE.darker().darker();
    public Integer id;
    public Integer sexualDesire;
    public Boolean isAlive;
    public Integer aSpeed; //animalSpeed
    public Integer aSpeedOriginal;
    public Integer aHunger;
    public Integer aHealth;
    public Integer aEnergy;
    public Integer aLifeLenght;
    public Integer width;
    public Integer height;
    public String aType;
    public Tile currentPosition;
    public String image;
    public EntityManager em;
    public Simulation sim = new Simulation();
    public Image EntityImage = new ImageIcon(image).getImage();
    public Lock lock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSexualDesire() {
        return sexualDesire;
    }

    public void setSexualDesire(Integer sexualDesire) {
        this.sexualDesire = sexualDesire;
    }

    public Boolean getAlive() {
        return isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    public Integer getaSpeed() {
        return aSpeed;
    }

    public void setaSpeed(Integer aSpeed) {
        this.aSpeed = aSpeed;
    }

    public Integer getaSpeedOriginal() {
        return aSpeedOriginal;
    }

    public void setaSpeedOriginal(Integer aSpeedOriginal) {
        this.aSpeedOriginal = aSpeedOriginal;
    }

    public Integer getaHunger() {
        return aHunger;
    }

    public void setaHunger(Integer aHunger) {
        this.aHunger = aHunger;
    }

    public Integer getaHealth() {
        return aHealth;
    }

    public void setaHealth(Integer aHealth) {
        this.aHealth = aHealth;
    }

    public Integer getaEnergy() {
        return aEnergy;
    }

    public void setaEnergy(Integer aEnergy) {
        this.aEnergy = aEnergy;
    }

    public Integer getaLifeLenght() {
        return aLifeLenght;
    }

    public void setaLifeLenght(Integer aLifeLenght) {
        this.aLifeLenght = aLifeLenght;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public Tile getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Tile currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Image getEntityImage() {
        return EntityImage;
    }

    public void setEntityImage(Image entityImage) {
        EntityImage = entityImage;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }
}
