package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.Model.Fox;
import com.cvut.simulation.view.Model.Rabbit;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Model.Entity;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FoxRunnable implements Runnable {

    public Fox fox;
    private final CountDownLatch latch;
    public Random rand = new Random();
    private final EntityManager em;
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

        while (fox.isAlive)
        {
            try
            {
                Thread.sleep(fox.aSpeed);
            } catch (InterruptedException ignored) {}
            if(em.isRunning){
               moveParticle();
            }
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
            if(fox.aHunger > 110) {
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


            // eat rabbit
            if((rabbit = fox.detectAnotherRabbit()) != null){
                if(fox.aHunger > 10){
                    rabbit.lock.lock();
                    em.lock.lock();
                    try {
                        rabbit.isAlive = false;
                        em.removeEntity(rabbit.id);
                        fox.sexualDesire += 10;
                        fox.aHunger -= 20;
                        LOGGER.log(Level.INFO, "Rabbit was eaten");
                        fox.aEnergy += 20;
                    }
                    finally {
                        rabbit.lock.unlock();
                        em.lock.unlock();
                    }
                } else{
                    simpleStep();
                }
            } // create new fox
            else if((nearFox = fox.detectAnotherFox()) != null){
                nearFox.lock.lock();
                em.lockMonitor();
                if(fox.available && nearFox.available && fox.aEnergy > 70 && nearFox.aEnergy > 70 && fox.aHunger < 30 && nearFox.aHunger < 30 && nearFox.sexualDesire > 70 && fox.sexualDesire > 70){
                    try {
                        fox.available = false;
                        nearFox.available = false;
                        em.addFox(em.getNextID(),fox.currentPosition.x, fox.currentPosition.y);
                        LOGGER.log(Level.INFO, "Fox was created");
                        fox.sexualDesire = 20;
                        fox.aHunger += 30;
                        fox.aEnergy -= 20;
                    }
                    finally {
                        fox.available = true;
                        nearFox.available = true;
                        nearFox.lock.unlock();
                        em.unlockMonitor();
                    }
                } else{
                    simpleStep();
                }
                nearFox.lock.unlock();
                em.lock.unlock();
            }else{
                simpleStep();
            }


            if(fox.aLifeLenght > 0){
                fox.aLifeLenght = fox.aLifeLenght - 1;
            }

        } finally
        {
            fox.lock.unlock();
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

        if(fox.sexualDesire < 100){
            fox.sexualDesire += 10;
        }
        if(fox.aEnergy > 0){
            fox.aEnergy -= 10;
        }
        if(fox.aHunger < 100){
            fox.aHunger += 1;
        }
    }


}
