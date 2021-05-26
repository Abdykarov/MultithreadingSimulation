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

public class WolfRunnable implements Runnable {

    public Wolf wolf;
    private final CountDownLatch latch;
    public Random rand = new Random();
    private final EntityManager em;
    private final static Logger LOGGER = Logger.getLogger(BulletRunnable.class.getName());

    public WolfRunnable(EntityManager em, Entity wolf, CountDownLatch latch) {
        this.em = em;
        this.wolf = (Wolf) wolf;
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

        while (wolf.isAlive)
        {
            try
            {
                Thread.sleep(wolf.aSpeed);
            } catch (InterruptedException ignored) {}
            if(em.isRunning){
              //  moveParticle();
            }
        }
    }

    private void moveParticle()
    {
        Sheep sheep;
        Wolf nearWolf;
        wolf.lock.lock();
        try
        {
            if(wolf.aLifeLenght == 0) {
                em.lock.lock();
                try {
                    wolf.isAlive = false;
                    em.removeEntity(wolf.id);
                    return;
                }
                finally {
                    em.lock.unlock();
                }
            }
            if(wolf.aHunger > 110) {
                em.lock.lock();
                try {
                    wolf.isAlive = false;
                    em.removeEntity(wolf.id);
                    return;
                }
                finally {
                    em.lock.unlock();
                }
            }


            // eat sheep
            if((sheep = wolf.detectAnotherSheep()) != null){
                if(wolf.aHunger > 10){
                    em.lock.lock();
                    try {
                        em.removeEntity(sheep.id);
                        wolf.sexualDesire += 10;
                        wolf.aHunger -= 20;
                        LOGGER.log(Level.INFO, "Sheep was eaten");
                        wolf.aEnergy += 20;
                    }
                    finally {
                        em.lock.unlock();
                    }
                } else{
                    simpleStep();
                }
            } // create new wolf
            else if((nearWolf = wolf.detectAnotherWolf()) != null){
                em.lock.lock();
                nearWolf.lock.lock();
                if(wolf.available && nearWolf.available && wolf.aEnergy > 70 && nearWolf.aEnergy > 70 && wolf.aHunger < 30 && nearWolf.aHunger < 30 && nearWolf.sexualDesire > 70 && wolf.sexualDesire > 70){
                    try {
                        wolf.available = false;
                        nearWolf.available = false;
                        em.addWolf(em.getNextID(),wolf.currentPosition.x, wolf.currentPosition.y);
                        LOGGER.log(Level.INFO, "New wolf was created");
                        wolf.sexualDesire = 20;
                        wolf.aHunger += 30;
                        wolf.aEnergy -= 20;
                    }
                    finally {
                        wolf.available = true;
                        nearWolf.available = true;
                        nearWolf.lock.unlock();
                    }
                } else{
                    simpleStep();
                }
                em.lock.unlock();
            }else{
                simpleStep();
            }
            if(wolf.aLifeLenght > 0){
                wolf.aLifeLenght = wolf.aLifeLenght - 1;
            }

        } finally
        {
            wolf.lock.unlock();
        }
    }




    public void simpleStep(){
//        int xDelta = wolf.currentPosition.x;
//        int yDelta = wolf.currentPosition.y;
//        // TODO update ai logic in future
//        int velocity = rand.nextInt(9-1) +1;
//        // there is will be 9 ways to go,
//        switch (velocity){
//            case 1:
//                xDelta+= 0;
//                yDelta += 0;
//                break;
//            case 2:
//                xDelta += 50;
//                yDelta += 0;
//                break;
//            case 3:
//                xDelta+= 50;
//                yDelta += 50;
//                break;
//            case 4:
//                xDelta += 0;
//                yDelta += 50;
//                break;
//            case 5:
//                xDelta += -50;
//                yDelta += 50;
//                break;
//            case 6:
//                xDelta += -50;
//                yDelta += 0;
//                break;
//            case 7:
//                xDelta += 50;
//                yDelta += -50;
//                break;
//            case 8:
//                xDelta += 0;
//                yDelta += -50;
//                break;
//            case 9:
//                xDelta += -50;
//                yDelta += -50;
//                break;
//            default:
//                break;
//        }
//
//        if(xDelta > em.gridWidth-50){
//            xDelta = em.gridWidth - 50;
//        }
//        if(xDelta < 50){
//            xDelta = 50;
//        }
//        if (yDelta > em.gridHeight-50){
//            yDelta = em.gridHeight - 50;
//        }
//        if(yDelta < 50){
//            yDelta = 50;
//        }
//
//        wolf.currentPosition.x = xDelta;
//        wolf.currentPosition.y = yDelta;
//
//        if(wolf.sexualDesire < 100){
//            wolf.sexualDesire += 10;
//        }
//        if(wolf.aEnergy > 0){
//            wolf.aEnergy -= 10;
//        }
//        if(wolf.aHunger < 100){
//            wolf.aHunger += 1;
//        }
    }


}
