/*
 * Diamond User Administration System
 * Copyright © 2015 Diamond Light Source Ltd
 */

package uk.ac.diamond.ss.domain;

import java.util.ArrayList;
import java.util.List;

public class ShiftReader{

    public List<Shift> load() {
        List<Shift> result = new ArrayList<Shift>();
        List<Shift> similar = new ArrayList<Shift>();
        List<Shift> longEx = new ArrayList<Shift>();
        Shift one = new Shift();
        one.setID(10);
        one.setStartTime(0);
        one.setEndTime(5);

        Shift two = new Shift();
        two.setID(20);
        two.setStartTime(6);
        two.setEndTime(10);
        /*one.addSimilar(two);
        two.addSimilar(one);*/

        Shift three = new Shift();
        three.setID(3);
        three.setStartTime(11);
        three.setEndTime(15);

        Shift four = new Shift();
        four.setID(4);
        four.setStartTime(16);
        four.setEndTime(18);

     /*   three.addLongExperiment(four);
        four.addLongExperiment(three);*/

        result.add(one);
        result.add(two);
        result.add(three);
        result.add(four);
        return result;
    }

}
