package com.cvut.simulation.view.View;

import com.cvut.simulation.view.Controller.StatisticsRunnable;
import com.cvut.simulation.view.Model.*;
import com.cvut.simulation.view.Utils.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParametrsView extends JPanel {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ParametrsView.class));

    private JPanel mainPanel;
    private GridMap gridMap;

    private JTextField ID;
    private JTextField energy;
    private JTextField health;
    private JTextField speed;
    private JTextField hunger;
    private JTextField lifeLenght;
    private JTextField sexualDesire;
    private JTextField simulationSpeed;

    private JComboBox<String> entityType;
    private JComboBox simulationSize;

    private JTextArea tableArea;
    private JScrollPane scrollBar;

    private  JLabel idLabel;
    private  JLabel energyLabel;
    private  JLabel healthLabel;
    private  JLabel speedLabel;
    private  JLabel hungerLabel;
    private  JLabel lifeLenghtLabel;
    private  JLabel sexualDesireLabel;
    private  JLabel simSpeedLabel;
    private  JLabel entityTypeLabel;
    private  JLabel tableLabel;

    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton startBtn;

    private final EntityManager em;

    /**
     * View for creating entities with different parameters, editing and removing.
     * @param mainPanel
     * @param ca
     * @param em
     */
    ParametrsView(JPanel mainPanel, CardLayout ca, EntityManager em, GridMap gridMap){
        this.mainPanel = mainPanel;
        this.gridMap = gridMap;
        this.em = em;
        setOpaque(true);
        setBackground(new Color(40, 40, 40));
        setLayout(null);

        generateComponents();
        generateListeners();
        listEntities();
    }

    /**
     * List all toStrings into table
     */
    public void listEntities(){
        System.out.println(em.getEntities().size());
        tableArea.setText(null);
        for (Entity entity: em.getEntities()){
            tableArea.append(entity.toString());
        }
    }

    /**
     * Generating components: buttons, labels, inputs etc.
     */
    private void generateComponents(){
        String[] entityTypes = new String[] { "Wolf", "Fox", "Rabbit", "Hunter", "Sheep" };
        entityType = new JComboBox<>(entityTypes);
        entityType.setBounds(25, 50, 100, 41);
        add(entityType);

        simulationSpeed = new JTextField();
        simulationSpeed.setLocation(875, 300);
        simulationSpeed.setSize(100, 41);
        add(simulationSpeed);

        health = new JTextField();
        health.setLocation(150, 50);
        health.setSize(75, 41);
        add(health);

        energy = new JTextField();
        energy.setLocation(250, 50);
        energy.setSize(75, 41);
        add(energy);

        speed = new JTextField();
        speed.setLocation(350, 50);
        speed.setSize(75, 41);
        add(speed);


        hunger = new JTextField();
        hunger.setLocation(450, 50);
        hunger.setSize(75, 41);
        add(hunger);

        sexualDesire = new JTextField();
        sexualDesire.setLocation(550, 50);
        sexualDesire.setSize(75, 41);
        add(sexualDesire);

        lifeLenght = new JTextField();
        lifeLenght.setLocation(650, 50);
        lifeLenght.setSize(75, 41);
        add(lifeLenght);

        ID = new JTextField();
        ID.setLocation(775, 50);
        ID.setSize(75, 41);
        add(ID);

        // labels

        tableLabel = new JLabel("Entities");
        tableLabel.setLocation(25, 125);
        tableLabel.setSize(100,40);
        tableLabel.setForeground(Color.white);
        add(tableLabel);

        simSpeedLabel = new JLabel("Sim speed");
        simSpeedLabel.setLocation(875, 250);
        simSpeedLabel.setSize(100,40);
        simSpeedLabel.setForeground(Color.white);
        add(simSpeedLabel);

        entityTypeLabel = new JLabel("Entity type");
        entityTypeLabel.setLocation(25, 15);
        entityTypeLabel.setSize(100,41);
        entityTypeLabel.setForeground(Color.white);
        add(entityTypeLabel);

        healthLabel = new JLabel("Health");
        healthLabel.setLocation(150, 15);
        healthLabel.setSize(100,41);
        healthLabel.setForeground(Color.white);
        add(healthLabel);

        energyLabel = new JLabel("Energy");
        energyLabel.setLocation(250, 15);
        energyLabel.setSize(100,41);
        energyLabel.setForeground(Color.white);
        add(energyLabel);

        speedLabel = new JLabel("Speed");
        speedLabel.setLocation(350, 15);
        speedLabel.setSize(75, 41);
        speedLabel.setForeground(Color.white);
        add(speedLabel);


        hungerLabel = new JLabel("Hunger");
        hungerLabel.setLocation(450, 15);
        hungerLabel.setSize(75, 41);
        hungerLabel.setForeground(Color.white);
        add(hungerLabel);

        sexualDesireLabel = new JLabel("Sex desire");
        sexualDesireLabel.setLocation(550, 15);
        sexualDesireLabel.setForeground(Color.white);
        sexualDesireLabel.setSize(75, 41);
        add(sexualDesireLabel);

        lifeLenghtLabel = new JLabel("Life length");
        lifeLenghtLabel.setForeground(Color.white);
        lifeLenghtLabel.setLocation(650, 15);
        lifeLenghtLabel.setSize(100, 41);
        add(lifeLenghtLabel);

        idLabel = new JLabel("Id");
        idLabel.setForeground(Color.white);
        idLabel.setLocation(775, 15);
        idLabel.setSize(75, 41);
        add(idLabel);

        addBtn = new JButton("Add");
        addBtn.setLocation(875, 50);
        addBtn.setSize(100, 40);
        add(addBtn);

        editBtn = new JButton("Edit");
        editBtn.setLocation(875, 100);
        editBtn.setSize(100, 40);
        add(editBtn);

        deleteBtn = new JButton("Remove");
        deleteBtn.setLocation(875, 150);
        deleteBtn.setSize(100, 40);
        add(deleteBtn);

        tableArea = new JTextArea();
        scrollBar = new JScrollPane(tableArea);
        scrollBar.setAutoscrolls(true);
        tableArea.setAutoscrolls(true);
        tableArea.setEditable(true);

        scrollBar.setLocation(25,175);
        scrollBar.setSize(825, 450);
        scrollBar.setViewportView(tableArea);
        add(scrollBar);

        startBtn = new JButton("Start");
        startBtn.setLocation(875, 350);
        startBtn.setSize(100, 40);
        add(startBtn);


    }

    /**
     * Creating new entity
     * @param type
     * @param healthValue
     * @param energyValue
     * @param ageLengthValue
     * @param speedValue
     * @param hungerValue
     * @param desireValue
     * @param id
     */
    public void createEntity(String type, int healthValue, int energyValue, int ageLengthValue, int speedValue, int hungerValue, int desireValue, int id){
        try {
            switch (type){
                case "Wolf":
                    em.addEntity(new Wolf(em,em.getRandomPosition(em.getEntities()),id,energyValue,healthValue,speedValue,hungerValue,ageLengthValue,desireValue ));
                    break;
                case "Fox":
                    em.addEntity(new Fox(em,em.getRandomPosition(em.getEntities()),id,energyValue,healthValue,speedValue,hungerValue,ageLengthValue,desireValue ));
                    break;
                case "Hunter":
                    em.addEntity(new Hunter(em,em.getRandomPosition(em.getEntities()),id,energyValue,healthValue,speedValue,hungerValue,ageLengthValue,desireValue ));
                    break;
                case "Rabbit":
                    em.addEntity(new Rabbit(em,em.getRandomPosition(em.getEntities()),id,energyValue,healthValue,speedValue,hungerValue,ageLengthValue,desireValue ));
                    break;
                case "Sheep":
                    em.addEntity(new Sheep(em,em.getRandomPosition(em.getEntities()),id,energyValue,healthValue,speedValue,hungerValue,ageLengthValue,desireValue ));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.log(Level.INFO, "Entity created!");
    }

    /**
     * Remove entity from array
     * @param idValue
     */
    public void removeEntity(int idValue){
        if( em.removeEntity(idValue)){
            LOGGER.log(Level.INFO, "Entity removed!");
        }else{
            LOGGER.log(Level.WARNING, "Entity remove exception!");
        }
    }

    /**
     * Editing entity, find by id and set new values
     * @param idValue
     * @param healthValue
     * @param energyValue
     * @param ageLengthValue
     * @param speedValue
     * @param desireValue
     */
    public void editEntity(int idValue, int healthValue,int energyValue, int ageLengthValue, int speedValue, int hungerValue, int desireValue){
        Entity entityToEdit = em.findEntity(idValue);
        if(entityToEdit != null){
            entityToEdit.aHealth = healthValue;
            entityToEdit.aEnergy = energyValue;
            entityToEdit.aLifeLenght = ageLengthValue;
            entityToEdit.aSpeed = speedValue;
            entityToEdit.aHunger = hungerValue;
            entityToEdit.sexualDesire = desireValue;
            LOGGER.log(Level.INFO,"Edited successfully");
        }
    }

    private void generateListeners(){


        // start simulation
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(simulationSpeed.getText() != null){
                    int speed = Integer.parseInt(simulationSpeed.getText());
                    if(speed > 0 && speed < 10000){
                        CardLayout cl = (CardLayout) (mainPanel.getLayout());
                        cl.show(mainPanel, "simulation");
                        em.simulationSpeed = Integer.parseInt(simulationSpeed.getText());
                        em.startThreads();
                        em.startStats();
                        gridMap.startRedraw();
                    }else{
                        LOGGER.log(Level.WARNING,"Incorrect simulation speed!");
                    }
                }
            }
        });

        // edit entity

        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int idValue = Integer.parseInt(ID.getText());
                int healthValue = Integer.parseInt(health.getText());
                int energyValue = Integer.parseInt(energy.getText());
                int ageLengthValue = Integer.parseInt(lifeLenght.getText());
                int speedValue = Integer.parseInt(speed.getText());
                int hungerValue = Integer.parseInt(hunger.getText());
                int desireValue = Integer.parseInt(sexualDesire.getText());
                editEntity(idValue,healthValue,energyValue,ageLengthValue,speedValue,hungerValue,desireValue);
                listEntities();
            }
        });

        // remove entity from array
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               int idValue = Integer.parseInt(ID.getText());

               removeEntity(idValue);
               listEntities();
            }
        });


        // create entity
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String type = (String) entityType.getSelectedItem();
                int healthValue = Integer.parseInt(health.getText());
                int energyValue = Integer.parseInt(energy.getText());
                int ageLengthValue = Integer.parseInt(lifeLenght.getText());
                int speedValue = Integer.parseInt(speed.getText());
                int hungerValue = Integer.parseInt(hunger.getText());
                int desireValue = Integer.parseInt(sexualDesire.getText());
                int id = em.getNextID();

                createEntity(type,healthValue,energyValue,ageLengthValue,speedValue,hungerValue,desireValue,id);
                listEntities();

            }
        });
    }

}
