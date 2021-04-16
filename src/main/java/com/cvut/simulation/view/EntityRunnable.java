package com.cvut.simulation.view;

public class EntityRunnable implements Runnable {

    private final Entity entity;
    private final GridMap gridMap;

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
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            Tile currentPosition = entity.currentPosition;
            Tile nextPosition = entity.nextPosition;
            moveEntity();
            gridMap.animateEntityStep(currentPosition, nextPosition);
        }
    }

    /**
     * Moves entity to next position
     */
    private void moveEntity () {

    }

}
