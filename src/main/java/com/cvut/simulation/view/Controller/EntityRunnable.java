package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.Simulation;
import com.cvut.simulation.view.View.GridMap;
import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.View.Tile;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EntityRunnable implements Runnable {

    public Entity entity;
    public GridMap gridMap;
    public Simulation sim;
    public final long fps = 400;
    Random rand = new Random();

    public EntityRunnable(Entity entity, GridMap gridMap) {
        this.entity = entity;
        this.gridMap = gridMap;
        sim = new Simulation();
    }

    /**
     * Overrides default thread method run
     */
    @Override
    public void run() {
        while (entity.isAlive) {
            try {
                TimeUnit.MILLISECONDS.sleep(fps);
            } catch (InterruptedException e) {
            }
//            decreaseAge();
            if(entity.aLifeLenght == 0){
                killEntity(entity);
            }
            entity.move();

        }
    }

    private void killEntity(Entity entity){
        entity.isAlive = false;
        sim.removeEntity(entity);
    }

    private void decreaseAge(){
        entity.aLifeLenght = entity.aLifeLenght - 1;
    }


}
