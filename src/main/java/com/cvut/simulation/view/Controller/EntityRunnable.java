package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.Simulation;
import com.cvut.simulation.view.View.GridMap;
import com.cvut.simulation.view.Model.Entity;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class EntityRunnable implements Runnable {

    public Entity entity;
    public Simulation sim;
    private final CountDownLatch latch;
    public final long fps = 100;
    Random rand = new Random();
    public int count;

    public EntityRunnable(Entity entity, CountDownLatch latch) {
        this.entity = entity;
        this.latch = latch;
        sim = new Simulation();
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


        while (entity.isAlive) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(count);
            count += 1;
            sim.lock.lock();
            try {

                    entity.move();

            }
            finally {
                sim.lock.unlock();
            }
        }
    }

//    private void moveParticle()
//    {
//        Particle obstacle;
//        particle.lock.lock();
//        try
//        {
//            for (;;)
//            {
//                handleBoundaryCollision();
//                if ((obstacle = gridPanel.detectCollision(particle.id, particle.state.nextPosition)) != null)
//                {
//                    /* Utilize resource-order locking based on particle IDs */
//                    if (particle.id < obstacle.id)
//                    {
//                        obstacle.lock.lock();
//                    } else
//                    {
//                        particle.lock.unlock();
//                        obstacle.lock.lock();
//                        particle.lock.lock();
//                    }
//                    try
//                    {
//                        if (inPath(obstacle))
//                        {
//                            ParticleState preCollisionState = particle.state;
//                            particle.collide(obstacle.state);
//                            obstacle.collide(preCollisionState);
//                        }
//                    } finally
//                    {
//                        obstacle.lock.unlock();
//                    }
//                } else
//                {
//                    particle.move();
//                    return;
//                }
//            }
//        } finally
//        {
//            particle.lock.unlock();
//        }
//    }
//
//    /** This method is only called while we hold our own lock **/
//    private void handleBoundaryCollision()
//    {
//        ParticleState state = particle.state;
//        ImmutablePoint nextPosition = state.nextPosition;
//        Velocity velocity = state.velocity;
//        if (!inRange(nextPosition.x, 0, gridPanel.width))
//        {
//            velocity = new Velocity(velocity.x * -1, velocity.y);
//        }
//        if (!inRange(nextPosition.y, 0, gridPanel.height))
//        {
//            velocity = new Velocity(velocity.x, velocity.y * -1);
//        }
//        particle.state = new ParticleState(state.currentPosition, velocity, Particle.COLOR);
//    }



    private void killEntity(Entity entity){
        entity.isAlive = false;
        sim.removeEntity(entity);
    }

    private void decreaseAge(){
        entity.aLifeLenght = entity.aLifeLenght - 1;
    }


}
