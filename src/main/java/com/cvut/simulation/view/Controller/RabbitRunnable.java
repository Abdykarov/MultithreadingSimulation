package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.Model.Rabbit;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Model.Entity;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RabbitRunnable implements Runnable {

    public Rabbit rabbit;
    private final CountDownLatch latch;
    public Random rand = new Random();
    private EntityManager em;
    private final static Logger LOGGER = Logger.getLogger(BulletRunnable.class.getName());

    public RabbitRunnable(EntityManager em, Entity rabbit, CountDownLatch latch) {
        this.em = em;
        this.rabbit = (Rabbit) rabbit;
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

        while (rabbit.isAlive)
        {
            try
            {
                Thread.sleep(rabbit.aSpeed);
            } catch (InterruptedException ignored) {}
            if(em.isRunning){
                moveParticle();
            }
        }
    }

    private void moveParticle()
    {
        Rabbit nearRabbit;
        rabbit.lock.lock();
        try
        {
            if(rabbit.aLifeLenght == 0) {
                em.lock.lock();
                try {
                    rabbit.isAlive = false;
                    em.removeEntity(rabbit.id);
                    return;
                }
                finally {
                    em.lock.unlock();
                }
            }
            if(rabbit.aHunger > 110) {
                em.lock.lock();
                try {
                    rabbit.isAlive = false;
                    em.removeEntity(rabbit.id);
                    return;
                }
                finally {
                    em.lock.unlock();
                }
            }


            if((nearRabbit = rabbit.detectAnotherRabbit()) != null){
                nearRabbit.lock.lock();
                em.lock.lock();
                if(rabbit.available && nearRabbit.available && rabbit.aEnergy > 70 && nearRabbit.aEnergy > 70 && rabbit.aHunger < 30 && nearRabbit.aHunger < 30 && nearRabbit.sexualDesire > 70 && rabbit.sexualDesire > 70){
                    try {
                        rabbit.available = false;
                        nearRabbit.available = false;
                        em.addFox(em.getNextID(),rabbit.currentPosition.x, rabbit.currentPosition.y);
                        LOGGER.log(Level.INFO, "New rabbit was created");
                        rabbit.sexualDesire = 20;
                        rabbit.aHunger += 30;
                        rabbit.aEnergy -= 20;
                    }
                    finally {
                        rabbit.available = true;
                        nearRabbit.available = true;
                        nearRabbit.lock.unlock();
                    }
                } else{
                    simpleStep();
                }
                em.lock.unlock();
            }else{
                simpleStep();
            }
            if(rabbit.aLifeLenght > 0){
                rabbit.aLifeLenght = rabbit.aLifeLenght - 1;
            }

        } finally
        {
            rabbit.lock.unlock();
        }
    }


    public void simpleStep(){
        int xDelta = rabbit.currentPosition.x;
        int yDelta = rabbit.currentPosition.y;
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

        rabbit.currentPosition.x = xDelta;
        rabbit.currentPosition.y = yDelta;

        if(rabbit.sexualDesire < 100){
            rabbit.sexualDesire += 10;
        }
        if(rabbit.aEnergy > 0){
            rabbit.aEnergy -= 10;
        }
        if(rabbit.aHunger < 100){
            rabbit.aHunger += 1;
        }
    }


}
