package com.cvut.simulation.view.View;

import com.cvut.simulation.view.Utils.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerView extends JPanel {

    private JButton stop;
    private JButton normal;
    private JButton fast;
    private JButton slow;

    private EntityManager em;
    private GridMap gm;

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ParametrsView.class));

    /**
     * A panel around the simulation, which contains controllers that manipulate with simulation
     */
    public ControllerView(EntityManager em){
        this.gm = gm;
        this.em = em;

        setOpaque(true);
        setLayout( new FlowLayout(FlowLayout.CENTER, 20, 10));
        setBackground(new Color(80, 77, 77));
        generateComponents();
        generateListeners();
    }

    private void generateListeners(){
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                em.lock.lock();
                try {

                    if(em.isRunning){
                        em.isRunning = false;
                    }else{
                        em.isRunning = true;
                    }
                }
                finally {
                    LOGGER.log(Level.INFO,"Simulation was stopped/continued");
                    em.lock.unlock();
                }
            }
        });

        slow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                em.lock.lock();
                try{
                    em.simulationSpeed = em.simulationSpeedOriginal * 2;
                    em.changeSpeeds("slow");
                }
                finally {
                    LOGGER.log(Level.INFO,"Speed was changed to slow");
                    em.lock.unlock();
                }
            }
        });


        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                em.lock.lock();
                try{
                    em.simulationSpeed = em.simulationSpeedOriginal;
                    em.changeSpeeds("normal");
                }
                finally {
                    LOGGER.log(Level.INFO,"Speed was changed to normal");
                    em.lock.unlock();
                }
            }
        });

        fast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                em.lock.lock();
                try{
                    em.simulationSpeed = em.simulationSpeedOriginal / 2;
                    em.changeSpeeds("fast");
                }
                finally {
                    LOGGER.log(Level.INFO,"Speed was changed to fast");
                    em.lock.unlock();
                }
            }
        });

    }


    private void generateComponents(){
        stop = new JButton("Start/stop");
        stop.setSize(150, 40);
        this.add(stop);

        slow = new JButton("0.5x speed ( Slow )");
        slow.setSize(150, 40);
        this.add(slow);

        normal = new JButton("1x speed ( Normal )");
        normal.setSize(150, 40);
        this.add(normal);

        fast = new JButton("2x speed ( Fast)");
        fast.setSize(150, 40);
        this.add(fast);

    }
}
