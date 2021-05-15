package com.cvut.simulation.view;

import com.cvut.simulation.view.Controller.EntityRunnable;
import com.cvut.simulation.view.Model.*;
import com.cvut.simulation.view.View.GridMap;
import com.cvut.simulation.view.View.Tile;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {

    private static final Random rand = new Random();

    public static void main(String[] args){

        int gridWidth = 20*50;
        int gridHeight = 13*50;

        List<Entity> entities = new ArrayList<>();


        // Creating new entities wtih different parametrs
        entities.add(new Fox(getRandomPosition(gridWidth,gridHeight, entities),1,
                100,100,50,70, 16));
        entities.add(new Fox(getRandomPosition(gridWidth,gridHeight, entities),1,
                100,100,50,70, 16));
        entities.add(new Fox(getRandomPosition(gridWidth,gridHeight, entities),1,
                100,100,50,70, 16));

        entities.add(new Rabbit(getRandomPosition(gridWidth,gridHeight, entities),1,
                100,100,50,70, 16));

        entities.add(new Rabbit(getRandomPosition(gridWidth,gridHeight, entities),1,
                100,100,50,70, 16));
        entities.add(new Rabbit(getRandomPosition(gridWidth,gridHeight, entities),1,
                100,100,50,70, 16));

        entities.add(new Wolf(getRandomPosition(gridWidth,gridHeight, entities),1,
                100,100,50,70, 16));

        entities.add(new Wolf(getRandomPosition(gridWidth,gridHeight, entities),1,
                100,100,50,70, 16));

        entities.add(new Sheep(getRandomPosition(gridWidth,gridHeight, entities),1,
                100,100,50,70, 16));

        entities.add(new Sheep(getRandomPosition(gridWidth,gridHeight, entities),1,
                100,100,50,70, 16));


        entities.add(new Meat(getRandomPosition(gridWidth,gridHeight, entities),1,
                100, 16));
        entities.add(new Meat(getRandomPosition(gridWidth,gridHeight, entities),1,
                100, 16));



        GridMap gridMap = new GridMap(gridWidth, gridHeight, entities);
        BoardManager boardManager = new BoardManager(gridMap);

        /* Start the Entity Runnables */
        for (Entity entity : entities)
        {
            EntityRunnable particleRunnable = new EntityRunnable(entity, gridMap);
            new Thread(particleRunnable).start();

        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                boardManager.generateWindow();
            }
        });


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
