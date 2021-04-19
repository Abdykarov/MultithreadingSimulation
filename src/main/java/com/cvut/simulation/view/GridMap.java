package com.cvut.simulation.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class GridMap extends JPanel{

    public final int width;
    public final int height;
    public final List<Entity> entities;

    GridMap(int width, int height, List<Entity> entities)
    {
        if (width % Entity.SIZE != 0 || height % Entity.SIZE != 0)
        {
            throw new IllegalArgumentException("Width and Height of grid must be a multiple of Particle.SIZE");
        }

        this.width = width;
        this.height = height;
        this.entities = entities;
    }

    public void animateEntityStep(Tile initialPosition, Tile NextPosition){

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // drawing tiles
        int tileSize = 50;
        Image RED_LAND;
        for(int hor = 0; hor < width/tileSize; hor++){
            for (int ver = 0; ver < height/tileSize; ver++){
                RED_LAND = new ImageIcon("logo.png").getImage();
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

        //draw entities
        for (Entity entity : entities)
        {
            Image EntityImage = new ImageIcon(entity.image).getImage();
            g.drawImage(EntityImage,entity.currentPosition.x, entity.currentPosition.y, entity.width, entity.height,null);
        }
    }




}
