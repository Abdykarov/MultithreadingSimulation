package com.cvut.simulation.view.View;

import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.Simulation;
import com.cvut.simulation.view.Utils.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GridMap extends JPanel implements Runnable{

    public EntityManager em;
    public boolean redraw;
    public int width;
    public int height;

   public GridMap(EntityManager em, int width, int height)
    {
        this.em = em;
        this.redraw = true;

        setOpaque(true);
        setBackground(new Color(240, 235, 232));

        Thread thr = new Thread(this);
        thr.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Entity> entities = em.getEntities();

        // drawing tiles
        int tileSize = 50;
        Image RED_LAND;
        for(int hor = 0; hor < width/tileSize; hor++){
            for (int ver = 0; ver < height/tileSize; ver++){
                g.drawRect(hor*tileSize, ver*tileSize, tileSize,tileSize);
            }
        }
        Random rand = new Random();

        //draw entities
        for (Entity entity : entities) {
            if (entity == null) {

            } else {
                g.drawImage(entity.EntityImage, entity.currentPosition.x, entity.currentPosition.y, entity.width, entity.height, null);
            }

        }

        if(entities.isEmpty()){
            redraw = false;
            JOptionPane.showMessageDialog(null, "My Goodness, simulation is canceld");
        }

    }

    public void redraw() {

        try {
            TimeUnit.MILLISECONDS.sleep(em.simulationSpeed);
        }
        catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        repaint();
        //optimise miltithreading
        Toolkit.getDefaultToolkit().sync();
   }

    @Override
    public void run() {
        while (redraw) {
            redraw();
        }
    }



}
