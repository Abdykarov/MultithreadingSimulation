package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.Model.Rabbit;
import com.cvut.simulation.view.Model.Sheep;
import com.cvut.simulation.view.Model.Wolf;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Model.Entity;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SheepRunnable implements Runnable {

    public Sheep sheep;
    private final CountDownLatch latch;
    public Random rand = new Random();
    private final EntityManager em;
    private final static Logger LOGGER = Logger.getLogger(BulletRunnable.class.getName());

    public SheepRunnable(EntityManager em, Entity sheep, CountDownLatch latch) {
        this.em = em;
        this.sheep = (Sheep) sheep;
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
        while (sheep.isAlive)
        {
            try
            {
                Thread.sleep(sheep.aSpeed);
            } catch (InterruptedException ignored) {}

            if(em.isRunning){
                moveParticle();
            }
        }
    }

    private void moveParticle()
    {
        Sheep nearSheep;
        try {
            sheep.lock.lockInterruptibly();
            sheep.lockAcquired = true;
            try
            {
                if(sheep.aLifeLenght == 0) {
                    em.lock.lockInterruptibly();
                    em.lockAcquired = true;
                    try {
                        sheep.isAlive = false;
                        em.removeEntity(sheep.id);
                    }
                    finally {
                        if(em.lockAcquired){
                            em.lockAcquired = false;
                            em.lock.unlock();
                        }
                    }
                }
                if(sheep.aHunger > 110) {
                    em.lock.lockInterruptibly();
                    em.lockAcquired = true;
                    try {
                        sheep.isAlive = false;
                        em.removeEntity(sheep.id);
                    }
                    finally {
                        if(em.lockAcquired){
                            em.lockAcquired = false;
                            em.lock.unlock();
                        }
                    }
                }



                if((nearSheep = sheep.detectAnotherSheep()) != null){
                    nearSheep.lock.lockInterruptibly();
                    nearSheep.lockAcquired = true;
                    em.lock.lockInterruptibly();
                    em.lockAcquired = true;
                    try {
                        if(sheep.available && nearSheep.available && sheep.aEnergy > 70 && nearSheep.aEnergy > 70 && sheep.aHunger < 30 && nearSheep.aHunger < 30 && nearSheep.sexualDesire > 70 && sheep.sexualDesire > 70){
                            try {
                                sheep.available = false;
                                nearSheep.available = false;
                                em.addSheep(em.getNextID(),sheep.currentPosition.x, sheep.currentPosition.y);
                                LOGGER.log(Level.INFO, "New sheep was created");
                                sheep.sexualDesire = 20;
                                sheep.aHunger += 30;
                                sheep.aEnergy -= 20;
                            }
                            finally {
                                if(em.lockAcquired && nearSheep.lockAcquired){
                                    sheep.available = true;
                                    nearSheep.available = true;
                                    nearSheep.lock.unlock();
                                    nearSheep.lockAcquired = false;
                                    em.lock.unlock();
                                    em.lockAcquired = false;
                                }
                            }
                        } else{
                            simpleStep();
                        }
                    }
                    finally {
                        if(em.lockAcquired){
                            em.lock.unlock();
                            em.lockAcquired = false;
                        }
                    }
                }else{
                    simpleStep();
                }
                if(sheep.aLifeLenght > 0){
                    sheep.aLifeLenght = sheep.aLifeLenght - 1;
                }

            } finally
            {
                if(sheep.lockAcquired)
                {
                    sheep.lockAcquired = false;
                    sheep.lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            sheep.isAlive = false;
        }
    }


    public void simpleStep(){
        int xDelta = sheep.currentPosition.x;
        int yDelta = sheep.currentPosition.y;
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

        sheep.currentPosition.x = xDelta;
        sheep.currentPosition.y = yDelta;

        if(sheep.sexualDesire < 100){
            sheep.sexualDesire += 10;
        }
        if(sheep.aEnergy > 0){
            sheep.aEnergy -= 10;
        }
        if(sheep.aHunger < 100){
            sheep.aHunger += 1;
        }
    }


}
