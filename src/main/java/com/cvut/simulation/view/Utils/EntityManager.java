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
    private int id;

    public EntityManager(int gridWidth, int gridHeight){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.id = 0;
    }

    private void removeEntity(){

    }

    public boolean removeEntity(int id){
        Entity entityToRemove = findEntity(id);
        if (entityToRemove != null){
            entities.remove(entityToRemove);
            return true;
        }
        return false;
    }

    public Entity findEntity(int id){
        for(Entity entity: entities){
            if(entity.id == id){
                return entity;
            }
        }
        return null;
    }

    public void addEntity(Entity entity){
        entities.add(entity);
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
    public Tile getRandomPosition(List<Entity> entities)
    {
        int xPos = rand.nextInt(gridWidth / Entity.SIZE) * Entity.SIZE;
        int yPos = rand.nextInt(gridHeight / Entity.SIZE) * Entity.SIZE;

        if(positionIsOccupied(new Tile(xPos,yPos),entities)){
            return getRandomPosition(entities);
        }

        return new Tile(xPos, yPos);

    }

    /**
     * Method checks if tile is occupied or not
     * @param position
     * @param entities
     * @return
     */
    private boolean positionIsOccupied(Tile position, List<Entity> entities)
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

    public int getNextID(){
        this.id = id + 1;
        return id + 1;
    }

    private void startThreads(){

    }
}
