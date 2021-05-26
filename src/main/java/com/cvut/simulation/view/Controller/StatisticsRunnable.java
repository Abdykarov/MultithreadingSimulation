package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.Model.Statistics;
import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.Utils.EntityManager;
import com.cvut.simulation.view.View.StatisticsView;

import java.util.*;


/**
 * Statistics runnable is thread class, which updates table of statistics every time
 */
public class StatisticsRunnable implements Runnable {
    public int TimePassed;
    public int TotalCount;
    public Statistics statistics;
    public EntityManager em;
    public final long fps = 100;
    private Random rand = new Random();
    private final StatisticsView statsView;

    public StatisticsRunnable(Statistics statistics, EntityManager em, StatisticsView statsView) {
        this.statistics = statistics;
        this.statsView = statsView;
        this.em = em;
    }

    /**
     * Update total count of entities
     * @param entities
     */
    public void updateCount(List<Entity> entities){
        TotalCount = entities.size();
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(em.simulationSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(em.isRunning){
                em.lock.lock();
                try {
                    updateCount(em.getEntities());
                    TimePassed += 1;
                    statistics.updateCounts();
                    setValues();
                }
                finally {
                    em.lock.unlock();
                }
            }
        }

    }


    /**
     * Setting new text to the labels of statistics view
     */
    private void setValues(){
        statsView.getTotalCount().setText("Total count: " + String.valueOf(statistics.getTotalCount()));
        statsView.getFoxCount().setText("Fox count: " + String.valueOf(statistics.getFoxCount()));
        statsView.getHunterCount().setText("Hunter count: " + String.valueOf(statistics.getHunterCount()));
        statsView.getSheepCount().setText("Sheep count: " + String.valueOf(statistics.getSheepCount()));
        statsView.getRabbitCount().setText("Rabbit count: " + String.valueOf(statistics.getRabbitCount()));
        statsView.getWolfCount().setText("Wolf count: " + String.valueOf(statistics.getWolfCount()));
        statsView.getIsRunning().setText("Time passed: " + String.valueOf(TimePassed));
    }

}
