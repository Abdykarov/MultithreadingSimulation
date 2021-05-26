package com.cvut.simulation.view.View;

import com.cvut.simulation.view.Utils.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * View panel, holds statistics view , main map of simulation and controller buttons view
 */
public class SimulationView extends JPanel {

    private EntityManager em;
    private GridMap gridMap;
    private ControllerView controller;
    private StatisticsView statsView;

    public SimulationView(EntityManager em, GridMap gridMap, StatisticsView statsView, ControllerView controller){
        this.gridMap = gridMap;
        this.statsView = statsView;
        this.controller = controller;
        this.em = em;
        int gridWidth = 20*50;
        int gridHeight = 13*50;


        setOpaque(true);
        setBackground(new Color(246, 246, 246));
        setLayout(new BorderLayout());
        add(statsView, BorderLayout.NORTH);
        add(gridMap, BorderLayout.CENTER);
        add(controller, BorderLayout.SOUTH);
    }

}
