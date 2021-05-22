package com.cvut.simulation.view;

import com.cvut.simulation.view.Controller.*;
import com.cvut.simulation.view.Model.*;
import com.cvut.simulation.view.View.BoardManager;
import com.cvut.simulation.view.View.GridMap;
import com.cvut.simulation.view.Utils.Tile;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Simulation {

    private static final Random rand = new Random();
    public volatile static List<Entity> entities = new ArrayList<>();
    public boolean isRunning = false;
    final CountDownLatch latch = new CountDownLatch(1);
    public final Lock lock = new ReentrantLock();
    public int gridWidth = 20*50;
    public int gridHeight = 13*50;



    public static void main(String[] args){
        int gridWidth = 20*50;
        int gridHeight = 13*50;

//         Creating new entities wtih different parametrs
        entities.add(new Fox(getRandomPosition(gridWidth,gridHeight, entities),1,
                100,100,400,20, 160, 100));
entities.add(new Fox(getRandomPosition(gridWidth,gridHeight, entities),2,
                100,100,400,20, 160, 100));
entities.add(new Fox(getRandomPosition(gridWidth,gridHeight, entities),3,
                100,100,400,20, 160, 100));
entities.add(new Fox(getRandomPosition(gridWidth,gridHeight, entities),4,
                100,100,400,20, 160, 100));
entities.add(new Fox(getRandomPosition(gridWidth,gridHeight, entities),5,
                100,100,400,20, 160, 100));
entities.add(new Fox(getRandomPosition(gridWidth,gridHeight, entities),6,
                100,100,400,20, 160, 100));
entities.add(new Fox(getRandomPosition(gridWidth,gridHeight, entities),7,
                100,100,400,20, 160, 100));

        entities.add(new Rabbit(getRandomPosition(gridWidth,gridHeight, entities),7,
                100,100,50,70, 16));

        entities.add(new Rabbit(getRandomPosition(gridWidth,gridHeight, entities),8,
                100,100,50,70, 50));
        entities.add(new Rabbit(getRandomPosition(gridWidth,gridHeight, entities),9,
                100,100,50,70, 50));
        entities.add(new Rabbit(getRandomPosition(gridWidth,gridHeight, entities),10,
                100,100,50,70, 50));
        entities.add(new Rabbit(getRandomPosition(gridWidth,gridHeight, entities),11,
                100,100,50,70, 50));


        entities.add(new Hunter(getRandomPosition(gridWidth,gridHeight, entities),12,
                100,20, 50));

        entities.add(new Hunter(getRandomPosition(gridWidth,gridHeight, entities),12,
                100,20, 50));




        GridMap gridMap = new GridMap(gridWidth, gridHeight);
        BoardManager boardManager = new BoardManager(gridMap);
        // thread starts after 1 second
        final CountDownLatch latch = new CountDownLatch(1);

        /* Start the Entity Runnables */
        for (Entity entity : entities)
        {
            if(entity.aType == "Fox"){
                FoxRunnable particleRunnable = new FoxRunnable(entity, latch);
                new Thread(particleRunnable).start();
            }
            if(entity.aType == "Rabbit"){
                RabbitRunnable particleRunnable = new RabbitRunnable(entity, latch);
                new Thread(particleRunnable).start();
            }
            if(entity.aType == "Hunter"){
                HunterRunnable particleRunnable = new HunterRunnable(entity, latch);
                new Thread(particleRunnable).start();
            }

        }

        Statistics stats = new Statistics( entities);
        StatisticsRunnable statsRunnable = new StatisticsRunnable(stats);
        new Thread(statsRunnable).start();

        // start gui thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                boardManager.generateWindow();
            }
        });




        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();

    }

    public Fox detectFox(Fox fox)
    {
        for (Entity entity : entities)
        {
            if (entity.id != fox.id)
            {
                if(entity.aType == "Fox" && entity.currentPosition.x == fox.currentPosition.x && entity.currentPosition.y == fox.currentPosition.y){
                    System.out.println("another fox detected");
                    return (Fox) entity;
                }
            }
        }
        return null;
    }

    public void removeEntity(Entity entity){

        for (Iterator<Entity> iterator = entities.iterator(); iterator.hasNext();) {
            Entity ent = iterator.next();
            if(ent != null && ent.id == entity.id){
                iterator.remove();
            }
        }

    }

    public void addWolf(){

    }

    public void addFox(int id, int posX, int posY){
        CountDownLatch foxLatch = new CountDownLatch(1);
        Tile tile = new Tile(posX, posY);
        Entity fox = new Fox(tile,id,
                100,100,50,70, 16, 20);
        entities.add(fox);
        FoxRunnable particleRunnable = new FoxRunnable(fox, foxLatch);
        new Thread(particleRunnable).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        foxLatch.countDown();
    }

    public void addBullet(int id, int posX, int posY, int direction){
         CountDownLatch bulletLatch = new CountDownLatch(1);
        Tile tile = new Tile(posX, posY);
        Entity bullet = new Bullet(tile,id, direction);
        entities.add(bullet);
        BulletRunnable particleRunnable = new BulletRunnable(bullet, bulletLatch);
        new Thread(particleRunnable).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bulletLatch.countDown();

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
