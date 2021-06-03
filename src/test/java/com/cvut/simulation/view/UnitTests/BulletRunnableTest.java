package com.cvut.simulation.view.UnitTests;

import com.cvut.simulation.view.Controller.BulletRunnable;
import com.cvut.simulation.view.Model.Bullet;
import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.Model.Fox;
import com.cvut.simulation.view.Model.Wolf;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BulletRunnableTest {

    private EntityManager emMocked;
    private BulletRunnable bulletRunnable;
    private Tile tileMocked;
    private Wolf wolf;
    private Bullet bulletMocked;
    private CountDownLatch latchMocked;

    @BeforeEach
    public void setUp(){
        emMocked = mock(EntityManager.class);
        bulletMocked = mock(Bullet.class);
        bulletMocked.id = 1;
        latchMocked = mock(CountDownLatch.class);
        bulletRunnable = new BulletRunnable(emMocked,bulletMocked,latchMocked);
    }

    @Test
    public void killWolf_Test(){
        //arrange
        wolf = new Wolf(emMocked, emMocked.getRandomPosition(emMocked.getEntities()),emMocked.getNextID(),100,1000,0,100,100,100);
        Mockito.doNothing().when(emMocked).lockMonitor();
        Mockito.doNothing().when(emMocked).unlockMonitor();
        when(emMocked.getEntities()).thenReturn(Arrays.asList(bulletMocked,wolf));
        emMocked.addEntity(bulletMocked);
        emMocked.addEntity(wolf);
        int expectedResult = emMocked.getEntities().size();
        //assert
        assertEquals(true, wolf.isAlive);
        //act
        bulletRunnable.killWolf(bulletMocked,wolf);

        //assert
        assertEquals(false, wolf.isAlive);
        assertEquals(2,expectedResult);

    }

    @Test
    public void inRange_Test(){

        //arrange
        bulletMocked.currentPosition = new Tile(-50,50);
        emMocked.gridHeight = 10* 50;
        emMocked.gridWidth = 20 * 50;

        //act
        bulletRunnable.inRange(bulletMocked.currentPosition.x, bulletMocked.currentPosition.y);
        boolean expectedResult = bulletRunnable.inRange(bulletMocked.currentPosition.x, bulletMocked.currentPosition.y);

        //assert
        assertEquals(false, expectedResult);
    }

    @Test
    public void simpleStep_Test(){
        //arrange
        bulletMocked.currentPosition = new Tile(50,50);
        emMocked.gridHeight = 10* 50;
        emMocked.gridWidth = 20 * 50;
        int currentStep = bulletMocked.steps;

        //act
        bulletRunnable.simpleStep();

        //assert
        assertNotSame(currentStep,bulletMocked.steps);
    }
}
