package com.cvut.simulation.view.View;

import com.cvut.simulation.view.Model.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GridMap extends JPanel implements Runnable{

    public final int width;
    public final int height;
    public final List<Entity> entities;
    public boolean redraw;

   public GridMap(int width, int height, List<Entity> entities)
    {
        if (width % Entity.SIZE != 0 || height % Entity.SIZE != 0)
        {
            throw new IllegalArgumentException("Width and Height of grid must be a multiple of Entity.SIZE");
        }

        this.width = width;
        this.redraw = true;
        this.height = height;
        this.entities = entities;
        Thread thr = new Thread(this);
        thr.start();
    }

    public void animateEntityStep(Tile initialPosition, Tile NextPosition){
        repaint(initialPosition.x, initialPosition.y, Entity.SIZE, Entity.SIZE);
        repaint(NextPosition.x, NextPosition.y, Entity.SIZE, Entity.SIZE);
        Toolkit.getDefaultToolkit().sync();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // drawing tiles
        int tileSize = 50;
        Image RED_LAND;
        for(int hor = 0; hor < width/tileSize; hor++){
            for (int ver = 0; ver < height/tileSize; ver++){
                RED_LAND = new ImageIcon("grass.png").getImage();
                g.drawImage(RED_LAND, hor*tileSize, ver*tileSize, tileSize,tileSize,null);
            }
        }
        Random rand = new Random();

        // draw trees
        int treeCount = 10;
        Image TREE;
        for(int t = 0; t < treeCount; t++){
            TREE = new ImageIcon("tree2.png").getImage();
            g.drawImage(TREE,rand.nextInt(width/Entity.SIZE)*Entity.SIZE,rand.nextInt(height/Entity.SIZE)*Entity.SIZE, tileSize,tileSize, null);
        }
        Toolkit.getDefaultToolkit().sync();
        //draw entities

        ListIterator<Entity> iter = entities.listIterator();
        while(iter.hasNext()){
            Entity entity = iter.next();
            if(!entity.isAlive){
                iter.remove();
            }else{
                g.drawImage(entity.EntityImage,entity.currentPosition.x, entity.currentPosition.y, entity.width, entity.height, null);
            }
        }

        if(entities.isEmpty()){
            redraw = false;
            JOptionPane.showMessageDialog(null, "My Goodness, simulation is canceld");
            System.out.println("all dead");
        }

//        for (Entity entity : entities)
//        {
//            if(entity.isAlive){
//                g.drawImage(entity.EntityImage,entity.currentPosition.x, entity.currentPosition.y, entity.width, entity.height, null);
//            }
//            else{
//                entities.remove(entity);
//            }
//        }
    }

    public void redraw() {

        try {
            TimeUnit.SECONDS.sleep(1);
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
