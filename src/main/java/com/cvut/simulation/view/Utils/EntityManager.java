package com.cvut.simulation.view.Utils;

import com.cvut.simulation.view.Model.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityManager {

    private static final Random rand = new Random();
    public volatile static List<Entity> entities = new ArrayList<>();
    private final int gridWidth;
    private final int gridHeight;

    public EntityManager(int gridWidth, int gridHeight){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
    }

    private void removeEntity(){

    }

    private void addEntity(){

    }

    public Entity getEntity(){
        return null;
    }

    public List<Entity> getEntities(){
        return entities;
    }

    /**
     * Return random tile for new entity. Divides the width/height of map by entities size
     * and then multiplies by entities size
     * @param gridWidth
     * @param gridHeight
     * @return
     */
    private static Tile getRandomPosition(int gridWidth, int gridHeight, List<Entity> entities)
    {
        int xPos = rand.nextInt(gridWidth / Entity.SIZE) * Entity.SIZE;
        int yPos = rand.nextInt(gridHeight / Entity.SIZE) * Entity.SIZE;

        if(positionIsOccupied(new Tile(xPos,yPos),entities)){
            return getRandomPosition(gridWidth,gridHeight,entities);
        }

        return new Tile(xPos, yPos);

    }

    /**
     * Method checks if tile is occupied or not
     * @param position
     * @param entities
     * @return
     */
    private static boolean positionIsOccupied(Tile position, List<Entity> entities)
    {
        for (Entity entity : entities)
        {
            if (entity.currentPosition.equals(position))
            {
                return true;
            }
        }
        return false;
    }

    private void startThreads(){

    }
}
