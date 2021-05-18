package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.Simulation;
import com.cvut.simulation.view.View.GridMap;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Statistics implements Runnable{

    public List<Entity> entityList;
    public Simulation sim;
    public GridMap gridMap;
    public int TotalCount;
    public int FoxCount;
    public int WolfCount;
    public int HunterCount;
    public int RabbitCount;
    public int SheepCount;
    public int TimePassed;

    public Statistics(GridMap gridMap, List<Entity> entities){
        this.gridMap = gridMap;
        this.sim = new Simulation();
        this.entityList = entities;
        TotalCount = entities.size();
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
        updateCount(sim.getEntities());
        TimePassed += 1;
        System.out.println(TimePassed);
    }
}
