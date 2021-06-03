package com.cvut.simulation.view.ProcessTests;

import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.Model.Wolf;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class createEntitiesAndEditTest {

    @Test
    public void create_wolf_and_edit_startSimulation_Test(){
        EntityManager em = new EntityManager(20 * 50, 12 * 50);

        Wolf wolf = new Wolf(em, new Tile(50,50), em.getNextID(),100,100,1000,0,100,100);
        Wolf wolf2 = new Wolf(em, new Tile(50,50), em.getNextID(),80,100,1000,0,100,100);

        //act
        em.addEntity(wolf);
        em.addEntity(wolf2);
        //assert
        assertEquals(2,em.getEntities().size());
        assertEquals(80,em.findEntity(2).aEnergy);

        //act
        em.editEntity(2,80,75,90,500,20,96);
        //assert
        assertEquals(75,em.findEntity(2).aEnergy);

        //act
        em.startThreads();
        //assert
        assertEquals(true,em.isRunning);

        //act
        for (Entity entity: em.getEntities()){
            entity.isAlive = false;
        }
        em.isRunning = false;
        //assert
        assertEquals(false, em.findEntity(2).isAlive);

    }

}
