package com.cvut.simulation.view.Model;

import com.cvut.simulation.view.Simulation;
import com.cvut.simulation.view.Utils.EntityManager;
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

    public EntityManager em;

    public Statistics(EntityManager em){
        this.em = em;
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

    public int getTotalCount(){
        int count = 0;
        count += foxCount;
        count += HunterCount;
        count += RabbitCount;
        count += SheepCount;
        count += WolfCount;
        return count;
    }

    public int getCountByType(String aType){
        int count = 0;
        for(Entity entity :em.getEntities()){
            if(entity.aType == aType) {
                count += 1;
            }
        }
        return count;
    }

    public int getFoxCount() {
        return foxCount;
    }

    public int getHunterCount() {
        return HunterCount;
    }

    public int getRabbitCount() {
        return RabbitCount;
    }

    public int getSheepCount() {
        return SheepCount;
    }

    public int getWolfCount() {
        return WolfCount;
    }
}
