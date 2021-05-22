package com.cvut.simulation.view.View;

import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.Utils.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParametrsView extends JPanel {
    private JPanel mainPanel;

    private JTextField id;
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

    ParametrsView(JPanel mainPanel, CardLayout ca, EntityManager em){
        this.mainPanel = mainPanel;
        this.em = em;
        setOpaque(true);
        setBackground(new Color(40, 40, 40));
        setLayout(null);

        generateComponents();
        generateListeners();
        listEntities();
    }

    private void listEntities(){
        tableArea.setText(null);
        for (Entity entity: em.getEntities()){
            tableArea.append(entity.toString());
        }
    }

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

        id = new JTextField();
        id.setLocation(775, 50);
        id.setSize(75, 41);
        add(id);

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
        this.add(scrollBar);

        startBtn = new JButton("Start");
        startBtn.setLocation(875, 350);
        startBtn.setSize(100, 40);
        add(startBtn);


    }

    private void generateListeners(){

    }

}
