package com.cvut.simulation.view.View;

import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.View.GridMap;

import javax.swing.*;
import java.awt.*;

public class BoardManager {

    private final EntityManager em;
    public BoardManager(EntityManager em){
        this.em = em;
    }

    /**
     *  Generates the swing main window and adds a gridMap there
     */
    public void generateWindow(){
        int gridWidth = 20*50;
        int gridHeight = 13*50;
        JFrame frame = new JFrame("Real Time Simulator");
        CardLayout ca = new CardLayout();
        JPanel mainPanel = new JPanel(ca);
        JPanel mainMenuCard = new MainMenu(mainPanel, ca);
        JPanel parametrsCard = new ParametrsView(mainPanel, ca,em);
        JPanel simulationCard = new SimulationView(em);

        mainPanel.add(mainMenuCard, "menu");
        mainPanel.add(parametrsCard, "params");
        mainPanel.add(simulationCard, "simulation");

        frame.add(mainPanel);
        frame.setResizable(false);
        frame.setSize(new Dimension(gridWidth+10, gridHeight+30));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
