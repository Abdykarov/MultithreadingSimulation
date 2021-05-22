package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.Model.Fox;
import com.cvut.simulation.view.Model.Rabbit;
import com.cvut.simulation.view.Simulation;
import com.cvut.simulation.view.View.GridMap;
import com.cvut.simulation.view.Model.Entity;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class RabbitRunnable implements Runnable {

    public Rabbit rabbit;
    private final CountDownLatch latch;
    public Random rand = new Random();
    private Simulation sim;
    public RabbitRunnable(Entity rabbit, CountDownLatch latch) {
        this.sim = new Simulation();
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

        while (rabbit.isAlive && sim.isRunning)
        {
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
            moveParticle();

        }
    }

    private void moveParticle()
    {;
        rabbit.lock.lock();
        try
        {
            if(rabbit.aLifeLenght == 0) {
                sim.lock.lock();
                try {
                    rabbit.isAlive = false;
                    sim.removeEntity(rabbit);
                    return;
                }
                finally {
                    sim.lock.unlock();
                }
            }
            if(rabbit.aHunger > 100) {
                sim.lock.lock();
                try {
                    rabbit.isAlive = false;
                    sim.removeEntity(rabbit);
                    return;
                }
                finally {
                    sim.lock.unlock();
                }
            }
                simpleStep();

//            move();
            rabbit.aLifeLenght = rabbit.aLifeLenght - 1;

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

        rabbit.currentPosition.x = xDelta;
        rabbit.currentPosition.y = yDelta;
        rabbit.sexualDesire += 10;
        rabbit.aHunger += 1;
        rabbit.aEnergy -= 10;
    }


}
