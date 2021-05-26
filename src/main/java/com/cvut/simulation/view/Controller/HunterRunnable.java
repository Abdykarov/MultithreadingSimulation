package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.Model.Fox;
import com.cvut.simulation.view.Model.Hunter;
import com.cvut.simulation.view.Model.Rabbit;
import com.cvut.simulation.view.Simulation;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.View.GridMap;
import com.cvut.simulation.view.Model.Entity;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hunter runnable thread class, holds hunter entity and manipulates with him
 */
public class HunterRunnable implements Runnable {

    public Hunter hunter;
    private final CountDownLatch latch;
    public Random rand = new Random();
    private EntityManager em;

    private final static Logger LOGGER = Logger.getLogger(BulletRunnable.class.getName());

    public HunterRunnable(EntityManager em, Entity hunter, CountDownLatch latch) {
        this.em = em;
        this.hunter = (Hunter) hunter;
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

        while (hunter.isAlive)
        {
            try
            {
                TimeUnit.MILLISECONDS.sleep(hunter.aSpeed);
            } catch (InterruptedException ignored) {}
            if(em.isRunning){
                shot();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                moveParticle();
            }
        }
    }

    /**
     * Move particle, different actions depending on position and values of entity.
     * I used lock and unlock methods for preventing deadlocks and race conditions.
     * It could be replaced by classic synchronized blocks, but i prefer more elegant solution
     */
    private void moveParticle()
    {
        hunter.lock.lock();
        try
        {
            if(hunter.aLifeLenght == 0) {
                em.lock.lock();
                try {
                    hunter.isAlive = false;
                    em.removeEntity(hunter.id);
                    return;
                }
                finally {
                    em.lock.unlock();
                }
            }

            if(hunter.reload == 0){
                shot();
                hunter.reload = 5;
            }else{
                simpleStep();
            }

            hunter.aLifeLenght = hunter.aLifeLenght - 1;

        } finally
        {
            hunter.lock.unlock();
        }
    }

    /**
     * Hunter makes shot, if reload is equals 0
     */
    public void shot() {

        em.lock.lock();
        try {
            em.addBullet(em.getNextID(),hunter.currentPosition.x, hunter.currentPosition.y, rand.nextInt(5-1) +1);
            LOGGER.log(Level.WARNING,"hunter shoots");
        }
        finally {
            em.lock.unlock();
        }
    }

    /**
     * Simple random step
     */
    public void simpleStep(){
        int xDelta = hunter.currentPosition.x;
        int yDelta = hunter.currentPosition.y;
        // TODO update ai logic in future
        int velocity = rand.nextInt(9-1) +1;
        // there is will be 9 ways to go,
        switch (velocity){
            case 1:
                xDelta+= 0;
                yDelta += 0;
                break;
            case 2:
                xDelta += 50;
                yDelta += 0;
                break;
            case 3:
                xDelta+= 50;
                yDelta += 50;
                break;
            case 4:
                xDelta += 0;
                yDelta += 50;
                break;
            case 5:
                xDelta += -50;
                yDelta += 50;
                break;
            case 6:
                xDelta += -50;
                yDelta += 0;
                break;
            case 7:
                xDelta += 50;
                yDelta += -50;
                break;
            case 8:
                xDelta += 0;
                yDelta += -50;
                break;
            case 9:
                xDelta += -50;
                yDelta += -50;
                break;
            default:
                break;
        }

        if(xDelta > em.gridWidth-50){
            xDelta = em.gridWidth - 50;
        }
        if(xDelta < 50){
            xDelta = 50;
        }
        if (yDelta > em.gridHeight-50){
            yDelta = em.gridHeight - 50;
        }
        if(yDelta < 50){
            yDelta = 50;
        }

        hunter.currentPosition.x = xDelta;
        hunter.currentPosition.y = yDelta;

        if(hunter.reload > 0){
            hunter.reload -= 1;
        }


    }


}
