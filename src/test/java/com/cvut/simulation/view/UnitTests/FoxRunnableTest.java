package com.cvut.simulation.view.UnitTests;

import com.cvut.simulation.view.Controller.BulletRunnable;
import com.cvut.simulation.view.Controller.FoxRunnable;
import com.cvut.simulation.view.Model.*;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.Mockito.*;

public class FoxRunnableTest {

    private EntityManager emMocked;
    private FoxRunnable foxRunnable;
    private Tile tileMocked;
    private Fox foxMocked;
    private CountDownLatch latchMocked;

    @BeforeEach
    public void setUp(){
        emMocked = Mockito.spy(new EntityManager(20*50,12*50));
        foxMocked = mock(Fox.class);
        foxMocked.currentPosition = new Tile(50,50);
        foxMocked.id = 1;
        latchMocked = mock(CountDownLatch.class);
        foxRunnable = new FoxRunnable(emMocked,foxMocked,latchMocked);
    }

    @Test
    public void eatRabbit_Test(){
        //arrange
        foxMocked.sexualDesire = 100;
        foxMocked.aHunger = 100;
        foxMocked.aEnergy = 100;
        Mockito.doNothing().when(emMocked).lockMonitor();
        Mockito.doNothing().when(emMocked).unlockMonitor();
        Rabbit rabbit = new Rabbit(emMocked, emMocked.getRandomPosition(emMocked.getEntities()),2,100,100,1000,0,100,100);

        emMocked.addEntity(rabbit);
        emMocked.addEntity(foxMocked);

        //act
        foxRunnable.eatRabbit(rabbit,foxMocked);

        //assert
        assertEquals(1, emMocked.getEntities().size());

    }

    @Test
    public void multiply_Test(){
        //arrange
        foxMocked.sexualDesire = 100;
        foxMocked.aHunger = 100;
        foxMocked.aEnergy = 100;
        Mockito.doNothing().when(emMocked).lockMonitor();
        Mockito.doNothing().when(emMocked).unlockMonitor();
        Fox nearFox = new Fox(emMocked, emMocked.getRandomPosition(emMocked.getEntities()),2,100,100,1000,0,100,100);

        emMocked.addEntity(nearFox);
        emMocked.addEntity(foxMocked);

        //act
        foxRunnable.multiply(nearFox,foxMocked);

        //assert
        assertEquals(3, emMocked.getEntities().size());

    }

}
