package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.Model.Fox;
import com.cvut.simulation.view.Model.Hunter;
import com.cvut.simulation.view.Model.Rabbit;
import com.cvut.simulation.view.Simulation;
import com.cvut.simulation.view.View.GridMap;
import com.cvut.simulation.view.Model.Entity;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class HunterRunnable implements Runnable {

    public Hunter hunter;
    private final CountDownLatch latch;
    public Random rand = new Random();

    public HunterRunnable(Entity hunter, CountDownLatch latch) {
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
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
            moveParticle();
        }
    }

    private void moveParticle()
    {
        Rabbit rabbit;
        Fox nearFox;
        Simulation sim = new Simulation();
        hunter.lock.lock();
        try
        {
            if(hunter.aLifeLenght == 0) {
                sim.lock.lock();
                try {
                    hunter.isAlive = false;
                    sim.removeEntity(hunter);
                    return;
                }
                finally {
                    sim.lock.unlock();
                }
            }
            if(hunter.aHunger > 100) {
                sim.lock.lock();
                try {
                    hunter.isAlive = false;
                    sim.removeEntity(hunter);
                    return;
                }
                finally {
                    sim.lock.unlock();
                }
            }

            shot();

            hunter.aLifeLenght = hunter.aLifeLenght - 1;

        } finally
        {
            hunter.lock.unlock();
        }
    }

    /**
     * Entity moves to next tile
     */
    public void shot() {
        // create new fox
        Simulation sim = new Simulation();

        sim.lock.lock();
            if(hunter.aEnergy > 70){
                try {
                    sim.addBullet(20,hunter.currentPosition.x, hunter.currentPosition.y, rand.nextInt(5-1) +1);
                    System.out.println("hunter shoots");
                    hunter.aEnergy += 20;
                }
                finally {
                    sim.lock.unlock();
                }
            } else {
                simpleStep();
            }


    }


    public void simpleStep(){
        Simulation sim = new Simulation();
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

        if(xDelta > sim.gridWidth-50){
            xDelta = sim.gridWidth - 50;
        }
        if(xDelta < 50){
            xDelta = 50;
        }
        if (yDelta > sim.gridHeight-50){
            yDelta = sim.gridHeight - 50;
        }
        if(yDelta < 50){
            yDelta = 50;
        }

        hunter.currentPosition.x = xDelta;
        hunter.currentPosition.y = yDelta;
        hunter.aEnergy -= 10;

    }


}
