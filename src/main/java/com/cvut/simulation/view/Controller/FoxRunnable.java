package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.Model.Fox;
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

public class FoxRunnable implements Runnable {

    public Fox fox;
    private final CountDownLatch latch;
    public Random rand = new Random();
    private EntityManager em;
    private final static Logger LOGGER = Logger.getLogger(BulletRunnable.class.getName());

    public FoxRunnable(EntityManager em, Entity fox, CountDownLatch latch) {
        this.em = em;
        this.fox = (Fox) fox;
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

        while (fox.isAlive && em.isRunning)
        {
            try
            {
                Thread.sleep(fox.aSpeed);
            } catch (InterruptedException ignored) {}
            moveParticle();
        }
    }

    private void moveParticle()
    {
        Rabbit rabbit;
        Fox nearFox;
        fox.lock.lock();
        try
        {
            if(fox.aLifeLenght == 0) {
                em.lock.lock();
                try {
                    fox.isAlive = false;
                    em.removeEntity(fox.id);
                    return;
                }
                finally {
                    em.lock.unlock();
                }
            }
            if(fox.aHunger > 100) {
                em.lock.lock();
                try {
                    fox.isAlive = false;
                    em.removeEntity(fox.id);
                    return;
                }
                finally {
                    em.lock.unlock();
                }
            }

            eatRabbit();

            createNewFox();

            fox.aLifeLenght = fox.aLifeLenght - 1;

        } finally
        {
            fox.lock.unlock();
        }
    }

    public void eatRabbit(){
        if(fox.detectAnotherRabbit() != null){
            if(fox.aHunger > 10){
                em.lock.lock();
                try {
                    em.removeEntity(fox.detectAnotherRabbit().id);
                    fox.sexualDesire += 10;
                    fox.aHunger -= 20;
                    LOGGER.log(Level.INFO, "Rabbit was eaten");
                    fox.aEnergy += 20;
                }
                finally {
                    em.lock.unlock();
                }
            } else{
                simpleStep();
            }
        }
        else{
            simpleStep();
        }
    }
    /**
     * Entity moves to next tile
     */
    public void createNewFox() {
        // create new fox
            if(fox.detectAnotherFox() != null){
                if(fox.aEnergy > 70 && fox.detectAnotherFox().aEnergy > 70 && fox.aHunger < 30 && fox.detectAnotherFox().aHunger < 30 && fox.sexualDesire > 90){
                    em.lock.lock();
                    try {
                        em.addFox(20,fox.currentPosition.x, fox.currentPosition.y);
                        LOGGER.log(Level.INFO, "Fox was created");
                        fox.sexualDesire = 50;
                        fox.aHunger += 20;
                        fox.aEnergy -= 20;
                    }
                    finally {
                        em.lock.unlock();
                    }
                } else{
                    simpleStep();
                }
            }
            else{
                simpleStep();
            }
    }


    public void simpleStep(){
        int xDelta = fox.currentPosition.x;
        int yDelta = fox.currentPosition.y;
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

        fox.currentPosition.x = xDelta;
        fox.currentPosition.y = yDelta;
        fox.sexualDesire += 10;
        fox.aEnergy -= 10;
        fox.aHunger += 1;
    }


}
