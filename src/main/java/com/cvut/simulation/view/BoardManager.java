package com.cvut.simulation.view;

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

        frame.add(gridMap);
    }
}
