package com.cvut.simulation.view;

import com.cvut.simulation.view.Controller.EntityRunnable;
import com.cvut.simulation.view.Model.*;
import com.cvut.simulation.view.View.BoardManager;
import com.cvut.simulation.view.View.GridMap;
import com.cvut.simulation.view.Model.Tile;

import javax.swing.*;
import java.util.*;

public class Simulation {

    private static final Random rand = new Random();
    public static List<Entity> entities = new ArrayList<>();

    public static void main(String[] args){

        int gridWidth = 20*50;
        int gridHeight = 13*50;


        // Creating new entities wtih different parametrs
        entities.add(new Fox(getRandomPosition(gridWidth,gridHeight, entities),1,
                100,100,400,20, 160, 100));
        entities.add(new Fox(getRandomPosition(gridWidth,gridHeight, entities),2,
                100,100,400,20, 160,100));


//        entities.add(new Rabbit(getRandomPosition(gridWidth,gridHeight, entities),4,
//                100,100,50,70, 16));
//
//        entities.add(new Rabbit(getRandomPosition(gridWidth,gridHeight, entities),5,
//                100,100,50,70, 16));
//        entities.add(new Rabbit(getRandomPosition(gridWidth,gridHeight, entities),6,
//                100,100,50,70, 16));
//
//        entities.add(new Wolf(getRandomPosition(gridWidth,gridHeight, entities),7,
//                100,100,50,70, 16));
//
//        entities.add(new Wolf(getRandomPosition(gridWidth,gridHeight, entities),8,
//                100,100,50,70, 16));
//
//        entities.add(new Sheep(getRandomPosition(gridWidth,gridHeight, entities),9,
//                100,100,50,70, 16));
//
//        entities.add(new Sheep(getRandomPosition(gridWidth,gridHeight, entities),10,
//                100,100,50,70, 16));
//
//        entities.add(new Hunter(getRandomPosition(gridWidth,gridHeight, entities),11,
//                100,50,70, 200));
//
//        entities.add(new Hunter(getRandomPosition(gridWidth,gridHeight, entities),12,
//                100,100,50,70, 200));
//
//
//        entities.add(new Meat(getRandomPosition(gridWidth,gridHeight, entities),13,
//                100, 16));
//        entities.add(new Meat(getRandomPosition(gridWidth,gridHeight, entities),14,
//                100, 16));





        GridMap gridMap = new GridMap(gridWidth, gridHeight);
        BoardManager boardManager = new BoardManager(gridMap);

        /* Start the Entity Runnables */
        for (Entity entity : entities)
        {
            EntityRunnable particleRunnable = new EntityRunnable(entity);
            new Thread(particleRunnable).start();

        }

        // start gui thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                boardManager.generateWindow();
            }
        });


    }

    public void removeEntity(Entity entity){

//        for (Entity ent : entities) {
//            if(ent != null && ent.id == entity.id){
//                entities.remove(ent);
//            }
//        }

        for (Iterator<Entity> iterator = entities.iterator(); iterator.hasNext();) {
            Entity ent = iterator.next();
            if(ent != null && ent.id == entity.id){
                iterator.remove();
            }
        }

    }

    public void addFox(int id, int posX, int posY){
        Tile tile = new Tile(posX, posY);
        Entity fox = new Fox(tile,id,
                100,100,50,70, 16, 20);
        entities.add(fox);
        EntityRunnable foxRunnable = new EntityRunnable(fox);
        new Thread(foxRunnable).start();

    }

    public void addBullet(int id, int posX, int posY, int direction){
        Tile tile = new Tile(posX, posY);
        Entity bullet = new Bullet(tile,id, direction);
        entities.add(bullet);
        EntityRunnable particleRunnable = new EntityRunnable(bullet);
        new Thread(particleRunnable).start();

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

}
