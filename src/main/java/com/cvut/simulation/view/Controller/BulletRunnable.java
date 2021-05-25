package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.Model.Bullet;
import com.cvut.simulation.view.Model.Fox;
import com.cvut.simulation.view.Model.Rabbit;
import com.cvut.simulation.view.Simulation;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.View.GridMap;
import com.cvut.simulation.view.Model.Entity;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BulletRunnable implements Runnable {

    public Bullet bullet;
    private final CountDownLatch latch;
    private EntityManager em;

    private final static Logger LOGGER = Logger.getLogger(BulletRunnable.class.getName());

    public BulletRunnable(EntityManager em,Entity entity, CountDownLatch latch) {
        this.em = em;
        this.bullet = (Bullet) entity;
        this.latch = latch;
    }

    /**
     * Overrides default thread method run
     */
    @Override
    public void run() {
        // TODO - LOCK, AWAIT, UNLOCK LOGIC WILL BE CALLED FROM HERE
        try
        {
            latch.await();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
            return;
        }

        while (bullet.isAlive)
        {
            try
            {
                Thread.sleep(bullet.aSpeed);
            } catch (InterruptedException ignored) {}
            if(em.isRunning){
                moveParticle();
            }
        }
    }

    private void moveParticle()
    {
        bullet.lock.lock();
        try
        {
            Entity detectedEntity;
            if(bullet.steps == 5){
                em.lock.lock();
                try {
                    bullet.isAlive = false;
                    LOGGER.log(Level.INFO, String.valueOf("bullet steps over"));
                    em.removeEntity(bullet.id);
                }finally {
                    em.lock.unlock();
                }
            }else if((detectedEntity =  bullet.detectCollision() )!= null){
                em.lock.lock();
                detectedEntity.lock.lock();
                try {
                    LOGGER.log(Level.INFO, String.valueOf("entity shoted"));
                    bullet.entityToDestroy.isAlive = false;
                    em.removeEntity(bullet.entityToDestroy.id);
                }
                finally {
                    em.lock.unlock();
                    detectedEntity.lock.unlock();
                }
            }else{
                simpleStep();
            }

        } finally
        {
            bullet.lock.unlock();
        }
    }

    private void simpleStep(){
        int xDelta = bullet.currentPosition.x;
        int yDelta = bullet.currentPosition.y;
        switch (bullet.direction){
            case 1:
                xDelta += 50;
                yDelta = yDelta;
                break;
            case 2:
                xDelta = xDelta;
                yDelta += 50;
                break;
            case 3:
                xDelta = xDelta;
                yDelta -= 50;
                break;
            case 4:
                xDelta -= 50;
                yDelta = yDelta;
        }

        bullet.currentPosition.x = xDelta;
        bullet.currentPosition.y = yDelta;
        bullet.steps += 1;

        if(!inRange(bullet.currentPosition.x, bullet.currentPosition.y)){
            bullet.isAlive = false;
            LOGGER.log(Level.INFO, String.valueOf("bullet is out of map"));
        }
    }


    public boolean inRange(int x, int y){
        if(x < em.gridWidth-50 && x > 0 && y > 0 && y < em.gridHeight - 50){
            return true;
        }
        return false;
    }



}
