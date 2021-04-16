package com.cvut.simulation.view;

import javax.swing.*;
import java.util.List;

public class GridMap extends JPanel {

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

}
