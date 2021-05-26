package com.cvut.simulation.view.UnitTests;

import com.cvut.simulation.view.Model.Bullet;
import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.Model.Fox;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BulletCollisionTest {

    private EntityManager emMocked;
    private Tile tileMocked;
    private Fox fox;
    private Bullet bullet;

    @Test
    public void bulletCollision_withFox_test(){
        emMocked = mock(EntityManager.class);
        tileMocked = mock(Tile.class);
        tileMocked.x = 0;
        tileMocked.y = 50;
        bullet = new Bullet(emMocked,tileMocked, 2, 1, 1000);
        fox = new Fox(emMocked,new Tile(50,50), 1, 100,100,1000,0,100,100);
        Fox expectedResult = fox;

        Mockito.doNothing().when(emMocked).lockMonitor();
        Mockito.doNothing().when(emMocked).unlockMonitor();
        when(emMocked.getEntities()).thenReturn(Arrays.asList(fox));

        emMocked.addEntity(bullet);
        emMocked.addEntity(fox);

        //act
        bullet.currentPosition.x += 50;
        Entity result = bullet.detectFox();

        // assert
        assertEquals(expectedResult, result);
    }

}
