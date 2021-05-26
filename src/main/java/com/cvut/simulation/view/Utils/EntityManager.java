package com.cvut.simulation.view.Utils;

import com.cvut.simulation.view.Controller.*;
import com.cvut.simulation.view.Model.*;
import com.cvut.simulation.view.View.StatisticsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

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
    public boolean lockAcquired = false;

    public EntityManager(int gridWidth, int gridHeight){
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.id = 1;
        this.isRunning = false;
    }

    public void lockMonitor(){
        this.lock.lock();
    }

    public void unlockMonitor(){
        this.lock.unlock();
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

    public void createEntity(String type, int healthValue, int energyValue, int ageLengthValue, int speedValue, int hungerValue, int desireValue, int id){
        try {
            switch (type){
                case "Wolf":
                    addEntity(new Wolf(this,new Tile(50,50),id,energyValue,healthValue,speedValue,hungerValue,ageLengthValue,desireValue ));
                    break;
                case "Fox":
                    addEntity(new Fox(this,new Tile(50,50),id,energyValue,healthValue,speedValue,hungerValue,ageLengthValue,desireValue ));
                    break;
                case "Hunter":
                    addEntity(new Hunter(this,getRandomPosition(getEntities()),id,energyValue,healthValue,speedValue,hungerValue,ageLengthValue,desireValue ));
                    break;
                case "Rabbit":
                    addEntity(new Rabbit(this,new Tile(50,50),id,energyValue,healthValue,speedValue,hungerValue,ageLengthValue,desireValue ));
                    break;
                case "Sheep":
                    addEntity(new Sheep(this,new Tile(50,50),id,energyValue,healthValue,speedValue,hungerValue,ageLengthValue,desireValue ));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        int currentId = this.id;
        this.id = currentId + 1;
        return currentId;
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
            if(entity.aType == "Wolf"){
                WolfRunnable particleRunnable = new WolfRunnable(this,entity, latch);
                new Thread(particleRunnable).start();
            }
            if(entity.aType == "Sheep"){
                SheepRunnable particleRunnable = new SheepRunnable(this,entity, latch);
                new Thread(particleRunnable).start();
            }
            if(entity.aType == "Bullet"){
                BulletRunnable particleRunnable = new BulletRunnable(this,entity, latch);
                new Thread(particleRunnable).start();
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }

    public void addRabbit(int id, int posX, int posY){
        CountDownLatch rabbitLatch = new CountDownLatch(1);
        Tile tile = new Tile(posX, posY);
        Entity rabbit = new Rabbit(this, tile,id,
                100,100,1000,0, 100, 20);
        entities.add(rabbit);
        RabbitRunnable particleRunnable = new RabbitRunnable(this,rabbit, rabbitLatch);
        new Thread(particleRunnable).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rabbitLatch.countDown();
    }

    public void addSheep(int id, int posX, int posY){
        CountDownLatch sheepLatch = new CountDownLatch(1);
        Tile tile = new Tile(posX, posY);
        Entity sheep = new Sheep(this, tile,id,
                100,100,1000,0, 150, 20);
        entities.add(sheep);
        SheepRunnable particleRunnable = new SheepRunnable(this,sheep, sheepLatch);
        new Thread(particleRunnable).start();

        sheepLatch.countDown();
    }


    public void addWolf(int id, int posX, int posY){
        CountDownLatch wolfLatch = new CountDownLatch(1);
        Tile tile = new Tile(posX, posY);
        Entity wolf = new Wolf(this, tile,id,
                100,100,1000,0, 100, 20);
        entities.add(wolf);
        WolfRunnable particleRunnable = new WolfRunnable(this,wolf, wolfLatch);
        new Thread(particleRunnable).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wolfLatch.countDown();
    }

    public void addFox(int id, int posX, int posY){
        CountDownLatch foxLatch = new CountDownLatch(1);
        Tile tile = new Tile(posX, posY);
        Entity fox = new Fox(this, tile,id,
                100,100,1000,0, 100, 20);
        entities.add(fox);
        FoxRunnable particleRunnable = new FoxRunnable(this,fox, foxLatch);
        new Thread(particleRunnable).start();

        foxLatch.countDown();
    }

    public void addBullet(int id, int posX, int posY, int direction){
        CountDownLatch bulletLatch = new CountDownLatch(1);
        Tile tile = new Tile(posX, posY);
        Entity bullet = new Bullet(this,tile,id, direction, 1000);
        entities.add(bullet);
        BulletRunnable particleRunnable = new BulletRunnable(this,bullet, bulletLatch);
        new Thread(particleRunnable).start();

        bulletLatch.countDown();

    }

    public void changeSpeeds(String type){
        this.lockMonitor();
        try {
            if(type == "slow"){
                for (Entity entity: entities){
                    if (entity != null){
                        entity.aSpeed = entity.aSpeedOriginal * 2;
                    }
                }
            }else if(type == "normal"){
                for (Entity entity: entities){
                    if(entity != null){
                        entity.aSpeed = entity.aSpeedOriginal;
                    }
                }
            }else if(type == "fast"){
                for (Entity entity: entities){
                    if (entity != null){
                        entity.aSpeed = entity.aSpeedOriginal / 2;
                    }
                }
            }
        }
        finally {
            this.unlockMonitor();
        }
    }

}
