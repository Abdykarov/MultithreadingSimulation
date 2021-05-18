package com.cvut.simulation.view.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Statistics {

    public Statistics(){

    }

    public int getTotalCount(){
        return 0;
    }

    public Integer[] getEntityCountArray(){
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        Set<Integer> keys = map.keySet();
        Integer[] array = keys.toArray(new Integer[keys.size()]);
        return array;
    }

}
