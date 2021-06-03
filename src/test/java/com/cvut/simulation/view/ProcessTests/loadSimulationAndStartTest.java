package com.cvut.simulation.view.ProcessTests;

import com.cvut.simulation.view.Utils.EntityManager;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class loadSimulationAndStartTest {

    @Test
    public void loadSimAndRemoveEntity(){
        EntityManager em = new EntityManager(20 * 50, 12 * 50);
        String xmlFile = "/home/abdykili/Desktop/ts-simulace/simulationFox.xml";
        //act
        try
        {
            //creating a constructor of file class and parsing an XML file
            //an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("entity");
            NodeList simSpeeds = doc.getElementsByTagName("simSpeed");
            System.out.println(simSpeeds);
            // nodeList is not iterable, so we are using for loop
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    String type = eElement.getElementsByTagName("type").item(0).getTextContent();
                    String energy = eElement.getElementsByTagName("energy").item(0).getTextContent();
                    String health = eElement.getElementsByTagName("health").item(0).getTextContent();
                    String ageLength = eElement.getElementsByTagName("ageLength").item(0).getTextContent();
                    String speed = eElement.getElementsByTagName("speed").item(0).getTextContent();
                    String hunger = eElement.getElementsByTagName("hunger").item(0).getTextContent();
                    String sexDesire = eElement.getElementsByTagName("sexDesire").item(0).getTextContent();
                    int id = em.getNextID();

                    em.createEntity(type,parseInt(health),parseInt(energy),parseInt(ageLength), parseInt(speed),parseInt(hunger), parseInt(sexDesire), id);

                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //assert
        assertEquals(2, em.getEntities().size());
        //act
        em.removeEntity(1);

        //assert
        assertEquals(1,em.getEntities().size());
    }

    public int parseInt(String str){
        int returnInt = 0;
        try {
            returnInt = Integer.parseInt(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnInt;
    }
}
