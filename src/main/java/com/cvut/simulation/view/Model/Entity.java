package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.Simulation;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

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



}
