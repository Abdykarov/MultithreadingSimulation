package com.cvut.simulation.view.Controller;

import com.cvut.simulation.view.Model.Statistics;
import com.cvut.simulation.view.Simulation;
import com.cvut.simulation.view.Model.Entity;
import com.cvut.simulation.view.Utils.EntityManager;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.CountDownLatch;


public class StatisticsRunnable implements Runnable {
    public int TimePassed;
    public int TotalCount;
    public Statistics statistics;
    public EntityManager em;
    public final long fps = 100;
    Random rand = new Random();

    public StatisticsRunnable(Statistics statistics, EntityManager em) {
        this.statistics = statistics;
        this.em = em;
    }

    public int getTotalCount(){
        return TotalCount;
    }

    public Integer[] getEntityCountArray(){
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        Set<Integer> keys = map.keySet();
        Integer[] array = keys.toArray(new Integer[keys.size()]);
        return array;
    }

    public void updateCount(List<Entity> entities){
        TotalCount = entities.size();
    }

    @Override
    public void run() {
        while(em.isRunning){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            em.lock.lock();
            try {
                updateCount(em.getEntities());
                TimePassed += 1;
                statistics.updateCounts();

                System.out.println(statistics.BulletCount);
            }
            finally {
                em.lock.unlock();
            }
        }

    }

}
