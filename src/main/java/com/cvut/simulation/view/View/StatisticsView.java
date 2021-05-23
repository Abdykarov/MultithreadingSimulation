package com.cvut.simulation.view.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatisticsView extends JPanel {

    /**
     * A panel around the simulation, which shows main statistics of all entities
     */
    public StatisticsView(){
        setOpaque(true);
        setBackground(new Color(80, 77, 77));

       JLabel totalCount = new JLabel("Total Count: 0");
        totalCount.setSize(75, 80);
        totalCount.setForeground(Color.white);
        add(totalCount);
    }

}
