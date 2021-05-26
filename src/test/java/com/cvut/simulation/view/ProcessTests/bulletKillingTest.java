package com.cvut.simulation.view.ProcessTests;

import com.cvut.simulation.view.Controller.BulletRunnable;
import com.cvut.simulation.view.Controller.SheepRunnable;
import com.cvut.simulation.view.Controller.WolfRunnable;
import com.cvut.simulation.view.Model.Bullet;
import com.cvut.simulation.view.Model.Sheep;
import com.cvut.simulation.view.Model.Wolf;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class bulletKillingTest {

    @Test
    public void bullet_kill_wolf_test(){
        int gridWidth = 20*50;
        int gridHeight = 12*50;

        EntityManager em = new EntityManager(gridWidth, gridHeight);

        em.simulationSpeed = 1000;
        em.simulationSpeedOriginal = 1000;

        Bullet bullet = new Bullet(em, new Tile(50,50), em.getNextID(),1,1000);
        Wolf wolf = new Wolf(em, new Tile(50,50), em.getNextID(),100,100,1000,0,100,100);

        em.addEntity(bullet);
        em.addEntity(wolf);

        CountDownLatch latch1 = new CountDownLatch(1);
        BulletRunnable bulletRunnable = new BulletRunnable(em, bullet, latch1);

        // act
        bulletRunnable.killWolf(bullet, wolf);


        // assert
        assertEquals(0,em.getEntities().size());
    }
}
