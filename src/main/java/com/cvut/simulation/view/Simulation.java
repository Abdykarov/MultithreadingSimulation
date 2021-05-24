package com.cvut.simulation.view;

import com.cvut.simulation.view.Controller.*;
import com.cvut.simulation.view.Model.*;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.View.BoardManager;
import com.cvut.simulation.view.View.GridMap;
import com.cvut.simulation.view.Utils.Tile;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Simulation {

    public static void main(String[] args){
        int gridWidth = 20*50;
        int gridHeight = 12*50;

        EntityManager em = new EntityManager(gridWidth, gridHeight);
        BoardManager boardManager = new BoardManager(em);

        // start gui thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                boardManager.generateWindow();
            }
        });


    }
}
