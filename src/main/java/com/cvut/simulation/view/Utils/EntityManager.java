package com.cvut.simulation.view.Utils;

import com.cvut.simulation.view.Controller.*;
import com.cvut.simulation.view.Model.Bullet;
import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.Model.Fox;
import com.cvut.simulation.view.Model.Statistics;
import com.cvut.simulation.view.View.StatisticsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EntityManager {

    private static final Random rand = new Random();
    public volatile static List<Entity> entities = new ArrayList<>();
    public final int gridWidth;
    public final int gridHeight;
    private int id;
    public int simulationSpeed;
    public int simulationSpeedOriginal;
    final CountDownLatch latch = new CountDownLatch(1);
    public final Lock lock = new ReentrantLock();
    public boolean isRunning;

    public EntityManager(int gridWidth, int gridHeight){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.id = 0;
        this.isRunning = false;
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

    public void startStats(StatisticsView statsView){
        Statistics stats = new Statistics( this);
        StatisticsRunnable statsRunnable = new StatisticsRunnable(stats,this, statsView);
        new Thread(statsRunnable).start();
    }

    public void startThreads(){
        this.isRunning = true;
        /* Start the Entity Runnables */
        for (Entity entity : entities)
        {
            if(entity.aType == "Fox"){
                FoxRunnable particleRunnable = new FoxRunnable(this,entity, latch);
                new Thread(particleRunnable).start();
            }
            if(entity.aType == "Rabbit"){
                RabbitRunnable particleRunnable = new RabbitRunnable(this,entity, latch);
                new Thread(particleRunnable).start();
            }
            if(entity.aType == "Hunter"){
                HunterRunnable particleRunnable = new HunterRunnable(this,entity, latch);
                new Thread(particleRunnable).start();
            }
//            if(entity.aType == "Wolf"){
//                WolfRunnable particleRunnable = new HunterRunnable(this,entity, latch);
//                new Thread(particleRunnable).start();
//            }
//            if(entity.aType == "Sheep"){
//                HunterRunnable particleRunnable = new HunterRunnable(this,entity, latch);
//                new Thread(particleRunnable).start();
//            }

        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }


    public void addWolf(){

    }

    public Fox detectFox(Fox fox)
    {
        for (Entity entity : entities)
        {
            if (entity.id != fox.id)
            {
                if(entity.aType == "Fox" && entity.currentPosition.x == fox.currentPosition.x && entity.currentPosition.y == fox.currentPosition.y){
                    return (Fox) entity;
                }
            }
        }
        return null;
    }


    public void addFox(int id, int posX, int posY){
        CountDownLatch foxLatch = new CountDownLatch(1);
        Tile tile = new Tile(posX, posY);
        Entity fox = new Fox(this, tile,id,
                100,100,1000,0, 100, 20);
        entities.add(fox);
        FoxRunnable particleRunnable = new FoxRunnable(this,fox, foxLatch);
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
        Entity bullet = new Bullet(this,tile,id, direction);
        entities.add(bullet);
        BulletRunnable particleRunnable = new BulletRunnable(this,bullet, bulletLatch);
        new Thread(particleRunnable).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bulletLatch.countDown();

    }

    public void changeSpeeds(String type){
        if(type == "slow"){
            for (Entity entity: entities){
                entity.aSpeed = entity.aSpeedOriginal /2;
            }
        }else if(type == "normal"){
            for (Entity entity: entities){
                entity.aSpeed = entity.aSpeedOriginal;
            }
        }else if(type == "fast"){
            for (Entity entity: entities){
                entity.aSpeed = entity.aSpeedOriginal * 2;
            }
        }
    }

}
