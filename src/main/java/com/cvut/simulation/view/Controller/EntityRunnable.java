package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.View.GridMap;
import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.View.Tile;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EntityRunnable implements Runnable {

    public Entity entity;
    public GridMap gridMap;
    public final long fps = 600;
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
        while (entity.isAlive) {
            try {
                TimeUnit.MILLISECONDS.sleep(fps);
            } catch (InterruptedException e) {
            }
//            decreaseAge();
            if(entity.aLifeLenght == 0) entity.isAlive = false;
            entity.move();

        }
    }

    private void decreaseAge(){
        entity.aLifeLenght = entity.aLifeLenght - 1;
    }

    /**
     * Moves entity to next position
     */
    private void moveEntity (int xDelta, int yDelta) {
        // TODO update ai logic in future
        int velocity = rand.nextInt(9-1) +1;
        // there is will be 9 ways to go,
        switch (velocity){
            case 1:
                xDelta+= 0;
                yDelta += 0;
                break;
            case 2:
                xDelta += 50;
                yDelta += 0;
                break;
            case 3:
                xDelta+= 50;
                yDelta += 50;
                break;
            case 4:
                xDelta += 0;
                yDelta += 50;
                break;
            case 5:
                xDelta += -50;
                yDelta += 50;
                break;
            case 6:
                xDelta += -50;
                yDelta += 0;
                break;
            case 7:
                xDelta += 50;
                yDelta += -50;
                break;
            case 8:
                xDelta += 0;
                yDelta += -50;
                break;
            case 9:
                xDelta += -50;
                yDelta += -50;
                break;
            default:
                break;
        }

        entity.currentPosition.x = xDelta;
        entity.currentPosition.y = yDelta;
    }

}
