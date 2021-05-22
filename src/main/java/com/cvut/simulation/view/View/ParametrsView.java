package com.cvut.simulation.view.View;

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

    private JComboBox entityType;
    private JComboBox simulationSize;

    private JTextArea table;
    private JScrollPane scroller;

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
    private  JLabel simSizeLabel;

    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;

    ParametrsView(JPanel mainPanel, CardLayout ca){
        this.mainPanel = mainPanel;
        setOpaque(true);
        setBackground(new Color(87, 42, 15));
    }
}
