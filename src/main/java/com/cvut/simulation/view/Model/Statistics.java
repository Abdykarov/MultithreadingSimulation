package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.Simulation;
import com.cvut.simulation.view.Utils.Tile;

import javax.swing.*;
import java.util.List;

public class Statistics {

    public int foxCount;
    public int HunterCount;
    public int RabbitCount;
    public int SheepCount;
    public int WolfCount;
    public int BulletCount;

    public Simulation sim;
    public List<Entity> entityList;



    public Statistics(List<Entity> EntityList){
        this.entityList = EntityList;
        this.sim = new Simulation();
        this.foxCount = getCountByType("Fox");
        this.HunterCount = getCountByType("Hunter");
        this.RabbitCount = getCountByType("Rabbit");
        this.SheepCount = getCountByType("Sheep");
        this.WolfCount = getCountByType("Wolf");
        this.BulletCount = getCountByType("Bullet");
    }

    public void updateCounts(){
        this.foxCount = getCountByType("Fox");
        this.HunterCount = getCountByType("Hunter");
        this.RabbitCount = getCountByType("Rabbit");
        this.SheepCount = getCountByType("Sheep");
        this.WolfCount = getCountByType("Wolf");
        this.BulletCount = getCountByType("Bullet");
    }

    public int getCountByType(String aType){
        int count = 0;
        for(Entity entity :entityList){
            if(entity.aType == aType) {
                count += 1;
            }
        }
        return count;
    }

    public void updateEntities(){
        entityList = sim.getEntities();
    }

}
