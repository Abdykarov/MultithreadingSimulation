package com.cvut.simulation.view.View;

import com.cvut.simulation.view.Utils.EntityManager;

import javax.swing.*;
import java.awt.*;

public class SimulationView extends JPanel {

    private EntityManager em;
    private JPanel gridMap;

    public SimulationView(EntityManager em, JPanel gridMap){
        this.gridMap = gridMap;
        this.em = em;
        int gridWidth = 20*50;
        int gridHeight = 13*50;

        JPanel controller = new JPanel();
        controller.setOpaque(true);
        controller.setBackground(new Color(80, 77, 77));
        JButton stop = new JButton("Start/stop");
        stop.setSize(150, 40);
        controller.add(stop);

        setOpaque(true);
        setBackground(new Color(246, 246, 246));
        setLayout(new BorderLayout());
        add(new StatisticsView(), BorderLayout.NORTH);
        add(gridMap, BorderLayout.CENTER);
        add(controller, BorderLayout.SOUTH);
    }
}
