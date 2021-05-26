package com.cvut.simulation.view.View;

import com.cvut.simulation.view.Utils.EntityManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Main menu has 2 buttons: new simulation and load simulation, uses card layout
 * for replacing panels between each other
 */
public class MainMenu extends JPanel {

    private JButton btnNew;
    private JButton btnLoad;
    final JFileChooser fc = new JFileChooser();
    private String xmlFile;
    private JPanel mainPanel;
    private EntityManager em;
    private ParametrsView params;

    MainMenu(JPanel mainPanel, CardLayout ca, EntityManager em, ParametrsView params){
        this.mainPanel = mainPanel;
        this.em = em;
        this.params = params;
        setLayout(null);

        btnNew = new JButton("new simulation");
        btnNew.setLocation(450, 250);
        btnNew.setSize(150, 60);
        this.add(btnNew);

        btnLoad = new JButton("load simulation");
        btnLoad.setLocation(450, 350);
        btnLoad.setSize(150, 60);
        this.add(btnLoad);

        btnNew.addActionListener(new ActionListener()   {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "params");
            }
        });


        btnLoad.addActionListener(new ActionListener()   {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
                int returnValue = jfc.showOpenDialog(null);
                // int returnValue = jfc.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = jfc.getSelectedFile();
                    xmlFile = file.getAbsolutePath();
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
                            System.out.println("\nNode Name :" + node.getNodeName());
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
                        CardLayout cl = (CardLayout) (mainPanel.getLayout());
                        cl.show(mainPanel, "params");
                        params.listEntities();
                        params.setSimulationSpeed("1000");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        setOpaque(true);
        setBackground(new Color(40, 40, 40));
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
