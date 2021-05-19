package com.cvut.simulation.view.View;

import com.cvut.simulation.view.View.GridMap;

import javax.swing.*;
import java.awt.*;

public class BoardManager {

    private final GridMap gridMap;

    public BoardManager(GridMap gridMap){
        this.gridMap = gridMap;
    }

    /**
     *  Generates the swing main window and adds a gridMap there
     */
    public void generateWindow(){
        JFrame frame = new JFrame("Real Time Simulator");
        gridMap.setOpaque(true);
        gridMap.setBackground(new Color(240, 235, 232));
        Jpanel1 = new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.add(jTextArea1);
        jPanel1.add(panel2);
        frame.add(gridMap);
        frame.setResizable(false);
        frame.setSize(new Dimension(gridMap.width+10, gridMap.height+30));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
