package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.View.GridMap;
import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.View.Tile;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EntityRunnable implements Runnable {

    public Entity entity;
    public GridMap gridMap;
    Random rand = new Random();

    public EntityRunnable(Entity entity, GridMap gridMap) {
        this.entity = entity;
        this.gridMap = gridMap;
    }

    /**
     * Overrides default thread method run
     */
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            entity.aLifeLenght = entity.aLifeLenght - 1;
            if(entity.aLifeLenght == 0) entity.isAlive = false;
            entity.move();
        }
    }

    /**
     * Moves entity to next position
     */
    private void moveEntity (int xDelta, int yDelta) {
        entity.currentPosition.x = xDelta;
        entity.currentPosition.y = yDelta;
    }

}
