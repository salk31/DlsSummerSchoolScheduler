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
        result.add(new Shift(1));
        result.add(new Shift(2));
        return result;
    }

}
