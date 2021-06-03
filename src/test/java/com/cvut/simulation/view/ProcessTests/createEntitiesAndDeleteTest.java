package com.cvut.simulation.view.ProcessTests;

import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.Model.Fox;
import com.cvut.simulation.view.Model.Wolf;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.Utils.Tile;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class createEntitiesAndDeleteTest {

    @Test
    public void create_fox_and_delete_startSimulation_Test(){

        EntityManager em = new EntityManager(20 * 50, 12 * 50);
        Fox fox = new Fox(em, new Tile(50,50), em.getNextID(),100,100,1000,0,100,100);

        //act
        em.addEntity(fox);
        //assert
        assertEquals(1,em.getEntities().size());

        //act
        em.removeEntity(1);
        //assert
        assertEquals(null,em.findEntity(1));

        //act
        em.startThreads();
        //assert
        assertEquals(false,em.isRunning);


    }

}
