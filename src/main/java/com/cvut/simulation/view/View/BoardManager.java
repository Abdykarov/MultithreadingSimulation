package com.cvut.simulation.view.View;

import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.View.GridMap;

import javax.swing.*;
import java.awt.*;

public class BoardManager {

    private final EntityManager em;
    private final GridMap gm;
    private final StatisticsView statsView;
    private final ControllerView controller;
    private final JPanel mainPanel;
    private final JPanel mainMenuCard;
    private final ParametrsView parametrsCard;
    private final JPanel simulationCard;
    private final CardLayout ca;
    private final int gridWidth;
    private final int gridHeight;

    public BoardManager(EntityManager em){
        this.em = em;
        this.gridWidth = 20*50;
        this.gridHeight = 12*50;
        this.gm = new GridMap(em,gridWidth,gridHeight);
        this.statsView = new StatisticsView();
        this.controller = new ControllerView(em);

        this.ca = new CardLayout();
        this.mainPanel = new JPanel(ca);
        this.parametrsCard = new ParametrsView(mainPanel, ca,em,gm,statsView);
        this.mainMenuCard = new MainMenu(mainPanel, ca,em, parametrsCard);
        this.simulationCard = new SimulationView(em, gm, statsView, controller);
    }

    /**
     *  Generates the swing main window and adds a gridMap there
     */
    public void generateWindow(){
        JFrame frame = new JFrame("Real Time Simulator");

        mainPanel.add(mainMenuCard, "menu");
        mainPanel.add(parametrsCard, "params");
        mainPanel.add(simulationCard, "simulation");

        frame.add(mainPanel);
        frame.setResizable(false);
        frame.setSize(new Dimension(gridWidth+10, gridHeight+110));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
