package com.cvut.simulation.view.ProcessTests;

import com.cvut.simulation.view.Controller.SheepRunnable;
import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.Model.Sheep;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class sheepSpawningTest {

    @Test
    public void sheep_spawn_test(){
        int gridWidth = 20*50;
        int gridHeight = 12*50;

        EntityManager em = new EntityManager(gridWidth, gridHeight);

        em.simulationSpeed = 1000;
        em.simulationSpeedOriginal = 1000;

        Sheep sheep1 = new Sheep(em, new Tile(50,50), em.getNextID(),100,100,1000,0,100,100);
        Sheep sheep2 = new Sheep(em, new Tile(800,50), em.getNextID(),100,100,1000,0,100,100);

        // act
        em.addEntity(sheep1);
        em.addEntity(sheep2);

        em.startThreads();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Entity entity: em.getEntities()){
            if(entity != null){
                System.out.println(entity.currentPosition.x);
            }
        }
        // assert
        assertEquals(2,em.getEntities().size());
    }
}
