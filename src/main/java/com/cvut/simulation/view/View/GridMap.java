package com.cvut.simulation.view.View;

import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.Model.Tile;
import com.cvut.simulation.view.Simulation;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GridMap extends JPanel implements Runnable{

    public final int width;
    public final int height;
    public Simulation sim;
    public boolean redraw;
    public final long fps = 400;

   public GridMap(int width, int height)
    {
        if (width % Entity.SIZE != 0 || height % Entity.SIZE != 0)
        {
            throw new IllegalArgumentException("Width and Height of grid must be a multiple of Entity.SIZE");
        }

        this.width = width;
        this.redraw = true;
        this.height = height;
        this.sim = new Simulation();
        Thread thr = new Thread(this);
        thr.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        List<Entity> entities = sim.getEntities();

        // drawing tiles
        int tileSize = 50;
        Image RED_LAND;
        for(int hor = 0; hor < width/tileSize; hor++){
            for (int ver = 0; ver < height/tileSize; ver++){
                g.drawRect(hor*tileSize, ver*tileSize, tileSize,tileSize);
            }
        }
        Random rand = new Random();


        //optimise miltithreading
        Toolkit.getDefaultToolkit().sync();


        //draw entities
        ListIterator<Entity> iter = entities.listIterator();
        while(iter.hasNext()){
            Entity entity = iter.next();

            g.drawImage(entity.EntityImage,entity.currentPosition.x, entity.currentPosition.y, entity.width, entity.height, null);

        }


//        for(Entity ent : entities){
//            if(ent != null){
//                System.out.println(ent.aType);
//            }
//        }

        if(entities.isEmpty()){
            redraw = false;
            JOptionPane.showMessageDialog(null, "My Goodness, simulation is canceld");
            System.out.println("all dead");
        }

    }

    public void redraw() {

        try {
            TimeUnit.MILLISECONDS.sleep(fps);
        }
        catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        repaint();
    }

    @Override
    public void run() {
        while (redraw == true) {
            redraw();
        }
    }



}
