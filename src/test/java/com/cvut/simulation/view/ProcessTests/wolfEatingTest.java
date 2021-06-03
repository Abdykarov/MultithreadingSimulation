package com.cvut.simulation.view.ProcessTests;

import com.cvut.simulation.view.Controller.BulletRunnable;
import com.cvut.simulation.view.Controller.WolfRunnable;
import com.cvut.simulation.view.Model.Bullet;
import com.cvut.simulation.view.Model.Sheep;
import com.cvut.simulation.view.Model.Wolf;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class wolfEatingTest {
    @Test
    public void wolf_eating_sheep_test(){
        int gridWidth = 20*50;
        int gridHeight = 12*50;

        EntityManager em = new EntityManager(gridWidth, gridHeight);

        em.simulationSpeed = 1000;
        em.simulationSpeedOriginal = 1000;

        Sheep sheep = new Sheep(em, new Tile(50,50), em.getNextID(),100,100,1000,0,100,100);
        Wolf wolf = new Wolf(em, new Tile(50,50), em.getNextID(),100,100,1000,0,100,100);
        //act
        em.addEntity(sheep);
        em.addEntity(wolf);

        //assert
        assertEquals(2,em.getEntities().size());

        CountDownLatch latch1 = new CountDownLatch(1);
        WolfRunnable wolfRunnable = new WolfRunnable(em, wolf, latch1);

        // act
        wolfRunnable.eatSheep(sheep, wolf);


        // assert
        assertEquals(1,em.getEntities().size());
    }
}
