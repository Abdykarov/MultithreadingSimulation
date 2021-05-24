package com.cvut.simulation.view.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatisticsView extends JPanel {

    private JLabel totalCount;
    private JLabel foxCount;
    private JLabel wolfCount;
    private JLabel sheepCount;
    private JLabel isRunning;
    private JLabel hunterCount;
    private JLabel rabbitCount;

    /**
     * A panel around the simulation, which shows main statistics of all entities
     */
    public StatisticsView(){
        setOpaque(true);
        setLayout( new FlowLayout(FlowLayout.CENTER, 20, 10));
        setBackground(new Color(80, 77, 77));
        generateComponents();
    }

    private void generateComponents(){

        totalCount = new JLabel("Total Count: 0");
        totalCount.setSize(75, 80);
        totalCount.setForeground(Color.white);
        this.add(totalCount);

        foxCount = new JLabel("Fox Count: 0");
        foxCount.setSize(75, 80);
        foxCount.setForeground(Color.white);
        this.add(foxCount);

        wolfCount = new JLabel("Wolf Count: 0");
        wolfCount.setSize(75, 80);
        wolfCount.setForeground(Color.white);
        this.add(wolfCount);

        sheepCount = new JLabel("Sheep Count: 0");
        sheepCount.setSize(75, 80);
        sheepCount.setForeground(Color.white);
        this.add(sheepCount);

        hunterCount = new JLabel("Hunter Count: 0");
        hunterCount.setSize(75, 80);
        hunterCount.setForeground(Color.white);
        this.add(hunterCount);

        rabbitCount = new JLabel("Rabbit Count: 0");
        rabbitCount.setSize(75, 80);
        rabbitCount.setForeground(Color.white);
        this.add(rabbitCount);

        isRunning = new JLabel("Time passed: 0");
        isRunning.setSize(75, 80);
        isRunning.setForeground(Color.white);
        this.add(isRunning);

    }

    public JLabel getFoxCount() {
        return foxCount;
    }

    public JLabel getWolfCount() {
        return wolfCount;
    }

    public JLabel getSheepCount() {
        return sheepCount;
    }

    public JLabel getIsRunning() {
        return isRunning;
    }

    public JLabel getHunterCount() {
        return hunterCount;
    }

    public JLabel getRabbitCount() {
        return rabbitCount;
    }

    public JLabel getTotalCount(){
        return totalCount;
    }

}
