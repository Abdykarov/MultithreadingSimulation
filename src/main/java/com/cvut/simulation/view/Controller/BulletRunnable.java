package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.Model.Bullet;
import com.cvut.simulation.view.Model.Fox;
import com.cvut.simulation.view.Model.Rabbit;
import com.cvut.simulation.view.Simulation;
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

    private final static Logger LOGGER = Logger.getLogger(BulletRunnable.class.getName());

    public BulletRunnable(Entity entity, CountDownLatch latch) {
        this.bullet = (Bullet) entity;
        this.latch = latch;
        Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.ALL);
        LOGGER.addHandler(handlerObj);
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
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

        while (true)
        {
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
            moveParticle();
//            LOGGER.log(Level.FINEST, String.valueOf());
        }
    }

    private void moveParticle()
    {

        bullet.lock.lock();
        try
        {
            move();

        } finally
        {
            bullet.lock.unlock();
        }
    }

    public void move() {
        Simulation sim = new Simulation();
        Entity detectedEntity;
            if(bullet.steps == 5){
                sim.lock.lock();
                try {
                    bullet.isAlive = false;
                    LOGGER.log(Level.FINEST, String.valueOf("steps over"));
                    sim.removeEntity(bullet);
                }finally {
                    sim.lock.unlock();
                }
            }else if((detectedEntity =  bullet.detectCollision() )!= null){
                sim.lock.lock();
                detectedEntity.lock.lock();
                try {
                    LOGGER.log(Level.FINEST, String.valueOf("entity shoted"));
                    bullet.entityToDestroy.isAlive = false;
                    sim.removeEntity(bullet.entityToDestroy);
                }
                finally {
                    sim.lock.unlock();
                    detectedEntity.lock.unlock();
                }
            }else{
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
            }
        }



}
