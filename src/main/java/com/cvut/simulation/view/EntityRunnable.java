package com.cvut.simulation.view;

public class EntityRunnable implements Runnable {

    public Entity entity;
    public GridMap gridMap;

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
            moveEntity(nextPosition.x, nextPosition.y);
            gridMap.animateEntityStep(currentPosition, nextPosition);

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
