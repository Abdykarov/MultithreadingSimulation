package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.Model.*;
import com.cvut.simulation.view.Simulation;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.View.GridMap;

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
                TimeUnit.MILLISECONDS.sleep(bullet.aSpeed);
            } catch (InterruptedException ignored) {}
            if(em.isRunning){
                moveParticle();
            }
        }
    }

    private void moveParticle()
    {
        Fox detectedFox;
        Wolf detectedWolf;
        Sheep detectedSheep;
        Rabbit detectedRabbit;
        bullet.lock.lock();
        try
        {
            if(bullet.steps == 5){
                em.lock.lock();
                try {
                    bullet.isAlive = false;
                    LOGGER.log(Level.INFO, String.valueOf("bullet steps over"));
                    em.removeEntity(bullet.id);
                }finally {
                    em.lock.unlock();
                }
            }else if((detectedFox = bullet.detectFox()) != null){
                em.lock.lock();
                detectedFox.lock.lock();
                try {
                    detectedFox.isAlive = false;
                    bullet.isAlive = false;
                    em.removeEntity(bullet.id);
                    em.removeEntity(detectedFox.id);
                }
                finally {
                    detectedFox.lock.unlock();
                    em.lock.unlock();
                }
            }else if((detectedRabbit = bullet.detectRabbit()) != null){
                em.lock.lock();
                detectedRabbit.lock.lock();
                try {
                    detectedRabbit.isAlive = false;
                    bullet.isAlive = false;
                    em.removeEntity(bullet.id);
                    em.removeEntity(detectedRabbit.id);
                }
                finally {
                    detectedRabbit.lock.unlock();
                    em.lock.unlock();
                }
            }else if((detectedSheep = bullet.detectSheep()) != null){
                em.lock.lock();
                detectedSheep.lock.lock();
                try {
                    detectedSheep.isAlive = false;
                    bullet.isAlive = false;
                    em.removeEntity(bullet.id);
                    em.removeEntity(detectedSheep.id);
                }
                finally {
                    detectedSheep.lock.unlock();
                    em.lock.unlock();
                }
            }else if((detectedWolf = bullet.detectWolf()) != null){
                em.lock.lock();
                detectedWolf.lock.lock();
                try {
                    detectedWolf.isAlive = false;
                    bullet.isAlive = false;

                    em.removeEntity(bullet.id);
                    em.removeEntity(detectedWolf.id);
                }
                finally {
                    detectedWolf.lock.unlock();
                    em.lock.unlock();
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
            em.removeEntity(bullet.id);
            LOGGER.log(Level.INFO, String.valueOf("bullet is out of map"));
        }
    }


    public boolean inRange(int x, int y){
        if(x < em.gridWidth && x > 0 && y > 0 && y < em.gridHeight){
            return true;
        }
        return false;
    }



}
