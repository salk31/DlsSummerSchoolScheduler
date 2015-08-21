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
        Shift one = new Shift();
        one.setID(10);
        Shift two = new Shift();
        two.setID(20);
        Shift three = new Shift();
        two.setID(20);
        three.setID(3);
        result.add(one);
        result.add(two);
        result.add(three);
        return result;
    }

}
