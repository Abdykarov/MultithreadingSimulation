package com.cvut.simulation.view.UnitTests;

import com.cvut.simulation.view.Controller.FoxRunnable;
import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.Model.Fox;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityManagerTest {

    @Test
    public void create_entity_test() {

        EntityManager em = new EntityManager(20 * 50, 12 * 50);
        String type = "Fox";
        int health = 100;
        int energy = 100;
        int ageLength = 100;
        int speed = 1000;
        int hunger = 0;
        int desire = 100;
        int id = 1;

        //act
        em.createEntity(type,health,energy,ageLength,speed,hunger,desire,id);

        //assert
        assertEquals(1,em.findEntity(1).id);
    }


    @Test
    public void remove_entity_test() {

        EntityManager em = new EntityManager(20 * 50, 12 * 50);
        String type = "Fox";
        int health = 100;
        int energy = 100;
        int ageLength = 100;
        int speed = 1000;
        int hunger = 0;
        int desire = 100;
        int id = 1;

        //act
        em.createEntity(type,health,energy,ageLength,speed,hunger,desire,id);
        boolean result = em.removeEntity(id);

        //assert
        assertEquals(true,result);
    }


    @Test
    public void change_simulation_speed_test() {

        EntityManager em = new EntityManager(20 * 50, 12 * 50);
        String type = "Fox";
        int health = 100;
        int energy = 100;
        int ageLength = 100;
        int speed = 1000;
        int hunger = 0;
        int desire = 100;
        int id = 1;
        String simSpeed = "fast";

        //act
        em.createEntity(type,health,energy,ageLength,speed,hunger,desire,id);
        em.changeSpeeds(simSpeed);

        //assert
        assertEquals(500,em.findEntity(id).getaSpeed());
    }

}