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
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
            }
            int max = 10;
            int min = -10;
            entity.move(rand.nextInt(max) * 1,0);
            System.out.println(entity.currentPosition.x);
            System.out.println(Thread.currentThread().getName());
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
